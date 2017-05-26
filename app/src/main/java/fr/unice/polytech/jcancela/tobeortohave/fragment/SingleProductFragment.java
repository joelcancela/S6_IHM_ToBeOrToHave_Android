package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fr.unice.polytech.jcancela.tobeortohave.R;
import fr.unice.polytech.jcancela.tobeortohave.database.ThumbnailsLoader;
import fr.unice.polytech.jcancela.tobeortohave.model.Product;

/**
 * Created by Joel CANCELA VAZ on 16/05/2017.
 */

public class SingleProductFragment extends android.support.v4.app.Fragment {

    private Product product;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_single_product, container, false);
        product = getArguments().getParcelable("article");

        ImageView imageView = (ImageView) view.findViewById(R.id.picture);
        TextView name = (TextView) view.findViewById(R.id.title);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView description = (TextView) view.findViewById(R.id.description);
        Button whoscheapest = (Button) view.findViewById(R.id.whoscheap);

        ThumbnailsLoader thumbnailsLoader = new ThumbnailsLoader(imageView);
        thumbnailsLoader.execute(product.getThumbnailName());
        name.setText(product.getName());
        price.setText(String.format("%.2f", product.getPrice())+"€");
        description.setText(product.getDescription());


        whoscheapest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putFloat("price",product.getPrice());
                Fragment fragment = new PriceComparatorFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack("productSingle");
                ft.commit();
            }
        });



        return view;
    }
}
