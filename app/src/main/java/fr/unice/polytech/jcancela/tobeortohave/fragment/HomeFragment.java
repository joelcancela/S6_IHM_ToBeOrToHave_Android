package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import fr.unice.polytech.jcancela.tobeortohave.R;

/**
 * Created by Joel CANCELA VAZ on 08/05/2017.
 */

public class HomeFragment extends android.support.v4.app.Fragment {

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.banner1,R.drawable.banner2, R.drawable.banner3};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        carouselView = (CarouselView) view.findViewById(R.id.home_banner);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);


        CardView shoppingListCardView = (CardView) view.findViewById(R.id.shopping_list_cardview);
        inflater.inflate(R.layout.cardview_home_shopping_list, shoppingListCardView);
        CardView storeCardView = (CardView) view.findViewById(R.id.stores_cardview);
        inflater.inflate(R.layout.cardview_home_store, storeCardView);
        CardView productsCardView = (CardView) view.findViewById(R.id.products_cardview);
        inflater.inflate(R.layout.cardview_home_products, productsCardView);
        CardView newsCardView = (CardView) view.findViewById(R.id.news_cardview);
        inflater.inflate(R.layout.cardview_home_news, newsCardView);
        CardView fidelityCardView = (CardView) view.findViewById(R.id.fidelity_status);
        inflater.inflate(R.layout.cardview_home_fidelity, fidelityCardView);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
}
