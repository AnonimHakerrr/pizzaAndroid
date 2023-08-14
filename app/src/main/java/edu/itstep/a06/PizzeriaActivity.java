package edu.itstep.a06;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PizzeriaActivity extends AppCompatActivity {

    private double totalPrice = 0.00;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzeria);

        final TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
        final CheckBox cheeseCheckbox = findViewById(R.id.cheeseCheckbox);
        final CheckBox pepperoniCheckbox = findViewById(R.id.pepperoniCheckbox);
        RadioGroup sizeRadioGroup = findViewById(R.id.sizeRadioGroup);
        Button nextButton = findViewById(R.id.nextButton);

        CompoundButton.OnCheckedChangeListener checkboxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    totalPrice += 2.50;
                } else {
                    totalPrice -= 2.50;
                }
                totalPriceTextView.setText("Total: $" + String.format("%.2f", totalPrice));
            }
        };

        cheeseCheckbox.setOnCheckedChangeListener(checkboxListener);
        pepperoniCheckbox.setOnCheckedChangeListener(checkboxListener);

        sizeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.smallRadio:
                        totalPrice = 10.00;
                        break;
                    case R.id.mediumRadio:
                        totalPrice = 15.00;
                        break;
                    case R.id.largeRadio:
                        totalPrice = 20.00;
                        break;
                }
                totalPriceTextView.setText("Total: $" + String.format("%.2f", totalPrice));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(PizzeriaActivity.this, ResultActivity.class);
                resultIntent.putExtra("totalPrice", totalPrice);
                startActivityForResult(resultIntent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                boolean changeCheese = data.getBooleanExtra("changeCheese", false);
                boolean changePepperoni = data.getBooleanExtra("changePepperoni", false);

                if (changeCheese) {
                    totalPrice += 2.50;
                }

                if (changePepperoni) {
                    totalPrice += 2.50;
                }

                TextView totalPriceTextView = findViewById(R.id.totalPriceTextView);
                totalPriceTextView.setText("Total: $" + String.format("%.2f", totalPrice));
            }
        }
    }
}
