package by.slizh.plantshop.presentation.viewModels.plantsCatalog

sealed class PlantEvent {
    object LoadPlants : PlantEvent()
    data class FilterPlants(val category: String) : PlantEvent()
    data class SelectChip(val category: String) : PlantEvent()
}