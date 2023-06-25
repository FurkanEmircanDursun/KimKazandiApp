package com.example.kimkazandi.detail

import android.graphics.Color
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.kimkazandi.databinding.FragmentDetailBinding
import com.example.kimkazandi.model.Draw
import com.example.kimkazandi.model.Follow
import com.example.kimkazandi.roomFollow.FollowDao
import com.example.kimkazandi.roomFollow.FollowDatabase

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailViewModel
    private lateinit var db: FollowDatabase
    private lateinit var followDao: FollowDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root



        // ViewModelProvider ile ViewModel'i oluştur
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        // Veriyi al ve print et
        val bundle = arguments
        val data = bundle?.getSerializable("data") as Draw

        db = Room.databaseBuilder(requireContext(), FollowDatabase::class.java, "follow")
            .allowMainThreadQueries()
            .build()

        followDao = db.followDao()

        var isFollowed = Runnable {

        var urlList=    followDao.getAllFollowByLink(data.link)

            if (urlList.size>0){

                binding.followButton.text="Takipten Çık"
                binding.followButton.setBackgroundColor(Color.RED)
            }



        }
        Thread(isFollowed).start()



        viewModel.getDetail(data.link)

            viewModel._myDetail.observe(viewLifecycleOwner){
                binding.TitleTextView.setText(it.title)
                binding.DescTextView.setText(it.detail)
                binding.tableStartingDateTextView.setText(it.startingDate)
                binding.tablelastParticipationDateTextView.setText(it.lastParticipationDate)
                binding.tableDrawTextView.setText(it.drawDate)
                binding.tableadvertDateTextView.setText(it.advertDate)
                binding.tableminExpenditureAmountTextView.setText(it.minExpenditureAmount)
                binding.tabletotalGiftValueTextView.setText(it.totalGiftValue)
                binding.tabletotalNumberOfGiftsTextView.setText(it.totalNumberOfGifts)

                Glide.with(view.context)
                    .load("https://www.kimkazandi.com/" + it.image)
                    .into(binding.detailImageView)
            }




        binding.followButton.setOnClickListener {
            var run = Runnable {
                val follow = Follow(data.baseUrl,data.title,data.time,data.count,data.price,data.img,data.link)
                var urlList=    followDao.getAllFollowByLink(data.link)

                if (urlList.size>0){
                    followDao.delete(follow.link)
                    binding.followButton.text="Takip Et"
                    binding.followButton.setBackgroundColor(Color.BLUE)
                }
                else{
                    followDao.insert(follow)
                    binding.followButton.text="Takipten Çık"
                    binding.followButton.setBackgroundColor(Color.RED)
                }




            }

            Thread(run).start()

        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
