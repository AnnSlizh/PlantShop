package by.slizh.plantshop.presentation.viewModels.plantDetails

sealed class PlantDetailsEvent {
    data class LoadPlantById(val id: Int) : PlantDetailsEvent()
}