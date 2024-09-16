package com.example.tiendavirtualuco.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tiendavirtualuco.R;
import com.example.tiendavirtualuco.adapters.CartAdapter;
import com.example.tiendavirtualuco.models.Product;
import com.example.tiendavirtualuco.utils.PriceUtils;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<Product> cartProductList;
    private TextView totalPriceTextView;
    private TextView shippingCostTextView;
    private TextView totalWithShippingTextView;
    private TextView addAddressTextView;
    private Button saveButton;
    private Button proceedToPaymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Inicializar las vistas
        recyclerView = findViewById(R.id.recyclerViewCart);
        totalPriceTextView = findViewById(R.id.textTotalPriceValue);
        shippingCostTextView = findViewById(R.id.tvShippingCostInfo);
        totalWithShippingTextView = findViewById(R.id.tvTotalValue);
        addAddressTextView = findViewById(R.id.addAddressTextView);
        saveButton = findViewById(R.id.saveButton);
        proceedToPaymentButton = findViewById(R.id.proceedToPaymentButton); // Botón para proceder al pago

        // Configurar RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        cartProductList = new ArrayList<>();
        loadDummyProducts();

        // Configurar adaptador
        cartAdapter = new CartAdapter(cartProductList);
        recyclerView.setAdapter(cartAdapter);

        // Calcular el precio total y mostrarlo
        calculateTotalPrice();

        // Configurar listeners
        addAddressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en "Añadir nueva dirección"
                Intent intent = new Intent(CartActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en "Guardar"

            }
        });

        proceedToPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en "Encargar y Pagar"
                Intent intent = new Intent(CartActivity.this, PaymentMethodActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadDummyProducts() {
        // Cargar productos ficticios
        cartProductList.add(new Product("Portatil Azus Tuf", 2500, 50, 1, true, R.drawable.product_image1));
        cartProductList.add(new Product("Playstation 5 slim Disco", 1500, 30, 2, true, R.drawable.product_image2));
    }

    private void calculateTotalPrice() {
        // Utilizar PriceUtils para calcular los valores
        double totalPrice = PriceUtils.calculateTotalPrice(cartProductList);
        double shippingCost = PriceUtils.calculateTotalShippingCost(cartProductList);
        double totalWithShipping = PriceUtils.calculateTotalWithShipping(cartProductList);

        // Mostrar los resultados en las vistas correspondientes
        totalPriceTextView.setText(String.format("$%.2f", totalPrice));
        shippingCostTextView.setText(shippingCost == 0 ? "Envío Gratis" : String.format("$%.2f", shippingCost));
        totalWithShippingTextView.setText(String.format("$%.2f", totalWithShipping));
    }
}
