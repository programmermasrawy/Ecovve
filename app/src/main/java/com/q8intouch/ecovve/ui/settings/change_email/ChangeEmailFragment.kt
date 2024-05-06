package com.q8intouch.ecovve.ui.settings.change_email

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.q8intouch.ecovve.R

class ChangeEmailFragment : Fragment() {

    companion object {
        fun newInstance() = ChangeEmailFragment()
    }

    private lateinit var viewModel: ChangeEmailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_change_email, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChangeEmailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
