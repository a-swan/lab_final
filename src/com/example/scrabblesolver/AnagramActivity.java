package com.example.scrabblesolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AnagramActivity extends Activity {
	
	private String hand;
	private List<String> anagrams = new ArrayList<String>();
	
	private Map<String, Integer> results = new HashMap<String, Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anagram);
		
		Bundle extras = getIntent().getExtras();
		TextView handView = (TextView)findViewById(R.id.handView);
		
		if(extras != null){
			//get board/hand/anagrams
			hand = extras.getString("hand");
			handView.setText(hand);
			anagrams = extras.getStringArrayList("anagrams");
		}
		
		
		
		
		generateTable();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.anagram, menu);
		return true;
	}
	
	private void generateTable(){
		LinearLayout main = (LinearLayout)findViewById(R.id.tableLayout);
		TableLayout table = new TableLayout(this);
		
		for(int i = anagrams.size()-1; i > 0; i--){
			TextView tmp = new TextView(this);
			TableRow tmpRow = new TableRow(this);
			
			
			LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			
			tmpRow.setLayoutParams(params);
			tmp.setText(anagrams.get(i).toUpperCase());
			
			tmpRow.setClickable(true);
			tmpRow.addView(tmp);
			tmpRow.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.w(VersionConstants.TAG, "Clicked! ");
				}
			});
			
			table.addView(tmpRow);
		}
		main.addView(table);
	}

}
