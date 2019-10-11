package ro.gojdu.shop.ui.main.ui.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ro.gojdu.shop.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
{
    private ArrayList<Product> products;

    public ProductAdapter (Context context, ArrayList<Product> list)
    {
        products=list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        TextView tvDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    
                }
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(products.get(position));
        holder.tvName.setText(products.get(position).getName());
        holder.tvDescription.setText(products.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
