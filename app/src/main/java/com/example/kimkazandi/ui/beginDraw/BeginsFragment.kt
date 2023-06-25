package com.example.kimkazandi.ui.beginDraw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kimkazandi.adapter.FollowAdapter
import com.example.kimkazandi.databinding.FragmentBeginsBinding
import com.example.kimkazandi.DrawViewModel
import com.example.kimkazandi.adapter.DrawAdapter


class BeginsFragment : Fragment() {

    private var _binding: FragmentBeginsBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeginsBinding.inflate(inflater, container, false)
        val drawViewModel =
            ViewModelProvider(this).get(DrawViewModel::class.java)


        val root: View = binding.root

        drawViewModel.getAllData(requireContext(),"https://www.kimkazandi.com/cekilisler/yeni-baslayanlar")
        drawViewModel.data.observe(viewLifecycleOwner) {
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recylerView.layoutManager = layoutManager
            val adapter = DrawAdapter(it, requireContext(),"BeginsFragment")
            binding.recylerView.adapter = adapter

        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}