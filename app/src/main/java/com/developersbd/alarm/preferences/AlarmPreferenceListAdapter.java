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
package com.developersbd.alarm.preferences;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.developersbd.alarm.Alarm;
import com.developersbd.alarm.R;
import com.developersbd.alarm.preferences.AlarmPreference.Type;

import android.content.Context;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class AlarmPreferenceListAdapter extends BaseAdapter implements Serializable {

	private Context context;
	private Alarm alarm;
	private String language;
	private List<AlarmPreference> preferences = new ArrayList<AlarmPreference>();
	private final String[] repeatDays = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};	
	private final String[] alarmDifficulties = {"Easy","Medium","Hard"};
	
	private String[] alarmTones;
	private String[] alarmTonePaths;
	
	public AlarmPreferenceListAdapter(Context context, Alarm alarm,String language) {
		setContext(context);
		this.language=language;
		
		
//		(new Runnable(){
//
//			@Override
//			public void run() {
				Log.d("AlarmPreferenceListAdapter", "Loading Ringtones...");
				
				RingtoneManager ringtoneMgr = new RingtoneManager(getContext());
				
				ringtoneMgr.setType(RingtoneManager.TYPE_ALARM);
				
				Cursor alarmsCursor = ringtoneMgr.getCursor();
				
				alarmTones = new String[alarmsCursor.getCount()+1];
				alarmTones[0] = "Silent"; 
				alarmTonePaths = new String[alarmsCursor.getCount()+1];
				alarmTonePaths[0] = "";
				
				if (alarmsCursor.moveToFirst()) {		    			
					do {
						alarmTones[alarmsCursor.getPosition()+1] = ringtoneMgr.getRingtone(alarmsCursor.getPosition()).getTitle(getContext());
						alarmTonePaths[alarmsCursor.getPosition()+1] = ringtoneMgr.getRingtoneUri(alarmsCursor.getPosition()).toString();
					}while(alarmsCursor.moveToNext());					
				}
				Log.d("AlarmPreferenceListAdapter", "Finished Loading " + alarmTones.length + " Ringtones.");
				alarmsCursor.close();
//				
//			}
//			
//		}).run();
//		
	    setMathAlarm(alarm);
	}

	@Override
	public int getCount() {
		return preferences.size();
	}

	@Override
	public Object getItem(int position) {
		return preferences.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AlarmPreference alarmPreference = (AlarmPreference) getItem(position);
		LayoutInflater layoutInflater = LayoutInflater.from(getContext());
		switch (alarmPreference.getType()) {
		case BOOLEAN:
			if(null == convertView || convertView.getId() != android.R.layout.simple_list_item_checked)
			convertView = layoutInflater.inflate(android.R.layout.simple_list_item_checked, null);

			CheckedTextView checkedTextView = (CheckedTextView) convertView.findViewById(android.R.id.text1);
			checkedTextView.setText(alarmPreference.getTitle());
			checkedTextView.setChecked((Boolean) alarmPreference.getValue());
			break;
		case INTEGER:
		case STRING:
		case LIST:
		case MULTIPLE_LIST:
		case TIME:

		default:
			if(null == convertView || convertView.getId() != android.R.layout.simple_list_item_2)
			convertView = layoutInflater.inflate(android.R.layout.simple_list_item_2, null);
			
			TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
			text1.setTextSize(18);
			text1.setText(alarmPreference.getTitle());
			
			TextView text2 = (TextView) convertView.findViewById(android.R.id.text2);
			text2.setText(alarmPreference.getSummary());
			break;
		}

		return convertView;
	}

	public Alarm getMathAlarm() {		
		for(AlarmPreference preference : preferences){
			switch(preference.getKey()){
				case ALARM_ACTIVE:
					alarm.setAlarmActive((Boolean) preference.getValue());
					break;
				case ALARM_NAME:
					alarm.setAlarmName((String) preference.getValue());
					break;
				case ALARM_TIME:
					alarm.setAlarmTime((String) preference.getValue());
					break;
				case ALARM_DAY:
					alarm.setAlarmDate((String) preference.getValue());
					break;

				case ALARM_DIFFICULTY:
					alarm.setDifficulty(Alarm.Difficulty.valueOf((String)preference.getValue()));
					break;
				case ALARM_TONE:
					alarm.setAlarmTonePath((String) preference.getValue());
					break;
				case ALARM_VIBRATE:
					alarm.setVibrate((Boolean) preference.getValue());
					break;
				case ALARM_REPEAT:
					alarm.setDays((Alarm.Day[]) preference.getValue());
					break;
				case ALARM_NOTE:
					alarm.setAlarmNote("Note");

			}
		}
				
		return alarm;
	}

	public void setMathAlarm(Alarm alarm) {
		this.alarm = alarm;
		preferences.clear();
		Log.d("prefchec",language);
//		String active=context.getString(R.string.active_bn);
		String active="ACTIVE";
		String category="CATEGORY";
		String time="TIME";
		String note="NOTE";
		String set_date="DATE";
		String repeat="REPEAT";
		String ringtone="RINGTONE";
		String vibration="VIBRATION";

		if(language.equals("en"))
		{
			active=context.getString(R.string.active_en);
			category=context.getString(R.string.inputcategory_en);
			time=context.getString(R.string.settime_en);
			note=context.getString(R.string.note_en);
			set_date=context.getString(R.string.date_en);
			repeat=context.getString(R.string.repeat_en);
			ringtone=context.getString(R.string.setringtone_en);
			vibration=context.getString(R.string.vibration_en);
		}
		else if(language.equals("bn"))
		{
			active=context.getString(R.string.active_bn);
			category=context.getString(R.string.inputcategory_bn);
			time=context.getString(R.string.settime_bn);
			note=context.getString(R.string.note_bn);
			set_date=context.getString(R.string.date_bn);
			repeat=context.getString(R.string.repeat_bn);
			ringtone=context.getString(R.string.setringtone_bn);
			vibration=context.getString(R.string.vibration_bn);
		}
		else if(language.equals("cn"))
		{
			active=context.getString(R.string.active_chn);
			category=context.getString(R.string.inputcategory_chn);
			time=context.getString(R.string.settime_chn);
			note=context.getString(R.string.note_chn);
			set_date=context.getString(R.string.date_chn);
			repeat=context.getString(R.string.repeat_chn);
			ringtone=context.getString(R.string.setringtone_chn);
			vibration=context.getString(R.string.vibration_chn);
		}
		else if(language.equals("ja"))
		{
			active=context.getString(R.string.active_jpn);
			category=context.getString(R.string.inputcategory_jpn);
			time=context.getString(R.string.settime_jpn);
			note=context.getString(R.string.note_jpn);
			set_date=context.getString(R.string.date_jpn);
			repeat=context.getString(R.string.repeat_jpn);
			ringtone=context.getString(R.string.setringtone_jpn);
			vibration=context.getString(R.string.vibration_jpn);
		}
		else if(language.equals("fr"))
		{
			active=context.getString(R.string.active_frn);
			category=context.getString(R.string.inputcategory_frn);
			time=context.getString(R.string.settime_frn);
			note=context.getString(R.string.note_frn);
			set_date=context.getString(R.string.date_frn);
			repeat=context.getString(R.string.repeat_frn);
			ringtone=context.getString(R.string.setringtone_frn);
			vibration=context.getString(R.string.vibration_frn);
		}
		else if(language.equals("gr"))
		{
			active=context.getString(R.string.active_gmn);
			category=context.getString(R.string.inputcategory_gmn);
			time=context.getString(R.string.settime_gmn);
			note=context.getString(R.string.note_gmn);
			set_date=context.getString(R.string.date_gmn);
			repeat=context.getString(R.string.repeat_gmn);
			ringtone=context.getString(R.string.setringtone_gmn);
			vibration=context.getString(R.string.vibration_gmn);
		}
		else if(language.equals("hn"))
		{
			active=context.getString(R.string.active_hin);
			category=context.getString(R.string.inputcategory_hin);
			time=context.getString(R.string.settime_hin);
			note=context.getString(R.string.note_hin);
			set_date=context.getString(R.string.date_hin);
			repeat=context.getString(R.string.repeat_hin);
			ringtone=context.getString(R.string.setringtone_hin);
			vibration=context.getString(R.string.vibration_hin);
		}
		else if(language.equals("sp"))
		{
			active=context.getString(R.string.active_spn);
			category=context.getString(R.string.inputcategory_spn);
			time=context.getString(R.string.settime_spn);
			note=context.getString(R.string.note_spn);
			set_date=context.getString(R.string.note_spn);
			repeat=context.getString(R.string.repeat_spn);
			ringtone=context.getString(R.string.setringtone_spn);
			vibration=context.getString(R.string.vibration_spn);
		}

		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_ACTIVE,active, null, null, alarm.getAlarmActive(),Type.BOOLEAN));
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_NAME, category,alarm.getAlarmName(), null, alarm.getAlarmName(), Type.STRING));
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_TIME, time,alarm.getAlarmTimeString(), null, alarm.getAlarmTime(), Type.TIME));
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_REPEAT, repeat,alarm.getRepeatDaysString(), repeatDays, alarm.getDays(),Type.MULTIPLE_LIST));
//		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_DIFFICULTY,"Difficulty", alarm.getDifficulty().toString(), alarmDifficulties, alarm.getDifficulty(), Type.LIST));
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_NOTE,note, alarm.getAlarmNote().toString(), null, alarm.getAlarmNote(), Type.STRING2));
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_DAY,set_date, alarm.getAlarmDateString().toString(), null, alarm.getAlarmDate(), Type.DATE));
			Uri alarmToneUri = Uri.parse(alarm.getAlarmTonePath());
			Ringtone alarmTone = RingtoneManager.getRingtone(getContext(), alarmToneUri);
		
		if(alarmTone instanceof Ringtone && !alarm.getAlarmTonePath().equalsIgnoreCase("")){
			preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_TONE, ringtone, alarmTone.getTitle(getContext()),alarmTones, alarm.getAlarmTonePath(), Type.LIST));
		}else{
			preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_TONE, ringtone, getAlarmTones()[0],alarmTones, null, Type.LIST));
		}
		
		preferences.add(new AlarmPreference(AlarmPreference.Key.ALARM_VIBRATE,vibration,null, null, alarm.getVibrate(), Type.BOOLEAN));
	}

	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String[] getRepeatDays() {
		return repeatDays;
	}

	public String[] getAlarmDifficulties() {
		return alarmDifficulties;
	}

	public String[] getAlarmTones() {
		return alarmTones;
	}

	public String[] getAlarmTonePaths() {
		return alarmTonePaths;
	}

}
