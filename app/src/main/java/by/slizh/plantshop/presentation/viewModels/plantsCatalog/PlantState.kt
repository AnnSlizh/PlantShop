package by.slizh.plantshop.presentation.viewModels.plantsCatalog

import by.slizh.plantshop.domain.model.Plant

data class PlantState(
    val plants: List<Plant> = emptyList(),
    val filteredPlants: List<Plant> = emptyList(),
    val selectedChip: String = "All",
    val isLoading: Boolean = false,
    val error: String? = null
)
