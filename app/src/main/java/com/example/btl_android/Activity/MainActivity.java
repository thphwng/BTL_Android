package com.example.btl_android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.btl_android.Adapter.FoodAdapter;
import com.example.btl_android.R;
import com.example.btl_android.databinding.ActivityMainBinding;
import com.example.btl_android.model.Food;

import java.util.ArrayList;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        // --- Khởi tạo slider ảnh ---
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.pho2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.phocuon, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.buncha2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banhmi, ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
// mở yêu thích
        binding.btnDsfavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });

        binding.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        initRecyclerView();
        handleSearchClick();
        handleCartIconClick();
        handleCategoryClicks(); // Xử lý click danh mục
    }

    private void handleCartIconClick() {
        binding.imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView() {
        ArrayList<Food> items = new ArrayList<>();
        items.add(new Food("pho1", "Phở Bò Tái", "Phở bò tái với nước dùng truyền thống.", 50000, "pho1", "Phở"));
        items.add(new Food("pho2", "Phở Bò Chín", "Phở bò chín mềm thơm, chuẩn vị Hà Nội.", 48000, "pho2", "Phở"));
        items.add(new Food("pho3", "Phở Gà", "Phở gà ta dai ngon, nước dùng trong.", 45000, "pho3", "Phở"));
        items.add(new Food("pho4", "Phở Bò Viên", "Phở bò viên kèm rau sống đầy đủ.", 47000, "pho1", "Phở"));
        items.add(new Food("pho5", "Phở Tái Gầu", "Tái gầu béo ngậy hòa quyện nước dùng.", 52000, "pho3", "Phở"));
        items.add(new Food("pho6", "Phở Bò Kobe", "Thịt bò Kobe nhập khẩu cao cấp.", 150000, "pho2", "Phở"));
        items.add(new Food("pho7", "Phở Tái Lăn", "Thịt bò tái xào thơm trước khi chan nước.", 55000, "pho3", "Phở"));
        items.add(new Food("pho8", "Phở Trộn", "Phở trộn không nước, kèm rau và nước sốt.", 45000, "pho3", "Phở"));

        binding.PopularView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.PopularView.setAdapter(new FoodAdapter(items));
    }

    private void handleSearchClick() {
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = binding.edtSearch.getText().toString().trim();

                if (!keyword.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, ListFoodActivity.class);
                    intent.putExtra("SearchKeyword", keyword);
                    intent.putExtra("isSearch", true); // Tìm kiếm
                    startActivity(intent);
                } else {
                    binding.edtSearch.setError("Vui lòng nhập từ khóa");
                }
            }
        });
    }

    private void handleCategoryClicks() {
        // Xử lý click vào từng ảnh danh mục món ăn
        binding.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListFoodByCategory("Phở");
            }
        });

        binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListFoodByCategory("Bánh mì");
            }
        });

        binding.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListFoodByCategory("Bún chả");
            }
        });

        binding.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListFoodByCategory("Bánh xèo");
            }
        });
    }

    private void openListFoodByCategory(String category) {
        Intent intent = new Intent(MainActivity.this, ListFoodActivity.class);
        intent.putExtra("Category", category);
        intent.putExtra("isSearch", false); // Không phải tìm kiếm
        startActivity(intent);
    }
}
