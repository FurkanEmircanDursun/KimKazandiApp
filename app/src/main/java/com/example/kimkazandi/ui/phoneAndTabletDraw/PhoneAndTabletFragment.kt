package com.example.kimkazandi.ui.phoneAndTabletDraw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kimkazandi.DrawViewModel
import com.example.kimkazandi.adapter.DrawAdapter
import com.example.kimkazandi.adapter.FollowAdapter
import com.example.kimkazandi.databinding.FragmentPhoneAndTabletBinding

class PhoneAndTabletFragment : Fragment() {

    private var _binding: FragmentPhoneAndTabletBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val drawViewModel =
            ViewModelProvider(this).get(DrawViewModel::class.java)

        _binding = FragmentPhoneAndTabletBinding.inflate(inflater, container, false)
        val root: View = binding.root

        drawViewModel.getAllData(requireContext(),"https://www.kimkazandi.com/cekilisler/telefon-tablet-kazan")
        drawViewModel.data.observe(viewLifecycleOwner) {
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recylerView.layoutManager = layoutManager
            val adapter = DrawAdapter(it, requireContext(),"PhoneAndTabletFragment")
            binding.recylerView.adapter = adapter

        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}