package edu.itstep.a06;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final CheckBox changeCheeseCheckbox = findViewById(R.id.changeCheeseCheckbox);
        final CheckBox changePepperoniCheckbox = findViewById(R.id.changePepperoniCheckbox);
        Button changeButton = findViewById(R.id.changeButton);

        double totalPrice = getIntent().getDoubleExtra("totalPrice", 0.00);
        TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        totalPriceTextView.setText("Total: $" + String.format("%.2f", totalPrice));

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("changeCheese", changeCheeseCheckbox.isChecked());
                resultIntent.putExtra("changePepperoni", changePepperoniCheckbox.isChecked());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
