package com.example.btl_android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.btl_android.Adapter.PlaceOrderAdapter;
import com.example.btl_android.databinding.ActivityPlaceOrderBinding;
import com.example.btl_android.model.Food;
import com.example.btl_android.utils.TinyDB;

import java.util.ArrayList;

public class PlaceOrderActivity extends AppCompatActivity {

    ActivityPlaceOrderBinding binding;
    TinyDB tinyDB;
    ArrayList<Food> cartList;
    PlaceOrderAdapter adapter;

    int totalAmount = 0;
    int shippingFee = 15000;
    int tax = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tinyDB = new TinyDB(this);
        cartList = tinyDB.getListObject("CART_LIST", Food.class);

        if (cartList == null || cartList.isEmpty()) {
            Toast.makeText(this, "Không có sản phẩm để đặt hàng", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        setupCartList();
        calculateTotal();
        handlePlaceOrder();
    }

    private void setupCartList() {
        adapter = new PlaceOrderAdapter(this, cartList);
        binding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewCart.setAdapter(adapter);
    }

    private void calculateTotal() {
        totalAmount = 0;
        for (Food food : cartList) {
            totalAmount += food.getPrice() * food.getQuantity();
        }

        binding.tvSubtotal.setText(totalAmount + " VND");
        binding.tvShipping.setText(shippingFee + "VND");
        binding.tvTax.setText(0 + " VND");
        binding.tvTotal.setText((totalAmount + shippingFee) + " VND");
    }

    private void handlePlaceOrder() {
        binding.btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtName.getText().toString().trim();
                String address = binding.edtAddress.getText().toString().trim();
              //  String payment = binding.edtPayment.getText().toString().trim();
                String coupon = binding.edtCoupon.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) ) {
                    Toast.makeText(PlaceOrderActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Xử lý đơn hàng ở đây nếu cần (gửi Firebase, lưu DB...)

                Toast.makeText(PlaceOrderActivity.this, "Đặt hàng thành công!", Toast.LENGTH_LONG).show();
                tinyDB.remove("CART_LIST");

                Intent intent = new Intent(PlaceOrderActivity.this, ThankYouActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
