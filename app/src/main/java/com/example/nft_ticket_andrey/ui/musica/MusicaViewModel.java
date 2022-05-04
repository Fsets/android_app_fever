package com.example.nft_ticket_andrey.ui.musica;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MusicaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MusicaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}