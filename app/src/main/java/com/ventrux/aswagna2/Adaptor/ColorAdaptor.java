package com.ventrux.aswagna2.Adaptor;

import android.content.Context;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ventrux.aswagna2.Model.Color;
import com.ventrux.aswagna2.Model.Common;
import com.ventrux.aswagna2.Model.item;
import com.ventrux.aswagna2.R;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class ColorAdaptor extends RecyclerView.Adapter<ColorAdaptor.ImageViewHolder> {
private Context mContext;
private List<Color> mUploads;

  int row_index=0;

    private OnitemClickListner mlistner;

    public interface  OnitemClickListner{

        void OnItemCLiCK(int position, View view);
        void OnCartCLiCK(int position);
    }
    public  void setonItemClickListner(OnitemClickListner listner){
        mlistner=listner;

    }

public ColorAdaptor(Context mContext, List<Color> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
        }

@Override
public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_colors, parent, false);
        return new ImageViewHolder(v,mlistner);
        }

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@Override
public void onBindViewHolder(final ImageViewHolder holder, final int position) {


        final Color uploadCurrent = mUploads.get(position);
    holder.ll.setBackgroundTintList(mContext.getResources().getColorStateList(uploadCurrent.getColor()));
        //holder.ll.setback(android.graphics.Color.parseColor(uploadCurrent.getColor()));

        /*if (!items.get(position).isIsselected())
            holder.ll.setBackgroundColor(android.graphics.Color.parseColor("#000"));
        else
            holder.ll.setBackgroundColor(android.graphics.Color.parseColor("#fff"));
*/

            // holder.imgca.setImageDrawable(mContext.getResources().getDrawable(uploadCurrent.getCode()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    row_index=position;
                    Common.currentitem=mUploads.get(position);
                   // Toast.makeText(mContext,"hi",Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();

                }
            });
            if (row_index==position){
                //holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#000000"));
                holder.imgca.setVisibility(View.VISIBLE);

            }
            else{
               // holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"));
                holder.imgca.setVisibility(View.GONE);
            }

        }

@Override
public int getItemCount() {
        return mUploads.size();
        }

public class ImageViewHolder extends RecyclerView.ViewHolder {

    ImageView imgca;
    LinearLayout ll;
    public ImageViewHolder(final View itemView, final OnitemClickListner listner) {
        super(itemView);
        //txtdesc=itemView.findViewById(R.id.subdesc);
        ll=itemView.findViewById(R.id.ll);
        imgca=itemView.findViewById(R.id.color);
        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listner !=null){
                    int position =getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listner.OnItemCLiCK(position,itemView);
                    }
                }
            }
        });*/
        //ll=itemView.findViewById(R.id.rr);


    }


}


}