package com.company.skinnie.ui.scan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.skinnie.databinding.FragmentBottomSheetBinding
import com.company.skinnie.ui.manual_input.ManualInputActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTryAgain.setOnClickListener {
            startActivity(Intent(requireContext(), PreviewImageActivity::class.java))
            activity?.finish()
        }

        binding.btnChoose.setOnClickListener {
            startActivity(Intent(requireContext(), ManualInputActivity::class.java))
            activity?.finish()
        }
    }
}