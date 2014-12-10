package com.cattsoft.mos.jsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cattsoft.mos.R;
import com.cattsoft.mos.dialog.SelectDialog;
import com.cattsoft.mos.util.StringUtil;
import com.cattsoft.mos.util.ViewUtil;
import com.cattsoft.mos.view.EditLabelText;
import com.cattsoft.mos.view.SelectLabelText;
/**
 * 整存整取
 * @author xieyunchao
 *
 */
public class ZCZQ {
	private Context context;
	private ViewGroup pv;
	
	private  SelectLabelText sltzczqbz;//币种
	private  EditLabelText sltzczqcsckje;//初始存款金额
	private  SelectLabelText sltzczqckqx;//存款期限
	private  EditLabelText eltzczqnlv;//年利率
	private EditLabelText  ltzczqsdlxje;//所得利息
	private EditLabelText ltzczqlxsje;//利息税
	private EditLabelText ltzczqbxhj;//本息合计
	
	private TextView tvbz;
	private EditText etcsckje;
	private TextView tvckqx;
	private TextView tvnlv;
	private TextView tvsdlx;
	private TextView tvlxs;
	private TextView tvbxhj;
	
	private List moneyTypeList;
	private Map bizhongMap;
	
	private List cunkuanQixianList=null;
	private Map cunkuanQixianMap=null;
	
	private double nlv=2.86;
	
	
	
	public ZCZQ(Context context,ViewGroup v) {
		this.context=context;
		this.pv=v;
		initData();
		//initView(context,v);
//		regListener(context);
	}
	
	public void initUI() {
		initView(context,pv);
		regListener(context);
	}
	
	public void initView(Context context,ViewGroup pv) {
		View v=View.inflate(context, R.layout.licai_tools_4zczq, null);
		sltzczqbz=(SelectLabelText)v.findViewById(R.id.bizhong);
		sltzczqcsckje=(EditLabelText)v.findViewById(R.id.csckje);
		sltzczqckqx= (SelectLabelText)v.findViewById(R.id.ckqx);
		eltzczqnlv=(EditLabelText)v.findViewById(R.id.nlv);
		ltzczqsdlxje=(EditLabelText)v.findViewById(R.id.sdlxje);
		ltzczqlxsje=(EditLabelText)v.findViewById(R.id.lxsje);
		ltzczqbxhj=(EditLabelText)v.findViewById(R.id.bxhj);
		
		tvbz=sltzczqbz.getValueText();
		etcsckje=sltzczqcsckje.getValueText();
		etcsckje.setInputType(InputType.TYPE_CLASS_NUMBER);
		tvckqx=sltzczqckqx.getValueText();
		tvckqx.setText("三个月");
		tvnlv=eltzczqnlv.getValueText();
		tvsdlx=ltzczqsdlxje.getValueText();
		ViewUtil.disableEditText(tvsdlx);
		tvlxs=ltzczqlxsje.getValueText();
		ViewUtil.disableEditText(tvlxs);
		tvbxhj=ltzczqbxhj.getValueText();
		
		tvnlv.setText(nlv+"  %");
		tvbz.setText((String)bizhongMap.get("name"));
		
		pv.addView(v);
		
	}
	
	private void initData() {
		cunkuanQixianList=new ArrayList();
		cunkuanQixianMap=new HashMap();
		cunkuanQixianMap.put("value", "3");
		cunkuanQixianMap.put("name","三个月");
		
		Map m1=new HashMap();
		m1.put("name", "三个月");
		m1.put("value", "3");
		
		Map m2=new HashMap();
		m2.put("name", "六个月");
		m2.put("value", "6");
		
		Map m3=new HashMap();
		m3.put("name", "一年");
		m3.put("value", "12");
		
		Map m4=new HashMap();
		m4.put("name", "两年");
		m4.put("value", "24");
		
		Map m5=new HashMap();
		m5.put("name", "三年");
		m5.put("value", "36");
		
		Map m6=new HashMap();
		m6.put("name", "五年");
		m6.put("value", "60");
		
		cunkuanQixianList.add(m1);
		cunkuanQixianList.add(m2);
		cunkuanQixianList.add(m3);
		cunkuanQixianList.add(m4);
		cunkuanQixianList.add(m5);
		
		
	}
	
