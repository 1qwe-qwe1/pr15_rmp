package com.example.pr15_rmp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView ivEmotionCalm;
    private ImageView ivEmotionRelaxed;
    private ImageView ivEmotionFocused;
    private ImageView ivEmotionExcited;

    private ImageView[] emotionIcons;
    private String[] emotionNames;
    private int selectedEmotionIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivEmotionCalm = findViewById(R.id.ivEmotionCalm);
        ivEmotionRelaxed = findViewById(R.id.ivEmotionRelaxed);
        ivEmotionFocused = findViewById(R.id.ivEmotionFocused);
        ivEmotionExcited = findViewById(R.id.ivEmotionExcited);

        emotionIcons = new ImageView[]{ivEmotionCalm, ivEmotionRelaxed, ivEmotionFocused, ivEmotionExcited};
        emotionNames = new String[]{"Спокойствие", "Расслабленность", "Сосредоточенность", "Возбуждение"};

        ivEmotionCalm.setOnClickListener(v -> selectEmotion(0));
        ivEmotionRelaxed.setOnClickListener(v -> selectEmotion(1));
        ivEmotionFocused.setOnClickListener(v -> selectEmotion(2));
        ivEmotionExcited.setOnClickListener(v -> selectEmotion(3));

        // Переход в профиль
        ImageButton btnNavProfile = findViewById(R.id.btnNavProfile);
        btnNavProfile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        // Переход к меню
        ImageView ivMenuMain = findViewById(R.id.ivMenuMain);
        ivMenuMain.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        // Переход к прослушиванию
        ImageButton btnNavMusic = findViewById(R.id.btnNavMusic);
        btnNavMusic.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListenActivity.class);
            startActivity(intent);
        });
    }

    private void selectEmotion(int selectedIndex) {
        Toast.makeText(this, "Выбрано: " + emotionNames[selectedIndex], Toast.LENGTH_SHORT).show();

        if (selectedEmotionIndex == selectedIndex) {
            emotionIcons[selectedIndex].setAlpha(1.0f);
            selectedEmotionIndex = -1;
            return;
        }

        if (selectedEmotionIndex != -1) {
            emotionIcons[selectedEmotionIndex].setAlpha(1.0f);
        }

        emotionIcons[selectedIndex].setAlpha(0.5f);
        selectedEmotionIndex = selectedIndex;
    }
}