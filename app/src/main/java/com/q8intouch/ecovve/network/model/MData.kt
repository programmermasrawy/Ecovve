package com.q8intouch.ecovve.network.model

data class MData(
        var chat_room: ChatRoom?,
        var chat_room_id: Int?, // 6
        var created_at: String?, // 2019-07-02 02:27:20
        var deleted_at: Any?, // null
        var id: Int?, // 31
        var message: String?, // ا
        var updated_at: String?, // 2019-07-02 02:27:20
        var user: User?,
        var user_id: Int? // 1
) {
    data class User(
            var active: Int?, // 1
            var all_time_habits: List<AllTimeHabit?>?,
            var area: String?, // delectus
            var avatar: String?, // images/placeholder.png
            var birthday: String?, // 2009-12-29
            var blocked_from_cash: Int?, // 0
            var city_id: Int?, // 12
            var created_at: String?, // 2019-07-01 13:43:45
            var deleted_at: Any?, // null
            var email: String?, // admin@ecovve.com
            var email_active: Int?, // 1
            var email_verified_at: Any?, // null
            var free_credit: String?, // 94.00
            var gender: String?, // male
            var id: Int?, // 1
            var last_active: String?, // 1976-11-19 01:39:00
            var mobile_active: Int?, // 0
            var name: String?, // Admin
            var news_letter_subscription: Int?, // 1
            var phone: String?, // (348) 829-3291
            var points: Int?, // 0
            var sms_notifications: Int?, // 1
            var social_media: SocialMedia?,
            var updated_at: String?, // 2019-07-01 17:45:38
            var verification_code: String? // voluptatem
    ) {
        data class AllTimeHabit(
                var id: Int? // 3
        )

        data class SocialMedia(
                var est: String?, // http://www.konopelski.info/
                var ipsam: String?, // http://www.gorczany.info/est-soluta-laudantium-quibusdam-et-tempore.html
                var quos: String? // http://www.mann.org/enim-non-dolor-in-ab-enim-et
        )
    }

    data class ChatRoom(
            var created_at: String?, // 2019-07-01 13:43:46
            var creator_id: Int?, // 9
            var deleted_at: Any?, // null
            var description: String?, // Exercitationem magnam cum ea tempora ut natus sint ipsa.
            var id: Int?, // 6
            var image: Any?, // null
            var name: String?, // Eileen O'Kon
            var updated_at: String?, // 2019-07-01 13:43:46
            var users: List<User?>?
    ) {
        data class User(
                var active: Int?, // 1
                var all_time_habits: List<Int?>?,
                var area: String?, // sint
                var avatar: String?, // images/placeholder.png
                var birthday: String?, // 2009-12-29
                var blocked_from_cash: Int?, // 0
                var city_id: Int?, // 21
                var created_at: String?, // 2019-07-01 13:43:45
                var deleted_at: Any?, // null
                var email: String?, // schulist.dudley@gmail.com
                var email_active: Int?, // 1
                var email_verified_at: Any?, // null
                var free_credit: String?, // 823.00
                var gender: String?, // male
                var id: Int?, // 3
                var last_active: String?, // 2001-03-03 18:18:00
                var mobile_active: Int?, // 0
                var name: String?, // Orval Dickinson
                var news_letter_subscription: Int?, // 1
                var phone: String?, // 1-520-588-7493 x37456
                var pivot: Pivot?,
                var points: Int?, // 197
                var sms_notifications: Int?, // 1
                var social_media: SocialMedia?,
                var updated_at: String?, // 2019-07-01 13:43:45
                var verification_code: String? // nemo
        ) {
            data class Pivot(
                    var chat_room_id: Int?, // 6
                    var created_at: String?, // 2019-07-01 13:43:46
                    var updated_at: String?, // 2019-07-01 13:43:46
                    var user_id: Int? // 3
            )

            data class SocialMedia(
                    var consequatur: String?, // https://kutch.org/praesentium-voluptatem-cumque-vero-qui.html
                    var dolorem: String?, // https://www.christiansen.com/blanditiis-dolorum-maiores-dignissimos-sit-aut
                    var qui: String? // http://waters.com/itaque-qui-quam-non-nostrum.html
            )
        }
    }
}