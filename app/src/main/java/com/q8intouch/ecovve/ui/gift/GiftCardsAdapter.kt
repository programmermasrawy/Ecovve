package com.q8intouch.ecovve.ui.gift

import com.q8intouch.ecovve.R
import com.q8intouch.ecovve.base.BindableAdapter
import com.q8intouch.ecovve.data.model.GiftCard
//import com.q8intouch.ecovve.databinding.GiftCardItemBinding

//class GiftCardsAdapter(
//        private val onClick: (cardId: String?) -> Unit
//) : BindableAdapter<GiftCard, GiftCardItemBinding, GiftCardsAdapter.GiftCardsViewHolder>() {
//
//    override fun getLayoutId() = R.layout.gift_card_item
//
//    override fun initViewHolder(binding: GiftCardItemBinding) = GiftCardsViewHolder(binding, onClick, items)
//
//    override fun onBindViewHolder(holder: GiftCardsViewHolder, position: Int) {
//        if (items.size > position) holder.bind(items[position])
//    }
//
//    class GiftCardsViewHolder(
//            private val binding: GiftCardItemBinding,
//            private val onClick: (cardId: String?) -> Unit,
//            items: List<GiftCard>
//    ) : BaseViewHolder(binding) {
//
//        init {
//            itemView.setOnClickListener { onClick(items[layoutPosition].cardId) }
//        }
//
//        fun bind(item: GiftCard) {
//            binding.item = item
//        }
//    }
//}