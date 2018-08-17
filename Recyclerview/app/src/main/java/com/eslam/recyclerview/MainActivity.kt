package com.eslam.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*
import com.eslam.recyclerview.R.id.productsList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productsList.layoutManager=GridLayoutManager(this,2)
//                LinearLayoutManager(this)

        productsList.setHasFixedSize(true);
        productsList.adapter=ProductViewAdapter(prepareProducts(),this)
 }

    fun prepareProducts():ArrayList<ProductItem>{
        var productItems:ArrayList<ProductItem>?=ArrayList()
        productItems?.add(addProduct(R.drawable.milk, "Labanita"))
        productItems?.add(addProduct(R.drawable.rice, "El Doha"))
        productItems?.add(addProduct(R.drawable.spices, "Shatshata"))

        return productItems!!
    }

    fun addProduct(imageRef:Int,productName:String):ProductItem{
        var productItem:ProductItem=ProductItem()
        productItem.productImage= imageRef
        productItem.productName="This is $productName"
        productItem.productDescription="This is $productName This is $productName This is $productName. "

        return productItem;
    }
}
