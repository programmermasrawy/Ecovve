package com.q8intouch.ecovve.network.model

data class EcovveAllRewards(
    var `data`: List<Data?>?
) {
    data class Data(
        var created_at: String?, // 2019-08-05 17:02:40
        var credit: String?, // 310
        var id: Int?, // 5
        var point: String?, // 30
        var updated_at: String? // 2019-08-05 17:02:40
    )
}