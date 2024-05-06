package com.q8intouch.ecovve.ui.home

import com.q8intouch.ecovve.network.model.EcovveAllHabits


data class ExtraModel(
        var id: Int,
        var choices: List<EcovveAllHabits.Data.Choice>,
        var is_required: Int
)
