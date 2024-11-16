package by.slizh.plantshop.presentation.viewModels.plantDetails

import by.slizh.plantshop.domain.model.Plant

data class PlantDetailsState(
    val plant: Plant? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)