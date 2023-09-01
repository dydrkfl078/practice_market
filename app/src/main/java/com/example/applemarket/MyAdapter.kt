package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ItemViewBinding
import com.example.applemarket.databinding.LikedItemViewBinding

class MyAdapter(private val mItems: MutableList<ItemData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    interface ItemLongClick {
        fun onLongClock(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
    var itemLongClick: ItemLongClick? = null


    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (mItems[position].mItemIsLiked) {
            ItemViewType.FAVORITE
        } else {
            ItemViewType.REGULAR
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            itemLongClick?.onLongClock(it, position)
            return@setOnLongClickListener true
        }
        when (mItems[position].mItemIsLiked){
            true -> {
                (holder as LikedItemViewHolder).bind(mItems[position])
                holder.itemLikeIcon.setImageResource(R.drawable.fill_heart)
            }

            false -> {
                (holder as ItemViewHolder).bind(mItems[position])
                holder.itemLikeIcon.setImageResource(R.drawable.heart_120)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemViewType.FAVORITE -> {
                LikedItemViewHolder(
                    LikedItemViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                ItemViewHolder(
                    ItemViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}


class ItemViewHolder(binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    val itemImage = binding.imageviewItem
    val itemName = binding.tvItemName
    val itemAddress = binding.tvItemTradingAddress
    val itemComment = binding.tvItemComment
    val itemLike = binding.tvItemLike
    val itemPrice = binding.tvItemPrice
    val itemLikeIcon = binding.imgItemLike
    fun bind(item: ItemData) {
        itemImage.setImageResource(item.mItemImage)
        itemImage.clipToOutline = true // 이미지 크기 조정
        itemName.text = item.mItemName
        itemAddress.text = item.mItemAddress
        itemComment.text = item.mItemComment.toString()
        itemLike.text = item.mItemLike.toString()
        itemPrice.text = MakeComma.makeComma(item.mItemPrice) + "원"
    }
}

class LikedItemViewHolder(binding: LikedItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    val itemImage = binding.imageviewItem
    val itemName = binding.tvItemName
    val itemAddress = binding.tvItemTradingAddress
    val itemComment = binding.tvItemComment
    val itemLike = binding.tvItemLike
    val itemPrice = binding.tvItemPrice
    val itemLikeIcon = binding.imgItemLike
    fun bind(item: ItemData) {
        itemImage.setImageResource(item.mItemImage)
        itemImage.clipToOutline = true // 이미지 크기 조정
        itemName.text = item.mItemName
        itemAddress.text = item.mItemAddress
        itemComment.text = item.mItemComment.toString()
        itemLike.text = item.mItemLike.toString()
        itemPrice.text = MakeComma.makeComma(item.mItemPrice) + "원"
    }
}