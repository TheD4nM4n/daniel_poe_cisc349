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

    private void updateNum(int num)
    {

        // using this silly method of storing the integer value
        if (operator.isEmpty())
        {
            if (numOne < 0)
            {
                numOne = numOne * 10 - num;
            }
            else
            {
                numOne = numOne * 10 + num;
            }

            editTextExpression.setText(String.valueOf(numOne));

        }
        else
        {
            if (numTwo < 0)
            {
                numTwo = numTwo * 10 - num;
            }
            else
            {
                numTwo = numTwo * 10 + num;
            }

            editTextExpression.setText(String.valueOf(numTwo));
        }
    }

    private void updateOperator(String op)
    {
        // chain calculations
        if (!operator.isEmpty())
        {
            calculate(true);
        }

        operator = op;
        textViewNumOne.setText(String.valueOf(numOne));

        // highlighting selected operator
        switch (op)
        {
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

    private void calculate(boolean chain)
    {

        switch (operator)
        {
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

        // display answer, get ready for next operation
        editTextExpression.setText(String.valueOf(numOne));
        numTwo = 0;

        if (!chain)
        {
            updateOperator("");
        }

        textViewNumOne.setText("");
    }

    private boolean clear()
    {
        // clearing ints, edittext, and operator
        editTextExpression.setText("");
        numOne = 0;
        numTwo = numOne;
        updateOperator("");

        // now you know
        Toast.makeText(getApplicationContext(), "Cleared data", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        // the usual
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }
        );

        // local widgets
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

        Button buttonEquals = findViewById(R.id.buttonEquals);

        // private widgets
        textViewNumOne = findViewById(R.id.textViewNumOne);
        editTextExpression = findViewById(R.id.editTextExpression);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonDivide = findViewById(R.id.buttonDivide);

        // number button listeners
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

        // operator button listeners
        buttonPlus.setOnClickListener(v -> updateOperator("+"));
        buttonMinus.setOnClickListener(v -> updateOperator("-"));
        buttonDivide.setOnClickListener(v -> updateOperator("/"));

        // equal button listeners
        buttonEquals.setOnClickListener(v -> calculate(false));
        buttonEquals.setOnLongClickListener(v -> clear());
    }
}