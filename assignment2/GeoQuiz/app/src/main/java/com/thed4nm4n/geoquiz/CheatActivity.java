package com.thed4nm4n.geoquiz;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.thed4nm4n.geoquiz.answer_is_true";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = findViewById(R.id.answer_text_view);
        mShowAnswer = findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(v -> {
            if (mAnswerIsTrue) {
                mAnswerTextView.setText((R.string.true_button));
            } else {
                mAnswerTextView.setText(R.string.false_button);
            }
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result", "Hello from CheatActivity");
            setResult(CheatActivity.RESULT_OK, returnIntent);
            finish();
        });
    }
}