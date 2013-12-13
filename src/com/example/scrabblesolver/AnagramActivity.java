package com.example.scrabblesolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AnagramActivity extends Activity {
	
	private String hand;
	private List<String> anagrams = new ArrayList<String>();
	private boolean isScrabble = true;
	private List<Word> valueOrder = new ArrayList<Word>();
	private	TableLayout table;		
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anagram);
		
		Bundle extras = getIntent().getExtras();
		TextView handView = (TextView)findViewById(R.id.handView);
		table = new TableLayout(this);
		if(extras != null){
			//get board/hand/anagrams
			hand = extras.getString("hand");
			handView.setText(hand);
			anagrams = extras.getStringArrayList("anagrams");
			isScrabble = extras.getBoolean("version");
		}
		
		for(int i = anagrams.size()-1; i > 0; i--){
			valueOrder.add(new Word(anagrams.get(i).toUpperCase(), value(anagrams.get(i).toUpperCase())));
		}
		
		generateTable();
		
		RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId){
				switch(checkedId){
				case R.id.wordlen:
					generateTable();
					break;
				case R.id.wordval:
					valueTable();
					break;
				}
            }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.anagram, menu);
		return true;
	}
	
	private void generateTable(){
		table.removeAllViews();
		table = new TableLayout(this);
		
		LinearLayout main = (LinearLayout)findViewById(R.id.tableLayout);
		
		for(int i = anagrams.size()-1; i > 0; i--){
			TextView tmp = new TextView(this);
			TableRow tmpRow = new TableRow(this);
				
			LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			
			tmpRow.setLayoutParams(params);
			tmp.setText(anagrams.get(i).toUpperCase() + " - " + value(anagrams.get(i).toUpperCase()));
			tmpRow.setBackgroundColor(Color.parseColor("#D0D0D0"));

			tmpRow.addView(tmp);
			tmpRow.setPadding(0, 15, 0, 2);
			table.addView(tmpRow);
		}
		main.addView(table);
		
		Collections.sort(valueOrder, new WordComparator());
		
		for(int i = 0; i<valueOrder.size(); i++){
			Log.w(VersionConstants.TAG, ""+valueOrder.get(i).getWord()+": "+valueOrder.get(i).getVal());
		}
	}
	
	private void valueTable(){
		table.removeAllViews();
		table = new TableLayout(this);
		
		LinearLayout main = (LinearLayout)findViewById(R.id.tableLayout);
		
		for(int i = 0; i<valueOrder.size(); i++){
			TextView tmp = new TextView(this);
			TableRow tmpRow = new TableRow(this);
			
			LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			
			tmpRow.setLayoutParams(params);
			tmp.setText(valueOrder.get(i).getWord() + " - " + valueOrder.get(i).getVal());
			tmpRow.setBackgroundColor(Color.parseColor("#D0D0D0"));
			
			tmpRow.addView(tmp);
			tmpRow.setPadding(0, 15, 0, 2);
			table.addView(tmpRow);
		}
		main.addView(table);
	}
	
	private int value(String word){
		int[] vals;
		if(isScrabble){
			vals = VersionConstants.SCRABBLE_VALS;
		}
		else{
			vals = VersionConstants.WORDS_VALS;
		}
		
		int total = 0;
		
		for(int i = 0; i < word.length(); i++){
			switch(word.charAt(i)){
			case 'A':
				total += vals[0];
				break;
			case 'B':
				total += vals[1];
				break;
			case 'C':
				total += vals[2];
				break;
			case 'D':
				total += vals[3];
				break;
			case 'E':
				total += vals[4];
				break;
			case 'F':
				total += vals[5];
				break;
			case 'G':
				total += vals[6];
				break;
			case 'H':
				total += vals[7];
				break;
			case 'I':
				total += vals[8];
				break;
			case 'J':
				total += vals[9];
				break;
			case 'K':
				total += vals[10];
				break;
			case 'L':
				total += vals[11];
				break;
			case 'M':
				total += vals[12];
				break;
			case 'N':
				total += vals[13];
				break;
			case 'O':
				total += vals[14];
				break;
			case 'P':
				total += vals[15];
				break;
			case 'Q':
				total += vals[16];
				break;
			case 'R':
				total += vals[17];
				break;
			case 'S':
				total += vals[18];
				break;
			case 'T':
				total += vals[19];
				break;
			case 'U':
				total += vals[20];
				break;
			case 'V':
				total += vals[21];
				break;
			case 'W':
				total += vals[22];
				break;
			case 'X':
				total += vals[23];
				break;
			case 'Y':
				total += vals[24];
				break;
			case 'Z':
				total += vals[25];
				break;
			default:
				break;
			}
		}
		
		return total;
	}

	public class WordComparator implements Comparator<Word>{
		public int compare(Word w1, Word w2){
			return w2.getVal() - w1.getVal();
		}
	}
	
}
