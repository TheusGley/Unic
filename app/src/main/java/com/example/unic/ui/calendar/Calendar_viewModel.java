package com.example.unic.ui.calendar;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Calendar_viewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public Calendar_viewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}