package com.example.kimkazandi.ui.vacationDraw

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

import com.example.kimkazandi.databinding.FragmentVacationDrawBinding

class VacationDrawFragment : Fragment() {

    private var _binding: FragmentVacationDrawBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentVacationDrawBinding.inflate(inflater, container, false)
        val drawViewModel =
            ViewModelProvider(this).get(DrawViewModel::class.java)


        val root: View = binding.root

        drawViewModel.getAllData(requireContext(),"https://www.kimkazandi.com/cekilisler/tatil-kazan")
        drawViewModel.data.observe(viewLifecycleOwner) {
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recylerView.layoutManager = layoutManager
            val adapter = DrawAdapter(it, requireContext(),"VacationDrawFragment")
            binding.recylerView.adapter = adapter

        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}