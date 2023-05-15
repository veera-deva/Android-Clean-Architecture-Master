package com.demo.common_ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.demo.common_ui.utils.ProgressDialogUtil
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : ViewBinding>(private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T) :
    Fragment() {
    private var _binding: T? = null
    val binding: T get() = _binding!!
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateMethod.invoke(inflater, container, false)
        dialog = ProgressDialogUtil.setProgressDialog(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor()
        setUpView()

    }

    protected open fun setStatusBarColor() {
        activity?.run {
            window.statusBarColor = getColor(androidx.appcompat.R.color.background_material_dark)
        }
    }

    abstract fun setUpView()

    /**
     * show progress dialog
     * */

    fun showLoading() = dialog.show()

    /**
     * Hide progress dialog
     * */
    fun hideLoading() = dialog.dismiss()

}