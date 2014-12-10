package com.cattsoft.mos.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * activity栈管理类，每当新产生一个activity时就加入，finish掉一个activity时就remove
 * @author xieyunchao
 * CreateTime Oct 27, 2012 3:21:26 PM
 */
public class ActivityStackControlUtil {


    private static List<Activity> activityList = new ArrayList<Activity>(); 

    public static void remove(Activity activity){ 
        activityList.remove(activity); 
    } 

      

    public static void add(Activity activity){ 
        activityList.add(activity); 
    } 

     

    public static void finishProgram() { 
    	for(int i=0;i<activityList.size();i++) {
    		Activity act=(Activity)activityList.get(i);
    		if(i<activityList.size()-1) {
    			act.finish();
    		}
    		
    	}

        for (Activity activity : activityList) { 
            activity.finish(); 
        } 

       // android.os.Process.killProcess(android.os.Process.myPid()); 

    } 



}
