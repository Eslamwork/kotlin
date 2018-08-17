package com.eslam.kotlin

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        init();
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            reset();
        }

    }

    var player1= ArrayList<Int>();
    var player2= ArrayList<Int>();
    var currentPlayer:Int=1;
    var btnsMap= HashMap<Int, Button>();

    fun init(){
        btnsMap.put(1, button1);
        btnsMap.put(2, button2);
        btnsMap.put(3, button3);
        btnsMap.put(4, button4);
        btnsMap.put(5, button5);
        btnsMap.put(6, button6);
        btnsMap.put(7, button7);
        btnsMap.put(8, button8);
        btnsMap.put(9, button9);
    }

    fun play(view: View)
    {
        var selectedButton= view as Button;
        var cellID:Int=1;

        for (i:Int in 1..btnsMap.size){
            if(selectedButton.equals(btnsMap.get(i))) {
                cellID=i;
                break;
            }
        }

        if(currentPlayer==1){
            selectedButton.setBackgroundColor(Color.RED);
            selectedButton.text="X";
            player1.add(cellID);
            currentPlayer=2;
        }

        selectedButton.isClickable=false;

        if (checkWinner(player1)){
            Toast.makeText(this.applicationContext,"Player1 wins",Toast.LENGTH_LONG).show();
        }
        else if (checkWinner(player2)){
            Toast.makeText(this.applicationContext,"Your mobile wins ",Toast.LENGTH_LONG).show();
        }
        else
        {
            computerTurnToPlay();
        }
    }

    fun computerTurnToPlay() {
        var selectedButton:Button;
        for (j: Int in 1..9) {
            if (!player1.contains(j)&&!player2.contains(j)) {
                selectedButton=btnsMap.get(j)as Button;
                selectedButton.setBackgroundColor(Color.GREEN);
                selectedButton.text = "O";
                player2.add(j);
                currentPlayer = 1;
                break;
            }

        }
    }

    fun checkWinner(player:ArrayList<Int>):Boolean{
        var pressedButton:Int=0;
        var col1= listOf<Int>(1,4,7);
        var col2= listOf<Int>(2,5,8);
        var col3= listOf<Int>(3,6,9);
        var row1= listOf<Int>(1,2,3);
        var row2= listOf<Int>(4,5,6);
        var row3= listOf<Int>(7,8,9);
        var diagonal1=listOf<Int>(1,5,9);
        var diagonal2=listOf<Int>(3,5,7);
        if(player.containsAll(col1)||player.containsAll(col2)||player.containsAll(col3)||player.containsAll(row1)
                ||player.containsAll(row2)||player.containsAll(row3)||player.containsAll(diagonal1)||player.containsAll(diagonal2)) {
            for(i:Int in 1..btnsMap.size)
            {
                (btnsMap.get(i) as Button).isClickable=false;
            }
            return true;
        }
        else {
            return false;
        }
    }

    fun reset(){
        for(i:Int in 1..btnsMap.size){
            restoreToDefault(btnsMap.get(i) as Button);
        }
        player1.clear();
        player2.clear();
        currentPlayer=1;
    }

    fun restoreToDefault(button:Button){
        button.text="";
        button.isClickable=true;
        button.setBackgroundColor(Color.GRAY);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}
