package com.ventrux.aswagna2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ventrux.aswagna2.Model.CatagoryModel;
import com.ventrux.aswagna2.Model.ProductListModel;
import com.ventrux.aswagna2.Model.SellerModel;
import com.ventrux.aswagna2.Model.SizeModel;
import com.ventrux.aswagna2.Model.SliderItem;
import com.ventrux.aswagna2.Model.SubCatagoryModel;
import com.ventrux.aswagna2.StaticData.CatagoryList;
import com.ventrux.aswagna2.fragment.Browse;
import com.ventrux.aswagna2.fragment.Home;
import com.ventrux.aswagna2.fragment.MoreFragment;
import com.ventrux.aswagna2.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    List<CatagoryModel> catagoryModels=new ArrayList<>();
    List<SubCatagoryModel> subCatagoryModels=new ArrayList<>();
    List<SizeModel> sizeModels=new ArrayList<>();
    List<SellerModel> sellerModels=new ArrayList<>();
    List<SliderItem> sliderItems=new ArrayList<>();
    List<ProductListModel> productListModels=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        catagoryModels.clear();
        catagoryModels.add(new CatagoryModel("1","Sports",R.drawable.icon52));
        catagoryModels.add(new CatagoryModel("2","Women",R.drawable.icon51));
        catagoryModels.add(new CatagoryModel("3","Gifts",R.drawable.icon50));
        catagoryModels.add(new CatagoryModel("4","Perfumes",R.drawable.icon48));
        catagoryModels.add(new CatagoryModel("5","Electronics",R.drawable.icon47));
        catagoryModels.add(new CatagoryModel("6","Shoes",R.drawable.icon46));

        subCatagoryModels.clear();
        subCatagoryModels.add(new SubCatagoryModel("1","All",R.drawable.icon52));
        subCatagoryModels.add(new SubCatagoryModel("2","Dress",R.drawable.icon51));
        subCatagoryModels.add(new SubCatagoryModel("3","Top",R.drawable.icon50));
        subCatagoryModels.add(new SubCatagoryModel("4","Jeans",R.drawable.icon48));
        subCatagoryModels.add(new SubCatagoryModel("5","T-Shirt",R.drawable.icon47));
        subCatagoryModels.add(new SubCatagoryModel("3","Top",R.drawable.icon50));
        subCatagoryModels.add(new SubCatagoryModel("4","Jeans",R.drawable.icon48));
        subCatagoryModels.add(new SubCatagoryModel("5","T-Shirt",R.drawable.icon47));
        subCatagoryModels.add(new SubCatagoryModel("6","Saree",R.drawable.icon46));

        sizeModels.add(new SizeModel("1"," S ",R.drawable.icon52));
        sizeModels.add(new SizeModel("2"," M ",R.drawable.icon51));
        sizeModels.add(new SizeModel("3"," L ",R.drawable.icon50));
        sizeModels.add(new SizeModel("4"," XL ",R.drawable.icon48));
        sizeModels.add(new SizeModel("5"," XXL",R.drawable.icon47));
        sizeModels.add(new SizeModel("3","XXXL",R.drawable.icon50));

        sellerModels.clear();
        sellerModels.add(new SellerModel("1","Seller name",R.drawable.s1));
        sellerModels.add(new SellerModel("2","Abc Company",R.drawable.s2));
        sellerModels.add(new SellerModel("3","Dummy Seller",R.drawable.s3));
        sellerModels.add(new SellerModel("4","Demo Seller",R.drawable.s4));
        sellerModels.add(new SellerModel("5","Electronics",R.drawable.s1));
        sellerModels.add(new SellerModel("6","Test Seller",R.drawable.s2));

        productListModels.clear();
        productListModels.add(new
                ProductListModel("1","Apple I Phone 7" ,"Desc","7.00","5.00","0","0",R.drawable.p1));
        productListModels.add(new
                ProductListModel("2","Mi tv 4A Pro 108 cm (43 inch)" ,"Desc","7.00","5.00","10","1",R.drawable.p2));
        productListModels.add(new
                ProductListModel("3","Apple I Phone 7" ,"Desc","7.00","5.00","0","0",R.drawable.p3));
        productListModels.add(new
                ProductListModel("4","Apple I Phone 7" ,"Desc","7.00","5.00","0","1",R.drawable.p4));
        productListModels.add(new
                ProductListModel("5","Apple I Phone 7" ,"Desc","7.00","5.00","10","0",R.drawable.p5));
        productListModels.add(new
                ProductListModel("6","Apple I Phone 7" ,"Desc","7.00","5.00","0","1",R.drawable.p6));
        productListModels.add(new
                ProductListModel("7","Apple I Phone 7" ,"Desc","7.00","5.00","0","0",R.drawable.p7));
        productListModels.add(new
                ProductListModel("8","Apple I Phone 7" ,"Desc","7.00","5.00","0","1",R.drawable.p8));
        productListModels.add(new
                ProductListModel("9","Apple I Phone 7" ,"Desc","7.00","5.00","20","0",R.drawable.p9));
        productListModels.add(new
                ProductListModel("10","Apple I Phone 7" ,"Desc","7.00","5.00","10","1",R.drawable.p10));

        CatagoryList.catagoryModels=catagoryModels;
        CatagoryList.subCatagoryModels=subCatagoryModels;
        CatagoryList.sizeModels=sizeModels;
        CatagoryList.productListModels=productListModels;
        CatagoryList.sellerModels=sellerModels;
        //loading the default fragment
        loadFragment(new Home());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.bottom_nav);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new Home();
                CatagoryList.fragment=fragment;
                break;

            case R.id.navigation_browse:
                fragment = new Browse();
                CatagoryList.fragment=fragment;
                break;

            case R.id.navigation_order:
                fragment = new OrderFragment();
                CatagoryList.fragment=fragment;
                break;

            case R.id.navigation_more:
                fragment = new MoreFragment();
                CatagoryList.fragment=fragment;
                break;
        }

        return loadFragment(fragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFragment(CatagoryList.fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}

