package com.example.btl_android.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.btl_android.Adapter.FoodAdapter;
import com.example.btl_android.databinding.ActivityListFoodBinding;
import com.example.btl_android.model.Food;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ListFoodActivity extends AppCompatActivity {
    ActivityListFoodBinding binding;
    ArrayList<Food> foodList;
    FoodAdapter adapter;

    private String categoryName, searchText;
    private boolean isSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        foodList = new ArrayList<>();
        adapter = new FoodAdapter(foodList);

        binding.foodList.setLayoutManager(new LinearLayoutManager(this));
        binding.foodList.setAdapter(adapter);

        getIntentExtra();
        initList();
        setupBackButton();
    }

    private void getIntentExtra() {
        categoryName = getIntent().getStringExtra("Category");
        searchText = getIntent().getStringExtra("SearchKeyword");
        isSearch = getIntent().getBooleanExtra("isSearch", false);

        if (isSearch && searchText != null) {
            binding.txtTitle.setText("Kết quả tìm kiếm: " + searchText);
        } else if (categoryName != null) {
            binding.txtTitle.setText("Danh mục: " + categoryName);
        } else {
            binding.txtTitle.setText("Danh sách món ăn");
        }
    }

    private void setupBackButton() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Quay lại màn hình trước
            }
        });
    }

    private void initList() {
        ArrayList<Food> allFoods = new ArrayList<>();

        // ===== Phở =====
        allFoods.add(new Food("pho2", "Phở Bò Chín", "Phở bò chín mềm thơm, chuẩn vị Hà Nội.", 48000, "pho2", "Phở"));
        allFoods.add(new Food("pho3", "Phở Gà", "Phở gà ta dai ngon, nước dùng trong.", 45000, "pho3", "Phở"));
        allFoods.add(new Food("pho4", "Phở Bò Viên", "Phở bò viên kèm rau sống đầy đủ.", 47000, "pho1", "Phở"));
        allFoods.add(new Food("pho5", "Phở Tái Gầu", "Tái gầu béo ngậy hòa quyện nước dùng.", 52000, "pho3", "Phở"));
        allFoods.add(new Food("pho6", "Phở Bò Kobe", "Thịt bò Kobe nhập khẩu cao cấp.", 150000, "pho2", "Phở"));
        allFoods.add(new Food("pho7", "Phở Tái Lăn", "Thịt bò tái xào thơm trước khi chan nước.", 55000, "pho3", "Phở"));
        allFoods.add(new Food("pho8", "Phở Trộn", "Phở trộn không nước, kèm rau và nước sốt.", 45000, "pho3", "Phở"));
        allFoods.add(new Food("pho9", "Phở Xào Bò", "Sợi phở xào cùng bò mềm đậm đà.", 52000, "pho1", "Phở"));
        allFoods.add(new Food("pho10", "Phở Cuốn", "Phở cuốn thịt bò rau sống chấm mắm nêm.", 40000, "phocuon", "Phở"));

        allFoods.add(new Food("bm1", "Bánh Mì Pate", "Bánh mì kèm pate béo ngậy, dưa leo, rau sống.", 20000, "banhmi", "Bánh mì"));
        allFoods.add(new Food("bm2", "Bánh Mì Trứng", "Bánh mì trứng chiên giòn, thêm tương ớt.", 18000, "banhmi2", "Bánh mì"));
        allFoods.add(new Food("bm3", "Bánh Mì Thịt", "Bánh mì kẹp thịt nguội, rau sống, dưa leo.", 22000, "banhmi3", "Bánh mì"));

        allFoods.add(new Food("bx1", "Bánh Xèo Miền Trung", "Bánh xèo nhỏ, giòn, nhân tôm thịt.", 30000, "banhxeo1", "Bánh xèo"));
        allFoods.add(new Food("bx2", "Bánh Xèo Miền Tây", "Bánh xèo to, vàng ươm, nhân đậu xanh.", 35000, "banhxeo2", "Bánh xèo"));
        allFoods.add(new Food("bx3", "Bánh Xèo Hải Sản", "Tôm, mực tươi cuộn trong bánh xèo giòn.", 45000, "hbanhxeo1", "Bánh xèo"));

        allFoods.add(new Food("bc1", "Bún Chả Hà Nội", "Thịt nướng than hoa ăn kèm bún, rau sống.", 45000, "buncha1", "Bún chả"));
        allFoods.add(new Food("bc2", "Bún Chả Truyền Thống", "Thịt chả miếng & viên với nước mắm pha.", 42000, "buncha2", "Bún chả"));
        allFoods.add(new Food("bc3", "Bún Chả Nem Rán", "Kết hợp chả nướng và nem rán giòn rụm.", 48000, "buncha4", "Bún chả"));
        allFoods.add(new Food("bc4", "Bún Chả Đặc Biệt", "Thịt nướng, chả viên, nem rán đầy đủ.", 52000, "buncha4", "Bún chả"));
        allFoods.add(new Food("bc5", "Bún Chả Chay", "Chả chay từ nấm và đậu phụ, thanh đạm.", 40000, "bunncha2", "Bún chả"));

        if (isSearch && searchText != null) {
            String keyword = removeDiacritics(searchText);
            for (Food food : allFoods) {
                if (removeDiacritics(food.getName()).contains(keyword)) {
                    foodList.add(food);
                }
            }
        } else if (categoryName != null) {
            for (Food food : allFoods) {
                if (food.getCategoryId().equalsIgnoreCase(categoryName)) {
                    foodList.add(food);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    private String removeDiacritics(String input) {
        if (input == null) return "";
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").toLowerCase();
    }
}
