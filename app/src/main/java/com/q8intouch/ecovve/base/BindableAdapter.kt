package com.q8intouch.ecovve.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BindableAdapter<M, T : ViewDataBinding, H : BindableAdapter.BaseViewHolder> :
  RecyclerView.Adapter<H>() {

  protected var items = mutableListOf<M>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
    val inflater = LayoutInflater.from(parent.context)
    val binding: T = DataBindingUtil.inflate(inflater, getLayoutId(), parent, false)
    return initViewHolder(binding)
  }

  override fun getItemCount() = items.size

  protected fun getItemFrom(position: Int) = items[position]

  fun update(items: List<M>, clearBeforeAdd: Boolean = true) {
    if (clearBeforeAdd) this.items.clear()
    this.items.addAll(items)
    if (clearBeforeAdd) {
      notifyDataSetChanged()
    } else {
      notifyItemRangeInserted(itemCount, this.items.size - 1)
    }
  }

  fun clear() {
    val size = itemCount
    items.clear()
    notifyItemRangeRemoved(0, size)
  }

  @LayoutRes protected abstract fun getLayoutId(): Int

  protected abstract fun initViewHolder(binding: T): H

  open class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

  interface ItemCallback<T> {

    fun onRecyclerItemClicked(item: T?)
  }
}