package ro.gojdu.shop.ui.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import ro.gojdu.shop.R;
import ro.gojdu.shop.ui.main.ui.shop.Product;

public class ShopItemDetailsActivity extends AppCompatActivity {

    public static final String PRODUCT_DATA ="ro.gojdu.shop.ui.shop.ShopItemDetailsActivity.data";

    private ImageView productImage;
    private TextView textTitle;
    private TextView textDetails;
    private TextView textPrice;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_item_details);

        product= (Product) getIntent().getSerializableExtra(PRODUCT_DATA);

        productImage=findViewById(R.id.imageProduct);
        textPrice=findViewById(R.id.textViewPrice);
        textTitle=findViewById(R.id.textViewTitle);
        textDetails=findViewById(R.id.textViewDetails);
        textTitle.setText(product.getName());
        textDetails.setText(product.getDescription());
        textPrice.setText("100");
    }
}
