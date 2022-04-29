package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String tempResult;

    public void zeroBtnClickHandler(View view) {
        this.updateText("0");
    }

    public void oneBtnClickHandler(View view) {
        this.updateText("1");
    }

    public void twoBtnClickHandler(View view) {
        this.updateText("2");
    }

    public void threeBtnClickHandler(View view) {
        this.updateText("3");
    }

    public void fourBtnClickHandler(View view) {
        this.updateText("4");
    }

    public void fiveBtnClickHandler(View view) {
        this.updateText("5");
    }

    public void sixBtnClickHandler(View view) {
        this.updateText("6");
    }

    public void sevenBtnClickHandler(View view) {
        this.updateText("7");
    }

    public void eightBtnClickHandler(View view) {
        this.updateText("8");
    }

    public void nineBtnClickHandler(View view) {
        this.updateText("9");
    }

    public void clearAllBtnClickHandler(View view) {
        this.display.setText("");
        this.tempResult = null;
    }

    public void backBtnClickHandler(View view) {
        this.clearOneCharacter();
    }

    public void equalBtnClickHandler(View view) {
        // calculate the result of the expression
        // clear the expression, set it to the
        // set the temp result to the current result for later usage.
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        Expression exp = new Expression(userExp);
        this.tempResult =  String.valueOf(exp.calculate());
        if (tempResult.equalsIgnoreCase("nan")) {
            this.display.setText("");
            tempResult = null;
            Toast.makeText(this, "The expression is not valid", Toast.LENGTH_SHORT).show();
            return;
        }
        this.display.setText(tempResult);
    }

    public void plusBtnClickHandler(View view) {
        this.updateText("+");
    }

    public void minusBtnClickHandler(View view) {
        this.updateText("-");
    }

    public void mulBtnClickHandler(View view) {
        this.updateText("×");
    }

    public void divBtnClickHandler(View view) {
        this.updateText("÷");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);
    }

    private void updateText(String strToAdd) {
        if (tempResult == null || tempResult.length() <= 0) {
            String oldDisplayString = display.getText().toString();
            String newString = oldDisplayString + strToAdd;
            display.setText(newString);
        } else if (strToAdd.matches("[+\\-×÷]")) {
            String oldDisplayString = display.getText().toString();
            String newString = oldDisplayString + strToAdd;
            display.setText(newString);
            tempResult = null;
        }
    }

    private void clearOneCharacter() {
        String currentString = display.getText().toString();
        if (currentString.length() <= 0) {
            this.tempResult = null;
            this.display.setText("");
            return;
        }
        StringBuilder builder = new StringBuilder(currentString);
        builder.deleteCharAt(builder.length() - 1);
        this.display.setText(builder.toString());
    }
}