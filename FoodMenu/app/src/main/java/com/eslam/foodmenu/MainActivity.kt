package com.eslam.foodmenu

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_category_list.view.*

class MainActivity : AppCompatActivity() {

    var categories = ArrayList<FoodCategory>();
    var foodAdapter: FoodAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initalizeCategories();

        var inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
        foodAdapter = FoodAdapter(categories, inflater,this);
        mainList.adapter = foodAdapter;
    }

    fun initalizeCategories() {
        categories.add(FoodCategory(R.drawable.cheese, "This is Cheese header", "This is Cheese Long Description alooo alfjlkafl;kjakdlfja asdjfd aldjflkajlk; al;df jlakjf l;afdj aldfj lkadjf lkadf;lj;dsf ;lkadsjflkasdjdf "))
        categories.add(FoodCategory(R.drawable.rice, "This is rice header", "This is rice Long Description alooo alfjlkafl;kjakdlfja asdjfd aldjflkajlk; al;df jlakjf l;afdj aldfj lkadjf lkadf;lj;dsf ;lkadsjflkasdjdf "))
        categories.add(FoodCategory(R.drawable.milk, "This is Milk header", "This is Milk Long Description alooo alfjlkafl;kjakdlfja asdjfd aldjflkajlk; al;df jlakjf l;afdj aldfj lkadjf lkadf;lj;dsf ;lkadsjflkasdjdf "))

    }

    inner class FoodAdapter : BaseAdapter {

        var inflater: LayoutInflater? = null;
        var foodCategories: ArrayList<FoodCategory>? = null;
        var context:Context?=null;

        constructor(foodCategories: ArrayList<FoodCategory>, inflater: LayoutInflater, context: Context) {
            this.inflater = inflater;
            this.foodCategories = foodCategories;
            this.context=context;
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var foodCategory = categories.get(p0) as FoodCategory;
            var foodCategoryView = inflater!!.inflate(R.layout.food_category_list, null);
            foodCategoryView.categoryDesc.text = foodCategory.catDescription;
            foodCategoryView.categoryHeader.text = foodCategory.catHeader;
            foodCategoryView.categoryImage.setImageResource(foodCategory.catImage!!);

            setActionListeners(p0,foodCategory, foodCategoryView);

            return foodCategoryView;
        }

        override fun getItem(p0: Int): Any {
            return p0;
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong();
        }

        override fun getCount(): Int {
            return categories.size;
        }

        fun setActionListeners(p0: Int,foodCategory:FoodCategory, foodCategoryView:View)
        {
            foodCategoryView.categoryHeader.setOnClickListener{
                var intent=Intent(this.context,FoodDetailsActivity::class.java)
                intent.putExtra("foodTitle",foodCategory.catHeader!!);
                intent.putExtra("foodDescription",foodCategory.catDescription!!);
                intent.putExtra("foodImage",foodCategory.catImage!!);
                this.context!!.startActivity(intent);
            }

            foodCategoryView.categoryImage.setOnClickListener {
                add(p0, foodCategory);
            }
            foodCategoryView.deleteCheckBox.setOnClickListener {
                delete(p0);
            }
        }

        fun delete(rowNumberToBeDeleted: Int) {
            this.foodCategories!!.removeAt(rowNumberToBeDeleted);
            foodAdapter!!.notifyDataSetChanged();
        }

        fun add(rowNumberToBeDeleted: Int, foodCategory: FoodCategory) {
            this.foodCategories!!.add(rowNumberToBeDeleted, foodCategory);
            foodAdapter!!.notifyDataSetChanged();
        }
    }
}
