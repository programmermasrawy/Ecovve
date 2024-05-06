package com.q8intouch.ecovve.ui.home

import com.q8intouch.ecovve.network.model.EcovveShowExtra

data class ExtraModelItems(
        var id: Int,
        var choices: List<EcovveShowExtra.Data.Choice?>?,
        var is_required: Int
)
