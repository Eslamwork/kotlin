package com.eslam.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var previousNumber=0.0;
    var calculatedNumber=0.0;
    var operator:String?=null;
    var operandPressed=false;

    fun reset(view: View){
        resultView.text="0";
        previousNumber=0.0;
        calculatedNumber=0.0;
        operator="";

    }


    fun updateView (view: View){
        var number:String?=null;
        var pressedButton=view as Button;
        when(pressedButton.id){
            bu0.id->number="0";
            bu1.id->number="1";
            bu2.id->number="2";
            bu3.id->number="3";
            bu4.id->number="4";
            bu5.id->number="5";
            bu6.id->number="6";
            bu7.id->number="7";
            bu8.id->number="8";
            bu9.id->number="9";
            bupoint.id-> number=".";
        }

        var numbersOnScreen:String=resultView.text.toString();
        if ((numbersOnScreen.length==1&&numbersOnScreen.startsWith('0',false)&&!number.equals("."))||operandPressed){
            numbersOnScreen= number!!;
            operandPressed=false;
        }else{
            numbersOnScreen+=number;
        }

        resultView.text=numbersOnScreen;
    }

    fun changeSign(view: View){
        if(resultView.toString().length>0 || (resultView.toString().length>2&&resultView.toString().endsWith("."))){
            var value=resultView.text.toString().toDouble()*-1
            resultView.text=""+value;
        }
    }

    fun startOperation (view: View){
        var pressedOperator=view as Button;
        when(pressedOperator.id) {
            buDivide.id -> operator = "/";
            buMultiply.id -> operator = "*";
            buMinus.id -> operator = "-";
            buPlus.id -> operator = "+";
        }
        if(previousNumber!=0.0){
            previousNumber=makeOperation(previousNumber.toDouble(),resultView.text.toString().toDouble(),operator!!);
        }else{
            previousNumber=resultView.text.toString().toDouble();
        }
        operandPressed=true;
        resultView.text=previousNumber.toString();
//        previousNumber=resultView.text.toString().toDouble();
//        resultView.text="0";
    }

    fun makeOperation(firstnumber:Double,secondNumber:Double,operand:String):Double{
        var result:Double=0.0;
        when(operand) {
            "/"-> result= firstnumber/secondNumber;
            "*"-> result= firstnumber* secondNumber;
            "+"-> result= firstnumber+secondNumber;
            "-"-> result= firstnumber-secondNumber;
        }
        return result;
    }

    fun calculate (view: View){
        var currentNumber=resultView.text.toString();
        previousNumber=makeOperation(previousNumber.toDouble(),currentNumber.toDouble(),operator!!);
        resultView.text=""+previousNumber;
    }
}
