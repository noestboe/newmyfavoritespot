package com.example.spot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.spot.databinding.ActivityMainBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ListAdapter listAdapter;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ListView listview = findViewById(R.id.listview);
        db = FirebaseFirestore.getInstance();


        db.collection("spots").get().addOnCompleteListener(task -> {
            ArrayList<ListData> spotList = new ArrayList<>();
            for (QueryDocumentSnapshot document : task.getResult()) {
                String id = document.getId();
                String nameOfSpot = document.getData().get("name_of_spot").toString();
                String typeOfSpot = document.getData().get("type_of_spot").toString();
                String rating = document.getData().get("rating").toString();
                String city = document.getData().get("city").toString();
                String description = document.getData().get("description").toString();
                double latitude = (double) document.getData().get("latitude");
                double longitude = (double) document.getData().get("longitude");
                spotList.add(new ListData(id, nameOfSpot, rating, city, typeOfSpot, description, latitude, longitude));
            }
            listAdapter = new ListAdapter(MainActivity.this, spotList);
            binding.listview.setAdapter(listAdapter);
        });
        binding.listview.setClickable(true);


        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListData selectedItem = (ListData) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DetailedActivitySpot.class);
                intent.putExtra("id", selectedItem.id);
                intent.putExtra("name", selectedItem.name);
                intent.putExtra("raiting", selectedItem.raiting);
                intent.putExtra("city", selectedItem.city);
                intent.putExtra("type_spot", selectedItem.spot_type);
                intent.putExtra("desc", selectedItem.desc);
                intent.putExtra("latitude", selectedItem.latitude);
                intent.putExtra("longitude", selectedItem.longitude);
                startActivity(intent);
            }
        });

        Button btn = (Button)findViewById(R.id.add_spot_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddSpotPage.class));
            }
        });

    }
}