package com.example.myapplicationdia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    BottomNavigationView bottomNavigationView;
    ImageView qrCodeView;
    private TextView[] mDots;
    private LinearLayout mDotLayout;
    private ViewPager2 mSlideViewPager;
    private ConstraintLayout parentId;
    private Singlton singlton;
    TextView tvNamePassport;

//    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         bottomNavigationView = findViewById(R.id.buttonNV);
         bottomNavigationView.setSelectedItemId(R.id.docoments);

         bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                 switch (item.getItemId()) {
                     case R.id.docoments:
                        break;
                     case R.id.service:
                         startActivity(new Intent(getApplicationContext(),Services_Activity.class));
                         overridePendingTransition(0,0);
                         break;
                     case R.id.notif:
                         startActivity(new Intent(getApplicationContext(),Notification_Activity.class));
                         overridePendingTransition(0,0);
                         break;
                     case R.id.menu:
                         startActivity(new Intent(getApplicationContext(),Menu_Activity.class));
                         overridePendingTransition(0,0);
                         break;
                 }
                 return false;
             }
         });
        singlton = Singlton.getInstance();

        mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
        tvNamePassport = findViewById(R.id.tvNamePass);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        mSlideViewPager.setAdapter(viewPagerAdapter);
        addDotsIndicator(0);

//      зазор между фрагментами с 67 по 83 строку
        mSlideViewPager.setClipToPadding(false);
        mSlideViewPager.setClipChildren(false);
        mSlideViewPager.setOffscreenPageLimit(3);
        mSlideViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        mSlideViewPager.setPageTransformer(compositePageTransformer);

        mSlideViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                addDotsIndicator(position);
            }
        });

        qrCodeView = findViewById(R.id.qrCode);
        qrCodeView.setOnClickListener(this);

    }

    public void addDotsIndicator(int position){

        mDots = new  TextView[3];
        for (int i = 0; i < mDots.length; i++) {
            mDots[i]= new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.black));
            parentId = findViewById(R.id.clParent);
            switch (position) {
                case 0:  parentId.setBackground(getResources().getDrawable(R.color.grey_1));
                    break;
                case 1:  parentId.setBackground(getResources().getDrawable(R.color.purple_500));
                    break;
                case 2:  parentId.setBackground(getResources().getDrawable(R.color.teal_200));
                    break;
                case 3:  mSlideViewPager.setBackground(getResources().getDrawable(R.color.purple_200));
                    break;
            }
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
//  QR сканер по 194 строку
    @Override
    public void onClick(View view) {
        scanCode();
    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        scanCode();
                    }
                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else {
                Toast.makeText(this,"no results", Toast.LENGTH_LONG).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}