package com.don.omdb.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import com.don.omdb.R
import com.don.omdb.databinding.FragmentSheetSearchBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber
import java.util.*


/**
 * Created by gideon on 8/11/2022
 * gideon@cicil.co.id
 * https://www.cicil.co.id/
 */
class SheetSearchFragment : BottomSheetDialogFragment() {

    var binding: FragmentSheetSearchBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sheet_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imm = parentFragment?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        binding = FragmentSheetSearchBinding.bind(view)
        binding?.apply {
            etSearch.setOnEditorActionListener(OnEditorActionListener { tv, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    imm.hideSoftInputFromWindow(etSearch.windowToken, 0)

                    Timber.e("==-== EVENT ${tv.text}")

                    //reset list on adapter
                    parentFragmentManager.setFragmentResult(
                        "requestKey",
                        bundleOf("bundleKey" to tv.text)
                    )
                    dismiss()
                    return@OnEditorActionListener true
                }
                false
            })
        }

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    dismiss()
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

