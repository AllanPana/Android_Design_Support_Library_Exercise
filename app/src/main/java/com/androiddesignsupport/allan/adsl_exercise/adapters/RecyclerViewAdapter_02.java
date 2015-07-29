package com.androiddesignsupport.allan.adsl_exercise.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.androiddesignsupport.allan.adsl_exercise.MyApplication;
import com.androiddesignsupport.allan.adsl_exercise.R;
import com.androiddesignsupport.allan.adsl_exercise.activities.FlowerDetailActivity;
import com.androiddesignsupport.allan.adsl_exercise.network.VolleySingleton;
import com.androiddesignsupport.allan.adsl_exercise.pojos.Flower;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by allan on 23/07/15.
 */
public class RecyclerViewAdapter_02 extends RecyclerView.Adapter<RecyclerViewAdapter_02.MyViewHolder>{

    private String URL_PHOTOS ="http://services.hanselandpetal.com/photos/";
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Flower> dataModelClassList;
    ImageLoader imageLoader;
    private Bitmap myBitmap;

    private String strPhotoUrl, name,category, instruction, photo;
    private double price;

    int mposition = -1;

    public RecyclerViewAdapter_02(Context context, List<Flower> dataModelClassList){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.dataModelClassList = dataModelClassList;

        imageLoader = VolleySingleton.getVolleySingletonInstance().getImageLoader();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.custom_layout_02,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Flower modelClass = dataModelClassList.get(position);


        name = modelClass.getName();
        price = modelClass.getPrice();
        category = modelClass.getCategory();
        instruction = modelClass.getInstructions();
        photo = modelClass.getPhoto();
        strPhotoUrl = URL_PHOTOS+ photo;

        holder.textView.setText(name);
        loadImage(strPhotoUrl, holder);


        Animation animation = AnimationUtils.loadAnimation(context,
                (mposition > position) ? R.anim.up_from_bottom : R.anim.down_from_top);

        holder.imageView.startAnimation(animation);
        holder.textView.startAnimation(animation);

        position = mposition;
    }



    /**
     *  Use the VolleySingleton and call the imageLoader to get the image
     *  call this method in onBindViewHolder() to get the thumbnailImage
     * @param urlThumbnail
     * @param holder
     */
    private void loadImage(String urlThumbnail, final MyViewHolder holder){

            imageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    if(imageContainer.getBitmap() != null){
                        myBitmap = imageContainer.getBitmap();
                        holder.imageView.setImageBitmap(myBitmap);
                    }else {
                        //set default image
                    }

                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(context, volleyError+"", Toast.LENGTH_SHORT).show();
                }
            },300,300);
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

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(context,name,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, FlowerDetailActivity.class);
                    intent.putExtra("strPhotoUrl", strPhotoUrl);
                    intent.putExtra("bitmap",myBitmap);
                    intent.putExtra("price",price);
                    intent.putExtra("category",category);
                    intent.putExtra("instruction", instruction);
                    intent.putExtra("name",name);
                    context.startActivity(intent);

                    ((AppCompatActivity) context).overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_top);
                }
            });
        }
    }
}
