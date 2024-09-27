package com.example.tiendavirtualuco.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tiendavirtualuco.R;

public class AddPaymentMethodActivity extends AppCompatActivity {

    private EditText cardNumberEditText;
    private EditText cardHolderEditText;
    private EditText expiryDateEditText;
    private EditText cvvEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_method);

        // Inicializar vistas
        cardNumberEditText = findViewById(R.id.cardNumberEditText);
        cardHolderEditText = findViewById(R.id.cardHolderEditText);
        expiryDateEditText = findViewById(R.id.expiryDateEditText);
        cvvEditText = findViewById(R.id.cvvEditText);
        saveButton = findViewById(R.id.saveButton);

        // Configurar listener para el botón de guardar
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener valores de los campos
                String cardNumber = cardNumberEditText.getText().toString().trim();
                String cardHolder = cardHolderEditText.getText().toString().trim();
                String expiryDate = expiryDateEditText.getText().toString().trim();
                String cvv = cvvEditText.getText().toString().trim();

                // Validar los campos (puedes agregar más validaciones si es necesario)
                if (cardNumber.isEmpty() || cardHolder.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty()) {
                    Toast.makeText(AddPaymentMethodActivity.this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                    return;
                }


                // Mostrar mensaje de éxito y regresar a la pantalla anterior
                Toast.makeText(AddPaymentMethodActivity.this, "Método de pago añadido exitosamente.", Toast.LENGTH_SHORT).show();
                finish(); // Regresa a la pantalla anterior
            }
        });
    }
}
