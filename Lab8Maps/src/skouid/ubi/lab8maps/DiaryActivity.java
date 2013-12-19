package skouid.ubi.lab8maps;

import java.util.ArrayList;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class DiaryActivity extends Activity implements OnClickListener, OnKeyListener {
	ArrayList<String> places;
	ArrayAdapter<String> entry;

	EditText diaryEntry;
	Button addEntry;
	ListView diaryList;

	SharedPreferences prefs;
	SharedPreferences.Editor edit;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diary);
		
		final Context context = this;
		Intent intent = getIntent();
		//setup edit box
		diaryEntry = (EditText) findViewById(R.id.entryText);
		//setup button
		addEntry = (Button) findViewById(R.id.diaryButton);
		//setup arraylist
		diaryList = (ListView) findViewById(R.id.diarylist);
		
		//set listeners for the buttons
		addEntry.setOnClickListener(this);
		diaryList.setOnKeyListener(this);

		places = new ArrayList<String>();
		entry = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, places);
		diaryList.setAdapter(entry);
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		edit = prefs.edit();

		diaryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView,
					View view, final int i, long l) {
				// TODO Auto-generated method stub
				LayoutInflater li = LayoutInflater.from(context);
				View promptView = li.inflate(R.layout.diary_view, null);
				AlertDialog.Builder alert = new AlertDialog.Builder(context);
				alert.setView(promptView);
				alert.setCancelable(false);
				alert.setNegativeButton("Delete Entry",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								places.remove(i);
								entry.notifyDataSetChanged();
							}
						});
				AlertDialog alert1 = alert.create();
				alert.show();
			}

		});

	}

	private void addItems(String item) {

		//get items for the list 
			this.entry.add(item);
			this.entry.notifyDataSetChanged();
			this.diaryEntry.setText("");

	}

	public void onClick(View v) {

		if (v == this.addEntry) {
			//add items to the list
			this.addItems(this.diaryEntry.getText().toString());
		}

	}

	public boolean onKey(View v, int keyCode, KeyEvent event) {

		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
			this.addItems(this.diaryEntry.getText().toString());

		return false;
	}
	public void sendMessage(View view)
	{
		//start main activity again
		Intent in = new Intent(this, MainActivity.class);
		startActivity(in);
	}
}