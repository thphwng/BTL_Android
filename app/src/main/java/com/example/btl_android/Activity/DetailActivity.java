package com.example.btl_android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.btl_android.R;
import com.example.btl_android.databinding.ActivityDetailBinding;
import com.example.btl_android.model.Food;
import com.example.btl_android.utils.TinyDB;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    private Food object;
    private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        setVariable();
        handleCartIconClick();
        handleHomeIconClick();
    }

    private void handleHomeIconClick() {
        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleCartIconClick() {
        binding.giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    // Lấy dữ liệu món ăn từ Intent
    private void getIntentExtra() {
        Intent intent = getIntent();
        if (intent != null && intent.getSerializableExtra("object") != null) {
            object = (Food) intent.getSerializableExtra("object");
        }
    }

    private void setVariable() {
        if (object == null) return;

        // Nút quay lại
        binding.btnBack.setOnClickListener(v -> finish());

        // Hiển thị thông tin món ăn
        int imageResId = getResources().getIdentifier(object.getImage(), "drawable", getPackageName());
        Glide.with(this)
                .load(imageResId)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.pic1);

        binding.txtTitleChitiet.setText(object.getName());
        binding.txtDetail.setText(object.getDescription());
        binding.txtPrice.setText(object.getPrice() + " VND");
        binding.txtQuantity.setText(String.valueOf(num));
        binding.Tongtien.setText(num * object.getPrice() + " VND");
        binding.rattingbar.setRating(4); // Tạm set cứng rating

        // Tăng số lượng
        binding.btnPlus.setOnClickListener(v -> {
            num++;
            binding.txtQuantity.setText(String.valueOf(num));
            binding.Tongtien.setText(num * object.getPrice() + " VND");
        });

        // Giảm số lượng
        binding.btnMinus.setOnClickListener(v -> {
            if (num > 1) {
                num--;
                binding.txtQuantity.setText(String.valueOf(num));
                binding.Tongtien.setText(num * object.getPrice() + " VND");
            }
        });

        // Thêm vào giỏ
        binding.btnAdd.setOnClickListener(v -> {
            object.setQuantity(num); // Cập nhật số lượng

            TinyDB tinyDB = new TinyDB(DetailActivity.this);
            ArrayList<Food> cartList = tinyDB.getListObject("CART_LIST", Food.class);

            if (cartList == null) cartList = new ArrayList<>();

            // Kiểm tra nếu món đã tồn tại thì cộng dồn
            boolean found = false;
            for (Food f : cartList) {
                if (f.getId().equals(object.getId())) {
                    f.setQuantity(f.getQuantity() + num);
                    found = true;
                    break;
                }
            }
            if (!found) {
                cartList.add(object);
            }

            tinyDB.putListObject("CART_LIST", cartList);

            // Chuyển đến CartActivity
            Intent intent = new Intent(DetailActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
