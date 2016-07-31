package com.pcs.plsqlcodesample;

import android.app.Activity;
import com.lib.mylib.ClsMyActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class ActPackageConfig extends ClsMyActionBarActivity implements OnClickListener, OnTouchListener {

	Button cmdGenerate;
	public ActPackageConfig() {
		super(false, 5);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_package_config);
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
		getMenuInflater().inflate(R.menu.act_package_config, menu);
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
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	private void fnShowScript() {
		Intent intDlg = new Intent(this.getApplicationContext(), ActShowScript.class);
		String strScript = "CREATE OR REPLACE PACKAGE emp_mgmt AS\n" +
				"   FUNCTION hire (last_name VARCHAR2, job_id VARCHAR2,\n" +
				"      manager_id NUMBER, salary NUMBER,\n" +
				"      commission_pct NUMBER, department_id NUMBER)\n" +
				"      RETURN NUMBER;\n" +
				"   FUNCTION create_dept(department_id NUMBER, location_id NUMBER)\n" +
				"      RETURN NUMBER;\n" +
				"   PROCEDURE remove_emp(employee_id NUMBER);\n" +
				"   PROCEDURE remove_dept(department_id NUMBER);\n" +
				"   PROCEDURE increase_sal(employee_id NUMBER, salary_incr NUMBER);\n" +
				"   PROCEDURE increase_comm(employee_id NUMBER, comm_incr NUMBER);\n" +
				"   no_comm EXCEPTION;\n" +
				"   no_sal EXCEPTION;\n" +
				"END emp_mgmt;\n\n\n" +
				"CREATE OR REPLACE PACKAGE BODY emp_mgmt AS\n" +
				"   tot_emps NUMBER;\n" +
				"   tot_depts NUMBER;\n" +
				"FUNCTION hire\n" +
				"   (last_name VARCHAR2, job_id VARCHAR2,\n" +
				"    manager_id NUMBER, salary NUMBER,\n" +
				"    commission_pct NUMBER, department_id NUMBER)\n" +
				"   RETURN NUMBER IS new_empno NUMBER;\n" +
				"BEGIN\n" +
				"   SELECT employees_seq.NEXTVAL\n" +
				"      INTO new_empno\n" +
				"      FROM DUAL;\n" +
				"   INSERT INTO employees\n" +
				"      VALUES (new_empno, 'First', 'Last','first.last@oracle.com',\n" +
				"              '(123)123-1234','18-JUN-02','IT_PROG',90000000,00,\n" +
				"              100,110);\n" +
				"      tot_emps := tot_emps + 1;\n" +
				"   RETURN(new_empno);\n" +
				"END;\n" +
				"FUNCTION create_dept(department_id NUMBER, location_id NUMBER)\n" +
				"   RETURN NUMBER IS\n" +
				"      new_deptno NUMBER;\n" +
				"   BEGIN\n" +
				"      SELECT departments_seq.NEXTVAL\n" +
				"         INTO new_deptno\n" +
				"         FROM dual;\n" +
				"      INSERT INTO departments\n" +
				"         VALUES (new_deptno, 'department name', 100, 1700);\n" +
				"      tot_depts := tot_depts + 1;\n" +
				"      RETURN(new_deptno);\n" +
				"   END;\n" +
				"PROCEDURE remove_emp (employee_id NUMBER) IS\n" +
				"   BEGIN\n" +
				"      DELETE FROM employees\n" +
				"      WHERE employees.employee_id = remove_emp.employee_id;\n" +
				"      tot_emps := tot_emps - 1;\n" +
				"   END;\n" +
				"PROCEDURE remove_dept(department_id NUMBER) IS\n" +
				"   BEGIN\n" +
				"      DELETE FROM departments\n" +
				"      WHERE departments.department_id = remove_dept.department_id;\n" +
				"      tot_depts := tot_depts - 1;\n" +
				"      SELECT COUNT(*) INTO tot_emps FROM employees;\n" +
				"   END;\n" +
				"PROCEDURE increase_sal(employee_id NUMBER, salary_incr NUMBER) IS\n" +
				"   curr_sal NUMBER;\n" +
				"   BEGIN\n" +
				"      SELECT salary INTO curr_sal FROM employees\n" +
				"      WHERE employees.employee_id = increase_sal.employee_id;\n" +
				"      IF curr_sal IS NULL\n" +
				"         THEN RAISE no_sal;\n" +
				"      ELSE\n" +
				"         UPDATE employees\n" +
				"         SET salary = salary + salary_incr\n" +
				"         WHERE employee_id = employee_id;\n" +
				"      END IF;\n" +
				"   END;\n" +
				"PROCEDURE increase_comm(employee_id NUMBER, comm_incr NUMBER) IS\n" +
				"   curr_comm NUMBER;\n" +
				"   BEGIN\n" +
				"      SELECT commission_pct\n" +
				"      INTO curr_comm\n" +
				"      FROM employees\n" +
				"      WHERE employees.employee_id = increase_comm.employee_id;\n" +
				"      IF curr_comm IS NULL\n" +
				"         THEN RAISE no_comm;\n" +
				"      ELSE\n" +
				"         UPDATE employees\n" +
				"         SET commission_pct = commission_pct + comm_incr;\n" +
				"      END IF;\n" +
				"   END;\n" +
				"END emp_mgmt;\n" +
				"/";
		
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
