package com.example.py5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class DetailActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editTextModel;
    private Button buttonAdd;
    private ArrayList<String> models = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        listView = findViewById(R.id.listViewDetail);
        editTextModel = findViewById(R.id.editTextModel);
        buttonAdd = findViewById(R.id.buttonAdd);

        Intent intent = getIntent();
        if (intent != null) {
            String brand = intent.getStringExtra("brand");
            switch (brand) {
                case "Samsung":
                    models.addAll(Arrays.asList("Samsung Galaxy S21", "Samsung Galaxy Note 20", "Samsung Galaxy Z Flip"));
                    break;
                case "iPhone":
                    models.addAll(Arrays.asList("iPhone 13", "iPhone 12", "iPhone 11"));
                    break;
                case "Nokia":
                    models.addAll(Arrays.asList("Nokia 8.3", "Nokia 7.2", "Nokia 3310"));
                    break;
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, models);
        listView.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String model = editTextModel.getText().toString();
                if (!model.isEmpty()) {
                    models.add(model);
                    adapter.notifyDataSetChanged();
                    editTextModel.setText("");
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                models.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
