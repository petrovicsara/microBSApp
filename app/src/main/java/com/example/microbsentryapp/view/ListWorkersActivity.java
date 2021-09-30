package com.example.microbsentryapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.microbsentryapp.R;
import com.example.microbsentryapp.model.Zaposleni;
import com.example.microbsentryapp.viewModel.ListWorkersViewModel;

import java.util.ArrayList;

public class ListWorkersActivity extends AppCompatActivity {

    private RecyclerView rvWorkers;
    private ListWorkersAdapter listWorkersAdapter;
    private ListWorkersViewModel listWorkersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_workers);

        rvWorkers = findViewById(R.id.rvWorkers);
        rvWorkers.setHasFixedSize(true);
        rvWorkers.setLayoutManager(new LinearLayoutManager(this));

        //listWorkersAdapter = new ListWorkersAdapter();

        listWorkersViewModel = new ViewModelProvider(this).get(ListWorkersViewModel.class);
        listWorkersViewModel.init(ListWorkersActivity.this);

        listWorkersViewModel.getZaposleniLiveData().observe(this, new Observer<ArrayList<Zaposleni>>() {
            @Override
            public void onChanged(ArrayList<Zaposleni> zaposlenis) {

                listWorkersAdapter = new ListWorkersAdapter(ListWorkersActivity.this, zaposlenis);
                listWorkersAdapter.setZaposleniData(zaposlenis);
                listWorkersAdapter.notifyDataSetChanged();
            }
        });
    }
}