package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btn00;
Button btnPer,btnAdd,btnSub,btnMul,btnDiv,btnEqual,btnPoint;
Button btnCut,btnClear;
TextView txtValue, txtResult;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtValue= findViewById(R.id.txtValue); txtResult = findViewById(R.id.txtResult);
        assignID(btn1,R.id.btn1); assignID(btn2,R.id.btn2); assignID(btn3,R.id.btn3); assignID(btn4,R.id.btn4); assignID(btn5,R.id.btn5); assignID(btn6,R.id.btn6);assignID(btn7,R.id.btn7); assignID(btn8,R.id.btn8); assignID(btn9,R.id.btn9); assignID(btn0,R.id.btn0); assignID(btn00,R.id.btn00); assignID(btnPer,R.id.btnPer); assignID(btnAdd,R.id.btnAdd); assignID(btnSub,R.id.btnSub); assignID(btnMul,R.id.btnMul); assignID(btnDiv,R.id.btnDiv); assignID(btnEqual,R.id.btnEqual); assignID(btnPoint,R.id.btnPoint); assignID(btnCut,R.id.btnCut); assignID(btnClear,R.id.btnClear);

    }

    void assignID(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = txtValue.getText().toString();

        if(buttonText.equals("AC")){
            txtValue.setText("");
            txtResult.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            txtValue.setText(txtResult.getText());
            return;
        }
        if(dataToCalculate.equals("") && buttonText.equals("C")){
            txtValue.setText("");
            txtResult.setText("0");
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        } else{
            dataToCalculate = dataToCalculate + buttonText;
        }

        txtValue.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            txtResult.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e){
            return "Err";
        }

    }
}
