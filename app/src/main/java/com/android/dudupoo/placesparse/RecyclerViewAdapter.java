package com.android.dudupoo.placesparse;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.dudupoo.placesparse.pojo.place.Place;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rachnagoel on 15/11/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{
    private ArrayList<String> imagesURLArray;
    private ArrayList<Place> placesArrayList;
    private Context context;
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView textView;
        private CardView cardView;
        private RatingBar ratingBar;
        public MyViewHolder(CardView itemView)
        {
            super(itemView);
            this.cardView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.cardImageView);
            textView = (TextView) cardView.findViewById(R.id.placeNameTextView);
            ratingBar = (RatingBar) cardView.findViewById(R.id.placeRating);
        }
    }

//    public RecyclerViewAdapter(ArrayList<Image> imagesArray, ArrayList<String> text)
//    {
//        this.imagesArray = imagesArray;
//        this.textArray = text;
//    }

    public RecyclerViewAdapter(ArrayList<Place> placesArrayList, ArrayList<String> placePhotoURLArrayList, Context context)
    {
        this.placesArrayList = placesArrayList;
        this.imagesURLArray = placePhotoURLArrayList;
        this.context = context;
        Log.e("ARRAY", placesArrayList.toString());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_other, parent, false);

        MyViewHolder viewHolder = new MyViewHolder((CardView) v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Place place = placesArrayList.get(position);
        holder.textView.setText(place.getPlaceName());
        if(imagesURLArray.get(position) == "NULL_URL")
        {
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        }
        else
        {
            Picasso.with(context).load(imagesURLArray.get(position)).into(holder.imageView);
        }
        holder.ratingBar.setRating((float) place.getRating());
    }

    @Override
    public int getItemCount()
    {
        return placesArrayList.size();
    }


}
