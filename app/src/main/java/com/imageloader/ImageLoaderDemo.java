package com.imageloader;

import com.bumptech.glide.Glide;
import com.main.functionlistsdemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
 
/**
 * ImageLoader的使用
 * @author admin
 *
 */

public class ImageLoaderDemo extends Activity implements OnClickListener{
	
	//切记application的name="全路径名";  name配置为类的全路径名  否则会报非法状态异常
	
	private String url = "http://b39.photo.store.qq.com/psu?/1214a53e-812e-4b02-8e11-a1720240e313/C5fFh6sVN0PONY31g4qjO5hLmJjAnUDqZOHcNR6JtoQ!/b/YVJEURctWwAAYglTVBckQwAA&bo=ngL2AQAAAAABBEg!&rf=viewer_4";
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = SystemHelper.getDisplayImageOptions();
	private ImageLoadingListener listener = new AnimateFirstDisplayListener();
	
	private ImageView iv;
	private Button image_loader_button;
	
	private boolean isLoading = false;
	
       @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.activity_imageloaderdemo);
    	iv = (ImageView) findViewById(R.id.iv);     
    	image_loader_button = (Button) findViewById(R.id.image_loader_button);
    	image_loader_button.setOnClickListener(this);
       }

	@Override
	public void onClick(View v) {
		        imageLoader.displayImage(url, iv, options, listener);
//		        Glide.with(this).load(url).into(iv);
		        Toast.makeText(this, "已加载缓存网络图片", Toast.LENGTH_SHORT).show();
			}
}
