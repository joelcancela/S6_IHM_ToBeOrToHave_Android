package fr.unice.polytech.jcancela.tobeortohave.database;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.jcancela.tobeortohave.R;
import fr.unice.polytech.jcancela.tobeortohave.model.Product;

/**
 * Created by Joel CANCELA VAZ on 30/03/2017.
 */

public class ProductsViewAdapter extends RecyclerView.Adapter<ProductsViewAdapter.NewsViewHolder> {

    private List<Product> productList;
    private List<Product> productListCopy;

    public ProductsViewAdapter(List<Product> productList) {
        this.productList = productList;
        this.productListCopy = new ArrayList<>();
        productListCopy.addAll(productList);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public CardView cardview;

        public NewsViewHolder(CardView cardview) {
            super(cardview);
            this.cardview=cardview;
        }
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       CardView cardview = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_products_product, parent, false);

        NewsViewHolder vh = new NewsViewHolder(cardview);
        return vh;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

        //CARDVIEW
        TextView title = (TextView) holder.cardview.findViewById(R.id.product_title);
        TextView price = (TextView) holder.cardview.findViewById(R.id.product_price);
        ImageView thumbnail = (ImageView) holder.cardview.findViewById(R.id.imageProduct);
        thumbnail.setImageResource(R.drawable.search);

        Product product = this.productList.get(position);
        title.setText(product.getName());
        price.setText(String.valueOf(product.getPrice())+"€");
        ThumbnailsLoader thumbnailsLoader = new ThumbnailsLoader(thumbnail);
        thumbnailsLoader.execute(product.getThumbnailName());


    }

    public void filter(String text) {
        productList.clear();
        if(text.isEmpty()){
            productList.addAll(productListCopy);
        } else{
            text = text.toLowerCase();
            for(Product item: productListCopy){
                if(item.getName().toLowerCase().contains(text.trim())){
                    productList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}