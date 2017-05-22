package fr.unice.polytech.jcancela.tobeortohave.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.unice.polytech.jcancela.tobeortohave.R;
import fr.unice.polytech.jcancela.tobeortohave.fragment.gmaps.StoresTask;
import fr.unice.polytech.jcancela.tobeortohave.model.Store;

/**
 * Created by Joel CANCELA VAZ on 16/05/2017.
 */

public class PriceComparatorFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_price_comparator,container,false);
        float price = getArguments().getFloat("price");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ToBeOrToHave",0);
        String favorite_store = sharedPreferences.getString("fav_store","");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.stores_recyclerview);
        List<Store> storesList = new ArrayList<>();
        try {
            storesList = new StoresTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new RecyclerViewStoreComparatorAdapter(price,storesList,favorite_store,this));

        return view;
    }

    public void switchContent(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack("pricecomparator");
        ft.commit();
    }
}
