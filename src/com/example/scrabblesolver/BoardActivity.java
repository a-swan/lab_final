package com.example.scrabblesolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
/////////////////
import android.app.ListActivity;
import android.widget.ListView;
import android.widget.ArrayAdapter;
///////////////

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.example.scrabblesolver.VersionConstants;


@TargetApi(Build.VERSION_CODES.HONEYCOMB) public class BoardActivity extends Activity {
	
	private EditText[] board;
	
	private boolean isScrabble;// = true;
	private String dictionary;// = "scrabL";
	///////
	private Dawg dawg;
	private List<String> wordList = new ArrayList<String>();
	private EditText letters;// = (EditText)findViewById(R.id.hand);
	private ArrayAdapter<String> listAdapter;
	///////
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        
        //////////////////////////////////
        //listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wordList);
        //setListAdapter(listAdapter);
        //listAdapter.notifyDataSetChanged();
        //////////////////////////////////
        board = new EditText[225];
        
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        
        String tmp = prefs.getString("versionPref", "is_words");
        isScrabble = tmp.equals("is_scrabble");
        
        dictionary = prefs.getString("dictionaryPref", "scrabble");
        
        Log.w(VersionConstants.TAG, "scrabble = "+ isScrabble);
        Log.w(VersionConstants.TAG, "dictionary = "+dictionary);
        
        //load board
        generateBoard();
    	boardLayout();

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.board, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.action_solve:
    		String boardVals[] = createBoard();
    		String CurrentLetters = ((EditText)findViewById(R.id.hand)).getText().toString();
    		Log.w(VersionConstants.TAG, "letters: "+CurrentLetters);
            try {
                dawg = new Dawg(this, wordList, dictionary);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                wordList.clear();
                HashMap<Integer, List<String>> wordMap = dawg.anagram(CurrentLetters);
                
                List<Integer> set = new ArrayList<Integer>(wordMap.keySet());
                //Log.w(VersionConstants.TAG, "set: "+set);
                Collections.sort(set);
                for(Integer i : set) {
                    wordList.addAll(wordMap.get(i));
                    String [] wordArray = wordList.toArray(new String[wordList.size()]);
                    Log.w(VersionConstants.TAG, "words: "+wordMap.get(i).toString());
                }

                if(wordList.isEmpty()) {
                    wordList.add("No matches found!");
                    Log.w(VersionConstants.TAG, "No words found");
                    //letters.setText("none");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
    		
            Intent solveActivity = new Intent(this, AnagramActivity.class);
            solveActivity.putExtra("hand", ((EditText)findViewById(R.id.hand)).getText().toString());
            solveActivity.putStringArrayListExtra("anagrams", (ArrayList<String>)wordList);
            solveActivity.putExtra("version", isScrabble);
            
            startActivity(solveActivity);
            
    		return true;
    	case R.id.action_clear:
    		clearBoard();
    		return true;
    	case R.id.action_settings:
    		//startActivityForResult(new Intent(this, SettingsActivity.class), rcPreferences);
    		startActivity(new Intent(this, SettingsActivity.class));
    		return true;
    		
    	default:
    		return false;
    	}
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	
    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    	
    	boolean newBoard = prefs.getString("versionPref", "is_scrabble").equals("is_scrabble");
    	String newDict = prefs.getString("dictionaryPref", "scrabL");
    	
    	//Log.w(VersionConstants.TAG, "newBoard != isScrabble, "+(newBoard != isScrabble));
    	
    	if(newBoard != isScrabble){
    		Log.w(VersionConstants.TAG, "Changing board");
    		isScrabble = newBoard;
    		boardLayout();
    		
    	}
    	
    	if(newDict != dictionary){
    		dictionary = newDict;
    		//change dictionary root
    	}
    	
        Log.w(VersionConstants.TAG, "scrabble = "+ newBoard);
        Log.w(VersionConstants.TAG, "dictionary = "+dictionary);

    }
    
