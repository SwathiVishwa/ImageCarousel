package com.app.imagecarouse

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.app.imagecarouse.databinding.ItemRoomsBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselGravity
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.model.CarouselType
import org.imaginativeworld.whynotimagecarousel.utils.setImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Kotlin
        val carousel: ImageCarousel = findViewById(R.id.carousel)

// Register lifecycle. For activity this will be lifecycle/getLifecycle() and for fragment it will be viewLifecycleOwner/getViewLifecycleOwner().
        carousel.registerLifecycle(lifecycle)

        val list = mutableListOf<CarouselItem>()

// Image drawable with caption
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.image_one,
                caption = "Hall"
            )
        )

// Just image drawable
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.image_two,
                caption = "Kitchen"
            )
        )
// Image drawable with caption
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.image_one,
                caption = "ERooms"
            )
        )

// Just image drawable
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.image_two,
                caption = "Dining"
            )
        )
// Image drawable with caption
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.image_one,
                caption = "Living"
            )
        )

// Just image drawable
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.image_two,
                caption = "OutDoor"
            )
        )


        scrollListener(carousel)
        carouselListener(carousel)
        carousel.apply {
            //Add data to the carousel
            setData(list)
            //Set background color
            carouselBackground = ColorDrawable(Color.parseColor("#FCFBFB"))
            //disable  the carousel to loop infinite
            infiniteCarousel = false
            //Hide Navigation buttons
            showNavigationButtons = false
            //Hinde circle Indicators
            showIndicator = false
            //Set Padding for b\w  each item
            carouselPaddingStart = 120
            carouselPaddingEnd = 120

            //Disabled the cation
            showCaption = false

            //Set Carousel type either BLOCK or SHOWCASE
            carouselType = CarouselType.SHOWCASE

            carousel.scaleOnScroll = true
            carousel.scalingFactor = .25f // 0 to 1; 1 means 100

            //Set Carousel Gravity CENTER or START
            carouselGravity = CarouselGravity.CENTER
        }
    }

    private fun scrollListener(carousel: ImageCarousel) {
        // Scroll listener
        carousel.onScrollListener = object : CarouselOnScrollListener {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int,
                position: Int,
                carouselItem: CarouselItem?
            ) {
                val titleTv: TextView = findViewById(R.id.titleTv)
                titleTv.text = carouselItem?.caption
            }

            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int,
                dy: Int,
                position: Int,
                carouselItem: CarouselItem?
            ) {
            }
        }
    }

    private fun carouselListener(carousel: ImageCarousel) {
        // Set the Custom view
        carousel.carouselListener = object : CarouselListener {
            override fun onCreateViewHolder(
                layoutInflater: LayoutInflater,
                parent: ViewGroup
            ): ViewBinding {
                return ItemRoomsBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            }

            override fun onBindViewHolder(
                binding: ViewBinding,
                item: CarouselItem,
                position: Int
            ) {
                val currentBinding = binding as ItemRoomsBinding

                currentBinding.ivRooms.apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP

                    item.imageDrawable?.let { setImage(item, it) }
                }
                currentBinding.tvRoomName.text = item.caption

            }

            override fun onClick(position: Int, carouselItem: CarouselItem) {
                super.onClick(position, carouselItem)
                //Implement the functionality on Click
            }

            override fun onLongClick(position: Int, carouselItem: CarouselItem) {
                super.onLongClick(position, carouselItem)

                //Implement the functionality on  Long Click
            }

        }
    }
}