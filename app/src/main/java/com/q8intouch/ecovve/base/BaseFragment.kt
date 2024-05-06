package com.q8intouch.ecovve.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.q8intouch.ecovve.EcovveApplication
import java.lang.RuntimeException
import java.lang.reflect.ParameterizedType



abstract class BaseFragment< V:ViewModel,  B : ViewDataBinding> : Fragment() {

    @LayoutRes
    abstract fun getLayoutRes(): Int


    lateinit var viewModeFactory: BaseViewModelFactory
    lateinit var binding: B
    lateinit var viewModel: V
    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModeFactory = ( activity!!.application as EcovveApplication).appComponent.baseViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,getLayoutRes(),container,false)
        binding.setLifecycleOwner(this)

        val viewModelClass =  getViewModelClass(viewModeFactory)
        viewModel = ViewModelProviders.of(this,viewModeFactory).get(viewModelClass)
       bindViewModelToFragment()
        return binding.root

    }

    @Suppress("UNCHECKED_CAST")
    fun getViewModelClass(viewModelsFactory: BaseViewModelFactory): Class<V> {
        val genericViewModelType = (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0].toString()
        viewModelsFactory.viewModelsMap.keys.forEach {
            if (it.toString() == genericViewModelType) return it as Class<V>
        }
        throw RuntimeException("Make sure to inject a ViewModel For ${this::class}")
    }

    @Suppress("UNCHECKED_CAST")
    fun bindViewModelToFragment() {
        try {
            binding::class.java.getDeclaredMethod("setViewModel",viewModel::class.java)
                .invoke(binding,viewModel)
        } catch (e: Throwable) {
            throw RuntimeException("You must set 'viewModel' variable inside fragment xml")
        }

    }

   open fun onBackPressed(): Boolean {
       activity!!.onBackPressed()
        return true
    }


}

