package com.ram.leadboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ram.leadboard.adapter.TeamListAdapter;
import com.ram.leadboard.utils.PairTestImp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainActivityViewModel viewModel;
    private TeamListAdapter teamListAdapter;
    private Button btnStartgame;
    private List<String> listteam = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.team_recycler);
        btnStartgame = findViewById(R.id.btn_retry);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        teamListAdapter = new TeamListAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(teamListAdapter);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getListOfTeam().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> list) {
                listteam = list;
                teamListAdapter.setValue((ArrayList<String>) list);
            }
        });

        btnStartgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PairTestImp.isListEven(listteam)) {
                    Intent intent=new Intent(getApplicationContext(),MatchSimulateActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}