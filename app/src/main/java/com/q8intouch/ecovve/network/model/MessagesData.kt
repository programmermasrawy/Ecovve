package com.q8intouch.ecovve.network.model

data class MessagesData(
        var `data`: List<Data?>?
) {
    data class Data(
            var chat_room_id: Int?, // 3
            var created_at: String?, // 2019-07-01 13:43:46
//            var deleted_at: String?, // null
            var id: Int?, // 9
            var message: String?, // culpa
            var updated_at: String?, // 2019-07-01 13:43:46
            var user_id: Int? // 3
    )
}