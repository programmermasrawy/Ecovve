package com.q8intouch.ecovve.network.model

data class EcovveContactUs(
        var `data`: Data?
) {
    data class Data(
            var address: List<Addres?>?,
            var created_at: String?, // 2019-05-01 19:33:21
            var email: List<Email?>?,
            var gift_cards: List<GiftCard?>?,
            var id: Int?, // 1
            var phone: List<Phone?>?,
            var site_name: String?, // asperiores
            var social_media: List<SocialMedia?>?,
            var updated_at: String? // 2019-05-01 19:38:42
    ) {
        data class Addres(
                var value: String? // 98835 Coleman FerryDamonburgh, PA 82486
        )

        data class GiftCard(
                var value: String? // 4000
        )

        data class Phone(
                var value: String? // 712.357.1542 x874
        )

        data class Email(
                var value: String? // gladyce78@hotmail.com
        )

        data class SocialMedia(
                var host: String?, // twitter
                var link: String?, // 3
                var value: String? // astroman@kuhlman.net
        )
    }
}