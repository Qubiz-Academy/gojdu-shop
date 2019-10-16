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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ro.gojdu.shop.R;
import ro.gojdu.shop.ui.main.ui.shop.Product;
import ro.gojdu.shop.ui.main.ui.shop.ProductAdapter;
import ro.gojdu.shop.ui.shop.ShopItemDetailsActivity;

public class MyCartFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MyCartViewModel myCartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myCartViewModel =
                ViewModelProviders.of(this).get(MyCartViewModel.class);
        return inflater.inflate(R.layout.fragment_mycart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new ProductAdapter(this.getActivity(), ShopSession.cart);
        recyclerView.setAdapter(myAdapter);
    }
}