package ro.gojdu.shop.ui.main.ui.shop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ro.gojdu.shop.R;
import ro.gojdu.shop.ui.shop.ShopItemDetailsActivity;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable
{
    private ArrayList<Product> products;
    private ArrayList<Product> productsFull;

    public ProductAdapter (Context context, ArrayList<Product> list)
    {
        products=list;
        productsFull = new ArrayList<>(products);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        TextView tvDescription;
        TextView tvPrice;
        ImageView ivProduct;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ShopItemDetailsActivity.class);
                    Product product = (Product) itemView.getTag();
                    intent.putExtra(ShopItemDetailsActivity.PRODUCT_DATA,product);
                    view.getContext().startActivity(intent);
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
        Product product = products.get(position);
        holder.itemView.setTag(product);
        holder.tvName.setText(product.getName());
        holder.tvDescription.setText(product.getDescription());
        Glide.with(holder.ivProduct.getContext()).load(product.getImageUrl()).into(holder.ivProduct);
        holder.tvPrice.setText("Price: "+String.format("%.2f", product.getPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Product> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Product item : productsFull) {
                    if (item.getName().toLowerCase().contains(filterPattern) || item.getDescription().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            products.clear();
            products.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

}
