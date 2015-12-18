package com.technorio.inc.bloodbankplus;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tenzin on 12/1/2015.
 */
public class ImageAdapter extends PagerAdapter {
    int size;
    Activity act;
    View layout;
    private TextView pagenumber1,pagenumber2,pagenumber3,pagenumber4;
    ImageView pageImage;
    Button click;
    public ImageAdapter(HowitWorks mainActivity, int noofsize) {
        // TODO Auto-generated constructor stub
        size = noofsize;
        act = mainActivity;
    }

    public ImageAdapter(Working working, int sizee) {
        size= sizee;
        act= working;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return size;
    }
    @Override
    public Object instantiateItem(View container, int position) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) act
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.work, null);
        pagenumber1 = (TextView)layout.findViewById(R.id.pagenumber1);
        pagenumber2 = (TextView)layout.findViewById(R.id.pagenumber2);
        pagenumber3 = (TextView)layout.findViewById(R.id.pagenumber3);
        pagenumber4 = (TextView)layout.findViewById(R.id.pagenumber4);
        pageImage = (ImageView)layout.findViewById(R.id.imageView1);
        int pagenumberTxt=position + 1;
        //pagenumber1.setText("Now your in Page No  " +pagenumberTxt );

        try {
            if(pagenumberTxt == 1){
                pageImage.setBackgroundResource(R.drawable.a);
                pagenumber1.setTextColor(Color.BLUE);
                pagenumber2.setTextColor(Color.WHITE);
                pagenumber3.setTextColor(Color.WHITE);
                pagenumber4.setTextColor(Color.WHITE);
               }
            else if(pagenumberTxt == 2){
                pageImage.setBackgroundResource(R.drawable.b);
                pagenumber1.setTextColor(Color.WHITE);
                pagenumber2.setTextColor(Color.BLUE);
                pagenumber3.setTextColor(Color.WHITE);
                pagenumber4.setTextColor(Color.WHITE);
               }else if(pagenumberTxt == 3){
                pageImage.setBackgroundResource(R.drawable.c);
                pagenumber1.setTextColor(Color.WHITE);
                pagenumber2.setTextColor(Color.WHITE);
                pagenumber3.setTextColor(Color.BLUE);
                pagenumber4.setTextColor(Color.WHITE);
               }
            else if(pagenumberTxt == 4){
                pageImage.setBackgroundResource(R.drawable.d);
                pagenumber1.setTextColor(Color.WHITE);
                pagenumber2.setTextColor(Color.WHITE);
                pagenumber3.setTextColor(Color.WHITE);
                pagenumber4.setTextColor(Color.BLUE);
               }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ((ViewPager) container).addView(layout, 0);
        return layout;
    }
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }
    @Override
    public Parcelable saveState() {
        return null;
    }

    // }
}

