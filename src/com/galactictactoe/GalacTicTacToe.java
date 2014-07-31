package com.galactictactoe;



import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GalacTicTacToe extends Activity implements OnClickListener {

	MediaPlayer bg_sfx, menu_click, planet_click, win, lose, draw;
	private static int game_difficulty = 0;
	boolean isTwoPlayer = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galac_tic_tac_toe);
        
        final ImageView startgame = (ImageView) findViewById(R.id.startgame);
        final ImageView howtoplay = (ImageView) findViewById(R.id.howtoplay);
        final ImageView about = (ImageView) findViewById(R.id.about);
        final ImageView quit = (ImageView) findViewById(R.id.quit);
        
        startgame.setOnClickListener((android.view.View.OnClickListener) this);
        howtoplay.setOnClickListener((android.view.View.OnClickListener) this);
        about.setOnClickListener((android.view.View.OnClickListener) this);
        quit.setOnClickListener((android.view.View.OnClickListener) this);
        
        bg_sfx = MediaPlayer.create(this, R.raw.bg_sounds);
        menu_click = MediaPlayer.create(this, R.raw.menu_click);
        planet_click = MediaPlayer.create(this, R.raw.planet_click);
        win = MediaPlayer.create(this, R.raw.win2);
        lose = MediaPlayer.create(this, R.raw.lose);
        draw = MediaPlayer.create(this, R.raw.draw);
        bg_sfx.setLooping(true);
        bg_sfx.start();
    }
    
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
		bg_sfx.release();
	}
    
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_gala_tic_tac_toe, menu);
        return true;
    }
    
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		final ImageView iv = (ImageView) v;
    	if(iv.getId() == R.id.startgame) 
    	{
    		menu_click.start();
    		mode_select();
    		//symbol_select();
    	}
    	
    	if(iv.getId() == R.id.howtoplay) 
    	{
    		menu_click.start();
    		startActivity(new Intent(this, HowToPlay.class));
    	}
    	
    	if(iv.getId() == R.id.about) 
    	{
    		menu_click.start();
    		startActivity(new Intent(this, About.class));
    	}
    	
    	if(iv.getId() == R.id.quit) 
    	{
    		finish();
    		System.exit(0);
    	}
	}
	
	protected Dialog onCreateDialog(int id) {
        Dialog mdialog = new Dialog(this);
        switch(id) {
        case NAME_DIALOG_ID:
        	if(game_mode == 1)
        	{
        		mdialog.setContentView(R.layout.name_dialog);
        		final Spinner difficulty = (Spinner) mdialog.findViewById(R.id.select_difficulty);
        		difficulty.setOnItemSelectedListener(new OnItemSelectedListener() {

    				@Override
    				public void onItemSelected(AdapterView<?> arg0, View arg1,
    						int arg2, long arg3) {
    					// TODO Auto-generated method stub
    					Log.e(String.valueOf(arg2), "ssssssssssssssssssim");
    					game_difficulty = arg2;
    				}

    				@Override
    				public void onNothingSelected(AdapterView<?> arg0) {
    					// TODO Auto-generated method stub
    					
    				}
    			});
        	}
        	else if(game_mode == 0)
        	{
        		mdialog.setContentView(R.layout.name_dialog_2);
        	}
        	
    		mdialog.setTitle("Player Names");
    		mdialog.setCancelable(true);
    		
    		final EditText namep1 = (EditText) mdialog.findViewById(R.id.namep1);
    		final EditText namep2 = (EditText) mdialog.findViewById(R.id.namep2);
    		final EditText name = (EditText) mdialog.findViewById(R.id.name);
    		
        
    		Button ok_b = (Button) mdialog.findViewById(R.id.ok);
    		ok_b.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				if(game_mode == 1)
    				{
    					player_name_2 = name.getText();
    					score_player_1 = 0;
        				score_player_2 = 0;
        				new_game(name.getText());
        				dismissDialog(1);
    				}
    				
    				else if(game_mode == 0)
    				{
    					player_name_2 = namep1.getText();
        				player_name_1 = namep2.getText();
        				score_player_1 = 0;
        				score_player_2 = 0;
        				new_game(namep2.getText());
        				dismissDialog(1);
    				}
    				
    			}
    		});
            break;
        default:
            mdialog = null;
        }
        //mode_select();
        return mdialog;
        
    }
	
	public void mode_select()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Choose your game mode : ")
        			.setPositiveButton("Vs Computer", new DialogInterface.OnClickListener() {
        				public void onClick(DialogInterface dialog, int id) {
        					Toast.makeText(getApplicationContext(), "Mode changed to Vs Computer", Toast.LENGTH_SHORT).show();
        					game_mode = 1;
        					score_player_1 = 0;
        					score_player_2 = 0;
        					showDialog(NAME_DIALOG_ID);
        					}
        			})
        			
        			.setNegativeButton("Vs Human", new DialogInterface.OnClickListener() {
        				public void onClick(DialogInterface dialog, int id) {
        					Toast.makeText(getApplicationContext(), "Play against your Opponent !", Toast.LENGTH_SHORT).show();
        					game_mode = 0;
        					score_player_1 = 0;
        					score_player_2 = 0;
        					showDialog(NAME_DIALOG_ID);
        				}
        			});
        builder.show();
        //symbol_select();
        return;
    }
	
	public void symbol_select() {
    	AlertDialog.Builder symbol_builder = new AlertDialog.Builder(this);
        symbol_builder.setMessage("Select Your Planet");
        symbol_builder.setCancelable(false);
        symbol_builder.setNegativeButton("Mars", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int id) {
        		user_symbol = 0;
        		new_game(player_name_1);
        	}
        });
        
        symbol_builder.setPositiveButton("Earth", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				user_symbol = 1;
        		new_game(player_name_1); 
			}
		});
        symbol_builder.show();
    }
	// **************************** Global variables ********************************************
    int count = 0;				// to count the number of moves made.
    int arr[][] = 
    {{0,0,0},{0,0,0},{0,0,0}};	// array which stores the movements made.
    int player = 1;				// sets the player no. to 1 by default.
	int game_mode = 1;			// default 0 : h Vs h ; 1 : h Vs Comp
    int analysis_arr[][] = 
		{{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};	// analysis_arr
	
	int map_arr[][] = 
		{{1,1,1},{1,1,1},{1,1,1}};	// friend and enemy map initialization.
	
	int user_symbol = 0;			// default 0: 0 to user, X to computer.
	boolean sound_enabled = true;	// default  : sound ON.
	
	int skin = 4;		// def:0; gal:1; ninja:2; red:3; system:4;
	int skin_cross = R.drawable.earth;	// default values.
	int skin_dot = R.drawable.mars;		// default values.
	int skin_layout = R.layout.game_layout;			// default values.
	int game_bg = 2;
	
	// player names initialized with default values.
	CharSequence player_name_1 = "Player 1";
	CharSequence player_name_2 = "Player 2";
	
	// score initialized to 0.
	int score_player_1 = 0;
	int score_player_2 = 0;
	
	// menu item numbers.
	int MENU_NEW_GAME = 0;
    int MENU_OPTIONS = 1;
    int MENU_QUIT = 2;
    
    // dialog IDs
    final int NAME_DIALOG_ID = 1;
    final int HELP_DIALOG_ID = 2;
	
	//************************ End of Global Variable Declaration ********************************** 
    
    /**
	 * Common onClickListener for all the ImageButtons in the Game. 
	 * */
    OnClickListener button_listener = new View.OnClickListener() {
        public void onClick(View v) {
            ImageButton ibutton = (ImageButton) v;
            planet_click.start();
        	// Button inactive for further clicks until a result is obtained.
        	ibutton.setClickable(false);
        	
        	// Increment Count on clicking the button.
        	count++;
        	
            if ((count % 2 != 0) && (game_mode == 0)) {
            	player = 1;
                ibutton.setImageResource(skin_cross);
            }
            else if ((count % 2 == 0) || (game_mode == 1)) {
            	player = 2;			// human player.
            	if ((user_symbol == 0) && (game_mode == 1))
            		ibutton.setImageResource(skin_dot);
            	else if ((user_symbol == 1) && (game_mode == 1))
            		ibutton.setImageResource(skin_cross);
            	else
            		ibutton.setImageResource(skin_dot);
            }
            
            // after_move function to check the result and decide.
        	after_move(ibutton);
        }
    };
    
	/**
	 * Check the array 'arr' and returns the result.
	 * @return True if array is full.
	 */
    public boolean arr_isFull () {
    	for (int i = 0; i < 3; i++)
    		for (int j = 0; j < 3; j++)
    			if (arr[i][j] == 0)
    				return false;				
    	return true;
    }
    
    /**
     * Starting point for the Game. Includes calling of Computer Game,
     * result checking and the skin setting.
     * @param player_name : the name of the player.
     */
    public void new_game(CharSequence player_name) {
    	
    	// reset the game view. (this must be the first line in this function)
		setContentView (skin_layout);
		LinearLayout content = (LinearLayout)findViewById(R.id.content_admob);
		AdView adViewTab = new AdView(GalacTicTacToe.this, AdSize.BANNER, "ca-app-pub-5610387420094247/6858946467");
		content.addView(adViewTab);
		adViewTab.loadAd(new AdRequest());
		
    	final ImageButton b3 = (ImageButton) findViewById(R.id.b3);
        final ImageButton b2 = (ImageButton) findViewById(R.id.b2);
        final ImageButton b1 = (ImageButton) findViewById(R.id.b1);

        final ImageButton b6 = (ImageButton) findViewById(R.id.b6);
        final ImageButton b5 = (ImageButton) findViewById(R.id.b5);
        final ImageButton b4 = (ImageButton) findViewById(R.id.b4);
        
        final ImageButton b9 = (ImageButton) findViewById(R.id.b9);
        final ImageButton b8 = (ImageButton) findViewById(R.id.b8);
        final ImageButton b7 = (ImageButton) findViewById(R.id.b7);
        
        // set the OnClickListeners.
        b1.setOnClickListener(button_listener);
        b2.setOnClickListener(button_listener);
        b3.setOnClickListener(button_listener);
        b4.setOnClickListener(button_listener);
        b5.setOnClickListener(button_listener);
        b6.setOnClickListener(button_listener);
        b7.setOnClickListener(button_listener);
        b8.setOnClickListener(button_listener);
        b9.setOnClickListener(button_listener);
        
        // Re-enable the Click-able property of buttons.
        b1.setClickable(true);
        b2.setClickable(true);
        b3.setClickable(true);
        b4.setClickable(true);
        b5.setClickable(true);
        b6.setClickable(true);
        b7.setClickable(true);
        b8.setClickable(true);
        b9.setClickable(true);
        
        // dismissDialog(NAME_DIALOG_ID);
        // dismissDialog(HELP_DIALOG_ID);
        
        
        // update the score board with the already existing values.
        // this line should come ONLY after the player name is set in the above lines.
		set_score(3);	

    	 // reset the array arr.
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				arr[i][j] = 0; 
		
		/* *********************************************************
		 * Initiates the computer's chance during start of the game,
		 * as well as when there is a win / loose and the next
		 * chance is for the computer.
		 * ********************************************************* */
		if ((game_mode == 1) && (count % 2 != 0))
			CompGame();
    }
    
    /**
     * Checks for the result and Selects the next player.
     * @param ib : Image button that was clicked by user / computer.
     */
    public void after_move (ImageButton ib) {
    	CharSequence pos_str = "";				// position as a string.
    	int pos = 0;
    	boolean result = false;
  
    	pos_str = (CharSequence) ib.getTag();	// get the position from the tag.
    	pos = (int) pos_str.charAt(0) - 48;		// char to integer conversion.
	
    	// set the values in the array according to the player number.
    	if (player == 1) {
    		if (pos < 4)				
    			arr[0][pos - 1] = 1;
    		else if (pos < 7) 
    			arr[1][(pos - 1) % 3] = 1;
    		else if (pos < 10)
    			arr[2][(pos - 1) % 3] = 1;
    	}
    	else {
    		if (pos < 4)				
    			arr[0][pos - 1] = 2;
    		else if (pos < 7) 
    			arr[1][(pos - 1) % 3] = 2;
    		else if (pos < 10)
    			arr[2][(pos - 1) % 3] = 2;
    	}
    	
    	// Check for the game result.
    	result = result_check(player);
    		
    	// Result check section.
    	if (result == true) {
    		// 	check for the player number.
    		if (player == 1) {
    			set_score(1);
    			if (game_mode == 0) {
    				show_result("Congrats. " + player_name_1 + " wins !!");
    			}
    			else {
    				bg_sfx.pause();
    				lose.start();
    				Handler resumeBgAfterLose = new Handler();
    				resumeBgAfterLose.postDelayed(new Runnable() {
    					public void run() { 
    						bg_sfx.start(); 
    			         }
    				}, 4000);
    				show_result("Computer Wins !!");
    			}
    		}
    		else {
    			set_score(2);
    			if (game_mode == 0) {	// human vs human  
    				show_result("Congrats. " + player_name_2 + " wins !!");
    			}
    			else {	// human vs computer
    				bg_sfx.pause();
    				win.start();
    				Handler resumeBgAfterWin = new Handler();
    				resumeBgAfterWin.postDelayed(new Runnable() {
    					public void run() { 
    						bg_sfx.start(); 
    			         }
    				}, 5000);
    				show_result("Congrats. You have won !!");
    			}
    		}
    		return;
    	
    	}
    	else if ((result == false) && arr_isFull()) {
    		bg_sfx.pause();
    		draw.start();
    		Handler resumeBgAfterDraw = new Handler();
    		resumeBgAfterDraw.postDelayed(new Runnable() {
				public void run() { 
					bg_sfx.start(); 
		         }
			}, 4000);
    		show_result("    Game Draw !    ");				// leave the space, or else dialog becomes cramped.
    		return;
    	}
    	
    	// Next Player select section.
    	if ((game_mode == 1) && (player == 2) && (result == false)) {  // player 2 : next is computer (player 1)'s chance.
			// CompGame - plays the computer's chance.
		   	CompGame();
    	}
    	else { } // continue game.
    }
    
    /**
     *  sets the score board.
     *
     * @param Pass the player number, so that the score of the
     * corresponding player can be increased.
     */
    public void set_score(int player_number) {
    	TextView tv = (TextView) findViewById(R.id.scoreboard);
    	
    	if (player_number == 1)
    		score_player_1 += 1;
    	else if (player_number == 2)
    		score_player_2 += 1;
    	else ;							// Don't change score, but set the score board right.
    	
    	// player name and number relation.
    	if (game_mode == 1) {
    		player_name_1 = "Computer";
    	}
    		
    	CharSequence score_txt = 
    		player_name_1 + " : " + score_player_1 + "                   " + player_name_2 + " : " + score_player_2;
    	
    	tv.setText(score_txt);
    }
    
    /** 
     * Shows the result of the game in a separate dialog.
     * */
    public boolean show_result(CharSequence message)		//function to select the game mode
    {   
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
        			.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
        				public void onClick(DialogInterface dialog, int id) {
        		    		// reset the game environment.
        						new_game(player_name_1);
        				}
        			});
        AlertDialog alert = builder.create();
        alert.show();
        return true;
    }
    
    
    /**
     * Checks the result after each move.
     * @param player_local : the player number who has played the move.
     * @return True is any player has won.
     */
    public boolean result_check(int player_local) {
    	boolean win = true;
    	int k = 0;
    	
    	// check for horizontal condition only.
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			if (arr[i][j] != player_local) {		// check with player number.
    				win = false;
    				break;
    			}
    		} // column loop.
    		if (win == true) {
				return true;
    		}
    		win = true;
    	} // row loop.
    	
    	win = true;			// resetting win to true.
    	
    	// checking for vertical condition only.
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			if (arr[j][i] != player_local) {
    				win = false;
    				break;
    			}
    		} // column loop.
    		if (win == true) {
				return true;
    		}
    		win = true;
    	} // row loop.
    	
    	win = true;			// reset win to true.
    	
    	// check for diagonal condition 1.
    	for (int i = 0; i < 3; i++)
    		if (arr[i][k++] != player_local) {
    			win = false;
    			break;
    		}

    	if (win == true) {
    		return true;
    	}
    	
    	k = 2;
    	win = true;			// reset win to true;
    	
    	// check for diagonal condition 2.
    	for (int i = 0; i < 3; i++)
    		if (arr[i][k--] != player_local) {
    			win = false;
    			break;
    		}
    	
    	if (win == true) {
    		return true;
    	}
    	
       	return false;
    }
    
    /**
     * Master function for the computer's play (AI).
     */
    public void CompGame() {
    	player = 1;
    	count++;
    	analysis_array();
    	
    	if (game_difficulty == 0) {    		
    		f_e_map();
    		best_move();		
    	}
    	else if (game_difficulty == 1) {
    		if (easy_move_win() == true)
        		return;
    		else {
        		f_e_map();
        		best_move();
        	}
    	}
    	else {
    		if (easy_move_win() == true)
        		return;
        	else if (easy_move_block() == true)
        		return;
        	else {
        		f_e_map();
        		best_move();
        	}
    	} 	
    	
    }

    /**
     * best move calculation : the f_e_map is traversed to see the highest numbered
     * (x, y) position and the move is made.
     */
    public void best_move() {
    	int highest = 0, k = 0;	// k - increment the x_pos, y_pos.
    	int pos[][] = {{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
    	int random_index = 0;	// stores the random index number.
    	int x = 0, y = 0;		// compatibility with comp_play (int, int)
    	
    	// calculate the highest score in the map_arr.
    	for (int i = 0; i < 3; i++)
    		for (int j = 0; j < 3; j++)
    			if (map_arr[i][j] > highest)
    				highest = map_arr[i][j];
    	
    	// traverse map_arr and store all the highest score indices (x, y) in pos[][].
    	for(int i = 0; i < 3; i++)
    		for (int j = 0; j < 3; j++)
    			if (map_arr[i][j] == highest) {
    				pos[k][0] = i;
    				pos[k][1] = j;
    				k++;
    			}
    	
    	// get a random index ( <= k ).
    	random_index = ((int) (Math.random() * 10)) % (k);
    	x = pos[random_index][0];
    	y = pos[random_index][1];			
    	
    	comp_play(x, y);
    }

    /**
     * Creates a friend and enemy map, based on all available moves
     * and the current position of the game.
     * 
     * Searches for (1, 0) combination in analysis_array and then increment
     * the corresponding row/col/diagonal in map_arr by 1. Also, the elements
     * in map_arr with value = 0, are not changed.
     * 
     */
    public void f_e_map() {
    	int k = 0;	// for diagonal traversal.
    	
    	// reset map_arr to all 1's every time function is called.
    	for (int i = 0; i < 3; i++)
    		for (int j = 0; j < 3; j++)
    			map_arr[i][j] = 1;
    	
    	// search for existing moves and mark 0 in map_arr, if found in arr.
    	for (int i = 0; i < 3; i++)
    		for (int j = 0; j < 3; j++)
    			if ((arr[i][j] == 1) || (arr[i][j] == 2))
    				map_arr[i][j] = 0;

    	for (int i = 0; i < 8; i++) {
    		if (((analysis_arr[i][0] == 1) && (analysis_arr[i][1] == 0)) || ((analysis_arr[i][0] == 0) && (analysis_arr[i][1] == 1)))
    			if (i < 3) { 
    				for (int j = 0; j < 3; j++)
    					if (map_arr[i][j] != 0)
    						map_arr[i][j] += 1;
    			}
    			else if (i < 6) {
    				for (int j = 0; j < 3; j++)
    					if (map_arr[j][i - 3] != 0)
    						map_arr[j][i - 3] += 1;
    			}
    			else if (i == 6) {
    				k = 0;
    				for (int m = 0; m < 3; m++) {
    					if (map_arr[m][k] != 0)
    						map_arr[m][k] += 1;
    					k++;
    				}
    			}
    			else if (i == 7) {
    				k = 2;
    				for (int m = 0; m < 3; m++) {
    					if (map_arr[m][k] != 0)
    						map_arr[m][k] += 1;
    					k--;
    				}
    			}
    	}
    }
    
    /**
     * Easy move block function : searches the analysis_arr for (0, 2) combination
     * and makes the move if found, returning a true value.
     * 
     * @return True if an easy Block Move is available.
     */
    public boolean easy_move_block () {
    	boolean flag = false;		// temporary flag to indicate a (0, 2) find.
    	int i, k = 0;		// k used for diagonal search.
    	// search analysis_arr for (0, 2) combination.
    	for (i = 0; i < 8; i++)
    		if ((analysis_arr[i][0] == 0) && (analysis_arr[i][1] == 2)) {
    			flag = true;
    			break;
    		}
    	
    	if (flag == true) {
    		// when position < 3, it is one of the 3 rows.
        	if (i < 3)	{
        		// search for the vacant position
        		for (int j = 0; j < 3; j++)
        			if (arr[i][j] == 0) {
        				comp_play(i, j);
        				return true;
        			}
        	}
        	else if (i < 6) {
        		for (int j = 0; j < 3; j++)
        			if (arr[j][i - 3] == 0) {
        				comp_play(j, (i - 3));
        				return true;
        			}
        	}
        	else if (i == 6) {
        		for (int j = 0; j < 3; j++) {
        			if (arr[j][k] == 0) {
        				comp_play(j, k);
        				return true;
        			}
        			k++;
        		}
        	}
        	else if (i == 7) {
        		k = 2;
        		for (int j = 0; j < 3; j++) {
        			if (arr[j][k] == 0) {
        				comp_play(j, k);
        				return true;
        			}
        			k--;
        		}
        	}
    	}
    	return false;	// false if easy move win is NOT available.
    }

    /**
     * Easy move win function : searches the analysis_arr for (2,0) combination
     * and makes the move if found, returning a true value.
     * @return True if an easy Win Move is available.
     */
    public boolean easy_move_win () {
    	boolean flag = false;		// temporary flag to indicate a (2,0) find.
    	int i, k = 0;		// k used for diagonal search.
    	// search analysis_arr for (2,0) combination.
    	for (i = 0; i < 8; i++)
    		if ((analysis_arr[i][0] == 2) && (analysis_arr[i][1] == 0)) {
    			flag = true;
    			break;
    		}
    	
    	if (flag == true) {
    		// when position < 3, it is one of the 3 rows.
        	if (i < 3)	{
        		// search for the vacant position
        		for (int j = 0; j < 3; j++)
        			if (arr[i][j] == 0) {
        				comp_play(i, j);
        				return true;
        			}
        	}
        	else if (i < 6) {
        		for (int j = 0; j < 3; j++)
        			if (arr[j][i - 3] == 0) {
        				comp_play(j, (i - 3));
        				return true;
        			}
        	}
        	else if (i == 6) {
        		for (int j = 0; j < 3; j++) {
        			if (arr[j][k] == 0) {
        				comp_play(j, k);
        				return true;
        			}
        			k++;
        		}
        	}
        	else if (i == 7) {
        		k = 2;
        		for (int j = 0; j < 3; j++) {
        			if (arr[j][k] == 0) {
        				comp_play(j, k);
        				return true;
        			}
        			k--;
        		}
        	}
    	}
    	return false;	// false if easy move win is NOT available.
    }
    
    
	/**
	 * Make the computer's move.
	 * @param x : the x co-ordinate of the move to made.
	 * @param y : the y co-ordinate of the move to made.
	 */
    public void comp_play (int x, int y) {
       	final ImageButton ib_tmp = (ImageButton) findViewById(R.id.b1);
       	int ib_id = ib_tmp.getId();		// initialize with 1st button's id.
       	
       	// set ib_id to exact ImageButton Id
       	if ((x == 0) && (y == 0)) {	
       		// ib_id same as initialized value.
       	}
       	else {
       		if (x == 0)
       			ib_id -= y;			// minus '-' : because id number not in proper order.
       		else if (x == 1)
       			ib_id += (3 - y);
       		else if (x == 2)
       			ib_id += (6 - y);	
       	}
       	
       	// bind new ib_id Image Button to variable ib.
       	final ImageButton ib = (ImageButton) findViewById (ib_id);
       	
       	// draw the symbol on the button
       	if (user_symbol == 0)
       		ib.setImageResource(skin_cross);
       	else
       		ib.setImageResource(skin_dot);
       	
       	// make the button un-clickable.
       	ib.setClickable(false);
   
       	// call the after_move function with the arguments.
       	after_move(ib);
    }
    
    /**
     * Function to set the analysis array.
     * The analysis array stores the count of Friendly Positions and the Enemy Positions in an 
     * 8 x 2 array. The first 3 rows refer to the 3 rows in the original 'arr' array. The next 3
     * refers to the 3 columns of the 'arr' and the last 2 rows of the analysis array refers
     * to the 2 diagonals in 'arr'. The original array 'arr' is traversed 3 times and then 
     * the values of the analysis array are incremented when and if an enemy or friend is found.
     */
     /*
      *		  		  F	  E
      * 			---------
      * 		R1	| 0	| 0	|
      * 		R2	| 0	| 0	|
      * 		R3	| 0	| 0	|
      * 		C1	| 0	| 0	|
      * 		C2	| 0	| 0	|
      * 		C3	| 0	| 0	|
      * 		D1	| 0	| 0	|
      * 		D2	| 0	| 0	|
      * 			---------	
      */
    public void analysis_array() {
    	
    	// initialize to zero every time this function is called.
    	for (int i = 0; i < 8; i++)
    		analysis_arr[i][0] = analysis_arr[i][1] = 0;
    	
    	// row-wise traversal and increment the value.
    	for (int i = 0; i < 3; i++)
    		for (int j = 0; j < 3; j++)
    			if (arr[i][j] == 1) // 1 = player 1 : computer
    				analysis_arr[i][0] += 1;
    			else if(arr[i][j] == 2) // 2 = player 2 : human
    				analysis_arr[i][1] += 1;
    	
    	
    	
    	// column-wise traversal and increment the value.
    	for (int i = 0; i < 3; i++)
    		for (int j = 0; j < 3; j++)
    			if (arr[j][i] == 1) 			// 1 = player 1
    				analysis_arr[i + 3][0] += 1;
    			else if(arr[j][i] == 2) 		// 2 = player 2, i + 3 to change index to refer to column.
    				analysis_arr[i + 3][1] += 1;
    	
    	
    	// diagonal 1 traversal.
    	int k = 0;
    	for (int i = 0; i < 3; i++) {
    		if (arr[i][k] == 1)
    			analysis_arr[6][0] +=1;
    		else if (arr[i][k] == 2)
    			analysis_arr[6][1] +=1;
    		k++;
    	}
    	
    	// diagonal 2 traversal.
    	// --> reset k to point to the 1st row, and last(3rd) element.
    	k = 2;						
    	for (int i = 0; i < 3; i++) {
    		if (arr[i][k] == 1)
    			analysis_arr[7][0] +=1;
    		else if (arr[i][k] == 2)
    			analysis_arr[7][1] +=1;
    		k--;
    	}
    		
    	// ------ end of analysis array initialization ------------- //
    }
}
