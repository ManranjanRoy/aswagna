package com.ventrux.aswagna2.StaticData;

import com.ventrux.aswagna2.Model.CatagoryModel;
import com.ventrux.aswagna2.Model.ProductListModel;
import com.ventrux.aswagna2.Model.SellerModel;
import com.ventrux.aswagna2.Model.SizeModel;
import com.ventrux.aswagna2.Model.SubCatagoryModel;
import com.ventrux.aswagna2.fragment.Home;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

public class CatagoryList {

    public static List<CatagoryModel>  catagoryModels=null;
    public static List<SubCatagoryModel>  subCatagoryModels=null;
    public static List<SizeModel>  sizeModels=null;
    public static List<SellerModel> sellerModels=null;
    public static List<ProductListModel> productListModels=null;

    public static Fragment  fragment= new Home();

}
