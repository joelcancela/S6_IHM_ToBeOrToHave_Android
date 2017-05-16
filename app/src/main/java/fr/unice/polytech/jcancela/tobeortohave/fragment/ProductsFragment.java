package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.unice.polytech.jcancela.tobeortohave.R;
import fr.unice.polytech.jcancela.tobeortohave.database.ProductsTask;
import fr.unice.polytech.jcancela.tobeortohave.database.ProductsViewAdapter;
import fr.unice.polytech.jcancela.tobeortohave.model.Product;

/**
 * Created by Joel CANCELA VAZ on 08/05/2017.
 */

public class ProductsFragment extends android.support.v4.app.Fragment {

    List<Product> productList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_products, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.products_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        try {
            productList = new ProductsTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        final ProductsViewAdapter recyclerViewAdapter = new ProductsViewAdapter(productList,this);
        recyclerView.setAdapter(recyclerViewAdapter);


        final SearchView searchView = (SearchView) view.findViewById(R.id.search_bar);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerViewAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewAdapter.filter(newText);
                return true;
            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void switchContent(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack("productsView");
        ft.commit();
    }
}
