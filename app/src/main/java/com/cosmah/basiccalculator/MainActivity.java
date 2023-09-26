package com.cosmah.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv, solution_tv;
    MaterialButton buttonC, buttonBrackOpen, buttonBracClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0, button1, button2,button3,button4,button5,button6,button7,button8, button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_tv = findViewById(R.id.result_tv);
        solution_tv = findViewById(R.id.solution_tv);

        assignId(button0,R.id.button_zero);
        assignId(button1,R.id.button_one);
        assignId(button2,R.id.button_two);
        assignId(button3,R.id.button_three);
        assignId(button4,R.id.button_four);
        assignId(button5,R.id.button_five);
        assignId(button6,R.id.button_six);
        assignId(button7,R.id.button_seven);
        assignId(button8,R.id.button_eight);
        assignId(button9,R.id.button_nine);
        assignId(buttonAC,R.id.button_AC);
        assignId(buttonC,R.id.button_c);
        assignId(buttonBracClose,R.id.button_closed_bracket);
        assignId(buttonBrackOpen,R.id.button_open_bracket);
        assignId(buttonPlus,R.id.button_add);
        assignId(buttonMinus,R.id.button_subtract);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonDot,R.id.button_dot);
        assignId(buttonEquals,R.id.button_equal);


    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;

        //extract and disply text from buttons
        String buttonText = button.getText().toString();
//        solution_tv.setText(buttonText);
        String dataToCalculate = solution_tv.getText().toString();

        if (buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }

        if (buttonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }

        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        solution_tv.setText(dataToCalculate);

        //get final result
        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")){
            result_tv.setText(finalResult);
        }

    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data, "Javascript", 1, null).toString();
            //remove decimal
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }


}