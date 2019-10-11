package ro.gojdu.shop.ui.main.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.snackbar.Snackbar;

import ro.gojdu.shop.R;

public class ShopFragment extends Fragment {
    TextView tvFilter;
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_shop, container, false);
        tvFilter = root.findViewById(R.id.tvFilter);
        tvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Filter options?", Snackbar.LENGTH_LONG).setAction("", null).show();
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = root.findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new ProductAdapter(this.getActivity(),ApplicationClass.products);
        recyclerView.setAdapter(myAdapter);
    }
}