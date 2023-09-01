package com.example.applemarket

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object {
        const val BOOLEAN_INTENT_KEY = "my_boolean_key"
        const val INT_INTENT_KEY = "my_int_key"
    }

    // 아이템 리스트 생성
    val itemList = mutableListOf<ItemData>()

    // RecyclerView에 들어가는 아이템 지정
    init {
        itemList.apply {
            add(
                ItemData(
                    R.drawable.sample1,
                    "산지 한달된 선풍기",
                    "서울 서대문구 창천동",
                    1000,
                    1,
                    0,
                    "대현동",
                    "이사가서 필요가 없어졌어요 급하게 내놓습니다", false

                )
            )
            add(
                ItemData(
                    R.drawable.sample2,
                    "김치냉장고",
                    "인천 계양규 귤현동",
                    20000,
                    1,
                    1,
                    "안마담",
                    "이사로 인해 내놔요", true
                )
            )
            add(
                ItemData(
                    R.drawable.sample3,
                    "샤넬 카드지갑",
                    "수성구 범어동",
                    10000,
                    1,
                    1,
                    "코코유",
                    "고퀄 지갑이구요 \n 사용감이 있어서 싸게 내어 둡니다", false
                )
            )
            add(
                ItemData(
                    R.drawable.sample4,
                    "금고",
                    "해운대구 우제2동",
                    10000,
                    0,
                    0,
                    "Nicole",
                    "금고\n떼서 가져가야함 \n대우월드 마크센텀\n미국 이주 관계로 싸게 팝니다.", false
                )
            )
            add(
                ItemData(
                    R.drawable.sample5,
                    "갤럭시 Z플립 3팝니다",
                    "연제구 연산제8동",
                    150000,
                    5,
                    5,
                    "절명",
                    "갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!",
                    false
                )
            )
            add(
                ItemData(
                    R.drawable.sample6,
                    "프라다 복조리백",
                    "수원시 영통구 원천동",
                    50000,
                    1,
                    6,
                    "미니멀하게",
                    "까임 오염없고 상태 깨끗합니다\n정품여부모름", false
                )
            )
            add(
                ItemData(
                    R.drawable.sample7,
                    "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장",
                    "남구 옥동",
                    150000,
                    2,
                    5,
                    "굿리치",
                    "울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방3개, 화장실3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다.",
                    false
                )
            )
            add(
                ItemData(
                    R.drawable.sample8,
                    "샤넬 탑핸들 가방",
                    "동래구 온천제2동",
                    180000,
                    0,
                    1,
                    "난쉽",
                    "샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\n색상 : 블랙\n사이즈 : 25.5cm * 17.5cm * 8cm\n구성 : 본품더스트\n급하게 돈이 필요해서 팝니다 ㅠ ",
                    false
                )
            )
            add(
                ItemData(
                    R.drawable.sample9,
                    "4행정 엔진분무기",
                    "원주시 명륜2동",
                    30000,
                    0,
                    10,
                    "알뜰한",
                    "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.",
                    false
                )
            )
            add(
                ItemData(
                    R.drawable.sample10,
                    "셀린느 버킷 가방",
                    "중구 동화동",
                    190000,
                    3,
                    20,
                    "똑태현",
                    "22년 신세계 대전 구매입니당\n\"셀린느 버킷백\"\n구매해서 몇번사용했어요\n까짐 스크래치 없습니다.\n타지역에서 보내는거라 택배로 진행합니당!",
                    false
                )
            )
        }
    }

    // viewBinding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Recyclerview와 어뎁터 연결.
        val myAdapter = MyAdapter(itemList)
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        // RecyclerView Divider 추가
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))


        // Detail Page 데이터 registerForActivity로 넘겨받기
        val getResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val returnLiked = result.data?.getBooleanExtra(BOOLEAN_INTENT_KEY, false) ?: false
                val returnPosition = result.data?.getIntExtra(INT_INTENT_KEY, 0) ?: 0
                if (returnLiked) {
                    itemList[returnPosition].mItemLike += 1
                    itemList[returnPosition].mItemIsLiked = true
                } else {
                    if (itemList[returnPosition].mItemIsLiked){
                        itemList[returnPosition].mItemLike -= 1
                        itemList[returnPosition].mItemIsLiked = false
                    }
                }
                myAdapter.notifyItemChanged(returnPosition)
            }
        }

        // 뒤로가기 버튼 클릭 이벤트 : callback 인스턴스 생성
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                ShowDialog().showDialog(this@MainActivity, "앱 종료", "앱을 종료하시겠습니까?") { finish() }
            }
        }
        // 뒤로가기 버튼 클릭 이벤트 : 미리 생성해둔 callback 인스턴스 적용
        this.onBackPressedDispatcher.addCallback(this, callback)


        // 툴 바 알림버튼 클릭 이벤트
        binding.mainBtnNotification.setOnClickListener {
            notification()
        }


        // RecyclerView Item 클릭 시 Item[position]의 Data확인하는 디테일 페이지로 이동
        myAdapter.itemClick = object : MyAdapter.ItemClick {
            val context = binding.root.context
            override fun onClick(view: View, position: Int) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("data", itemList[position])
                intent.putExtra("itemPosition", position)
                getResult.launch(intent)
//                intent.run { context.startActivity(this) }
            }
        }


        // RecyclerView Item 길게 클릭 시 Item[position]의 Data 삭제하는 Dialog 출력
        myAdapter.itemLongClick = object : MyAdapter.ItemLongClick {
            val context = binding.root.context
            override fun onLongClock(view: View, position: Int) {
                Log.d("TAG", "onLongClock: ${position} 롱 클릭 ")
                val showDialog = ShowDialog()
                // ShowDialog에서 Dialog만 받아오고 positiveButton 클릭 시, ltemList.removeAt(position)
                showDialog.showDialog(context, "아이템 삭제 알림", "아이템을 삭제하시겠습니까?") {
                    itemList.removeAt(position)
                    myAdapter.notifyItemRemoved(position)
                }
            }
        }

        // floatingButton Animation 설정
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        var isTop = true

        // RecyclerView 스크롤 리스너 생성
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerView.canScrollVertically(-1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    binding.mainFloatBtn.startAnimation(fadeOut)
                    binding.mainFloatBtn.visibility = View.GONE
                    isTop = true
                } else if (isTop) {
                    binding.mainFloatBtn.visibility = View.VISIBLE
                    binding.mainFloatBtn.startAnimation(fadeIn)
                    isTop = false
                }
            }
        })

        // RecyclerView 스크롤 상단 이동 floationg버튼
        binding.mainFloatBtn.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }

    }

    // 알림 기능
    private fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if (Build.VERSION.SDK_INT >= VERSION_CODES.O) {
            val channelID = "channel"
            val channel = NotificationChannel(
                channelID, "My Channel", NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Channel Description"
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes
                    .Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
            }
            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(this, channelID)
        } else {
            builder = NotificationCompat.Builder(this)
        }

        val bitMap = BitmapFactory.decodeResource(resources, R.drawable.apple1)

        builder.run {
            setSmallIcon(R.drawable.baseline_android_24)
            setWhen(System.currentTimeMillis())
            setTitle("알림제목")
            setContentText("알림내용")
            setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("알림 내용 입니다.")
            )
            setLargeIcon(bitMap)
        }
        manager.notify(1, builder.build())
    }
}