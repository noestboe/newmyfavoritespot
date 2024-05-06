package com.example.spot;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddSpotPage extends AppCompatActivity {
    private FirebaseFirestore db;
    private EditText nameOfSpotEditText;
    private EditText typeOfSpotEditText;
    private EditText ratingEditText;
    private EditText descriptionEditText;
    private EditText cityEditText;
    private double latitue;
    private double longitude;
    private TextView locationName;
    private Button createSpotButton;
    private Button addLocationButton;

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //result
                    Log.d("onactivityresult", "her burde komme noe");
                    if (result.getResultCode() == 78) {
                        Intent intent = result.getData();

                        if (intent != null) {
                            double latitue = intent.getDoubleExtra("selected_latitude", 0);
                            double longitude = intent.getDoubleExtra("selected_longitude", 0);
                            String locationName = intent.getStringExtra("selected_locationName");
                            Log.d("latitue", String.valueOf(latitue));
                            Log.d("longitude", String.valueOf(longitude));
                            Log.d("locationName", locationName);
                            AddSpotPage.this.latitue = latitue;
                            AddSpotPage.this.longitude = longitude;
                            AddSpotPage.this.locationName.setText("Selected location: "+locationName);
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spot_page);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();

        // Find views by their IDs
        nameOfSpotEditText = findViewById(R.id.name_of_spot);
        typeOfSpotEditText = findViewById(R.id.type_of_spot);
        ratingEditText = findViewById(R.id.rating);
        descriptionEditText = findViewById(R.id.description);
        cityEditText = findViewById(R.id.city);
        createSpotButton = findViewById(R.id.createSpotButton);
        addLocationButton = findViewById(R.id.addLocationButton);
        locationName = findViewById(R.id.locationName);

        // Set OnClickListener for the button
        createSpotButton.setOnClickListener(v -> createSpot());
        addLocationButton.setOnClickListener(v -> goToMap());

    }

    private void goToMap() {
        Intent intent = new Intent(AddSpotPage.this, Locationpage.class);
        activityLauncher.launch(intent);
    }

    private void createSpot() {
        // Get text from EditText fields
        String nameOfSpot = nameOfSpotEditText.getText().toString();
        String typeOfSpot = typeOfSpotEditText.getText().toString();
        String rating = ratingEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String city = cityEditText.getText().toString();

        // Create a Map to store the spot details
        Map<String, Object> spot = new HashMap<>();
        spot.put("name_of_spot", nameOfSpot);
        spot.put("type_of_spot", typeOfSpot);
        spot.put("rating", rating);
        spot.put("description", description);
        spot.put("city", city);
        spot.put("latitude", this.latitue);
        spot.put("longitude", this.longitude);
        spot.put("locationName", this.locationName.getText().toString());

        // Add the spot to Firestore
        db.collection("spots").add(spot)
                .addOnSuccessListener(documentReference -> {
                    Log.d("AddSpotPage", "Spot added successfully");
                    // Navigate back to the main activity
                    Intent intent = new Intent(AddSpotPage.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish(); // Finish the current activity
                })
                .addOnFailureListener(e -> {
                    Log.d("AddSpotPage", "Spot failed to be added");
                });
    }
}
