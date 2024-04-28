package com.example.spot;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.spot.databinding.ActivityDetailedSpotBinding;

public class DetailedActivitySpot extends AppCompatActivity {

    ActivityDetailedSpotBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedSpotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = this.getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            String raiting = intent.getStringExtra("raiting");
            int ingredients = intent.getIntExtra("ingredients", R.string.cienMontaditosType);
            int desc = intent.getIntExtra("desc", R.string.cienMontaditosDesc);
            binding.detailName.setText(name);
            binding.detailTime.setText(raiting);
            binding.detailDesc.setText(desc);
            binding.detailIngredients.setText(ingredients);
        }
    }
}
