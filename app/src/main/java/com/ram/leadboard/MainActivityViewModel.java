package com.ram.leadboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ram.leadboard.utils.PairData;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    MainActivityRepository repository;
    private List<String> list=new ArrayList<>();
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository=new MainActivityRepository(application);
    }

    public LiveData<List<String>> getListOfTeam(){
        return repository.getTeamList();
    }
    public void updateTeamList(List<String> listOfTeam){
        list=listOfTeam;
    }
    public LiveData<List<PairData<String, String>>> getSimulateMatch(){

         return repository.getSimulateMatch(list);
    }
}
