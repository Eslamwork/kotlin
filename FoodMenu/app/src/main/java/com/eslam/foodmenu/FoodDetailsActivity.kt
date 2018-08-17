package com.eslam.foodmenu

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_food_details.*

class FoodDetailsActivity : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)

         var foodCategory=intent.extras;
         categoryHeader.text=foodCategory.getString("foodTitle");
         categoryDesc.text=foodCategory.getString("foodDescription");
         categoryImage.setImageResource(foodCategory.getInt("foodImage"));

    }

}
