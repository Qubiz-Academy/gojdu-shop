package ro.gojdu.shop.ui.main.ui.mycart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyCartViewModel extends ViewModel {


    private MutableLiveData<String> mText;

    public MyCartViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is My Cart fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}