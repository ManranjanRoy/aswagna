package com.ventrux.aswagna2.Adaptor;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ventrux.aswagna2.Model.ProductListModel;
import com.ventrux.aswagna2.R;
import com.ventrux.aswagna2.fragment.ProductDetailsFragment;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class BrowseProductslistAdaptor extends RecyclerView.Adapter<BrowseProductslistAdaptor.ImageViewHolder> {
private Context mContext;
private List<ProductListModel> mUploads;

public BrowseProductslistAdaptor(Context mContext, List<ProductListModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
        }

@Override
public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_product1, parent, false);
        return new ImageViewHolder(v);
        }

@Override
public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final ProductListModel uploadCurrent = mUploads.get(position);

//            String imgurl= "http://buyseller.digitalwebgurukul.com/public/uploaded/"+uploadCurrent.getImage();
//            Picasso.with(mContext)
//                    .load(imgurl)
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .fit()
//                    .into(holder.imgca);
            holder.imgca.setImageDrawable(mContext.getResources().getDrawable(uploadCurrent.getImg()));

    if (uploadCurrent.getName().length()>18){
        holder.txtcatname.setText(uploadCurrent.getName().substring(0,18)+"...");
    }else {
        holder.txtcatname.setText(uploadCurrent.getName());
    }
    holder.oprice.setText(uploadCurrent.getOprice());
    holder.pprice.setText("KD "+uploadCurrent.getPprice());
    holder.pprice.setPaintFlags(holder.pprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    if (uploadCurrent.getWishlist().equals("0")){
        holder.pwishlist.setImageResource(R.drawable.wishlistoff);
    }else{
        holder.pwishlist.setImageResource(R.drawable.wishliston);
    }
    holder.pwishlist.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (uploadCurrent.getWishlist().equals("0")){
                uploadCurrent.setWishlist("1");
                holder.pwishlist.setImageResource(R.drawable.wishlistoff);
            }else{
                uploadCurrent.setWishlist("0");
                holder.pwishlist.setImageResource(R.drawable.wishliston);
            }
        }
    });
    if (uploadCurrent.getDiscount().equals("0")){
    holder.poffer.setVisibility(View.GONE);
    } else{
        holder.poffer.setVisibility(View.VISIBLE);
        holder.poffer.setText(uploadCurrent.getDiscount()+"%\noff");
    }
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentTransaction transaction =((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new ProductDetailsFragment());
            transaction.addToBackStack(null);// Add your fragment class
            transaction.commit();
        }
    });
        }

@Override
public int getItemCount() {
        return mUploads.size();
        }

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public TextView txtcatname,pprice,oprice,poffer;
    ImageView imgca,pwishlist;
    RelativeLayout ll;
    public ImageViewHolder(View itemView) {
        super(itemView);
        txtcatname = itemView.findViewById(R.id.pname);
        pprice=itemView.findViewById(R.id.pprice);
        oprice=itemView.findViewById(R.id.oprice);
        imgca=itemView.findViewById(R.id.pimg);
        poffer=itemView.findViewById(R.id.poffer);
        pwishlist=itemView.findViewById(R.id.pwishlist);
        //ll=itemView.findViewById(R.id.rr);


    }


}


}