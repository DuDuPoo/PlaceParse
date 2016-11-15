package com.android.dudupoo.placesparse;

import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rachnagoel on 15/11/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{
    private ArrayList<Image> imagesArray;
    private ArrayList<String> textArray;
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView textView;
        private CardView cardView;
        public MyViewHolder(CardView itemView)
        {
            super(itemView);
            this.cardView = itemView;
//            imageView = (ImageView) itemView.findViewById(R.id.cardImageView);
            textView = (TextView) cardView.findViewById(R.id.cardTextView);
        }
    }

//    public RecyclerViewAdapter(ArrayList<Image> imagesArray, ArrayList<String> text)
//    {
//        this.imagesArray = imagesArray;
//        this.textArray = text;
//    }

    public RecyclerViewAdapter(ArrayList<String> namesArray)
    {
        this.textArray = namesArray;
        Log.e("ARRAY", textArray.toString());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);

        MyViewHolder viewHolder = new MyViewHolder((CardView) v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.textView.setText(textArray.get(position));
    }

    @Override
    public int getItemCount()
    {
        return textArray.size();
    }


}
