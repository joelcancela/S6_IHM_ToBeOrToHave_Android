package fr.unice.polytech.jcancela.tobeortohave;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

import fr.unice.polytech.jcancela.tobeortohave.model.Store;

/**
 * Created by Joel CANCELA VAZ on 16/05/2017.
 */

public class RecyclerViewStoreComparatorAdapter extends RecyclerView.Adapter<RecyclerViewStoreComparatorAdapter.StoreHolder> {

    float price;
    List<Store> storesList;

    public RecyclerViewStoreComparatorAdapter(float price, List<Store> storesList) {
        this.price = price;
        this.storesList= storesList;
        Collections.shuffle(storesList);
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
        public StoreHolder(CardView cardView, List<Store> storesList) {
            super(cardView);
            this.cardview = cardView;
        }
    }
}
