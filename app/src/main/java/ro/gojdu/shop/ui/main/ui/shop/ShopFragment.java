package ro.gojdu.shop.ui.main.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ro.gojdu.shop.R;

public class ShopFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private View root;
    private LinearLayout linearLayout;
    private BottomSheetBehavior bottomSheetBehavior;
    private TextView tvFilter;
    private TextView tvFilterAZ;
    private TextView tvFilterZA;
    private TextView tvFilterPriceDown;
    private TextView tvFilterPriceUp;
    private TextView tvPriceRange;
    private ArrayList<Product> products = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = root.findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new ProductAdapter(this.getActivity(), products);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_shop, container, false);
        getProducts();
        linearLayout = root.findViewById(R.id.bottom);
        tvFilterAZ = root.findViewById(R.id.tvFilterAZ);
        tvFilterZA = root.findViewById(R.id.tvFilterZA);
        tvFilterPriceDown = root.findViewById(R.id.tvFilterPriceDown);
        tvFilterPriceUp = root.findViewById(R.id.tvFilterPriceUp);
        tvPriceRange = root.findViewById(R.id.tvPriceRange);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        tvFilter = root.findViewById(R.id.tvFilter);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        tvFilterAZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortAZ(true);
                myAdapter.notifyDataSetChanged();
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        tvFilterZA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortAZ(false);
                myAdapter.notifyDataSetChanged();
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        tvFilterPriceDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        tvFilterPriceUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        tvPriceRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        tvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN)
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                else
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        return root;
    }

    private void getProducts()
    {
        products.add(new Product("Computer 2","Very good better than 2nd.",200.00));
        products.add(new Product("Computer 4","Very good better than 1st.aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaAlso pretty meh!",30000.00));
        products.add(new Product("Computer 3","Pretty meh.",0.00));
        products.add(new Product("Computer 1 with a name too long to display","Quite average.",1000.00));
    }

    private void sortAZ(final boolean az)
    {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                String name1 = p1.getName();
                String name2 = p2.getName();
                if(az)
                    return name1.compareTo(name2);
                else
                    return name2.compareTo(name1);
            }
        });
    }
}