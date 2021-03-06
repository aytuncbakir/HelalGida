package com.sivamalabrothers.gidareyonum;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class YiyecekMenuCustomAdapter extends RecyclerView.Adapter<YiyecekMenuCustomAdapter.MyViewHolder> {


    ArrayList<YiyecekMenuItem> mDataList;
    LayoutInflater inflater;
    int pos;
    Context context;

    public YiyecekMenuCustomAdapter(Context context, ArrayList<YiyecekMenuItem> data){

        this.context = context;
        //inflater = (Layoutiflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(context);
        this.mDataList = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.grid_menu_list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(v);

        return holder;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        YiyecekMenuItem tiklananItem = mDataList.get(position);
        holder.setData(tiklananItem,position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void tiklananMenuCagir(int pos){
        YiyecekSayfasi girisSayfasi;
        girisSayfasi = (YiyecekSayfasi) context;
        girisSayfasi.tiklananMenuItem(pos);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mText;
        int tiklananPos;
        RelativeLayout list_layout;




        public MyViewHolder(View itemView) {
            super(itemView);


            list_layout = itemView.findViewById(R.id.list_layout);

            mText = itemView.findViewById(R.id.list_text);
            mText.setTextColor(context.getResources().getColor(R.color.buton_rengi_pres));

            list_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tiklananMenuCagir(tiklananPos);
                }
            });
            mText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tiklananMenuCagir(tiklananPos);
                }
            });



        }

        public void setData(YiyecekMenuItem tiklananItem, int position) {
            mText.setTextSize(16);
            this.mText.setText(tiklananItem.getAdi());
            this.tiklananPos = position;
        }



    }
}
