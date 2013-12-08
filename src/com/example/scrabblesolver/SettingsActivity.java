package com.example.scrabblesolver;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SettingsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction().replace(android.R.id.content, new SolverPreferencesFragment()).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	/** This fragment shows the preferences for the media player */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class SolverPreferencesFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			// Make sure default values are applied.
			PreferenceManager.setDefaultValues(getActivity(),
			R.xml.solver_preference, false);
			// Load the preferences from an XML resource
			addPreferencesFromResource(R.xml.solver_preference);
		}
	}

}
