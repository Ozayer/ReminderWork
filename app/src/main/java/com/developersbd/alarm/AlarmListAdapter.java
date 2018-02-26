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

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.developersbd.alarm.database.Database;

import java.util.ArrayList;
import java.util.List;

public class AlarmListAdapter extends BaseAdapter {


	private AlarmActivity alarmActivity;
	private List<Alarm> alarms = new ArrayList<Alarm>();

	public static final String ALARM_FIELDS[] = { Database.COLUMN_ALARM_ACTIVE,
			Database.COLUMN_ALARM_TIME, Database.COLUMN_ALARM_DAYS };

	public AlarmListAdapter(AlarmActivity alarmActivity) {
		this.alarmActivity = alarmActivity;

//		Database.init(alarmActivity);
//		alarms = Database.getAll();
	}

	@Override
	public int getCount() {
		return alarms.size();
	}

	@Override
	public Object getItem(int position) {
		return alarms.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		if (null == view)
//			view = LayoutInflater.from(alarmActivity).inflate(
//					R.layout.alarm_list_element, null);
			view = LayoutInflater.from(alarmActivity).inflate(
					R.layout.list_item, null);

		Alarm alarm = (Alarm) getItem(position);


		 CheckBox checkBox = (CheckBox) view.findViewById(R.id.alarmCheck);
		 checkBox.setChecked(alarm.getAlarmActive());
		 checkBox.setTag(position);
		 checkBox.setOnClickListener(alarmActivity);

		boolean check=alarm.getAlarmActive();



		TextView titleView = (TextView) view
				.findViewById(R.id.titleText);
		if(check==true) {
			titleView.setText("Active Alarm");
			titleView.setBackgroundColor(Color.rgb(40,148,17));
//			titleView.setBackgroundColor(Color.parseColor(""+R.color.));
			titleView.setTextColor(Color.WHITE);


		}
		else
		{
			titleView.setText("Deactive Alarm");
			titleView.setTextColor(Color.WHITE);
			titleView.setBackgroundColor(Color.rgb(50,50,50));
		}


		TextView alarmCategoryView = (TextView) view
				.findViewById(R.id.cateGoryText);
		alarmCategoryView.setText(alarm.getAlarmName());
		 
		TextView alarmTimeView = (TextView) view
				.findViewById(R.id.alarmtimetext);
		alarmTimeView.setText(alarm.getAlarmTimeString());

		TextView alarmDateView = (TextView) view
				.findViewById(R.id.alarmdate);
		alarmDateView.setText(alarm.getAlarmDateString());

		
			TextView alarmDaysView = (TextView) view
					.findViewById(R.id.repeatText);
			alarmDaysView.setText(alarm.getRepeatDaysString());

		TextView noteView = (TextView) view
				.findViewById(R.id.noteText);
		noteView.setText(alarm.getAlarmNote());
		

		return view;
	}

	public List<Alarm> getMathAlarms() {
		return alarms;
	}

	public void setMathAlarms(List<Alarm> alarms) {
		this.alarms = alarms;
	}

}
