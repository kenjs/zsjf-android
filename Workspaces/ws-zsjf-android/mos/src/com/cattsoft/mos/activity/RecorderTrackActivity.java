package com.cattsoft.mos.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cattsoft.mos.R;
import com.cattsoft.mos.util.AppLog;

public class RecorderTrackActivity extends BasicActivity {
	private String TAG = "session";

	private RadioGroup mRadioGroup1, mRadioGroup2, mRadioGroup3;
	private RadioButton mRadio1, mRadio2, mRadio3, mRadio4, mRadio5, mRadio6,
			mRadio7;

	private static final int RECORDER_BPP = 16;
	// private static final String AUDIO_RECORDER_FILE_EXT_WAV = ".wav";
	public static final String AUDIO_RECORDER_FOLDER = "朗宸资讯/音频";
	private static final String AUDIO_RECORDER_TEMP_FILE = "record_temp.raw";
	private static int frequency = 11025;
	private static int channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
	private static int EncodingBitRate = AudioFormat.ENCODING_PCM_16BIT;

	private AudioRecord audioRecord = null;
	private AudioTrack audioTrack = null;
	private int recBufSize = 0;
	private int playBufSize = 0;
	private Thread recordingThread = null;
	private boolean isRecording = false;
	private boolean isTracking = false;
	private boolean m_keep_running;

	private TextView tvTime = null;

	protected PCMAudioTrack m_player;
	SeekBar skbVolume;// 调节音量

	int second = 0, minute = 0, hour = 0, tempSencond=0;;
	long totalDataLen = 0;
	
	private Animation operatingAnimBig=null;
	private ImageView ivBig=null;
	
	private Animation operatingAnimSmall=null;
	private ImageView ivSmall=null;
	
