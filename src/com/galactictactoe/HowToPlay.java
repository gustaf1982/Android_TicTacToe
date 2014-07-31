package com.galactictactoe;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HowToPlay extends Activity /*implements OnClickListener*/ {

	Button back_howtoplay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howtoplay);
		
		/*back_howtoplay = (Button) findViewById(R.id.back_howtoplay);
		back_howtoplay.setOnClickListener((android.view.View.OnClickListener) this);*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	/*@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.back_howtoplay)
		{
			finish();
			System.exit(0);
			startActivity(new Intent(this, GalacTicTacToe.class));
		}
	}*/
	
}
