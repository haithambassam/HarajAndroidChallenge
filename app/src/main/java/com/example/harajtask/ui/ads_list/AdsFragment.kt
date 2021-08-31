package com.example.harajtask.ui.ads_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harajtask.R
import com.example.harajtask.databinding.FragmentAdsListBinding
import com.example.harajtask.decorator.SymmetricRVItemDecorator
import com.example.harajtask.ui.MainViewModel

class AdsFragment : Fragment() {

    private lateinit var binding: FragmentAdsListBinding

    private val activityViewModel by activityViewModels<MainViewModel>()

    private val adsRVAdapter = AdsRVAdapter { adPosition ->
        activityViewModel.selectAd(adPosition)
        findNavController().navigate(R.id.action_adsListFragment_to_adDetailsFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAdsListBinding.inflate(inflater)
        prepareLayout()
        observeActivityViewModel()
        activityViewModel.getAdvertisements()
        return binding.root
    }

    private fun prepareLayout() {
        binding.adsRV.layoutManager = LinearLayoutManager(context)
        binding.adsRV.setHasFixedSize(true)
        binding.adsRV.adapter = adsRVAdapter

        // add decorator to the RecyclerView
        val horizontalMargin = resources.getDimension(R.dimen.ads_RV_horizontal_margin)
        val verticalMargin = resources.getDimension(R.dimen.ads_RV_vertical_margin)
        val adsRVItemDecorator = SymmetricRVItemDecorator(horizontalMargin.toInt(), verticalMargin.toInt())
        binding.adsRV.addItemDecoration(adsRVItemDecorator)
    }

    private fun observeActivityViewModel() {
        activityViewModel.ads.observe(viewLifecycleOwner) {
            adsRVAdapter.submitList(it)
        }
    }

}