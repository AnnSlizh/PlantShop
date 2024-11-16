package by.slizh.plantshop.presentation.viewModels.plantsCatalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.plantshop.domain.model.Plant
import by.slizh.plantshop.domain.repository.PlantRepository
import by.slizh.plantshop.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val _plantState = MutableStateFlow(PlantState())
    val plantState: StateFlow<PlantState> = _plantState.asStateFlow()

    init {
        onEvent(PlantEvent.LoadPlants)
    }

    fun onEvent(event: PlantEvent) {
        when (event) {
            is PlantEvent.LoadPlants -> getPlants()
            is PlantEvent.FilterPlants -> filterPlants(event.category)
            is PlantEvent.SelectChip -> _plantState.value = _plantState.value.copy(selectedChip = event.category)
        }
    }

    private fun getPlants() {
        viewModelScope.launch(Dispatchers.IO) {
            plantRepository.getPlants().collect { result ->
                handleResult(result)
            }
        }
    }

    private fun filterPlants(category: String) {
        val filteredPlants = when (category) {
            "All" -> _plantState.value.plants
            "Popular" -> _plantState.value.plants.filter {
                (it.rating.toDoubleOrNull() ?: 0.0) >= 7.0
            }

            else -> _plantState.value.plants.filter { it.category == category }
        }
        _plantState.value = _plantState.value.copy(filteredPlants = filteredPlants)
    }

    private fun handleResult(result: Resource<List<Plant>>) {
        when (result) {
            is Resource.Loading -> _plantState.value =
                _plantState.value.copy(isLoading = result.isLoading)

            is Resource.Success -> _plantState.value =
                _plantState.value.copy(
                    plants = result.data ?: emptyList(),
                    filteredPlants = result.data ?: emptyList(),
                    isLoading = false
                )

            is Resource.Error -> _plantState.value =
                _plantState.value.copy(error = result.message, isLoading = false)
        }
    }
}