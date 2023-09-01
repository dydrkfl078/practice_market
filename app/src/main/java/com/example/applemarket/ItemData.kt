package com.example.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemData(
    val mItemImage : Int,
    val mItemName : String,
    val mItemAddress : String,
    val mItemPrice : Int,
    val mItemComment : Int,
    var mItemLike : Int,
    val mUserName : String,
    val mItemDesc : String,
    var mItemIsLiked : Boolean
) : Parcelable
