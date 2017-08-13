package com.example.anmol.events.Events;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.anmol.events.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by anmol on 13/8/17.
 */

public class DetailsOfEvents extends AppCompatActivity {

    RelativeLayout rel;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_of_event);

        rel= (RelativeLayout) findViewById(R.id.RelativeLayoutDetailsOfEvents);
        imageView= (ImageView) findViewById(R.id.ImageViewDetailsOfEvents);

        String url=getIntent().getExtras().getString("ImageUrl");


        Picasso.with(DetailsOfEvents.this).load(url).fit().into(imageView,
                new Callback() {
                    @Override
                    public void onSuccess() {
                        scheduleStartPostponedTransition(imageView);
                    }

                    @Override
                    public void onError() {

                    }
                });


        Glide.with(DetailsOfEvents.this).load(url).bitmapTransform(new BlurTransformation(DetailsOfEvents.this,50)).into(new SimpleTarget<GlideDrawable>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                rel.setBackground(resource);

            }
        });



    }


    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }

}
