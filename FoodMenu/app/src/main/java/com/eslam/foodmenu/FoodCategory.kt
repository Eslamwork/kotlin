package com.eslam.foodmenu

import android.accounts.AuthenticatorDescription

class FoodCategory {
    var catImage:Int?=null;
    var catHeader:String?=null;
    var catDescription:String?=null;

    constructor(catImage:Int,catHeader:String,catDescription:String){
        this.catImage=catImage;
        this.catHeader=catHeader;
        this.catDescription=catDescription;
    }
}