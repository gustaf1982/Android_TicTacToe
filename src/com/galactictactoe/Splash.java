package com.galactictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class Splash extends Activity {

	ImageView ciit, earthmaniebo, michaelalcaraz, adamguiritan;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		ciit = (ImageView) findViewById(R.id.ciit);
		earthmaniebo = (ImageView) findViewById(R.id.earthmaniebo);
		michaelalcaraz = (ImageView) findViewById(R.id.michaelalcaraz);
		adamguiritan = (ImageView) findViewById(R.id.adamguiritan);
		
		final Animation fadeIn1 = new AlphaAnimation(0.0f, 1.0f);
		fadeIn1.setDuration(2000);
		fadeIn1.setStartOffset(2000);
		
		final Animation fadeIn2 = new AlphaAnimation(0.0f, 1.0f);
		fadeIn2.setDuration(1500);
		
		final Animation fadeIn3 = new AlphaAnimation(0.0f, 1.0f);
		fadeIn3.setDuration(1500);
		
		final Animation fadeIn4 = new AlphaAnimation(0.0f, 1.0f);
		fadeIn4.setDuration(1500);
		
		ScaleAnimation zoomIn = new ScaleAnimation(0, 1, 0, 1);
		zoomIn.setDuration(2000);
		ciit.startAnimation(zoomIn);		
		
		
		fadeIn1.setAnimationListener(new AnimationListener(){

	        @Override
	        public void onAnimationEnd(Animation arg0) {
	        	// TODO Auto-generated method stub
	        	earthmaniebo.startAnimation(fadeIn2);
	        	
	        	Handler em = new Handler();
	    		em.postDelayed(new Runnable() {
	    			public void run() { 
	    				earthmaniebo.setVisibility(View.VISIBLE);
	    	         }
	    		}, 1500);
	        }

	        @Override
	        public void onAnimationRepeat(Animation arg0) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void onAnimationStart(Animation arg0) {
	            // TODO Auto-generated method stub

	        }
	    });
		
		fadeIn2.setAnimationListener(new AnimationListener(){

	        @Override
	        public void onAnimationEnd(Animation arg0) {
	        	// TODO Auto-generated method stub
	        	michaelalcaraz.startAnimation(fadeIn3);
	        	Handler ma = new Handler();
	    		ma.postDelayed(new Runnable() {
	    			public void run() { 
	    				michaelalcaraz.setVisibility(View.VISIBLE);
	    	         }
	    		}, 1500);
	        }

	        @Override
	        public void onAnimationRepeat(Animation arg0) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void onAnimationStart(Animation arg0) {
	            // TODO Auto-generated method stub

	        }
	    });
		
		fadeIn3.setAnimationListener(new AnimationListener(){

	        @Override
	        public void onAnimationEnd(Animation arg0) {
	        	// TODO Auto-generated method stub
	        	adamguiritan.startAnimation(fadeIn4);
	        	Handler ag = new Handler();
	    		ag.postDelayed(new Runnable() {
	    			public void run() { 
	    				adamguiritan.setVisibility(View.VISIBLE);
	    	         }
	    		}, 1500);
	        }

	        @Override
	        public void onAnimationRepeat(Animation arg0) {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void onAnimationStart(Animation arg0) {
	            // TODO Auto-generated method stub

	        }
	    });
		
		Handler splash = new Handler();
		splash.postDelayed(new Runnable() {
			public void run() { 
				startActivity(new Intent("com.galactictactoe.MAIN"));
				finish();
				System.exit(0);
	         }
		}, 10000);
	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
}
