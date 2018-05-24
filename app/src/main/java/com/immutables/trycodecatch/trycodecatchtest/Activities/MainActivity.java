package com.immutables.trycodecatch.trycodecatchtest.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.immutables.trycodecatch.trycodecatchtest.ApplicationContext;
import com.immutables.trycodecatch.trycodecatchtest.DonationsAdapter;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.DonationResponse;
import com.immutables.trycodecatch.trycodecatchtest.Models.BackendModels.DonationResponseData;
import com.immutables.trycodecatch.trycodecatchtest.R;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Response;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        mRecyclerView = (RecyclerView) findViewById(R.id.donationsRecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecor);
        final ArrayList<DonationResponseData> recyclerData = new ArrayList<>();
        DonationResponseData emptyData = new DonationResponseData();
        emptyData.modifiedAt = "";
        emptyData.description = "";
        emptyData.user = null;
        emptyData.id = "";
        recyclerData.add(emptyData);
        DonationResponseData firstData = new DonationResponseData();
        firstData.modifiedAt = "2018-01-23";
        firstData.description = "Prvo davanje krvi";
        firstData.user = null;
        firstData.id = "";
        recyclerData.add(firstData);
        DonationResponseData secondData = new DonationResponseData();
        secondData.modifiedAt = "2017-02-23";
        secondData.description = "Drugo davanje krvi";
        secondData.user = null;
        secondData.id = "";
        recyclerData.add(secondData);
        DonationResponseData thirdData = new DonationResponseData();
        thirdData.modifiedAt = "2018-06-13";
        thirdData.description = "Trece davanje krvi";
        thirdData.user = null;
        thirdData.id = "";
        recyclerData.add(thirdData);
        final Call<DonationResponse> donationCall = ApplicationContext.backendService.getUserDonations(ApplicationContext.token.accessToken, ApplicationContext.loggedInUser.id);


        mAdapter = new DonationsAdapter(recyclerData);
        mRecyclerView.setAdapter(mAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View header = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        ImageView QRImageView = (ImageView) header.findViewById(R.id.QR_image_view);
        ((TextView) header.findViewById(R.id.user_textview)).setText(ApplicationContext.loggedInUser.firstName + " " + ApplicationContext.loggedInUser.lastName);
        ((TextView) header.findViewById(R.id.userEmail)).setText(ApplicationContext.loggedInUser.email);
        String QRCodeString = ApplicationContext.loggedInUser.email; // Whatever you need to encode in the QR code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(QRCodeString, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            QRImageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout)
        {
            SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.putString("username", null);
            editor.putString("password", null);
            editor.putString("accessToken", null);
            editor.apply();
            ApplicationContext.token = null;
            ApplicationContext.loggedInUser = null;
            Intent loginActivityIntent = new Intent(MainActivity.this, LoginActivity.class);
            loginActivityIntent.setFlags(loginActivityIntent.getFlags() | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginActivityIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.questions)
        {
            Intent answersActivityIntent = new Intent(MainActivity.this, AnswersActivity.class);
            startActivity(answersActivityIntent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
