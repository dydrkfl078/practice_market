package com.example.applemarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 받아온 데이터로 DetailPage View설정
        val data = intent.getParcelableExtra<ItemData>("data")
        binding.detailImageviewItem.setImageResource(data!!.mItemImage)
        binding.detailTvUserName.text = data?.mUserName
        binding.detailTvItemTradingAddress.text = data?.mItemAddress
        binding.detailTvItemName.text = data?.mItemName
        binding.detailTvItemPrice.text = MakeComma.makeComma(data?.mItemPrice)+"원"
        binding.detailTvItemDescription.text = data?.mItemDesc
        binding.detailBtnLike.setImageResource(if (data.mItemIsLiked){R.drawable.fill_heart}else{R.drawable.heart_120})




        // 하단 좋아요 버튼 클릭 이벤트
        var isLiked = data.mItemIsLiked
        var itemPosition = intent.getIntExtra("itemPosition",0)
        binding.detailBtnLike.setOnClickListener {
            Snackbar.make(binding.root,"좋아요 버튼 클릭",Snackbar.LENGTH_LONG).show()
            if (isLiked){
                isLiked = false
            } else if (!isLiked){
                isLiked = true
            }
            binding.detailBtnLike.setImageResource(if (!isLiked){R.drawable.heart_120}else{R.drawable.fill_heart})
        }

        // 메인화면으로 돌아가기 버튼
        binding.detailBtnBackNavigation.setOnClickListener {
            val context = binding.root.context
            val intent = Intent(context, MainActivity::class.java).apply {
            putExtra(MainActivity.INT_INTENT_KEY,itemPosition)
            putExtra(MainActivity.BOOLEAN_INTENT_KEY, isLiked)
            }
            setResult(RESULT_OK, intent)
            if (!isFinishing)finish()
        }

    }
}