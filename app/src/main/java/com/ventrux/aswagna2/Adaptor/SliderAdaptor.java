package com.ventrux.aswagna2.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.ventrux.aswagna2.Model.CatagoryModel;
import com.ventrux.aswagna2.Model.SliderItem;
import com.ventrux.aswagna2.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class SliderAdaptor extends RecyclerView.Adapter<SliderAdaptor.ImageViewHolder> {
private Context mContext;
private List<SliderItem> mUploads;
ViewPager2 viewPager2;
public SliderAdaptor(Context mContext, List<SliderItem> mUploads,ViewPager2 viewPager2) {
        this.mContext = mContext;
        this.mUploads = mUploads;
        this.viewPager2=viewPager2;
        }

@Override
public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_slider, parent, false);
        return new ImageViewHolder(v);
        }

@Override
public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final SliderItem uploadCurrent = mUploads.get(position);
holder.setImage(uploadCurrent);
if (position==mUploads.size()-2){
    viewPager2.post(runnable);
}
//            String imgurl= "http://buyseller.digitalwebgurukul.com/public/uploaded/"+uploadCurrent.getImage();
//            Picasso.with(mContext)
//                    .load(imgurl)
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .fit()
//                    .into(holder.imgca);




            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* Intent i=new Intent(mContext, TestScreenActivity.class);
                    i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
                    //i.putExtra("url",uploadCurrent.getId());
                    StaticData.chapter_id= String.valueOf(uploadCurrent.getId());
                    mContext.startActivity(i);*/
                }
            });

        }

@Override
public int getItemCount() {
        return mUploads.size();
        }

public class ImageViewHolder extends RecyclerView.ViewHolder {
   RoundedImageView imgca;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imgca=itemView.findViewById(R.id.imageslides);
        //ll=itemView.findViewById(R.id.rr);
    }
    void setImage(SliderItem  sliderItem){
        imgca.setImageResource(sliderItem.getImage());
    }


}

private  Runnable runnable=new Runnable() {
    @Override
    public void run() {
        mUploads.addAll(mUploads);
        notifyDataSetChanged();
    }
};


}