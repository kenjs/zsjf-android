package com.cattsoft.mos.pub;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class AsyncImageLoader {
	/**
	 * 
	 * 软引用对象，在响应内存需要时，由垃圾回收器决定是否清除此对象。软引用对象最常用于实现内存敏感的缓存。
	 */
	public HashMap<String, SoftReference<Drawable>> imageCache;

	public AsyncImageLoader() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();
	}

	public Drawable loadDrawable(final String imageUrl,
			final ImageView imageView, final ImageCallback imagecallback) {
		if (imageCache.containsKey(imageUrl)) {
			// 从缓存中读取人人
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			Drawable drawable = softReference.get();
			if (drawable != null) {
				return drawable;
			}
		}

		final Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				imagecallback.imageLoaded((Drawable) msg.obj, imageView,
						imageUrl);
			}

		};

		new Thread() {
			public void run() {
				Drawable drawable = loadImageFromUrl(imageUrl);
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		}.start();

		return null;
	}

	public static Drawable loadImageFromUrl(String urlPath) {
		URL url;
		InputStream is = null;

		try {
			url = new URL(urlPath);
			is = (InputStream) url.getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Drawable drawable = Drawable.createFromStream(is, "src");
		return drawable;

	}

}
