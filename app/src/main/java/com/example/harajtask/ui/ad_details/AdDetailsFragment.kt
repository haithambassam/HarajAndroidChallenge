package com.example.harajtask.ui.ad_details

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.harajtask.R
import com.example.harajtask.databinding.FragmentAdDetailsBinding
import com.example.harajtask.ui.MainViewModel
import com.example.harajtask.util.format_dd_MM_YYYY_HH_mm

class AdDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAdDetailsBinding

    private val activityViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentAdDetailsBinding.inflate(inflater)
        setHasOptionsMenu(true)
        observeActivityViewModel()
        return binding.root
    }

    private fun observeActivityViewModel() {
        activityViewModel.selectedAd.observe(viewLifecycleOwner) { ad ->
            binding.apply {
                Glide
                    .with(binding.root)
                    .load(ad.thumbURL)
                    .centerCrop()
                    .placeholder(R.drawable.vector_no_image)
                    .into(adIV)
                adNameTV.text = ad.title
                adCreationDateTV.text = format_dd_MM_YYYY_HH_mm.format(ad.date.toLong() * 1000)
                sellerNameTV.text = ad.username
                locationTV.text = ad.city
                adDetailsTV.text = ad.body
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.ad_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share_menu -> {
                context?.apply {
                    activityViewModel.selectedAd.value?.title?.let { title ->
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, title)
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        startActivity(shareIntent)
                    }
                }
                return true
            }

        }
        return super.onOptionsItemSelected(item)

    }
}