package com.q8intouch.ecovve.base

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.q8intouch.ecovve.EcovveApplication
import com.q8intouch.ecovve.data.repo.CartRepo
import com.q8intouch.ecovve.util.extension.bindViewModel
import com.q8intouch.ecovve.util.extension.getParentActivity
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import java.util.*
import javax.inject.Inject

abstract class BaseItemViewModel< B : ViewDataBinding>  : ViewModel(),
    IFlexible<BaseItemViewModel.BaseViewHolder<B>> {


    @Inject
    lateinit var cartRepo: CartRepo


    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
        holder: BaseViewHolder<B>?,
        position: Int,
        payloads: MutableList<Any>?
    ) {
       val binding =  holder!!.bind(this)
        onViewBound(binding)

    }

    override fun equals(other: Any?): Boolean {
        return Objects.equals(this, other)
    }


    override fun createViewHolder(
        view: View?,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
    ): BaseViewHolder<B> {
        val binding : B =
            DataBindingUtil.inflate(LayoutInflater.from(view!!.context), layoutRes, adapter!!.recyclerView, false)

        return BaseViewHolder(binding, adapter)
    }

    open fun onViewBound(binding: B) {

    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    class BaseViewHolder<B : ViewDataBinding>
        (
        private val binding: B,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
    ) : FlexibleViewHolder(binding.root, adapter) {
        fun bind(itemViewModel: BaseItemViewModel<B>): B {
            binding.bindViewModel(itemViewModel)
            return binding
        }

    }

    protected var mEnabled = true
    protected var mHidden = false
    protected var mSelectable = true
    protected var mDraggable = true
    protected var mSwipeable = true

    /*---------------*/
    /* BASIC METHODS */
    /*---------------*/

    override fun isEnabled(): Boolean {
        return mEnabled
    }

    override fun setEnabled(enabled: Boolean) {
        mEnabled = enabled
    }

    override fun isHidden(): Boolean {
        return mHidden
    }

    override fun setHidden(hidden: Boolean) {
        mHidden = hidden
    }

    override fun getSpanSize(spanCount: Int, position: Int): Int {
        return 1
    }

    override fun shouldNotifyChange(newItem: IFlexible<*>): Boolean {
        return true
    }

    override fun isSelectable(): Boolean {
        return mSelectable
    }

    override fun setSelectable(selectable: Boolean) {
        this.mSelectable = selectable
    }

    override fun getBubbleText(position: Int): String {
        return (position + 1).toString()
    }

    override fun isDraggable(): Boolean {
        return mDraggable
    }

    override fun setDraggable(draggable: Boolean) {
        mDraggable = draggable
    }

    override fun isSwipeable(): Boolean {
        return mSwipeable
    }

    override fun setSwipeable(swipeable: Boolean) {
        mSwipeable = swipeable
    }

    override fun getItemViewType(): Int {
        return layoutRes
    }


    override fun unbindViewHolder(adapter: FlexibleAdapter<IFlexible<*>>, holder: BaseItemViewModel.BaseViewHolder<B>, position: Int) {}

    override fun onViewAttached(adapter: FlexibleAdapter<IFlexible<*>>, holder: BaseItemViewModel.BaseViewHolder<B>, position: Int) {
        cartRepo = (adapter.recyclerView!!.getParentActivity()!!.application as EcovveApplication).appComponent.cartRepo()
    }

    override fun onViewDetached(adapter: FlexibleAdapter<IFlexible<*>>, holder: BaseItemViewModel.BaseViewHolder<B>, position: Int) {}
}