package com.example.tiendavirtualuco.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tiendavirtualuco.R;

public class PaymentMethodActivity extends AppCompatActivity {

    private RadioGroup paymentMethodGroup;
    private Button confirmPaymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        // Inicializar las vistas
        paymentMethodGroup = findViewById(R.id.paymentMethodGroup);
        confirmPaymentButton = findViewById(R.id.button_confirm_payment);

        // Configurar listener para el botón de Confirmar Pago
        confirmPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes realizar validaciones si es necesario

                // Iniciar la actividad de pago exitoso
                Intent intent = new Intent(PaymentMethodActivity.this, SuccessfulPaymentActivity.class);
                startActivity(intent);

                // Opcional: Si quieres cerrar la actividad actual después de redirigir
                // finish();
            }
        });
    }
}
