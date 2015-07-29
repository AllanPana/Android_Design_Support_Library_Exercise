package com.androiddesignsupport.allan.adsl_exercise.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.androiddesignsupport.allan.adsl_exercise.R;
import com.androiddesignsupport.allan.adsl_exercise.pojos.Flower;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by allan on 23/07/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private String URL_PHOTOS ="http://services.hanselandpetal.com/photos/";
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Flower> dataModelClassList;
    private int mposition = 1;

    public RecyclerViewAdapter(Context context,List<Flower> dataModelClassList ){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.dataModelClassList = dataModelClassList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.custom_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        //String linkLocation = "http://cdn.bigbangfish.com/beautiful/beautiful-flowers/beautiful-flowers-9.jpg";
        Flower modelClass = dataModelClassList.get(position);
        holder.textView.setText(modelClass.getName());
        //Picasso.with(context).load(linkLocation).resize(300, 300).into(holder.imageView);
        Picasso.with(context).load(URL_PHOTOS +modelClass.getPhoto()).resize(300,300).into(holder.imageView);


        Animation animation = AnimationUtils.loadAnimation(context,
                (mposition > position) ? R.anim.up_from_bottom : R.anim.down_from_top);

        holder.imageView.startAnimation(animation);
        holder.textView.startAnimation(animation);

        position = mposition;
        
    }

    @Override
    public int getItemCount() {
        return dataModelClassList.size();
    }


    /**
     * ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.imageViewId)
        ImageView imageView;

        @Bind(R.id.textViewTitle)
        TextView textView;


        public MyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