	//注册计算按钮的监听
	public void regJsListener(Button  btn,final Context context) {
		btn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(validate(context)) {
					String cscrje=etcsckje.getText().toString();
					String ckqx=(String)cunkuanQixianMap.get("value");
					
					int icscrje=Integer.parseInt(cscrje);
					int ickqx=Integer.parseInt(ckqx);
					String cdlxje=String.format("%.2f" ,icscrje*(nlv*0.01*ickqx/12));
					tvsdlx.setText(cdlxje+"   元");
					tvbxhj.setText(Double.parseDouble(cdlxje)+icscrje+"元");
				}
			}
		});
	}
	
	private boolean validate(Context context) {
		if(StringUtil.isBlank(etcsckje.getText().toString())) {
			Toast.makeText(context.getApplicationContext(), "请填写初始存款金额！", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
		
	}
	
	private  void regListener(final Context context) {
		sltzczqbz.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(context, R.style.selectDialog,"币种",moneyTypeList,bizhongMap,new SelectDialog.OnSelectItemgListener() {
					@Override
					public void refreshActivity(Map m) {
						tvbz.setText((String)m.get("name"));
						bizhongMap=m;
					}
				});
				 dialog.show();
			}
		});
		
		sltzczqckqx.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Dialog dialog = new SelectDialog(context, R.style.selectDialog,"存款期限",cunkuanQixianList,cunkuanQixianMap,new SelectDialog.OnSelectItemgListener() {
					@Override
					public void refreshActivity(Map m) {
						tvckqx.setText((String)m.get("name"));
						cunkuanQixianMap=m;
					}
				});
				 dialog.show();
			}
		});
		
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public SelectLabelText getSltzczqbz() {
		return sltzczqbz;
	}

	public void setSltzczqbz(SelectLabelText sltzczqbz) {
		this.sltzczqbz = sltzczqbz;
	}

	public EditLabelText getSltzczqcsckje() {
		return sltzczqcsckje;
	}

	public void setSltzczqcsckje(EditLabelText sltzczqcsckje) {
		this.sltzczqcsckje = sltzczqcsckje;
	}

	public SelectLabelText getSltzczqckqx() {
		return sltzczqckqx;
	}

	public void setSltzczqckqx(SelectLabelText sltzczqckqx) {
		this.sltzczqckqx = sltzczqckqx;
	}

	public EditLabelText getEltzczqnlv() {
		return eltzczqnlv;
	}

	public void setEltzczqnlv(EditLabelText eltzczqnlv) {
		this.eltzczqnlv = eltzczqnlv;
	}

	public EditLabelText getLtzczqsdlxje() {
		return ltzczqsdlxje;
	}

	public void setLtzczqsdlxje(EditLabelText ltzczqsdlxje) {
		this.ltzczqsdlxje = ltzczqsdlxje;
	}

	public EditLabelText getLtzczqlxsje() {
		return ltzczqlxsje;
	}

	public void setLtzczqlxsje(EditLabelText ltzczqlxsje) {
		this.ltzczqlxsje = ltzczqlxsje;
	}

	public EditLabelText getLtzczqbxhj() {
		return ltzczqbxhj;
	}

	public void setLtzczqbxhj(EditLabelText ltzczqbxhj) {
		this.ltzczqbxhj = ltzczqbxhj;
	}

	public TextView getTvbz() {
		return tvbz;
	}

	public void setTvbz(TextView tvbz) {
		this.tvbz = tvbz;
	}

	public EditText getEtcsckje() {
		return etcsckje;
	}

	public void setEtcsckje(EditText etcsckje) {
		this.etcsckje = etcsckje;
	}

	public TextView getTvckqx() {
		return tvckqx;
	}

	public void setTvckqx(TextView tvckqx) {
		this.tvckqx = tvckqx;
	}

	public TextView getTvnlv() {
		return tvnlv;
	}

	public void setTvnlv(TextView tvnlv) {
		this.tvnlv = tvnlv;
	}

	public TextView getTvsdlx() {
		return tvsdlx;
	}

	public void setTvsdlx(TextView tvsdlx) {
		this.tvsdlx = tvsdlx;
	}

	public TextView getTvlxs() {
		return tvlxs;
	}

	public void setTvlxs(TextView tvlxs) {
		this.tvlxs = tvlxs;
	}

	public TextView getTvbxhj() {
		return tvbxhj;
	}

	public void setTvbxhj(TextView tvbxhj) {
		this.tvbxhj = tvbxhj;
	}

	public List getMoneyTypeList() {
		return moneyTypeList;
	}

	public void setMoneyTypeList(List moneyTypeList) {
		this.moneyTypeList = moneyTypeList;
	}

	public Map getBizhongMap() {
		return bizhongMap;
	}

	public void setBizhongMap(Map bizhongMap) {
		this.bizhongMap = bizhongMap;
	}

	public List getCunkuanQixianList() {
		return cunkuanQixianList;
	}

	public void setCunkuanQixianList(List cunkuanQixianList) {
		this.cunkuanQixianList = cunkuanQixianList;
	}

	public Map getCunkuanQixianMap() {
		return cunkuanQixianMap;
	}

	public void setCunkuanQixianMap(Map cunkuanQixianMap) {
		this.cunkuanQixianMap = cunkuanQixianMap;
	}

	public double getNlv() {
		return nlv;
	}

	public void setNlv(double nlv) {
		this.nlv = nlv;
	}

	public ViewGroup getPv() {
		return pv;
	}

	public void setPv(ViewGroup pv) {
		this.pv = pv;
	}
	
	

}
