package com.example.spot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.spot.databinding.ActivityDetailedSpotBinding;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailedActivitySpot extends AppCompatActivity {

    ActivityDetailedSpotBinding binding;
    private Button viewLocationButton;
    private String id;
    private Button deleteSpotButton;
    private double latitude;
    private double longitude;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedSpotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = FirebaseFirestore.getInstance();
        deleteSpotButton = findViewById(R.id.deleteSpotButton);

        Intent intent = this.getIntent();
        if (intent != null){
            this.id = intent.getStringExtra("id");
            String name = intent.getStringExtra("name");
            String raiting = intent.getStringExtra("raiting");
            String ingredients = intent.getStringExtra("type_spot");
            String city = intent.getStringExtra("city");
            String desc = intent.getStringExtra("desc");
            this.latitude = intent.getDoubleExtra("latitude", 0);
            this.longitude = intent.getDoubleExtra("longitude", 0);
            binding.detailName.setText(name);
            binding.detailedCity.setText(city);
            binding.detailTime.setText(raiting);
            binding.detailDesc.setText(desc);
            binding.detailIngredients.setText(ingredients);
        }
        viewLocationButton = findViewById(R.id.viewLocationButton);
        viewLocationButton.setOnClickListener(v -> goToMap());
        deleteSpotButton.setOnClickListener(v -> deleteSpot());

    }

    private void deleteSpot() {
        Log.d("delete", this.id.toString());
        db.collection("spots").document(this.id).delete();
        Intent intent = new Intent(DetailedActivitySpot.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void goToMap() {
        Intent intent = new Intent(DetailedActivitySpot.this, ViewLocationPage.class);
        intent.putExtra("latitude", this.latitude);
        intent.putExtra("longitude", this.longitude);
        startActivity(intent);
    }


}
