package com.kaundinyakasibhatla.trainman_assignment.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaundinyakasibhatla.trainman_assignment.R
import com.kaundinyakasibhatla.trainman_assignment.base.BaseRecyclerViewAdapter
import com.kaundinyakasibhatla.trainman_assignment.ui.main.helper.GifDiffCallback
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val icons: List<String>,
    private val listener:(String)->Unit,
) : BaseRecyclerViewAdapter<String,MainAdapter.DataViewHolder>(icons.toMutableList()) {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(icon: String) {

            Glide.with(itemView.imageViewIcon.context)
                .load(icon)
                .centerCrop()
                .placeholder(R.drawable.ic_loading)
                .into(itemView.imageViewIcon)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )


    override fun onBindViewHolder(holder: DataViewHolder, position: Int){
        holder.bind(mList[position])
        holder.itemView.setOnClickListener {
            listener(mList[position])
        }

    }

    override fun setData(list: List<String>) {

        val diffCallback = GifDiffCallback(this.mList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        mList.clear()
        mList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }
}