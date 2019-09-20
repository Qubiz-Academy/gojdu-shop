package ro.gojdu.shop.ui.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import ro.gojdu.shop.R;

public class ShopItemDetailsActivity extends AppCompatActivity {
    private ImageView productImage;
    private TextView textTitle;
    private TextView textDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_item_details);
        productImage=findViewById(R.id.imageProduct);
        textTitle=findViewById(R.id.textViewTitle);
        textDetails=findViewById(R.id.textViewDetails);
        textTitle.setText("1234");
        textDetails.setText("detalii");
    }
}
