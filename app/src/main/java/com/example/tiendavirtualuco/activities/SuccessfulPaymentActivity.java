package com.example.tiendavirtualuco.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tiendavirtualuco.R;

public class SuccessfulPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_payment);

        // Inicializar el botón de regreso
        ImageButton backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Regresar a la actividad de carrito
                Intent intent = new Intent(SuccessfulPaymentActivity.this, CartActivity.class);
                startActivity(intent);


            }
        });
    }
}
