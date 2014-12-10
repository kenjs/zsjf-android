package com.cattsoft.mos.pub;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public interface ImageCallback {
	  public void imageLoaded(Drawable imageDrawable,ImageView imageView, String imageUrl);
}
