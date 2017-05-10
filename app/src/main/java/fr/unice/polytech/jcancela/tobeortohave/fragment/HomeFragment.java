package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import fr.unice.polytech.jcancela.tobeortohave.R;

/**
 * Created by Joel CANCELA VAZ on 08/05/2017.
 */

public class HomeFragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
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
}
