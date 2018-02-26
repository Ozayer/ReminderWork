/* Copyright 2014 Sheldon Neilson www.neilson.co.za
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.developersbd.alarm;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.developersbd.alarm.database.Database;
import com.developersbd.alarm.preferences.AlarmPreferencesActivity;

import java.util.List;



public class AlarmActivity extends BaseActivity {

	ImageButton newButton;
	ListView mathAlarmListView;
	AlarmListAdapter alarmListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.alarm_activity);



		mathAlarmListView = (ListView) findViewById(android.R.id.list);
		mathAlarmListView.setLongClickable(true);
		mathAlarmListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
				view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);

				final Alarm alarm = (Alarm) alarmListAdapter.getItem(position);
				Builder dialog = new Builder(AlarmActivity.this);
				dialog.setTitle("Delete");
				dialog.setMessage("Delete this alarm?");
				dialog.setPositiveButton("Ok", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

						Database.init(AlarmActivity.this);
						Database.deleteEntry(alarm);
						AlarmActivity.this.callMathAlarmScheduleService();
						
						updateAlarmList();
					}
				});
				dialog.setNegativeButton("Cancel", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

				dialog.show();

				return true;
			}
		});

		callMathAlarmScheduleService();

		alarmListAdapter = new AlarmListAdapter(this);
		this.mathAlarmListView.setAdapter(alarmListAdapter);
		mathAlarmListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
				v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
				Alarm alarm = (Alarm) alarmListAdapter.getItem(position);

				Intent intent = new Intent(AlarmActivity.this, AlarmPreferencesActivity.class);
				intent.putExtra("alarm", alarm);
				startActivity(intent);
			}

		});
		language=getIntent().getStringExtra("language");
//		Toast.makeText(getBaseContext(),"Language: "+language,Toast.LENGTH_LONG).show();
//		if(language.equals("en"))
//		{
//			Menu menu=null;
//			Button b= (Button) menu.findItem(R.id.addView);
//			b.setBackground(getResources().getDrawable(R.drawable.flag_us));
////					language_btn.setBackground(getResources().getDrawable(R.drawable.flag_us));
//		}
//		else if(language.equals("bn"))
//		{
//			Menu menu=null;
//			Button b= (Button) menu.findItem(R.id.addView);
//			b.setBackground(getResources().getDrawable(R.drawable.flag_bangla));
//			//language_btn.setBackground(getResources().getDrawable(R.drawable.flag_bangla));
//		}
//		else if(language.equals("cn"))
//		{
//			Menu menu=null;
//			Button b= (Button) menu.findItem(R.id.addView);
//			b.setBackground(getResources().getDrawable(R.drawable.flag_china));
//			//language_btn.setBackground(getResources().getDrawable(R.drawable.flag_china));
//		}
//		else if(language.equals("ja"))
//		{
//			//language_btn.setBackground(getResources().getDrawable(R.drawable.flag_japan));
//		}
//		else if(language.equals("gr"))
//		{
//			//language_btn.setBackground(getResources().getDrawable(R.drawable.flag_german));
//		}
//		else if(language.equals("fr"))
//		{
//			//language_btn.setBackground(getResources().getDrawable(R.drawable.flag_franch));
//		}
//		else if(language.equals("sp"))
//		{
//			//language_btn.setBackground(getResources().getDrawable(R.drawable.flag_span));
//		}
//		else if(language.equals("hn"))
//		{
//			//language_btn.setBackground(getResources().getDrawable(R.drawable.flag_india));
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);		
		menu.findItem(R.id.menu_item_save).setVisible(false);


		try {
			if (language.equals("en")) {

				menu.findItem(R.id.addView).setIcon(R.drawable.flag_us);
			} else if (language.equals("bn")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_bangla);
			} else if (language.equals("cn")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_china);
			} else if (language.equals("ja")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_japan);
			} else if (language.equals("gr")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_german);
			} else if (language.equals("fr")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_franch);
			} else if (language.equals("sp")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_span);
			} else if (language.equals("hn")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_india);
			}
		}catch (Exception e)
		{
			SharedPreferences sharedPreferences=getSharedPreferences("MyPref",MODE_PRIVATE);
			String l=sharedPreferences.getString("lan","en");
			language=l;
			if (language.equals("en")) {

				menu.findItem(R.id.addView).setIcon(R.drawable.flag_us);
			} else if (language.equals("bn")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_bangla);
			} else if (language.equals("cn")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_china);
			} else if (language.equals("ja")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_japan);
			} else if (language.equals("gr")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_german);
			} else if (language.equals("fr")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_franch);
			} else if (language.equals("sp")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_span);
			} else if (language.equals("hn")) {
				menu.findItem(R.id.addView).setIcon(R.drawable.flag_india);
			}
		}
		menu.findItem(R.id.menu_item_delete).setVisible(false);
	    return result;
	}
		
	@Override
	protected void onPause() {
		// setListAdapter(null);
		Database.deactivate();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateAlarmList();
	}
	
	public void updateAlarmList(){
		Database.init(AlarmActivity.this);
		final List<Alarm> alarms = Database.getAll();
		alarmListAdapter.setMathAlarms(alarms);
		
		runOnUiThread(new Runnable() {
			public void run() {
				// reload content			
				AlarmActivity.this.alarmListAdapter.notifyDataSetChanged();				
				if(alarms.size() > 0){
					findViewById(android.R.id.empty).setVisibility(View.INVISIBLE);
				}else{
					findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.alarmCheck) {

			CheckBox checkBox = (CheckBox) v;
			Alarm alarm = (Alarm) alarmListAdapter.getItem((Integer) checkBox.getTag());
			alarm.setAlarmActive(checkBox.isChecked());
			Database.update(alarm);
			AlarmActivity.this.callMathAlarmScheduleService();
			if (checkBox.isChecked()) {
				Toast.makeText(AlarmActivity.this, alarm.getTimeUntilNextAlarmMessage(), Toast.LENGTH_LONG).show();
//				titleView.setText("Active Alarm");
//				titleView.setBackgroundColor(Color.rgb(40,148,17));
//			titleView.setBackgroundColor(Color.parseColor(""+R.color.));
//				titleView.setTextColor(Color.WHITE);
//				alarmListAdapter.notifyDataSetChanged();
			}

//			else
//			{
//				titleView.setText("Deactive Alarm");
//				titleView.setTextColor(Color.WHITE);
//				titleView.setBackgroundColor(Color.rgb(50,50,50));
//			}
		}

	}

}