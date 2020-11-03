package com.ventrux.aswagna2.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ventrux.aswagna2.Model.CatagoryModel;
import com.ventrux.aswagna2.R;


import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CatagorylistAdaptor extends RecyclerView.Adapter<CatagorylistAdaptor.ImageViewHolder> {
private Context mContext;
private List<CatagoryModel> mUploads;

public CatagorylistAdaptor(Context mContext, List<CatagoryModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
        }

@Override
public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_catagory, parent, false);
        return new ImageViewHolder(v);
        }

@Override
public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final CatagoryModel uploadCurrent = mUploads.get(position);

//            String imgurl= "http://buyseller.digitalwebgurukul.com/public/uploaded/"+uploadCurrent.getImage();
//            Picasso.with(mContext)
//                    .load(imgurl)
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .fit()
//                    .into(holder.imgca);
            holder.imgca.setImageDrawable(mContext.getResources().getDrawable(uploadCurrent.getImage()));

            holder.txtcatname.setText(uploadCurrent.getName());


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
    public TextView txtcatname,txtdesc;
    ImageView imgca;
    RelativeLayout ll;
    public ImageViewHolder(View itemView) {
        super(itemView);
        txtcatname = itemView.findViewById(R.id.name);
        //txtdesc=itemView.findViewById(R.id.subdesc);
        imgca=itemView.findViewById(R.id.icon);
        //ll=itemView.findViewById(R.id.rr);


    }


}


}