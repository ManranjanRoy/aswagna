package com.ventrux.aswagna2.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ventrux.aswagna2.Adaptor.CartlistAdaptor;
import com.ventrux.aswagna2.Adaptor.FeatureProductslistAdaptor;
import com.ventrux.aswagna2.Model.ProductListModel;
import com.ventrux.aswagna2.R;
import com.ventrux.aswagna2.StaticData.CatagoryList;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    List<ProductListModel> productListModels=new ArrayList<>();
    RecyclerView cartyrecycler,cartrecycle1;
    CartlistAdaptor cartlistAdaptor;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_cart, container, false);
        init(v);
        v.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragmentManager().getBackStackEntryCount() > 0){
                    getFragmentManager().popBackStackImmediate();
                }
            }
        });
        return v;
    }

    private void init(View v) {
        cartyrecycler = v.findViewById(R.id.recyclercartproduct);
        cartyrecycler.setHasFixedSize(true);
        cartyrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        cartlistAdaptor = new CartlistAdaptor(getContext(), CatagoryList.productListModels.subList(0,3));
        cartyrecycler.setAdapter(cartlistAdaptor);
        cartrecycle1 = v.findViewById(R.id.recyclercartproduct1);
        cartrecycle1.setHasFixedSize(true);
        cartrecycle1.setLayoutManager(new LinearLayoutManager(getContext()));
        cartlistAdaptor = new CartlistAdaptor(getContext(), CatagoryList.productListModels.subList(0,2));
        cartrecycle1.setAdapter(cartlistAdaptor);

    }
}
