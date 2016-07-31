package com.pcs.plsqlcodesample;

import com.lib.mylib.ClsMyActionBarActivity;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

public class ActProcedureConfig extends ClsMyActionBarActivity implements OnClickListener, OnTouchListener {

	Button cmdGenerate;
	float fltIncrease = 10.0f, fltIncreaseInit = 0.0f, fltWeightInit = 0.0f, fltAmtInit = 0.0f;
	boolean blnInitialValueRecorded = false;
	float fltExistingValue = 0.0f;
	boolean blnIsSingleSelection = true;

	public ActProcedureConfig() {
		super(false, 3);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_procedure_config);
		fnInitLocalControls();
		fnInit();
		fnSetChange(fltIncrease);
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private void fnInitLocalControls() {
		cmdGenerate = (Button)findViewById(R.id.cmdGenerateScript);
		cmdGenerate.setOnClickListener(this);

		if (Build.VERSION.SDK_INT >= 19) {

		} else {
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.wight_amount, menu);
		return true;
	}

	private void fnRecordInitialValue() {
		fltExistingValue = 0.0f;
		if (!blnInitialValueRecorded) {
			blnInitialValueRecorded = true;
			fltIncreaseInit = fltIncrease;
		}
	}

	private void fnResetValue() {
		if (blnInitialValueRecorded) {
			blnInitialValueRecorded = false;
			fltIncrease = fltIncreaseInit;
			fltExistingValue = 0.0f;
			fnSetChange(fltIncrease);
		}
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

	private boolean fnValidateInput() {
		boolean blnInvalid = false;
		if (blnInvalid) {
			fnShowToast("Both fields are mandatory & Should greater than zero");
			return false;
		}
		return true;
	}

	private void fnSetChange(double dblValue) {
		String strValue = String.format("%.2f", dblValue);
	}

	private void fnFindAmt(boolean blnIsUp) {
		fnRecordInitialValue();
		try {
		} catch (ArithmeticException e) {
			// TODO Auto-generated catch block
			fnShowToast(e.getMessage());
			e.printStackTrace();
			viwCurrentView = null;
		}
	}

	private void fnFindWeight(boolean blnIsUp) {
		fnRecordInitialValue();
		try {
		} catch (ArithmeticException e) {
			fnShowToast(e.getMessage());
			e.printStackTrace();
			viwCurrentView = null;
		}
	}

	Toast tst=null;
	private void fnShowToast(String strMsg) {
		if(tst!=null)
			tst.cancel();
		
		tst = Toast.makeText(this.getApplicationContext(), strMsg, Toast.LENGTH_SHORT);
		tst.show();
	}

	private void fnShowScript() {
		Intent intDlg = new Intent(this.getApplicationContext(), ActShowScript.class);
		String strScript = "CREATE PROCEDURE remove_emp (employee_id NUMBER) AS\n" +
				"   tot_emps NUMBER;\n" +
				"   BEGIN\n" +
				"      DELETE FROM employees\n" +
				"      WHERE employees.employee_id = remove_emp.employee_id;\n" +
				"   tot_emps := tot_emps - 1;\n" +
				"   END;";
		
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

	View viwCurrentView=null;

	private final Handler handler = new Handler();
	private final Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if (viwCurrentView != null) {
				switch (viwCurrentView.getId()) {

				}

				handler.postDelayed(runnable, 250);
			}
		}
	};

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			viwCurrentView = v;
			handler.postDelayed(runnable, 200);
		}

		if (event.getAction() == MotionEvent.ACTION_UP) {
			handler.removeCallbacks(runnable);
		}

		return false;
	}
}
