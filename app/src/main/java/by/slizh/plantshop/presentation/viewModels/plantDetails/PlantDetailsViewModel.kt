package by.slizh.plantshop.presentation.viewModels.plantDetails

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
class PlantDetailsViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    private val _detailsState = MutableStateFlow(PlantDetailsState())
    val detailsState: StateFlow<PlantDetailsState> = _detailsState.asStateFlow()

    fun onEvent(event: PlantDetailsEvent) {
        when (event) {
            is PlantDetailsEvent.LoadPlantById -> getPlantById(event.id)
        }
    }

    private fun getPlantById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            plantRepository.getPlantById(id).collect { result ->
                handleResult(result)
            }
        }
    }

    private fun handleResult(result: Resource<Plant>) {
        when (result) {
            is Resource.Loading -> _detailsState.value =
                _detailsState.value.copy(isLoading = result.isLoading)

            is Resource.Success -> _detailsState.value =
                _detailsState.value.copy(plant = result.data, isLoading = false)

            is Resource.Error -> _detailsState.value =
                _detailsState.value.copy(error = result.message, isLoading = false)
        }
    }
}