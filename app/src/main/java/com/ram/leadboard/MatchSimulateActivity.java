package com.ram.leadboard;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.ram.leadboard.adapter.MatchListAdapter;
import com.ram.leadboard.adapter.TeamListAdapter;
import com.ram.leadboard.utils.CommonUtils;
import com.ram.leadboard.utils.PairData;
import com.ram.leadboard.utils.PairTestImp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MatchSimulateActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MainActivityViewModel viewModel;
    private MatchListAdapter teamListAdapter;
    private Button btnSimulategame;
    private List<PairData<String, String>> listOfpair;
    private List<String> listOfTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_match_simulate);
       /* Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        recyclerView = findViewById(R.id.team_recycler);
        btnSimulategame=findViewById(R.id.btn_retry);
        btnSimulategame.setText("Play");
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        listOfpair=new ArrayList<>();
        listOfTeam=new ArrayList<>();
        teamListAdapter = new MatchListAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(teamListAdapter);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        listOfTeam= CommonUtils.getListOfTeam();
        teamListAdapter.setResult(false);
       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        viewModel.updateTeamList(listOfTeam);
        updateList();
        btnSimulategame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // btnSimulategame.setText("Simulate");
                String buttonText = ((Button) v).getText().toString();
                if (buttonText.equals("Play")){
                    btnSimulategame.setText("Simulate");
                    teamListAdapter.setResult(true);
                    /*viewModel.updateTeamList(listOfTeam);
                    updateList();*/
                }else if (buttonText.equalsIgnoreCase("Simulate")){

                    
                    if (listOfpair.size()==1){
                        btnSimulategame.setText("Restart");
                        teamListAdapter.setResult(false);
                    }else {
                        listOfTeam=PairTestImp.getResultBySimulate((ArrayList<PairData<String, String>>) listOfpair);
                        teamListAdapter.setResult(true);
                        if (PairTestImp.isListEven(listOfTeam)){
                            viewModel.updateTeamList(listOfTeam);
                            updateList();
                        }else {
                            Toast.makeText(getApplicationContext(),"Your function should accept any number n, where n is an even number > 0 and return you n/2\n" +
                                    "pairs/tuples",Toast.LENGTH_LONG).show();
                        }
                    }
                }else if (buttonText.equalsIgnoreCase("Restart")){
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }



            }
        });
    }

    private void updateList() {

        viewModel.getSimulateMatch().observe(this, new Observer<List<PairData<String, String>>>() {
            @Override
            public void onChanged(List<PairData<String, String>> pairData) {
                //Toast.makeText(getApplicationContext(),"CAll",Toast.LENGTH_LONG).show();
                listOfpair=pairData;
                teamListAdapter.setValue((ArrayList<PairData<String, String>>) listOfpair);
                if (listOfpair.size()==1){
                    btnSimulategame.setText("Restart");
                }
            }
        });
    }
}