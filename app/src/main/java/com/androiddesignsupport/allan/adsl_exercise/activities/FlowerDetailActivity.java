package com.androiddesignsupport.allan.adsl_exercise.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.androiddesignsupport.allan.adsl_exercise.R;
import com.androiddesignsupport.allan.adsl_exercise.network.VolleySingleton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FlowerDetailActivity extends AppCompatActivity {

    ImageLoader imageLoader;


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.image)
    ImageView imageView;

    @Bind(R.id.tv_price)
    TextView tv_price;

    @Bind(R.id.tv_category)
    TextView tv_category;

    @Bind(R.id.tv_instruction)
    TextView tv_instruction;



    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_detail);



        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        getSupportActionBar().setTitle(intent.getStringExtra("name"));
        tv_price.setText("$" + intent.getDoubleExtra("price", 0.00));
        tv_category.setText(intent.getStringExtra("category"));
        tv_instruction.setText(intent.getStringExtra("instruction"));


        bitmap = intent.getParcelableExtra("bitmap");
        imageView.setImageBitmap(bitmap);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_in_top);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flower_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
