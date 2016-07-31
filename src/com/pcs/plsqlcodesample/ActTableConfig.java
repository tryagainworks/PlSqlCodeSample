package com.pcs.plsqlcodesample;


import java.io.IOException;
import java.io.InputStream;

import com.lib.mylib.ClsMyActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActTableConfig extends ClsMyActionBarActivity implements OnClickListener {

	Button cmdGenerate;
	
	public ActTableConfig() {
		super(false,2);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_config);
		fnInit();
		fnInitLocalControls();
	}

	private void fnShowScript() {
		Intent intDlg = new Intent(this.getApplicationContext(), ActShowScript.class);
		String strScript = "CREATE TABLE hr.admin_emp (\n" +
				"         empno      NUMBER(5) PRIMARY KEY,\n" +
				"         ename      VARCHAR2(15) NOT NULL,\n" +
				"         ssn        NUMBER(9) ENCRYPT,\n" +
				"         job        VARCHAR2(10),\n" +
				"         mgr        NUMBER(5),\n" +
				"         hiredate   DATE DEFAULT (sysdate),\n" +
				"         photo      BLOB,\n" +
				"         sal        NUMBER(7,2),\n" +
				"         hrly_rate  NUMBER(7,2) GENERATED ALWAYS AS (sal/2080),\n" +
				"         comm       NUMBER(7,2),\n" +
				"         deptno     NUMBER(3) NOT NULL\n" +
				"                     CONSTRAINT admin_dept_fkey REFERENCES hr.departments\n" +
				"                     (department_id))\n" +
				"   TABLESPACE admin_tbs\n" +
				"   STORAGE ( INITIAL 50K);\n" +
				"COMMENT ON TABLE hr.admin_emp IS 'Enhanced employee table';";
		
		InputStream is;
		try {
			is = this.getResources().getAssets().open("sqlhelper");
			int size = is.available();
			Log.i("sqlite", ""+size);
			float f = (float)ClsPrjGlobal.getJNIMethod().fnConvertGramstoKiloGrams(3.4);
			Log.i("sqlite1", getApplicationContext().getDatabasePath("sqlhelper").getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		strScript = ClsPrjGlobal.getJNIMethod().fnFormatValue(this.getResources().getAssets(), getApplicationContext().getDatabasePath("sqlhelper").getPath());
		
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
	
	private void fnInitLocalControls(){
		cmdGenerate = (Button)findViewById(R.id.cmdGenerateScript);
		cmdGenerate.setOnClickListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.distance_time, menu);
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
		switch(v.getId()){
		case R.id.cmdGenerateScript:
			fnShowScript();
			break;
		}		
	}
}