    private void generateBoard(){
    	board[0] = (EditText)findViewById(R.id.tableRow0Text0);
    	board[1] = (EditText)findViewById(R.id.tableRow0Text1);
    	board[2] = (EditText)findViewById(R.id.tableRow0Text2);
    	board[3] = (EditText)findViewById(R.id.tableRow0Text3);
    	board[4] = (EditText)findViewById(R.id.tableRow0Text4);
    	board[5] = (EditText)findViewById(R.id.tableRow0Text5);
    	board[6] = (EditText)findViewById(R.id.tableRow0Text6);
    	board[7] = (EditText)findViewById(R.id.tableRow0Text7);
    	board[8] = (EditText)findViewById(R.id.tableRow0Text8);
    	board[9] = (EditText)findViewById(R.id.tableRow0Text9);
    	board[10] = (EditText)findViewById(R.id.tableRow0Text10);
    	board[11] = (EditText)findViewById(R.id.tableRow0Text11);
    	board[12] = (EditText)findViewById(R.id.tableRow0Text12);
    	board[13] = (EditText)findViewById(R.id.tableRow0Text13);
    	board[14] = (EditText)findViewById(R.id.tableRow0Text14);
    	board[15] = (EditText)findViewById(R.id.tableRow1Text0);
    	board[16] = (EditText)findViewById(R.id.tableRow1Text1);
    	board[17] = (EditText)findViewById(R.id.tableRow1Text2);
    	board[18] = (EditText)findViewById(R.id.tableRow1Text3);
    	board[19] = (EditText)findViewById(R.id.tableRow1Text4);
    	board[20] = (EditText)findViewById(R.id.tableRow1Text5);
    	board[21] = (EditText)findViewById(R.id.tableRow1Text6);
    	board[22] = (EditText)findViewById(R.id.tableRow1Text7);
    	board[23] = (EditText)findViewById(R.id.tableRow1Text8);
    	board[24] = (EditText)findViewById(R.id.tableRow1Text9);
    	board[25] = (EditText)findViewById(R.id.tableRow1Text10);
    	board[26] = (EditText)findViewById(R.id.tableRow1Text11);
    	board[27] = (EditText)findViewById(R.id.tableRow1Text12);
    	board[28] = (EditText)findViewById(R.id.tableRow1Text13);
    	board[29] = (EditText)findViewById(R.id.tableRow1Text14);
    	board[30] = (EditText)findViewById(R.id.tableRow2Text0);
    	board[31] = (EditText)findViewById(R.id.tableRow2Text1);
    	board[32] = (EditText)findViewById(R.id.tableRow2Text2);
    	board[33] = (EditText)findViewById(R.id.tableRow2Text3);
    	board[34] = (EditText)findViewById(R.id.tableRow2Text4);
    	board[35] = (EditText)findViewById(R.id.tableRow2Text5);
    	board[36] = (EditText)findViewById(R.id.tableRow2Text6);
    	board[37] = (EditText)findViewById(R.id.tableRow2Text7);
    	board[38] = (EditText)findViewById(R.id.tableRow2Text8);
    	board[39] = (EditText)findViewById(R.id.tableRow2Text9);
    	board[40] = (EditText)findViewById(R.id.tableRow2Text10);
    	board[41] = (EditText)findViewById(R.id.tableRow2Text11);
    	board[42] = (EditText)findViewById(R.id.tableRow2Text12);
    	board[43] = (EditText)findViewById(R.id.tableRow2Text13);
    	board[44] = (EditText)findViewById(R.id.tableRow2Text14);
    	board[45] = (EditText)findViewById(R.id.tableRow3Text0);
    	board[46] = (EditText)findViewById(R.id.tableRow3Text1);
    	board[47] = (EditText)findViewById(R.id.tableRow3Text2);
    	board[48] = (EditText)findViewById(R.id.tableRow3Text3);
    	board[49] = (EditText)findViewById(R.id.tableRow3Text4);
    	board[50] = (EditText)findViewById(R.id.tableRow3Text5);
    	board[51] = (EditText)findViewById(R.id.tableRow3Text6);
    	board[52] = (EditText)findViewById(R.id.tableRow3Text7);
    	board[53] = (EditText)findViewById(R.id.tableRow3Text8);
    	board[54] = (EditText)findViewById(R.id.tableRow3Text9);
    	board[55] = (EditText)findViewById(R.id.tableRow3Text10);
    	board[56] = (EditText)findViewById(R.id.tableRow3Text11);
    	board[57] = (EditText)findViewById(R.id.tableRow3Text12);
    	board[58] = (EditText)findViewById(R.id.tableRow3Text13);
    	board[59] = (EditText)findViewById(R.id.tableRow3Text14);
    	board[60] = (EditText)findViewById(R.id.tableRow4Text0);
    	board[61] = (EditText)findViewById(R.id.tableRow4Text1);
    	board[62] = (EditText)findViewById(R.id.tableRow4Text2);
    	board[63] = (EditText)findViewById(R.id.tableRow4Text3);
    	board[64] = (EditText)findViewById(R.id.tableRow4Text4);
    	board[65] = (EditText)findViewById(R.id.tableRow4Text5);
    	board[66] = (EditText)findViewById(R.id.tableRow4Text6);
    	board[67] = (EditText)findViewById(R.id.tableRow4Text7);
    	board[68] = (EditText)findViewById(R.id.tableRow4Text8);
    	board[69] = (EditText)findViewById(R.id.tableRow4Text9);
    	board[70] = (EditText)findViewById(R.id.tableRow4Text10);
    	board[71] = (EditText)findViewById(R.id.tableRow4Text11);
    	board[72] = (EditText)findViewById(R.id.tableRow4Text12);
    	board[73] = (EditText)findViewById(R.id.tableRow4Text13);
    	board[74] = (EditText)findViewById(R.id.tableRow4Text14);
    	board[75] = (EditText)findViewById(R.id.tableRow5Text0);
    	board[76] = (EditText)findViewById(R.id.tableRow5Text1);
    	board[77] = (EditText)findViewById(R.id.tableRow5Text2);
    	board[78] = (EditText)findViewById(R.id.tableRow5Text3);
    	board[79] = (EditText)findViewById(R.id.tableRow5Text4);
    	board[80] = (EditText)findViewById(R.id.tableRow5Text5);
    	board[81] = (EditText)findViewById(R.id.tableRow5Text6);
    	board[82] = (EditText)findViewById(R.id.tableRow5Text7);
    	board[83] = (EditText)findViewById(R.id.tableRow5Text8);
    	board[84] = (EditText)findViewById(R.id.tableRow5Text9);
    	board[85] = (EditText)findViewById(R.id.tableRow5Text10);
    	board[86] = (EditText)findViewById(R.id.tableRow5Text11);
    	board[87] = (EditText)findViewById(R.id.tableRow5Text12);
    	board[88] = (EditText)findViewById(R.id.tableRow5Text13);
    	board[89] = (EditText)findViewById(R.id.tableRow5Text14);
    	board[90] = (EditText)findViewById(R.id.tableRow6Text0);
    	board[91] = (EditText)findViewById(R.id.tableRow6Text1);
    	board[92] = (EditText)findViewById(R.id.tableRow6Text2);
    	board[93] = (EditText)findViewById(R.id.tableRow6Text3);
    	board[94] = (EditText)findViewById(R.id.tableRow6Text4);
    	board[95] = (EditText)findViewById(R.id.tableRow6Text5);
    	board[96] = (EditText)findViewById(R.id.tableRow6Text6);
    	board[97] = (EditText)findViewById(R.id.tableRow6Text7);
    	board[98] = (EditText)findViewById(R.id.tableRow6Text8);
    	board[99] = (EditText)findViewById(R.id.tableRow6Text9);
    	board[100] = (EditText)findViewById(R.id.tableRow6Text10);
    	board[101] = (EditText)findViewById(R.id.tableRow6Text11);
    	board[102] = (EditText)findViewById(R.id.tableRow6Text12);
    	board[103] = (EditText)findViewById(R.id.tableRow6Text13);
    	board[104] = (EditText)findViewById(R.id.tableRow6Text14);
    	board[105] = (EditText)findViewById(R.id.tableRow7Text0);
    	board[106] = (EditText)findViewById(R.id.tableRow7Text1);
    	board[107] = (EditText)findViewById(R.id.tableRow7Text2);
    	board[108] = (EditText)findViewById(R.id.tableRow7Text3);
    	board[109] = (EditText)findViewById(R.id.tableRow7Text4);
    	board[110] = (EditText)findViewById(R.id.tableRow7Text5);
    	board[111] = (EditText)findViewById(R.id.tableRow7Text6);
    	board[112] = (EditText)findViewById(R.id.tableRow7Text7);
    	board[113] = (EditText)findViewById(R.id.tableRow7Text8);
    	board[114] = (EditText)findViewById(R.id.tableRow7Text9);
    	board[115] = (EditText)findViewById(R.id.tableRow7Text10);
    	board[116] = (EditText)findViewById(R.id.tableRow7Text11);
    	board[117] = (EditText)findViewById(R.id.tableRow7Text12);
    	board[118] = (EditText)findViewById(R.id.tableRow7Text13);
    	board[119] = (EditText)findViewById(R.id.tableRow7Text14);
    	board[120] = (EditText)findViewById(R.id.tableRow8Text0);
    	board[121] = (EditText)findViewById(R.id.tableRow8Text1);
    	board[122] = (EditText)findViewById(R.id.tableRow8Text2);
    	board[123] = (EditText)findViewById(R.id.tableRow8Text3);
    	board[124] = (EditText)findViewById(R.id.tableRow8Text4);
    	board[125] = (EditText)findViewById(R.id.tableRow8Text5);
    	board[126] = (EditText)findViewById(R.id.tableRow8Text6);
    	board[127] = (EditText)findViewById(R.id.tableRow8Text7);
    	board[128] = (EditText)findViewById(R.id.tableRow8Text8);
    	board[129] = (EditText)findViewById(R.id.tableRow8Text9);
    	board[130] = (EditText)findViewById(R.id.tableRow8Text10);
    	board[131] = (EditText)findViewById(R.id.tableRow8Text11);
    	board[132] = (EditText)findViewById(R.id.tableRow8Text12);
    	board[133] = (EditText)findViewById(R.id.tableRow8Text13);
    	board[134] = (EditText)findViewById(R.id.tableRow8Text14);
    	board[135] = (EditText)findViewById(R.id.tableRow9Text0);
    	board[136] = (EditText)findViewById(R.id.tableRow9Text1);
    	board[137] = (EditText)findViewById(R.id.tableRow9Text2);
    	board[138] = (EditText)findViewById(R.id.tableRow9Text3);
    	board[139] = (EditText)findViewById(R.id.tableRow9Text4);
    	board[140] = (EditText)findViewById(R.id.tableRow9Text5);
    	board[141] = (EditText)findViewById(R.id.tableRow9Text6);
    	board[142] = (EditText)findViewById(R.id.tableRow9Text7);
    	board[143] = (EditText)findViewById(R.id.tableRow9Text8);
    	board[144] = (EditText)findViewById(R.id.tableRow9Text9);
    	board[145] = (EditText)findViewById(R.id.tableRow9Text10);
    	board[146] = (EditText)findViewById(R.id.tableRow9Text11);
    	board[147] = (EditText)findViewById(R.id.tableRow9Text12);
    	board[148] = (EditText)findViewById(R.id.tableRow9Text13);
    	board[149] = (EditText)findViewById(R.id.tableRow9Text14);
    	board[150] = (EditText)findViewById(R.id.tableRow10Text0);
    	board[151] = (EditText)findViewById(R.id.tableRow10Text1);
    	board[152] = (EditText)findViewById(R.id.tableRow10Text2);
    	board[153] = (EditText)findViewById(R.id.tableRow10Text3);
    	board[154] = (EditText)findViewById(R.id.tableRow10Text4);
    	board[155] = (EditText)findViewById(R.id.tableRow10Text5);
    	board[156] = (EditText)findViewById(R.id.tableRow10Text6);
    	board[157] = (EditText)findViewById(R.id.tableRow10Text7);
    	board[158] = (EditText)findViewById(R.id.tableRow10Text8);
    	board[159] = (EditText)findViewById(R.id.tableRow10Text9);
    	board[160] = (EditText)findViewById(R.id.tableRow10Text10);
    	board[161] = (EditText)findViewById(R.id.tableRow10Text11);
    	board[162] = (EditText)findViewById(R.id.tableRow10Text12);
    	board[163] = (EditText)findViewById(R.id.tableRow10Text13);
    	board[164] = (EditText)findViewById(R.id.tableRow10Text14);
    	board[165] = (EditText)findViewById(R.id.tableRow11Text0);
    	board[166] = (EditText)findViewById(R.id.tableRow11Text1);
    	board[167] = (EditText)findViewById(R.id.tableRow11Text2);
    	board[168] = (EditText)findViewById(R.id.tableRow11Text3);
    	board[169] = (EditText)findViewById(R.id.tableRow11Text4);
    	board[170] = (EditText)findViewById(R.id.tableRow11Text5);
    	board[171] = (EditText)findViewById(R.id.tableRow11Text6);
    	board[172] = (EditText)findViewById(R.id.tableRow11Text7);
    	board[173] = (EditText)findViewById(R.id.tableRow11Text8);
    	board[174] = (EditText)findViewById(R.id.tableRow11Text9);
    	board[175] = (EditText)findViewById(R.id.tableRow11Text10);
    	board[176] = (EditText)findViewById(R.id.tableRow11Text11);
    	board[177] = (EditText)findViewById(R.id.tableRow11Text12);
    	board[178] = (EditText)findViewById(R.id.tableRow11Text13);
    	board[179] = (EditText)findViewById(R.id.tableRow11Text14);
    	board[180] = (EditText)findViewById(R.id.tableRow12Text0);
    	board[181] = (EditText)findViewById(R.id.tableRow12Text1);
    	board[182] = (EditText)findViewById(R.id.tableRow12Text2);
    	board[183] = (EditText)findViewById(R.id.tableRow12Text3);
    	board[184] = (EditText)findViewById(R.id.tableRow12Text4);
    	board[185] = (EditText)findViewById(R.id.tableRow12Text5);
    	board[186] = (EditText)findViewById(R.id.tableRow12Text6);
    	board[187] = (EditText)findViewById(R.id.tableRow12Text7);
    	board[188] = (EditText)findViewById(R.id.tableRow12Text8);
    	board[189] = (EditText)findViewById(R.id.tableRow12Text9);
    	board[190] = (EditText)findViewById(R.id.tableRow12Text10);
    	board[191] = (EditText)findViewById(R.id.tableRow12Text11);
    	board[192] = (EditText)findViewById(R.id.tableRow12Text12);
    	board[193] = (EditText)findViewById(R.id.tableRow12Text13);
    	board[194] = (EditText)findViewById(R.id.tableRow12Text14);
    	board[195] = (EditText)findViewById(R.id.tableRow13Text0);
    	board[196] = (EditText)findViewById(R.id.tableRow13Text1);
    	board[197] = (EditText)findViewById(R.id.tableRow13Text2);
    	board[198] = (EditText)findViewById(R.id.tableRow13Text3);
    	board[199] = (EditText)findViewById(R.id.tableRow13Text4);
    	board[200] = (EditText)findViewById(R.id.tableRow13Text5);
    	board[201] = (EditText)findViewById(R.id.tableRow13Text6);
    	board[202] = (EditText)findViewById(R.id.tableRow13Text7);
    	board[203] = (EditText)findViewById(R.id.tableRow13Text8);
    	board[204] = (EditText)findViewById(R.id.tableRow13Text9);
    	board[205] = (EditText)findViewById(R.id.tableRow13Text10);
    	board[206] = (EditText)findViewById(R.id.tableRow13Text11);
    	board[207] = (EditText)findViewById(R.id.tableRow13Text12);
    	board[208] = (EditText)findViewById(R.id.tableRow13Text13);
    	board[209] = (EditText)findViewById(R.id.tableRow13Text14);
    	board[210] = (EditText)findViewById(R.id.tableRow14Text0);
    	board[211] = (EditText)findViewById(R.id.tableRow14Text1);
    	board[212] = (EditText)findViewById(R.id.tableRow14Text2);
    	board[213] = (EditText)findViewById(R.id.tableRow14Text3);
    	board[214] = (EditText)findViewById(R.id.tableRow14Text4);
    	board[215] = (EditText)findViewById(R.id.tableRow14Text5);
    	board[216] = (EditText)findViewById(R.id.tableRow14Text6);
    	board[217] = (EditText)findViewById(R.id.tableRow14Text7);
    	board[218] = (EditText)findViewById(R.id.tableRow14Text8);
    	board[219] = (EditText)findViewById(R.id.tableRow14Text9);
    	board[220] = (EditText)findViewById(R.id.tableRow14Text10);
    	board[221] = (EditText)findViewById(R.id.tableRow14Text11);
    	board[222] = (EditText)findViewById(R.id.tableRow14Text12);
    	board[223] = (EditText)findViewById(R.id.tableRow14Text13);
    	board[224] = (EditText)findViewById(R.id.tableRow14Text14);
    	

    }
    
