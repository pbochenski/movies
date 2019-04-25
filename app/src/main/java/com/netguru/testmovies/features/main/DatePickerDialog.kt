package com.netguru.testmovies.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.netguru.testmovies.R
import com.netguru.testmovies.features.main.MainViewModel
import kotlinx.android.synthetic.main.date_picker_fragment.*
import org.koin.android.viewmodel.ext.android.getViewModel

class DatePickerDialog: DialogFragment() {

    private val viewModel by lazy { requireActivity().getViewModel<MainViewModel>() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.date_picker_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        picker.setOnValueChangedListener { picker, oldVal, newVal ->
            viewModel.setYear(newVal)
        }

        resetButton.setOnClickListener {
            viewModel.setYear(null)
            dismiss()
        }
    }
}