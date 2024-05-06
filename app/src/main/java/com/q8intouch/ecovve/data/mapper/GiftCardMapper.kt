package com.q8intouch.ecovve.data.mapper

import com.q8intouch.ecovve.data.model.GiftCard
import com.q8intouch.ecovve.network.model.GiftCardsListResponse
import javax.inject.Inject

class GiftCardMapper @Inject constructor() {

    fun map(data: List<GiftCardsListResponse.DataEntity>): List<GiftCard> {
        return data.map {
            map(it)
        }
    }

    private fun map(data: GiftCardsListResponse.DataEntity): GiftCard {
        data.apply {
            return GiftCard(
                    amount = data.amount,
                    cardId = data.id
            )
        }
    }
}