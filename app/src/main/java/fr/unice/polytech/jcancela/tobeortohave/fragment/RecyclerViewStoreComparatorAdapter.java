package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

import fr.unice.polytech.jcancela.tobeortohave.R;
import fr.unice.polytech.jcancela.tobeortohave.fragment.gmaps.StoresFragment;
import fr.unice.polytech.jcancela.tobeortohave.model.Store;

/**
 * Created by Joel CANCELA VAZ on 16/05/2017.
 */

public class RecyclerViewStoreComparatorAdapter extends RecyclerView.Adapter<RecyclerViewStoreComparatorAdapter.StoreHolder> {

    float price;
    List<Store> storesList;
    Fragment fatherFragment;

    public RecyclerViewStoreComparatorAdapter(float price, List<Store> storesList, Fragment fatherFragment) {
        this.price = price;
        this.storesList= storesList;
        Collections.shuffle(storesList);
        this.fatherFragment=fatherFragment;
    }

    @Override
    public RecyclerViewStoreComparatorAdapter.StoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StoreHolder viewHolder = null;
        CardView layoutView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.price_comparator_item, parent, false);
        viewHolder = new StoreHolder(layoutView, storesList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewStoreComparatorAdapter.StoreHolder holder, int position) {
        //CARDVIEW
        TextView title = (TextView) holder.cardview.findViewById(R.id.store_name);
        TextView priceLabel = (TextView) holder.cardview.findViewById(R.id.price);

        Store store = this.storesList.get(position);
        title.setText(store.getName());
        double randomPrice = price+(position*Math.random()*2);//Random number
        NumberFormat formatter = new DecimalFormat("#0.00");
        priceLabel.setText(formatter.format(randomPrice)+"€");
        if(position==0){
            priceLabel.setTextColor(Color.parseColor("#00ff00"));
        }
    }

    @Override
    public int getItemCount() {
        return storesList.size();
    }

    public class StoreHolder extends RecyclerView.ViewHolder {
        public CardView cardview;
        public StoreHolder(CardView cardView, final List<Store> storesList) {
            super(cardView);
            this.cardview = cardView;
            cardview.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        fragmentJump(storesList.get(pos));
                    }
                }
            });
        }
        private void fragmentJump(Store store) {
            Fragment mFragment = new StoresFragment();
            Bundle mBundle = new Bundle();
            mBundle.putParcelable("goto_store", store);
            mFragment.setArguments(mBundle);
            PriceComparatorFragment priceComparatorFragment = (PriceComparatorFragment) fatherFragment;
            priceComparatorFragment.switchContent(mFragment);

        }
    }

}
