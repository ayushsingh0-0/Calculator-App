package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtResult, txtInput;
    Button btnC, btnPer, btnBack, btnDiv, btn7, btn8, btn9, btnMul, btn4, btn5, btn6, btnSub, btn1, btn2, btn3, btnSum, btn00, btn0, btnDot, btnEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);
        txtInput = findViewById(R.id.txtInput);
        assignValues(btn0, R.id.button0);
        assignValues(btn00, R.id.button00);
        assignValues(btn1, R.id.button1);
        assignValues(btn2, R.id.button2);
        assignValues(btn3, R.id.button3);
        assignValues(btn4, R.id.button4);
        assignValues(btn5, R.id.button5);
        assignValues(btn6, R.id.button6);
        assignValues(btn7, R.id.button7);
        assignValues(btn8, R.id.button8);
        assignValues(btn9, R.id.button9);
        assignValues(btnC, R.id.buttonC);
        assignValues(btnPer, R.id.buttonPer);
        assignValues(btnBack, R.id.buttonBack);
        assignValues(btnDiv, R.id.buttonDiv);
        assignValues(btnMul, R.id.buttonMul);
        assignValues(btnSub, R.id.buttonSub);
        assignValues(btnSum, R.id.buttonSum);
        assignValues(btnDot, R.id.buttonDot);
        assignValues(btnEqual, R.id.buttonEqual);

    }

    public void assignValues(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String txtBtn = btn.getText().toString();
        String txtOldValue = txtInput.getText().toString();

        if (txtBtn.equals("C")) {
            txtResult.setText("0");
            txtInput.setText("");
            return;
        }
        if (txtBtn.equals("=")) {
            txtInput.setText(txtResult.getText());
            return;
        }
        if (txtBtn.equals("⌫")) {
            if(txtOldValue.length()==0){
                txtOldValue = "";
                txtResult.setText("");
            }else {
                txtOldValue = txtOldValue.substring(0, txtOldValue.length() - 1);
            }
        } else {
            txtOldValue += txtBtn;
        }
        txtInput.setText(txtOldValue);

        String finalValue = getResult(txtOldValue);
        if (!finalValue.equals("Invalid")) {
            txtResult.setText(finalValue);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String calculatedValue = context.evaluateString(scriptable, data, "JavaScript", 1, null).toString();
            if (calculatedValue.endsWith(".0")) {
                calculatedValue = calculatedValue.replace(".0", "");
            }
            return calculatedValue;
        } catch (Exception e) {
            return "Invalid";
        }
    }
}