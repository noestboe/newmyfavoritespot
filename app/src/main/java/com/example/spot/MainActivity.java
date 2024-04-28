package com.example.spot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.spot.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] typeList = {R.string.cienMontaditosType, R.string.museoPradoType, R.string.retiroType, R.string.plazaEspanaType, R.string.riuRooftopType, R.string.royalPalaceType, R.string.kapitalType};
        int[] descList = {R.string.cienMontaditosDesc, R.string.museoPradoDesc, R.string.retiroDesc, R.string.plazaEspanaDesc, R.string.riuRooftopDesc, R.string.royalPalaceDesc, R.string.kapitalDesc};

        String[] nameList = {"Cien Montaditos", "Museo Prado", "Retiro", "Plaza espana", "Riu rooftop","Royal Palace", "Kapital"};
        String[] ratingList = {"7/10", "7/10", "9/10","6/10", "9/10", "7/10", "7/10"};
        String [] cityList = {"Madrid", "Madrid", "Madrid", "Madrid", "Madrid", "Madrid", "Madrid"};

        for (int i = 0; i < nameList.length; i++){
            listData = new ListData(nameList[i], ratingList[i], cityList[i], typeList[i], descList[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListAdapter(MainActivity.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailedActivitySpot.class);
                intent.putExtra("name", nameList[i]);
                intent.putExtra("raiting", ratingList[i]);
                intent.putExtra("city", cityList[i]);
                intent.putExtra("type_spot", typeList[i]);
                intent.putExtra("desc", descList[i]);
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