package com.androiddesignsupport.allan.adsl_exercise.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.androiddesignsupport.allan.adsl_exercise.R;
import com.androiddesignsupport.allan.adsl_exercise.adapters.ViewPagerAdapter;
import com.androiddesignsupport.allan.adsl_exercise.fragments.MyFragment;
import com.androiddesignsupport.allan.adsl_exercise.fragments.MyFragment_02;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Bind(R.id.drawer_layout_main)
    DrawerLayout drawerLayout;

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    ActionBarDrawerToggle drawerToggle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        setUpDrawerToggle();
        setUpTabsAndViewPager();
        setFab();
    }




    void setUpDrawerToggle(){
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }


    void setUpTabsAndViewPager() {
        ViewPagerAdapter viewPagerAdapter =
                new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragmentInTab(new MyFragment(),"Retrofit");
        viewPagerAdapter.addFragmentInTab(new MyFragment_02(),"Volley");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    void setFab(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Snackbar",Snackbar.LENGTH_LONG).setAction(null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
