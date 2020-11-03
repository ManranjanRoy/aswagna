package com.ventrux.aswagna2.fragment;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.ventrux.aswagna2.Adaptor.BrowseProductslistAdaptor;
import com.ventrux.aswagna2.Adaptor.ColorAdaptor;
import com.ventrux.aswagna2.Adaptor.ProductByCatAdaptor;
import com.ventrux.aswagna2.Adaptor.SizeAdaptor;
import com.ventrux.aswagna2.Model.Color;
import com.ventrux.aswagna2.Model.ProductListModel;
import com.ventrux.aswagna2.R;
import com.ventrux.aswagna2.StaticData.CatagoryList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {

    private ViewPager viewPager;
    private LinearLayout layout_dots;
    private AdapterImageSlider adapterImageSlider;
    private Runnable runnable = null;
    private Handler handler = new Handler();
    TextView pprice;
    List<Integer> array_imgs=new ArrayList<>();

    RecyclerView chapterrecycler;
    ColorAdaptor colorAdaptor;
    RecyclerView nrecyclerview,catageryrecycler;
    SizeAdaptor sizeAdaptor;
    RecyclerView relatedrecycleview;
    ProductByCatAdaptor productByCatAdaptor;
    List<ProductListModel> productListModels=new ArrayList<>();
    BrowseProductslistAdaptor browseProductslistAdaptor;
    TextView plus,minus,quantity;
    int q=1;
    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_product_details, container, false);
        init(v);
         v.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(getFragmentManager().getBackStackEntryCount() > 0){
                     getFragmentManager().popBackStackImmediate();
                 }
             }
         });
        v.findViewById(R.id.addtocart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new CartFragment());
                transaction.addToBackStack(null);// Add your fragment class
                transaction.commit();
            }
        });
        array_imgs.clear();
        array_imgs.add(R.drawable.icon92);
        array_imgs.add(R.drawable.icon92);
        array_imgs.add(R.drawable.icon92);
        array_imgs.add(R.drawable.icon92);
       pprice=v.findViewById(R.id.pprice);

       pprice.setText("KD 8.600");
       pprice.setPaintFlags(pprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
       initComponent(v);
        return v;
    }

    private void init(View v) {

        relatedrecycleview = v.findViewById(R.id.recyclerviewrelatedproduct);
        relatedrecycleview.setHasFixedSize(true);
        relatedrecycleview.setLayoutManager(new GridLayoutManager(getContext(),2));
        browseProductslistAdaptor = new BrowseProductslistAdaptor(getContext(), CatagoryList.productListModels);
        relatedrecycleview.setAdapter(browseProductslistAdaptor);
        List<Color> colors=new ArrayList<>();
        colors.clear();
        colors.add(new Color("1","1",R.color.bottom_color));
        colors.add(new Color("1","1",R.color.colorPrimary));
        colors.add(new Color("1","1",R.color.black));
        chapterrecycler = v.findViewById(R.id.recyclerviewcolor);
        chapterrecycler.setHasFixedSize(true);
        chapterrecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        //List<Color> colors=uploadCurrent.getColor();
        colorAdaptor=new ColorAdaptor(getContext(),colors);
        chapterrecycler.setAdapter(colorAdaptor);
        colorAdaptor.setonItemClickListner(new ColorAdaptor.OnitemClickListner() {
            @Override
            public void OnItemCLiCK(int position,View v) {
                Toast.makeText(getContext(),"hii",Toast.LENGTH_LONG).show();
                v.findViewById(R.id.ll).setBackgroundColor(android.graphics.Color.parseColor("#000"));
            }

            @Override
            public void OnCartCLiCK(int position) {

            }
        });

        catageryrecycler = v.findViewById(R.id.recyclerviewsize);
        catageryrecycler.setHasFixedSize(true);
        catageryrecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        sizeAdaptor=new SizeAdaptor(getContext(), CatagoryList.sizeModels);
        catageryrecycler.setAdapter(sizeAdaptor);

        plus=v.findViewById(R.id.plus);
        minus=v.findViewById(R.id.minus);
        quantity=v.findViewById(R.id.pquantity);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value=Integer.parseInt(quantity.getText().toString().trim());
                value=value+1;
                quantity.setText(String.valueOf(value));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value=Integer.parseInt(quantity.getText().toString().trim());
                if (value !=1){
                value=value-1;
                quantity.setText(String.valueOf(value));
                }else if (value==1){
                    Toast.makeText(getContext(), "Minimun Quantity", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initComponent(View v) {
        layout_dots = v.findViewById(R.id.layout_dots);
        viewPager = v.findViewById(R.id.pager);
        adapterImageSlider = new AdapterImageSlider((AppCompatActivity) getContext(), array_imgs);

        adapterImageSlider.setItems(array_imgs);
        viewPager.setAdapter(adapterImageSlider);

        // displaying selected image first
        viewPager.setCurrentItem(0);
        addBottomDots(layout_dots, adapterImageSlider.getCount(), 0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int pos) {
                addBottomDots(layout_dots, adapterImageSlider.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        /**
         * auto slider
         */
        startAutoSlider(adapterImageSlider.getCount());
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getContext());
            int width_height = 25;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.circle);
            dots[i].setColorFilter(ContextCompat.getColor(getContext(), R.color.white), PorterDuff.Mode.SRC_ATOP);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setColorFilter(ContextCompat.getColor(getContext(), R.color.bottom_color), PorterDuff.Mode.SRC_ATOP);
        }
    }
    private static class AdapterImageSlider extends PagerAdapter {

        private AppCompatActivity act;
        private List<Integer> items;

        // constructor
        private AdapterImageSlider(AppCompatActivity activity, List<Integer> items) {
            this.act = activity;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        public void setItems(List<Integer> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_slider_image, container, false);

            ImageView imageView = v.findViewById(R.id.image);
            displayImageOriginal(act, imageView, items, position);

            (container).addView(v);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((RelativeLayout) object);
        }

    }

    private void startAutoSlider(final int count) {
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 8000);
            }
        };
        handler.postDelayed(runnable, 8000);
    }

    /**
     * Can be a method form Utils to use many times on project
     * @param context
     * @param img
     * @param url
     * @param position
     */
    private static void displayImageOriginal(Context context, ImageView img, List<Integer> url, int position) {
        try {
            Picasso.with(context)
                    .load(url.get(position))
                    .placeholder(R.drawable.ic_launcher_background)
                    .fit()
                    .into(img);
           /* Glide.with(context).load(url.get(position))
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img);*/
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public void onDestroy() {
        if (runnable != null) handler.removeCallbacks(runnable);
        super.onDestroy();
    }



}
