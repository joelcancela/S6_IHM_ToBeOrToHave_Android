package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.unice.polytech.jcancela.tobeortohave.R;
import fr.unice.polytech.jcancela.tobeortohave.fragment.gmaps.StoreChooserFragment;
import fr.unice.polytech.jcancela.tobeortohave.fragment.twitter.EmbeddedTwitterTimelineFragment;

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
        SharedPreferences settings = getActivity().getApplicationContext().getSharedPreferences("ToBeOrToHave", 0);
        int listSize = settings.getInt("todolist_size",0);

        TextView number_todolist = (TextView) shoppingListCardView.findViewById(R.id.number_todolist);
        number_todolist.setText(String.valueOf(listSize));

        shoppingListCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ShoppingListFragment());
            }
        });


        CardView storeCardView = (CardView) view.findViewById(R.id.stores_cardview);
        inflater.inflate(R.layout.cardview_home_store, storeCardView);
        String favstore = settings.getString("fav_store","ToBeOrToHave Nice");

        TextView fav_store = (TextView) storeCardView.findViewById(R.id.store);
        fav_store.setText(favstore);

        storeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new StoreChooserFragment());
            }
        });


        CardView productsCardView = (CardView) view.findViewById(R.id.products_cardview);
        inflater.inflate(R.layout.cardview_home_products, productsCardView);
        productsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ProductsFragment());
            }
        });



        CardView newsCardView = (CardView) view.findViewById(R.id.news_cardview);
        inflater.inflate(R.layout.cardview_home_news, newsCardView);
        newsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new EmbeddedTwitterTimelineFragment());
            }
        });


        CardView fidelityCardView = (CardView) view.findViewById(R.id.fidelity_status);
        inflater.inflate(R.layout.cardview_home_fidelity, fidelityCardView);
        TextView user_points = (TextView) fidelityCardView.findViewById(R.id.points);
        int points = settings.getInt("user_points",0);
        user_points.setText(String.valueOf(points)+" pts");


        TextView dateTextView = (TextView) fidelityCardView.findViewById(R.id.date);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        dateTextView.setText("au "+ dateFormat.format(date));
        fidelityCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new FidelityCardFragment());
            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(sampleImages[position]);
        }
    };


    private void setFragment(Fragment fragment){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack("home");
        ft.commit();
    }
}
