package com.ventrux.aswagna2.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ventrux.aswagna2.Adaptor.BrowseProductslistAdaptor;
import com.ventrux.aswagna2.Adaptor.CatagorylistAdaptor;
import com.ventrux.aswagna2.Adaptor.FeatureProductslistAdaptor;
import com.ventrux.aswagna2.Adaptor.SliderAdaptor;
import com.ventrux.aswagna2.Adaptor.TopsellerlistAdaptor;
import com.ventrux.aswagna2.Model.CatagoryModel;
import com.ventrux.aswagna2.Model.ProductListModel;
import com.ventrux.aswagna2.Model.SellerModel;
import com.ventrux.aswagna2.Model.SizeModel;
import com.ventrux.aswagna2.Model.SliderItem;
import com.ventrux.aswagna2.Model.SubCatagoryModel;
import com.ventrux.aswagna2.R;
import com.ventrux.aswagna2.StaticData.CatagoryList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
List<CatagoryModel> catagoryModels=new ArrayList<>();
List<SubCatagoryModel> subCatagoryModels=new ArrayList<>();
List<SizeModel> sizeModels=new ArrayList<>();
List<SellerModel> sellerModels=new ArrayList<>();
List<SliderItem> sliderItems=new ArrayList<>();
List<ProductListModel> productListModels=new ArrayList<>();
    RecyclerView catagoryrecycler,fproducrecycle,nrecyclerview,bestproductrecycler,topsellerrecyclerview;
    CatagorylistAdaptor courseslistAdaptor;
    FeatureProductslistAdaptor featureProductslistAdaptor;
    BrowseProductslistAdaptor browseProductslistAdaptor;
    TopsellerlistAdaptor topsellerlistAdaptor;
    ProgressDialog progressDialog;
    ViewPager2 viewPager2;

    Handler slidehandler=new Handler();
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        v.findViewById(R.id.cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new CartFragment());
                transaction.addToBackStack(null);// Add your fragment class
                transaction.commit();
            }
        });
        /*v.findViewById(R.id.cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new CartFragment());
                transaction.addToBackStack(null);// Add your fragment class
                transaction.commit();
            }
        });*/
        init(v);
        return  v;
    }

    private void init(View v) {

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait while Loading ...");
        progressDialog.setCancelable(false);

        catagoryrecycler = v.findViewById(R.id.recyclerview);
        catagoryrecycler.setHasFixedSize(true);
        catagoryrecycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        courseslistAdaptor = new CatagorylistAdaptor(getContext(), CatagoryList.catagoryModels);
        catagoryrecycler.setAdapter(courseslistAdaptor);

        fproducrecycle = v.findViewById(R.id.recyclerviewfproduct);
        fproducrecycle.setHasFixedSize(true);
        fproducrecycle.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        featureProductslistAdaptor = new FeatureProductslistAdaptor(getContext(), CatagoryList.productListModels);
        fproducrecycle.setAdapter(featureProductslistAdaptor);

        nrecyclerview = v.findViewById(R.id.recyclerviewnproduct);
        nrecyclerview.setHasFixedSize(true);
        nrecyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));
        browseProductslistAdaptor = new BrowseProductslistAdaptor(getContext(), CatagoryList.productListModels);
        nrecyclerview.setAdapter(browseProductslistAdaptor);

        bestproductrecycler = v.findViewById(R.id.recyclerviewbestproduct);
        bestproductrecycler.setHasFixedSize(true);
        bestproductrecycler.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        featureProductslistAdaptor = new FeatureProductslistAdaptor(getContext(), CatagoryList.productListModels);
        bestproductrecycler.setAdapter(featureProductslistAdaptor);

        topsellerrecyclerview = v.findViewById(R.id.recyclerviewtopseller);
        topsellerrecyclerview.setHasFixedSize(true);
        topsellerrecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        topsellerlistAdaptor = new TopsellerlistAdaptor(getContext(), CatagoryList.sellerModels);
        topsellerrecyclerview.setAdapter(topsellerlistAdaptor);

        sliderItems.add(new SliderItem(R.drawable.slide1));
        sliderItems.add(new SliderItem(R.drawable.slide2));
        sliderItems.add(new SliderItem(R.drawable.slide1));
        sliderItems.add(new SliderItem(R.drawable.slide2));
        sliderItems.add(new SliderItem(R.drawable.slide1));
        viewPager2=v.findViewById(R.id.pager);
        viewPager2.setAdapter(new SliderAdaptor(getContext(),sliderItems,viewPager2));
        viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f + r *0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slidehandler.removeCallbacks(slideRunnable);
                slidehandler.postDelayed(slideRunnable,3000);
            }
        });
    }
    private Runnable slideRunnable=new Runnable() {
        @Override
        public void run() {
viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slidehandler.removeCallbacks(slideRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slidehandler.postDelayed(slideRunnable,3000);
    }
}
