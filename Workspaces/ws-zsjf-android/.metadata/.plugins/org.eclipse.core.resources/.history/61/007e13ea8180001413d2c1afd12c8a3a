package com.cattsoft.mos.vo.mvo;

import android.app.Notification;
import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable{
	
	/**
	 * 任务id
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 名称
	 */
	private String path;
	
	/**
	 * 进度
	 */
	private int progress;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * Notification 
	 */
	private Notification notification;
	
	/**
	 * 当前状态 
	 */
	private int status;
	
	public Task(){
		
	}
	/**
	 * 实现Parcelable必须完成以下方法，Parcelable为android提供的对象传递的接口，实现该接口类似与实现Serializable，但两者性能不一样，Parcelable性能高============================
	 */
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(icon);
        out.writeString(name);
        out.writeString(path);
        out.writeParcelable(notification, flags);
        out.writeInt(this.id);
        out.writeInt(this.status);
        out.writeInt(this.progress);
    }

    public static final Parcelable.Creator<Task> CREATOR
            = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
    
    private Task(Parcel in) {
    	icon = in.readString();
    	name = in.readString();
    	path = in.readString();
    	notification = in.readParcelable(Task.class.getClassLoader());
    	id = in.readInt();
    	status = in.readInt();
    	progress = in.readInt();
    }
    /**
     * end===================================== 
     */

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
