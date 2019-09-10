package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDiv,
            buttonMul, buttonC, buttonEq;

    TextView calcEditText;

    int mValueOne, mValueTwo;

    boolean calcAddition, mSubtract, calcMultiplication, calcDivision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSub);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv= findViewById(R.id.buttonDiv);
        buttonC = findViewById(R.id.buttonClear);
        buttonEq= findViewById(R.id.buttonEq);
        calcEditText = findViewById(R.id.editText);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcEditText.setText(calcEditText.getText() + "0");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcEditText.setText(calcEditText.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcEditText.setText(calcEditText.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcEditText.setText(calcEditText.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcEditText.setText(calcEditText.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcEditText.setText(calcEditText.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcEditText.setText(calcEditText.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcEditText.setText(calcEditText.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcEditText.setText(calcEditText.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcEditText.setText(calcEditText.getText() + "9");
            }
        });



        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (calcEditText == null) {
                    calcEditText.setText("");
                } else {
                    mValueOne = Integer.parseInt(calcEditText.getText() + "");
                    calcAddition = true;
                    calcEditText.setText(null);
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Integer.parseInt(calcEditText.getText() + "");
                mSubtract = true;
                calcEditText.setText(null);
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Integer.parseInt(calcEditText.getText() + "");
                calcMultiplication = true;
                calcEditText.setText(null);
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Integer.parseInt(calcEditText.getText() + "");
                calcDivision = true;
                calcEditText.setText(null);
            }
        });

        buttonEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueTwo = Integer.parseInt(calcEditText.getText() + "");

                if (calcAddition) {
                    calcEditText.setText(mValueOne + mValueTwo + "");
                    calcAddition = false;
                }

                if (mSubtract) {
                    calcEditText.setText(mValueOne - mValueTwo + "");
                    mSubtract = false;
                }

                if (calcMultiplication) {
                    calcEditText.setText(mValueOne * mValueTwo + "");
                    calcMultiplication = false;
                }

                if (calcDivision) {
                    calcEditText.setText(mValueOne / mValueTwo + "");
                    calcDivision = false;
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcEditText.setText("");
            }
        });
    }
}
