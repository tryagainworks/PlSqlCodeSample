package com.pcs.plsqlcodesample;

import com.lib.mylib.ClsMyActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActStartUp extends ClsMyActionBarActivity implements OnClickListener {

	Button cmdCreateTable, cmdCreateProcedure, cmdCreateFunction, cmdCreatePackage;

	public ActStartUp() {
		super(true,1);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//new ClsEula(this).show();
		setContentView(R.layout.activity_startup);
		fnInitLocalControls();
		fnInit();
	}
	
	private void fnInitLocalControls(){
		MyException ex = new MyException();
		ex=null;
		cmdCreateTable = (Button)findViewById(R.id.cmdCreateTable);
		cmdCreateTable.setOnClickListener(this);
		
		cmdCreateProcedure = (Button)findViewById(R.id.cmdCreateProcedure);
		cmdCreateProcedure.setOnClickListener(this);
		
		cmdCreateFunction = (Button)findViewById(R.id.cmdCreateFunction);
		cmdCreateFunction.setOnClickListener(this);
		
		cmdCreatePackage = (Button)findViewById(R.id.cmdCreatePackage);
		cmdCreatePackage.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
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

	private void fnShowTableConfiguration(){
		this.startActivity(new Intent(this.getApplicationContext(),ActTableConfig.class));
	}
	
	private void fnShowProcedureConfiguration(){
		this.startActivity(new Intent(this.getApplicationContext(),ActProcedureConfig.class));
	}
	
	private void fnShowFunctionConfiguration(){
		this.startActivity(new Intent(this.getApplicationContext(),ActFunctionConfig.class));
	}
	
	private void fnShowPackageConfiguration(){
		this.startActivity(new Intent(this.getApplicationContext(),ActPackageConfig.class));
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.cmdCreateTable:
			fnShowTableConfiguration();
			break;
		case R.id.cmdCreateProcedure:
			fnShowProcedureConfiguration();
			break;
		case R.id.cmdCreateFunction:
			fnShowFunctionConfiguration();
			break;
		case R.id.cmdCreatePackage:
			fnShowPackageConfiguration();
			break;			
		}
	}
}
