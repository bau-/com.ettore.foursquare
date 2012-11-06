/*
 * Copyright 2010 Small Light Room CO., LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jiramot.foursquare.android;

/**
 * Encapsulation of a MainActivity: a demo for using api
 *
 * @author jiramot@gmail.com
 */
import java.io.IOException;
import java.net.MalformedURLException;

import com.jiramot.foursquare.android.Foursquare.DialogListener;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main extends Activity {
	Foursquare foursquare;
	
    // Identificatore delle preferenze dell'applicazione
    private final static String MY_PREFERENCES = "preferenze";
    // Costante relativa al nome della particolare preferenza
    private final static String TEXT_DATA_KEY = "textData";
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		foursquare = new Foursquare(
				"VNHX0JOVR1JUZG5B5JVB2EQQFGOIHJN1Y310HO2FLMKDM4NL",
				"5WNJHTOP5NMAUJHU5KAZZPQNL31LTCFSOOHZRRF1BWXCJ422",
				"https://x-oauthflow-foursquare");

		foursquare.authorize(this, new FoursquareAuthenDialogListener());
		
		// Leggiamo le Preferences
        SharedPreferences prefs = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        // Leggiamo l'informazione associata alla proprietà TEXT_DATA
        String textData = prefs.getString(TEXT_DATA_KEY, "No Preferences!");
        // Lo impostiamo alla TextView
        TextView outputView = (TextView) findViewById(R.id.textView1);
        outputView.setText(textData);
	}

	private class FoursquareAuthenDialogListener implements DialogListener {

		public void onComplete(Bundle values) {
			try {
				String aa = null;
				aa = foursquare.request("users/self");
				Log.d("Foursquare-Main", aa);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void onFoursquareError(FoursquareError e) {
			// TODO Auto-generated method stub

		}

		public void onError(DialogError e) {
			// TODO Auto-generated method stub

		}

		public void onCancel() {
			// TODO Auto-generated method stub

		}

	}
}