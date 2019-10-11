package ro.gojdu.shop.ui.main.ui.shop;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application
{
    public static ArrayList<Product> products;

    @Override
    public void onCreate() {
        super.onCreate();
        products = new ArrayList<Product>();
        products.add(new Product("Computer 1","Very good better than 2nd."));
        products.add(new Product("Computer 2","Very good better than 1st.aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaAlso pretty meh!"));
        products.add(new Product("Computer 3","Pretty meh."));
    }
}
