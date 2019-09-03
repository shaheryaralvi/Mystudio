package com.mian.MZ;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = DrawerActivity.class.getName();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        context = DrawerActivity.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView email = header.findViewById(R.id.navHeaderUserEmail);
        TextView name = header.findViewById(R.id.navHeaderUserName);
        ImageView userImage = header.findViewById(R.id.navHeaderUserImageView);
        email.setText("hamza@gmail.com");
        name.setText("hamza");
        userImage.setImageResource(R.drawable.profile);

        setTitleInAppBar("Home");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            setTitleInAppBar("Home");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
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
            Toast.makeText(context, item.getTitle(), Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.action_profile) {
            Toast.makeText(context, item.getTitle(), Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        clearFragmentBackStack();


        if (id == R.id.nav_camera) {
            // Handle the camera action
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new Camera(context)).addToBackStack(null).commit();

            setTitleInAppBar(Camera.class.getSimpleName());

        } else if (id == R.id.nav_gallery) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new Gallery(context), Gallery.class.getName() ).addToBackStack(null).commit();
            setTitleInAppBar(Gallery.class.getSimpleName());

        } else if (id == R.id.nav_slideshow) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home,new Slideshow(context)).addToBackStack(null).commit();

            setTitleInAppBar(Slideshow.class.getSimpleName());

        } else if (id == R.id.nav_manage) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home,new Tools(context)).addToBackStack(null).commit();
            setTitleInAppBar(Tools.class.getSimpleName());


        } else if (id == R.id.nav_share) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home,new Share(context)).addToBackStack(null).commit();

            setTitleInAppBar(Share.class.getSimpleName());



        } else if (id == R.id.nav_send) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home,new Send(context)).addToBackStack(null).commit();

            setTitleInAppBar(Send.class.getSimpleName());


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void clearFragmentBackStack(){

        for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++){

            getSupportFragmentManager().popBackStack();

        }

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        Log.e(TAG, "onAttachFragment: " );
        setTitleInAppBar( fragment.getTag() );

    }

    private void setTitleInAppBar(String title){

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);

    }

    private android.support.v4.app.Fragment getVisibleFragment() {

        for (android.support.v4.app.Fragment fragment : getSupportFragmentManager().getFragments()){

            if (fragment != null && fragment.isVisible())
                return fragment;

        }

        return null;
    }
}
