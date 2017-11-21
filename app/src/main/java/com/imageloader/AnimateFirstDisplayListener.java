/**
 * ProjectName:AndroidO2OLive2014Moblie
 * PackageName:net.O2OLive2014.android.common
 * FileNmae:AnimateFirstDisplayListener.java
 */
package com.imageloader;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * 图片加载第一次显示监听器
 * @author KingKong·HE
 * @Time 2014�?�?5�?下午3:39:19
 */
public class AnimateFirstDisplayListener implements ImageLoadingListener  {

	static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

	@Override
	public void onLoadingCancelled(String arg0, View arg1) {
		
	}
	@Override
	public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
		
	}
	@Override
	public void onLoadingStarted(String arg0, View arg1) {
		
	}
	@Override
	public void onLoadingComplete(String imageUri, View arg1, Bitmap loadedImage) {
		if (loadedImage != null) {
			ImageView imageView = (ImageView) arg1;
			// 是否第一次显�?
			boolean firstDisplay = !displayedImages.contains(imageUri);
			if (firstDisplay) {
				// 图片淡入效果
				FadeInBitmapDisplayer.animate(imageView, 500);
				displayedImages.add(imageUri);
			}
		}
		
	}
}
