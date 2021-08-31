package com.example.harajtask.ui.ads_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harajtask.R
import com.example.harajtask.databinding.ItemAdBinding
import com.example.harajtask.model.Ad
import com.example.harajtask.util.getTimeDifference
import com.google.android.material.shape.CornerFamily


class AdsRVAdapter(internal val onClickListener: (Int) -> (Unit)) : ListAdapter<Ad, AdsRVAdapter.AdsViewHolder>(RecitationsDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        val binding = ItemAdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.adIV.shapeAppearanceModel = binding.adIV.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, parent.resources.getDimension(R.dimen.car_image_corner_radius))
            .build()
        return AdsViewHolder(binding).apply { prepareListener(this) }
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        val ad = getItem(position)
        holder.bind(ad)
    }


    class RecitationsDiffCallback : DiffUtil.ItemCallback<Ad>() {
        override fun areItemsTheSame(oldItem: Ad, newItem: Ad): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ad, newItem: Ad): Boolean {
            return true
        }
    }

    private fun prepareListener(holder: AdsViewHolder) {
        holder.binding.root.setOnClickListener {
            onClickListener(holder.adapterPosition)
        }
    }

    class AdsViewHolder(val binding: ItemAdBinding) : RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun bind(ad: Ad) {
            binding.apply {
                adNameTV.text = ad.title
                addedDateTV.text = getTimeDifference(ad.date.toLong())
                sellerNameTV.text = ad.username
                locationTV.text = ad.city
                Glide
                    .with(binding.root)
                    .load(ad.thumbURL)
                    .centerCrop()
                    .placeholder(R.drawable.vector_no_image)
                    .into(adIV)

                if (ad.commentCount > 0) {
                    commentTV.visibility = View.VISIBLE
                    commentIV.visibility = View.VISIBLE
                    commentTV.text = "(${ad.commentCount})"
                } else {
                    commentTV.visibility = View.INVISIBLE
                    commentIV.visibility = View.INVISIBLE
                }
            }
        }


    }


}