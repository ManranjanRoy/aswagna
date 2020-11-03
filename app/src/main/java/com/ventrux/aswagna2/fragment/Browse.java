package com.ventrux.aswagna2.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.ventrux.aswagna2.Adaptor.DynamicFragmentAdapter;
import com.ventrux.aswagna2.FilterActivity;
import com.ventrux.aswagna2.MainActivity;
import com.ventrux.aswagna2.R;
import com.ventrux.aswagna2.StaticData.CatagoryList;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class Browse extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    View v;
    public Browse() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v= inflater.inflate(R.layout.fragment_browse, container, false);
        v.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        v.findViewById(R.id.filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FilterActivity.class));
            }
        });
        viewPager = v.findViewById(R.id.viewpager);
        mTabLayout =  v.findViewById(R.id.tabs);
        init();
        return  v;
    }

    @Override
    public void onResume() {
        super.onResume();
       // Toast.makeText(getContext(), "Hi", Toast.LENGTH_SHORT).show();
        init();
    }

    private void init() {

       /* viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        Toast.makeText(getContext(),  tabLayout.getSelectedTabPosition(), Toast.LENGTH_SHORT).show();*/

        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setDynamicFragmentToTabLayout();

    }
    private void setDynamicFragmentToTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("All"));
        for (int i = 0; i < CatagoryList.catagoryModels.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(CatagoryList.catagoryModels.get(i).getName()));
        }
        DynamicFragmentAdapter mDynamicFragmentAdapter =
                new DynamicFragmentAdapter(getActivity().getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(mDynamicFragmentAdapter);
        viewPager.setCurrentItem(0);
    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        LayoutInflater inflator = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int count = 10;
        for (int i=0; i<count; i++){

            MoreFragment fView = new MoreFragment();
            View view = fView.getView();
            /*TextView txtTabItemNumber = (TextView)view.findViewById(R.id.txtTabItemNumber);
            txtTabItemNumber.setText("TAB " + i);*/
            adapter.addFrag(fView,"TAB " + i);
        }

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
