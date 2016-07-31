package com.pcs.plsqlcodesample;

import android.app.Activity;
import com.lib.mylib.ClsMyActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActFunctionConfig extends ClsMyActionBarActivity implements OnClickListener {

	Button cmdGenerate;
	public ActFunctionConfig() {
		super(false, 4);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function_config);
		fnInitLocalControls();
		fnInit();
	}
	
	private void fnInitLocalControls(){
		cmdGenerate = (Button)findViewById(R.id.cmdGenerateScript);
		cmdGenerate.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act_function_config, menu);
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

	private void fnShowScript() {
		Intent intDlg = new Intent(this.getApplicationContext(), ActShowScript.class);

		String strScript = "CREATE FUNCTION get_bal(acc_no IN NUMBER)\n" +
		"   RETURN NUMBER\n" +
		"   IS acc_bal NUMBER(11,2);\n" +
		"   BEGIN\n" +
		"      SELECT order_total\n" +
		"      INTO acc_bal\n" +
		"      FROM orders\n" +
		"      WHERE customer_id = acc_no;\n" +
		"      RETURN(acc_bal);\n" +
		"    END;";

		intDlg.putExtra("SCRIPT", strScript);
		this.startActivityForResult(intDlg, 1);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == Activity.RESULT_OK) {
				// Write your code if there's no result				
			}
			if (resultCode == Activity.RESULT_CANCELED) {
				// Write your code if there's no result
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.cmdGenerateScript:
			fnShowScript();
			break;
		default:
		}
	}
}
