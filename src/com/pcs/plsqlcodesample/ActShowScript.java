package com.pcs.plsqlcodesample;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.lib.mylib.ClsMyActionBarActivity;

public class ActShowScript extends ClsMyActionBarActivity implements OnClickListener {

	EditText txtScript;
	String strScript;

	public ActShowScript() {
		super(false, 6);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_script);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			strScript = extras.getString("SCRIPT");
		}
		
		fnInit();
		fnInitLocalControls();

		fnSetScript(strScript);
	}

	private void fnInitLocalControls() {
		txtScript = (EditText) findViewById(R.id.txtScript);
	}

	private void fnSetScript(String strText) {
		txtScript.setText(strText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_show_script, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
