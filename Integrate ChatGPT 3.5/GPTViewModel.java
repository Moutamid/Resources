package com.moutamid.sampleproject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GPTViewModel extends ViewModel {
    MutableLiveData<GPTModel> message = new MutableLiveData<>();

    MutableLiveData<GPTModel> getMesajlar() {
        if (message == null) {
            message = new MutableLiveData<>();
        }
        return message;
    }
}
