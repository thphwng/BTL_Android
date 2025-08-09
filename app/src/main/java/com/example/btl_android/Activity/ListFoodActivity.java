package com.example.btl_android.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.btl_android.Adapter.FoodAdapter;
import com.example.btl_android.Adapter.ListFoodAdapter;
import com.example.btl_android.R;
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

        binding.foodList.setLayoutManager(new GridLayoutManager(this,2));
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
        allFoods.add(new Food("bm4", "Bánh Mì Thịt Nướng", "Bánh mì thịt nươớng đậm ương vị miền Trung.", 25000, "banhmi4", "Bánh mì"));
        allFoods.add(new Food("bm5", "Bánh Mì Heo quay", "Heo quay giòn rụm, nước sốt đặc biệt.", 30000, "banhmi3", "Bánh mì"));
        allFoods.add(new Food("bm6", "Bánh Mì cá", "Cá chiên giòn với rau sống và tương.", 22000, "banhmi", "Bánh mì"));
        allFoods.add(new Food("bm7", "Bánh Mì xíu mại", "Xíu mại cay nhẹ, nước sốt hấp dẫn.", 22000, "banhmi3", "Bánh mì"));
        allFoods.add(new Food("bm8", "Bánh Mì Gà Xé", "Gà xé sợi kèm bơ và hành phi.", 25000, "banhmi", "Bánh mì"));

        allFoods.add(new Food("bx1", "Bánh Xèo Miền Trung", "Bánh xèo nhỏ, giòn, nhân tôm thịt.", 30000, "banhxeo1", "Bánh xèo"));
        allFoods.add(new Food("bx2", "Bánh Xèo Miền Tây", "Bánh xèo to, vàng ươm, nhân đậu xanh.", 35000, "banhxeo3", "Bánh xèo"));
        allFoods.add(new Food("bx3", "Bánh Xèo Hải Sản", "Tôm, mực tươi cuộn trong bánh xèo giòn.", 45000, "banhxeo1", "Bánh xèo"));
        allFoods.add(new Food("bx4", "Bánh Xèo Miền Tây đặc biệt", "Tôm, mực tươi cuộn trong bánh xèo giòn.", 45000, "banhxeo1", "Bánh xèo"));

        allFoods.add(new Food("bc1", "Bún Chả Hà Nội", "Thịt nướng than hoa ăn kèm bún, rau sống.", 45000, "buncha1", "Bún chả"));
        allFoods.add(new Food("bc2", "Bún Chả Truyền Thống", "Thịt chả miếng & viên với nước mắm pha.", 42000, "buncha2", "Bún chả"));
        allFoods.add(new Food("bc3", "Bún Chả Nem Rán", "Kết hợp chả nướng và nem rán giòn rụm.", 48000, "buncha4", "Bún chả"));
        allFoods.add(new Food("bc4", "Bún Chả Đặc Biệt", "Thịt nướng, chả viên, nem rán đầy đủ.", 52000, "buncha4", "Bún chả"));
        allFoods.add(new Food("bc5", "Bún Chả Chay", "Chả chay từ nấm và đậu phụ, thanh đạm.", 40000, "buncha2", "Bún chả"));
        allFoods.add(new Food("bc6", "Bún Chả Thịt xiên", "Thịt xiên nướng than thơm nức.", 55000, "buncha4", "Bún chả"));
        allFoods.add(new Food("bc7", "Bún Chả Mắm Tôm", "Phiên bản đậm đà dành cho người sành ăn.", 52000, "buncha1", "Bún chả"));
        allFoods.add(new Food("bc8", "Bún Chả Heo Quay",  "Heo quay giòn rụm kết hợp hài hòa.", 58000, "buncha4", "Bún chả"));
        allFoods.add(new Food("bc9", "Bún Chả Sốt Me", "Chả nướng rưới sốt me chua ngọt.", 54000, "buncha4", "Bún chả"));
        allFoods.add(new Food("bc10", "Bún Chả Xá Xíu", "Thịt xiên nướng than thơm nức kèm thêm xá xíu ngọt ngọt.", 57000, "buncha4", "Bún chả"));

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
