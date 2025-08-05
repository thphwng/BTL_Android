package com.example.btl_android.view.auth;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_android.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edtPassword;
    private EditText edtPasswordAgain;
    private ImageButton btnShowPassword;
    private ImageButton btnShowPasswordAgain;
    private Button btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        initViews();
        setupListeners();
    }

    private void initViews() {
        edtPassword = findViewById(R.id.edtPassword);
        edtPasswordAgain = findViewById(R.id.edtPassword_again);
        btnShowPassword = findViewById(R.id.btn_showPassword);
        btnShowPasswordAgain = findViewById(R.id.btn_showPassword_again);
        btnComplete = findViewById(R.id.btnComplete);
    }

    private void setupListeners() {
        btnShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(edtPassword, btnShowPassword);
            }
        });

        btnShowPasswordAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility(edtPasswordAgain, btnShowPasswordAgain);
            }
        });

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegistrationCompletion();
            }
        });
    }

    private void togglePasswordVisibility(EditText editText, ImageButton imageButton) {
        if (editText.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            imageButton.setAlpha(1.0f);
        } else {
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            imageButton.setAlpha(0.3f);
        }
        editText.setSelection(editText.getText().length());
    }

    private void handleRegistrationCompletion() {
        String password = edtPassword.getText().toString();
        String confirmPassword = edtPasswordAgain.getText().toString();

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ mật khẩu và xác nhận mật khẩu.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu xác nhận không khớp.", Toast.LENGTH_SHORT).show();
            return;
        }


        Toast.makeText(this, "Hoàn tất!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}