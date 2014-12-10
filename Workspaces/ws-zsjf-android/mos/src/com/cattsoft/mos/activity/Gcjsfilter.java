package com.cattsoft.mos.activity;

import java.util.HashMap;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

import com.cattsoft.mos.R;
import com.cattsoft.mos.util.ActivityUtil;
import com.cattsoft.mos.view.EditLabelText;
import com.cattsoft.mos.view.SelectLabelText;

/**
 * 工程建设信息筛选
 * 
 * @author xieyunchao
 * 
 */
public class Gcjsfilter extends BasicActivity {

	private SelectLabelText slStartTime;
	private SelectLabelText slEndTime;
	private EditLabelText eltDept;
	private Button btnok;

	String startTime;
	String endTime;
	String dept;

	Intent intent = null;
	DatePickerDialog datePickerDialog = null;

	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gcjsfilter);

		// 设置标题栏
		super.setTitleBar("筛选", View.VISIBLE, View.GONE, View.INVISIBLE, false);

		initData();
		initView();
		registerListener();
		ActivityUtil.getInstance().addActivity(this);

	}

	private void initData() {
		intent = getIntent();
		this.initCalendar();
		slStartTime = (SelectLabelText) findViewById(R.id.gcjs_start_time);
		slEndTime = (SelectLabelText) findViewById(R.id.gcjs_end_time);
		eltDept = (EditLabelText) findViewById(R.id.gcjs_requ_dept);
	}

	@Override
	protected void initView() {
		btnok = (Button) findViewById(R.id.btn_ok);
	}

	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});
	}

	private OnDateSetListener startTimeListener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			slStartTime.getValueText().setText(
					year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

		}
	};

	private OnDateSetListener endTimeListener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			slEndTime.getValueText().setText(
					year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

		}
	};

	@Override
	protected void registerListener() {
		slStartTime.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				datePickerDialog = new DatePickerDialog(Gcjsfilter.this,
						startTimeListener, thisYear, thisMonth, thisDay);
				datePickerDialog.show();
			}
		});

		slEndTime.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				datePickerDialog = new DatePickerDialog(Gcjsfilter.this,
						endTimeListener, thisYear, thisMonth, thisDay);
				datePickerDialog.show();
			}
		});

		btnok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startTime = slStartTime.getValueText().getText().toString();
				endTime = slEndTime.getValueText().getText().toString();
				dept = eltDept.getValueText().getText().toString();

				HashMap m = new HashMap();
				m.put("startTime", startTime);
				m.put("endTime", endTime);
				m.put("dept", dept);
				intent.putExtra("parm", m);
				setResult(RESULT_OK, intent);
				Gcjsfilter.this.finish();
			}
		});

	}

}
