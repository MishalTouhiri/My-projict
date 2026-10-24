package com.example.mymenuproject;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/** One item on the cafe menu. Prices are whole riyals. */
public final class Product {

    @StringRes public final int name;
    @StringRes public final int description;
    @DrawableRes public final int image;
    public final int price;

    public Product(@StringRes int name, @StringRes int description, @DrawableRes int image, int price) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public static final Product[] DRINKS = {
            new Product(R.string.item_coffee, R.string.desc_coffee, R.drawable.ccafe, 8),
            new Product(R.string.item_latte, R.string.desc_latte, R.drawable.cafee, 14),
            new Product(R.string.item_espresso, R.string.desc_espresso, R.drawable.ocoffe, 10),
            new Product(R.string.item_iced_coffee, R.string.desc_iced_coffee, R.drawable.cafa, 15),
    };

    public static final Product[] BREAKFAST = {
            new Product(R.string.item_croissant, R.string.desc_croissant, R.drawable.shz, 9),
            new Product(R.string.item_toast, R.string.desc_toast, R.drawable.toest, 7),
    };
}
