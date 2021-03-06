package com.example.aac.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aac.databinding.SearchItemBinding
import com.example.aac.lib.PointInfo
import com.example.aac.ui.main.SearchDetailFragment
import com.example.aac.ui.main.SearchFragmentDirections
import com.example.aac.ui.main.SuperRtFragment
import com.example.aac.ui.main.SuperRtFragmentDirections

class SearchListAdapter : ListAdapter<PointInfo, SearchListViewHolder>(SearchListDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        return SearchListViewHolder(SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
        }
    }
}

class SearchListViewHolder(
    private val binding: SearchItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.clickListener = View.OnClickListener {
            binding.point?.let { id ->
                it.findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToSearchDetailFragment(id.pointId)
                )
            }
        }
    }

    fun bind(item: PointInfo) {
        binding.apply {
            point = item
            executePendingBindings()
        }
    }

}

private class SearchListDiff : DiffUtil.ItemCallback<PointInfo>() {
    override fun areItemsTheSame(oldItem: PointInfo, newItem: PointInfo): Boolean {
        return oldItem.pointId == newItem.pointId
    }

    override fun areContentsTheSame(oldItem: PointInfo, newItem: PointInfo): Boolean {
        return oldItem == newItem
    }
}
