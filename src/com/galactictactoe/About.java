package com.galactictactoe;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class About extends Activity /*implements OnClickListener */{

	Button back_about;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		//back_about = (Button) findViewById(R.id.back_about);
		//back_about.setOnClickListener((android.view.View.OnClickListener) this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	//@Override
	/*public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.back_about)
		{
			finish();
			System.exit(0);
			startActivity(new Intent(this, GalacTicTacToe.class));
		}
	}*/
	
}