    private String[] createBoard(){
    	String[] tmp = new String[225];
    	
    	for(int i = 0; i<225; i++){
    		tmp[i] = board[i].getText().toString();
    		
    		//Log.w(VersionConstants.TAG, "board["+i+"] = "+ tmp[i]);
    	}
    	
    	return tmp;
    }
    
    private void clearBoard(){
    	for(int i = 0; i<225; i++){
    		board[i].setText("");
    	}
    	
    	((EditText)findViewById(R.id.hand)).setText("");
    }
    
    private void boardLayout(){
    	int boardLayout[];
    	if(isScrabble){
    		boardLayout = VersionConstants.SCRABBLE_BOARD;
    		Log.w(VersionConstants.TAG, "is_scrabble");
    	}
    	else{
    		boardLayout = VersionConstants.WORDS_BOARD;
    		Log.w(VersionConstants.TAG, "isnt_scrabble");
    	}
    	
    	for(int i = 0; i < boardLayout.length; i++){
    		    		
    		switch(boardLayout[i]){
    		case 0:
    			board[i].setBackgroundColor(Color.parseColor("#DBA856"));
    			break;
    		case 1:
    			board[i].setBackgroundColor(Color.parseColor("#B8DEDE"));
    			break;
    		case 2:
    			board[i].setBackgroundColor(Color.parseColor("#DB8181"));
    			break;
    		case 3:
    			board[i].setBackgroundColor(Color.parseColor("#25A1A1"));
    			break;
    		case 4:
    			board[i].setBackgroundColor(Color.parseColor("#ED2626"));
    			break;
    		case 5:
    			board[i].setHint("X");
    			break;
    		default:
    			board[i].setBackgroundColor(Color.parseColor("#B8DEDE"));
    			break;
    		}
    	}
    }
    
    private boolean emptyBoard(){
    	for(int i = 0; i<225; i++){
    		if(board[i].getText().toString() != ""){
    			return false;
    		}
    	}
    	
    	return true;
    }
    
}