	private Button btnStart=null;
	private Button btnStop=null;
	private Button btnPlay=null;
	private Button btnSave=null;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_track);
		super.setTitleBar("录音", View.VISIBLE, View.VISIBLE, View.INVISIBLE,
				false);
		initView();
		super.setTitleRightButtonImg(R.drawable.title_bar_file_list);
		setButtonHandlers();
		enableButton1(false);

	}
	
	

	private void setButtonHandlers() {
		((Button) findViewById(R.id.btnRecord)).setOnClickListener(btnClick);
		((Button) findViewById(R.id.btnStop)).setOnClickListener(btnClick);
		((Button) findViewById(R.id.btnTrack)).setOnClickListener(btnClick);
		((Button) findViewById(R.id.btnsave)).setOnClickListener(btnClick);
		// ((Button)findViewById(R.id.btnExit)).setOnClickListener(btnClick);
		// mRadioGroup1 = (RadioGroup) findViewById(R.id.myRadioGroup1);
		// mRadioGroup2 = (RadioGroup) findViewById(R.id.myRadioGroup2);
		// mRadioGroup3 = (RadioGroup) findViewById(R.id.myRadioGroup3);
		// mRadio1 = (RadioButton) findViewById(R.id.myRadio1Button1);
		// mRadio2 = (RadioButton) findViewById(R.id.myRadio1Button2);
		// mRadio3 = (RadioButton) findViewById(R.id.myRadio2Button1);
		// mRadio4 = (RadioButton) findViewById(R.id.myRadio2Button2);
		// mRadio5 = (RadioButton) findViewById(R.id.myRadio2Button3);
		// mRadio6 = (RadioButton) findViewById(R.id.myRadio3Button1);
		// mRadio7 = (RadioButton) findViewById(R.id.myRadio3Button2);

		// mRadioGroup1.setOnCheckedChangeListener(mChangeRadio1);
		// mRadioGroup2.setOnCheckedChangeListener(mChangeRadio2);
		// mRadioGroup3.setOnCheckedChangeListener(mChangeRadio3);

		tvTime = (TextView) findViewById(R.id.tv_time);

	}

	private void enableButton(int id, boolean isEnable) {
		((Button) findViewById(id)).setEnabled(isEnable);
	}

	private void enableButton0(boolean isRecording) {
		enableButton(R.id.btnRecord, !isRecording);
		enableButton(R.id.btnTrack, !isRecording);
		enableButton(R.id.btnsave,!isRecording);
		enableButton(R.id.btnStop, isRecording);
	}

	private void enableButton1(boolean isRecording) {
		enableButton(R.id.btnRecord, !isRecording);
		enableButton(R.id.btnTrack, isRecording);
		enableButton(R.id.btnsave,!isRecording);
		enableButton(R.id.btnStop, isRecording);
	}

	private void enableButton2(boolean isRecording) {
		enableButton(R.id.btnRecord, !isRecording);
		enableButton(R.id.btnTrack, !isRecording);
		enableButton(R.id.btnsave,!isRecording);
		enableButton(R.id.btnStop, isRecording);
	}

	private void enableButton3(boolean isTracking) {
		enableButton(R.id.btnRecord, !isTracking);
		enableButton(R.id.btnStop, !isTracking);
		enableButton(R.id.btnTrack, !isTracking);
		enableButton(R.id.btnsave,isTracking);
	}

	private void enableButton4(boolean isTracking) {
		enableButton(R.id.btnRecord, !isTracking);
		enableButton(R.id.btnStop, isTracking);
		enableButton(R.id.btnTrack, !isTracking);
		enableButton(R.id.btnsave,!isTracking);
	}

	private String getFilename() {
		String filepath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		File file = new File(filepath, AUDIO_RECORDER_FOLDER);

		if (file.exists()) {
			file.delete();
		}

		return (file.getAbsolutePath() + "/temp.wav");
	}

	private String getTempFilename() {
		String filepath = Environment.getExternalStorageDirectory().getPath();
		File file = new File(filepath, AUDIO_RECORDER_FOLDER);

		if (!file.exists()) {
			file.mkdirs();
		}

		File tempFile = new File(filepath, AUDIO_RECORDER_TEMP_FILE);

		if (tempFile.exists())
			tempFile.delete();

		return (file.getAbsolutePath() + "/" + AUDIO_RECORDER_TEMP_FILE);
	}

	private void startRecording() {

		createAudioRecord();

		audioRecord.startRecording();

		isRecording = true;

		recordingThread = new Thread(new Runnable() {

			@Override
			public void run() {
				writeAudioDataToFile();
			}
		}, "AudioRecorder Thread");

		recordingThread.start();
	}

	private void writeAudioDataToFile() {
		byte data[] = new byte[recBufSize];
		String filename = getTempFilename();
		FileOutputStream os = null;

		try {
			os = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int read = 0;

		if (null != os) {
			while (isRecording) {
				read = audioRecord.read(data, 0, recBufSize);

				if (AudioRecord.ERROR_INVALID_OPERATION != read) {
					try {
						os.write(data);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void stopRecording() {
		if (null != audioRecord) {
			isRecording = false;

			audioRecord.stop();
			audioRecord.release();

			audioRecord = null;
			recordingThread = null;
		}

		copyWaveFile(getTempFilename(), getFilename());
		deleteTempFile();
	}

	private void deleteTempFile() {
		File file = new File(getTempFilename());

		file.delete();
	}

	private void copyWaveFile(String inFilename, String outFilename) {
		FileInputStream in = null;
		FileOutputStream out = null;
		long totalAudioLen = 0;
		totalDataLen = totalAudioLen + 36;
		long longSampleRate = frequency;
		int channels = 2;
		long byteRate = RECORDER_BPP * frequency * channels / 8;

		byte[] data = new byte[recBufSize];

		try {
			in = new FileInputStream(inFilename);
			out = new FileOutputStream(outFilename);
			totalAudioLen = in.getChannel().size();
			totalDataLen = totalAudioLen + 36;

			AppLog.logString("File size: " + totalDataLen);

			WriteWaveFileHeader(out, totalAudioLen, totalDataLen,
					longSampleRate, channels, byteRate);

			while (in.read(data) != -1) {
				out.write(data);
			}

			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void WriteWaveFileHeader(FileOutputStream out, long totalAudioLen,
			long totalDataLen, long longSampleRate, int channels, long byteRate)
			throws IOException {

		byte[] header = new byte[44];

		header[0] = 'R'; // RIFF/WAVE header
		header[1] = 'I';
		header[2] = 'F';
		header[3] = 'F';
		header[4] = (byte) (totalDataLen & 0xff);
		header[5] = (byte) ((totalDataLen >> 8) & 0xff);
		header[6] = (byte) ((totalDataLen >> 16) & 0xff);
		header[7] = (byte) ((totalDataLen >> 24) & 0xff);
		header[8] = 'W';
		header[9] = 'A';
		header[10] = 'V';
		header[11] = 'E';
		header[12] = 'f'; // 'fmt ' chunk
		header[13] = 'm';
		header[14] = 't';
		header[15] = ' ';
		header[16] = 16; // 4 bytes: size of 'fmt ' chunk
		header[17] = 0;
		header[18] = 0;
		header[19] = 0;
		header[20] = 1; // format = 1
		header[21] = 0;
		header[22] = (byte) channels;
		header[23] = 0;
		header[24] = (byte) (longSampleRate & 0xff);
		header[25] = (byte) ((longSampleRate >> 8) & 0xff);
		header[26] = (byte) ((longSampleRate >> 16) & 0xff);
		header[27] = (byte) ((longSampleRate >> 24) & 0xff);
		header[28] = (byte) (byteRate & 0xff);
		header[29] = (byte) ((byteRate >> 8) & 0xff);
		header[30] = (byte) ((byteRate >> 16) & 0xff);
		header[31] = (byte) ((byteRate >> 24) & 0xff);
		header[32] = (byte) (2 * 16 / 8); // block align
		header[33] = 0;
		header[34] = RECORDER_BPP; // bits per sample
		header[35] = 0;
		header[36] = 'd';
		header[37] = 'a';
		header[38] = 't';
		header[39] = 'a';
		header[40] = (byte) (totalAudioLen & 0xff);
		header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
		header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
		header[43] = (byte) ((totalAudioLen >> 24) & 0xff);

		out.write(header, 0, 44);
	}

	
	private void startAnimation(){
		ivBig=(ImageView)findViewById(R.id.iv_wheel_big);
		if(operatingAnimBig==null) {
			operatingAnimBig = AnimationUtils.loadAnimation(this, R.anim.big_wheel);  
		}
		
		LinearInterpolator lin = new LinearInterpolator();  
		operatingAnimBig.setInterpolator(lin);  
		ivBig.startAnimation(operatingAnimBig); 
		
		
		ivSmall=(ImageView)findViewById(R.id.iv_wheel_small);
		if(operatingAnimSmall==null) {
			operatingAnimSmall = AnimationUtils.loadAnimation(this, R.anim.small_wheel);  
		}
		LinearInterpolator lin2 = new LinearInterpolator();  
		operatingAnimSmall.setInterpolator(lin2);  
		ivSmall.startAnimation(operatingAnimSmall); 
		
		
	}
	
	private void stopAnimation() {
		ivBig.clearAnimation();
		ivSmall.clearAnimation();
	}
	
	private View.OnClickListener btnClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
}
		}
	};

	public void createAudioRecord() {
		recBufSize = AudioRecord.getMinBufferSize(frequency,
				channelConfiguration, EncodingBitRate);

		audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, frequency,
				channelConfiguration, EncodingBitRate, recBufSize);
	}

	public void createAudioTrack() {
		playBufSize = AudioTrack.getMinBufferSize(frequency,
				channelConfiguration, EncodingBitRate);

		audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, frequency,
				channelConfiguration, EncodingBitRate, playBufSize,
				AudioTrack.MODE_STREAM);
	}

	class PCMAudioTrack extends Thread {

		protected byte[] m_out_bytes;

		final String FILE_PATH = "/sdcard/朗宸资讯/音频";
		final String FILE_NAME = "temp.wav";

		File file;
		FileInputStream in;
		int fileSize=0;
		int fileTempSise=0;

		public void init() {
			try {
				file = new File(FILE_PATH, FILE_NAME);
				file.createNewFile();
				in = new FileInputStream(file);
				fileSize=in.available();
				System.out.println("文件大小为：：：" +fileSize);

				// in.read(temp, 0, length);

				m_keep_running = true;

				createAudioTrack();

				m_out_bytes = new byte[playBufSize];

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void free() {
			m_keep_running = false;
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				Log.d("sleep exceptions...\n", "");
			}
		}

		public void run() {
			byte[] bytes_pkg = null;
			audioTrack.play();
			while (m_keep_running) {
				try {
					
					in.read(m_out_bytes);
					bytes_pkg = m_out_bytes.clone();
					audioTrack.write(bytes_pkg, 0, bytes_pkg.length);
					
					//根据已播放文件大小判断是否播放完成
					fileTempSise=fileTempSise+m_out_bytes.length;
					if(fileTempSise>fileSize) {
						Message m1=new Message();
						m1.what=1;
						handler.sendMessage(m1);
						break;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			audioTrack.stop();
			audioTrack = null;
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("播放完成------------------------------");
		}
	}

	RadioGroup.OnCheckedChangeListener mChangeRadio1 = new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if (checkedId == mRadio1.getId()) {

				channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_STEREO;

			} else if (checkedId == mRadio2.getId()) {

				channelConfiguration = AudioFormat.CHANNEL_CONFIGURATION_MONO;
			}
		}
	};

	RadioGroup.OnCheckedChangeListener mChangeRadio2 = new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if (checkedId == mRadio3.getId()) {

				frequency = 44100;
			} else if (checkedId == mRadio4.getId()) {

				frequency = 22050;
			} else if (checkedId == mRadio5.getId()) {

				frequency = 11025;
			}
		}
	};

	RadioGroup.OnCheckedChangeListener mChangeRadio3 = new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if (checkedId == mRadio6.getId()) {

				EncodingBitRate = AudioFormat.ENCODING_PCM_16BIT;
			} else if (checkedId == mRadio7.getId()) {

				EncodingBitRate = AudioFormat.ENCODING_PCM_8BIT;
			}
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Override
	protected void initView() {
		btnStart=(Button) findViewById(R.id.btnRecord);
		btnStop=(Button)findViewById(R.id.btnStop);
		btnPlay=(Button)findViewById(R.id.btnTrack);
		btnSave=(Button)findViewById(R.id.btnsave);
		
//		((Button) findViewById(R.id.btnRecord)).setOnClickListener(btnClick);
//		((Button) findViewById(R.id.btnStop)).setOnClickListener(btnClick);
//		((Button) findViewById(R.id.btnTrack)).setOnClickListener(btnClick);
//		((Button) findViewById(R.id.btnsave)).setOnClickListener(btnClick);
	}

	@Override
	protected void registerListener() {

	}

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				stopAnimation();
			}
			case -1: {
			
			}
			}
			
			if (mProgressDialog != null)
				mProgressDialog.dismiss();// 当接到消息时，关闭进度条
			
		}
	};;

	private Runnable taskAdd = new Runnable() {
		
		public void run() {
			if (isRecording) {
				handler.postDelayed(this, 1000);
				second++;
				tempSencond++;
				if (second < 60) {
					tvTime.setText(format(second));
				} else if (second < 3600) {
					minute = second / 60;
					tempSencond = second % 60;
				} else {
					hour = second / 3600;
					minute = (second % 3600) / 60;
					tempSencond = (second % 3600) % 60;
				}
				tvTime.setText(format(hour) + ":" + format(minute) + ":"
						+ format(tempSencond));
			}
		}
	};
	
