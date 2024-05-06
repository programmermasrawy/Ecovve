package com.q8intouch.ecovve.network.model

data class EcovveUserPhoto(
        var `data`: List<Data?>?
) {
    data class Data(
            var message: String?,
            var user: User?
    ) {
        data class User(
                var active: Int?, // 1
                var all_time_habits: List<AllTimeHabit?>?,
                var area: String?, // aut
                var avatar: String?, // storage/images/avatar/20190413100810-07653748d7e18c18a1ca91504a5a8fc697c4864171824.jpg
                var birthday: String?, // 2008-05-12
                var blocked_from_cash: Int?, // 0
                var city_id: Int?, // 1
                var created_at: String?, // 2019-04-06 12:04:19
                var deleted_at: Any?, // null
                var email: String?, // admin@ecovve.com
                var email_active: Int?, // 1
                var email_verified_at: Any?, // null
                var free_credit: String?, // 797.00
                var gender: String?, // male
                var id: Int?, // 1
                var last_active: String?, // 1993-07-12 04:05:02
                var mobile_active: Int?, // 0
                var name: String?, // adsasd
                var news_letter_subscription: Int?, // 0
                var phone: String?, // 87654321
                var points: Int?, // 0
                var sms_notifications: Int?, // 1
                var updated_at: String?, // 2019-04-13 22:08:10
                var used_coupons: List<Int?>?,
                var verification_code: String? // explicabo
        ) {
            data class AllTimeHabit(
                    var id: Int? // 8
            )
        }
    }
}