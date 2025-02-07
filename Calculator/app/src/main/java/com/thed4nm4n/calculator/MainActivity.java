package com.thed4nm4n.calculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editTextExpression;

    private Button buttonPlus;
    private Button buttonMinus;

    private Button buttonDivide;
    private int numOne;
    private int numTwo;
    private TextView textViewNumOne;
    private String operator = "";

    private void updateNum(int num) {
        if (operator.isEmpty())
        {
            numOne = numOne * 10 + num;
            editTextExpression.setText(String.valueOf(numOne));
        } else
        {
            numTwo = numTwo * 10 + num;
            editTextExpression.setText(String.valueOf(numTwo));
        }
    }

    private void updateOperator(String op) {
        operator = op;
        textViewNumOne.setText(String.valueOf(numOne));

        switch (op) {
            case "+":
                buttonPlus.setBackgroundColor(getColor(R.color.button_selected));
                buttonMinus.setBackgroundColor(getColor(R.color.button_default));
                buttonDivide.setBackgroundColor(getColor(R.color.button_default));
                break;
            case "-":
                buttonPlus.setBackgroundColor(getColor(R.color.button_default));
                buttonMinus.setBackgroundColor(getColor(R.color.button_selected));
                buttonDivide.setBackgroundColor(getColor(R.color.button_default));
                break;
            case "/":
                buttonPlus.setBackgroundColor(getColor(R.color.button_default));
                buttonMinus.setBackgroundColor(getColor(R.color.button_default));
                buttonDivide.setBackgroundColor(getColor(R.color.button_selected));
                break;
            case "":
                buttonPlus.setBackgroundColor(getColor(R.color.button_default));
                buttonMinus.setBackgroundColor(getColor(R.color.button_default));
                buttonDivide.setBackgroundColor(getColor(R.color.button_default));
                break;
        }
    }

    private void calculate() {
        switch (operator) {
            case "+":
                numOne = numOne + numTwo;
                break;
            case "-":
                numOne = numOne - numTwo;
                break;
            case "/":
                numOne = numOne / numTwo;
                break;
        }

        editTextExpression.setText(String.valueOf(numOne));
        numTwo = 0;
        updateOperator("");
        textViewNumOne.setText("");
    }

    private void clear() {
        editTextExpression.setText("");
        numOne = 0;
        numTwo = numOne;
        updateOperator("");

        Toast.makeText(getApplicationContext(), "Cleared data", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewNumOne = findViewById(R.id.textViewNumOne);
        editTextExpression = findViewById(R.id.editTextExpression);
        Button buttonOne = findViewById(R.id.buttonOne);
        Button buttonTwo = findViewById(R.id.buttonTwo);
        Button buttonThree = findViewById(R.id.buttonThree);
        Button buttonFour = findViewById(R.id.buttonFour);
        Button buttonFive = findViewById(R.id.buttonFive);
        Button buttonSix = findViewById(R.id.buttonSix);
        Button buttonSeven = findViewById(R.id.buttonSeven);
        Button buttonEight = findViewById(R.id.buttonEight);
        Button buttonNine = findViewById(R.id.buttonNine);
        Button buttonZero = findViewById(R.id.buttonZero);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonEquals = findViewById(R.id.buttonEquals);

        buttonOne.setOnClickListener(v -> updateNum(1));
        buttonTwo.setOnClickListener(v -> updateNum(2));
        buttonThree.setOnClickListener(v -> updateNum(3));
        buttonFour.setOnClickListener(v -> updateNum(4));
        buttonFive.setOnClickListener(v -> updateNum(5));
        buttonSix.setOnClickListener(v -> updateNum(6));
        buttonSeven.setOnClickListener(v -> updateNum(7));
        buttonEight.setOnClickListener(v -> updateNum(8));
        buttonNine.setOnClickListener(v -> updateNum(9));
        buttonZero.setOnClickListener(v -> updateNum(0));

        buttonPlus.setOnClickListener(v -> updateOperator("+"));
        buttonMinus.setOnClickListener(v -> updateOperator("-"));
        buttonDivide.setOnClickListener(v -> updateOperator("/"));
        buttonEquals.setOnClickListener(v -> calculate());
        buttonEquals.setOnLongClickListener(v ->
        {
            clear();
            return true;
        });
    }
}