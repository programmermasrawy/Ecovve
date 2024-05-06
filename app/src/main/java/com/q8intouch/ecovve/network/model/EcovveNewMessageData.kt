package com.q8intouch.ecovve.network.model

data class EcovveNewMessageData(
        var `data`: Data?
) {
    data class Data(
            var chat_room: ChatRoom?,
            var chat_room_id: Int?, // 3
            var created_at: String?, // 2019-07-01 03:55:49
            var id: Int?, // 33
            var message: String?, // test
            var updated_at: String?, // 2019-07-01 03:55:49
            var user: User?,
            var user_id: Int? // 1
    ) {
        data class ChatRoom(
                var created_at: String?, // 2019-06-25 13:23:31
                var creator_id: Int?, // 6
                var deleted_at: String?, // null
                var description: String?, // Non inventore molestiae asperiores est animi voluptate.
                var id: Int?, // 3
                var image: String?, // null
                var name: String?, // Orie D'Amore III
                var updated_at: String?, // 2019-06-25 13:23:31
                var users: List<User?>?
        )
    }
}