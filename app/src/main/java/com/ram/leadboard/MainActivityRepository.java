package com.ram.leadboard;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.ram.leadboard.utils.CommonUtils;
import com.ram.leadboard.utils.PairData;
import com.ram.leadboard.utils.PairTestImp;

import java.util.ArrayList;
import java.util.List;

public class MainActivityRepository {

    Application mapplication;

    private MediatorLiveData<List<String>> livedatareturn=new MediatorLiveData<>();
    private MediatorLiveData<List<PairData<String, String>>> liveMatch=new MediatorLiveData<>();
    public MainActivityRepository(Application application){
        mapplication=application;

    }

    public LiveData<List<String>> getTeamList(){
        livedatareturn.postValue(CommonUtils.getListOfTeam());
        return livedatareturn;
    }

    public LiveData<List<PairData<String, String>>> getSimulateMatch(List<String> listOfTeam){
        liveMatch.postValue(PairTestImp.getPairData(listOfTeam));
        return liveMatch;
    }


}
