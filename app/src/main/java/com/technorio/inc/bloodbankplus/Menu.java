package com.technorio.inc.bloodbankplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Menu extends AppCompatActivity  implements View.OnClickListener,BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    int size = 5;
    ViewPager myPager = null;
    int count = 0;
    Timer timer;
    private Button btnRegister, btnEmergency, btnAbout, btnMessage;
    static TextView mDotsText[];
    private int mDotsCount;
    private LinearLayout mDotsLayout;
    private SliderLayout mDemoSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        initializing();
//        btnSupport.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnEmergency.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnMessage.setOnClickListener(this);




        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("BloodBank",R.drawable.banner);
        file_maps.put("DonateBlood",R.drawable.blackb);
        file_maps.put("SaveLife",R.drawable.redb);


        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        //ListView l = (ListView)findViewById(R.id.transformers);
        // l.setAdapter(new TransformerAdapter(this));
        // l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        /*    @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
                Toast.makeText(ViewPagerAdapter.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });*/


    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }



    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_custom_indicator:
                mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
                break;

            case R.id.action_restore_default:
                mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                break;
            case R.id.action_github:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia/AndroidImageSlider"));
                startActivity(browserIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
//        image slide with timer
       // ViewPagerAdapter adapter = new ViewPagerAdapter(Menu.this, size);
      /*  myPager = (ViewPager) findViewById(R.id.reviewpager);
        myPager.setAdapter(adapter);
        myPager.setCurrentItem(0);

        // Timer for auto sliding
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count <= 5) {
                            myPager.setCurrentItem(count);
                            count++;
                        } else {
                            count = 0;
                            myPager.setCurrentItem(count);
                        }
                    }
                });
            }
        }, 500, 7000);*/
    //}

    //    initialzing the button of the class
    public void initializing()
    {
        btnRegister =(Button ) findViewById(R.id.btnRegister);
        btnEmergency =(Button) findViewById(R.id.btnEmergency);
        btnAbout =(Button) findViewById(R.id.btnAbout);
        btnMessage =(Button) findViewById(R.id.btnMessage);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnRegister:
                startActivity(new Intent(Menu.this, Register.class));
                finish();
                break;
            case R.id.btnEmergency:
                startActivity(new Intent(Menu.this,Emergency.class));
                finish();
                break;
            case R.id.btnAbout:
                startActivity(new Intent(Menu.this,About.class));
                finish();
                break;
            case R.id.btnMessage:
                startActivity(new Intent(Menu.this,Message.class));
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.info:

                startActivity(new Intent(Menu.this,HowitWorks.class));
                finish();

                  break;
            // action with ID action_settings was selected
            case R.id.dis:
               startActivity(new Intent(Menu.this, Disclaimer.class));
                finish();
                break;
            default:
                break;
        }

        return true;
    }
}
