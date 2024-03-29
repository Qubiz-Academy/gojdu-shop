package ro.gojdu.shop.ui.main.ui.mycart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ro.gojdu.shop.R;
import ro.gojdu.shop.ui.shop.ShopItemDetailsActivity;

public class MyCartFragment extends Fragment {

    private MyCartViewModel myCartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myCartViewModel =
                ViewModelProviders.of(this).get(MyCartViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mycart, container, false);
        final TextView textView = root.findViewById(R.id.text_mycart);
        myCartViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(view.getContext(), ShopItemDetailsActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}