private Runnable taskjian = new Runnable() {
		
		public void run() {
			
				
				if(second>=0) {
					handler.postDelayed(this, 1000);
					second--;
					tempSencond--;
					
					if(second>=3600) {
						hour = second / 3600;
						minute = (second % 3600) / 60;
						tempSencond = (second % 3600) % 60;
					}else if(second>60 && second<3600) {
						minute = second / 60;
						System.out.println("tempSencondtempSencond="+tempSencond);
						if(tempSencond==0) {
							tempSencond=60;
						}
						tempSencond = second % 60;
					}else {
//						tvTime.setText(format(second));
						tempSencond=second;
						minute=0;
					}
					
//					
//					if (second > 60) {
//						tvTime.setText(format(second));
//						minute=0;
//					} else if (second < 3600) {
//						minute = second / 60;
//						System.out.println("tempSencondtempSencond="+tempSencond);
//						if(tempSencond==0) {
//							tempSencond=60;
//						}
//						tempSencond = second % 60;
//					} else {
//						hour = second / 3600;
//						minute = (second % 3600) / 60;
//						tempSencond = (second % 3600) % 60;
//					}
					tvTime.setText(format(hour) + ":" + format(minute) + ":"
							+ format(tempSencond));
				}
				
		
		}
	};
	

	private String format(int f) {
		String s = "";
		if (f < 10) {
			s = "0" + String.valueOf(f);
		} else {
			s = String.valueOf(new Integer(f));
		}
		return s;
	}

	private void resetTime() {
		tvTime.setText("00:00:00");
		second = 0;
	}

	@Override
	public void rightButtonOnClick() {
		this.getTitleRightButton().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				HashMap m=new HashMap();
				m.put("pageFlag", "L");
				intent.putExtra("parmList", m);
				intent.setClass(RecorderTrackActivity.this,
						RecorderListActivity.class);
				startActivityForResult(intent, 1);
			
			}
		});
	}


}