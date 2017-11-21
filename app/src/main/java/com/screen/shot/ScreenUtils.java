package com.screen.shot;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ListView;
import android.widget.ScrollView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenUtils {
 
/**
 * 截取scrollview的屏幕
 * @param scrollView
 * @return
 */
public static Bitmap getBitmapByView(ScrollView scrollView) {
  int h = 0;
  Bitmap bitmap = null;
  // 获取scrollview实际高度
  for (int i = 0; i < scrollView.getChildCount(); i++) {
    h += scrollView.getChildAt(i).getHeight();
    scrollView.getChildAt(i).setBackgroundColor(
        Color.parseColor("#ffffff"));
  }
  // 创建对应大小的bitmap
  bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
      Bitmap.Config.RGB_565);
  final Canvas canvas = new Canvas(bitmap);
  scrollView.draw(canvas);
  return bitmap;
}
 
/**
 * 截图listview
 * **/
public static Bitmap getListViewBitmap(ListView listView, String picpath) {
  int h = 0;
  Bitmap bitmap;
  // 获取listView实际高度
  for (int i = 0; i < listView.getChildCount(); i++) {
    h += listView.getChildAt(i).getHeight();
  }
  // 创建对应大小的bitmap
  bitmap = Bitmap.createBitmap(listView.getWidth(), h,
      Bitmap.Config.ARGB_8888);
  final Canvas canvas = new Canvas(bitmap);
  listView.draw(canvas);
  return bitmap;
}
 
 
/**
 * 压缩图片
 * @param image
 * @return
 */
public static Bitmap compressImage(Bitmap image) {
  ByteArrayOutputStream baos = new ByteArrayOutputStream();
  // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
  image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
  int options = 100;
  // 循环判断如果压缩后图片是否大于250K,大于继续压缩
  while (baos.toByteArray().length / 1024 > 1024 && options >10) {
    // 重置baos
    baos.reset();
    // 这里压缩options%，把压缩后的数据存放到baos中
    image.compress(Bitmap.CompressFormat.JPEG, options, baos);
    // 每次都减少10
    options -= 10;
  }
  // 把压缩后的数据baos存放到ByteArrayInputStream中
  ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
  // 把ByteArrayInputStream数据生成图片
  Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
  return bitmap;
}
 
/**
 * 保存到sdcard
 * @param b
 * @return
 */
public static String savePic(Context context, Bitmap b) {
 
  File outfile = new File("/sdcard/image");
  // 如果文件不存在，则创建一个新文件
  if (!outfile.isDirectory()) {
    try {
      outfile.mkdir();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  String fname = outfile + "/" + System.currentTimeMillis() + ".jpg";
  FileOutputStream fos = null;
  try {
    fos = new FileOutputStream(fname);
    if (null != fos) {
      b.compress(Bitmap.CompressFormat.JPEG, 90, fos);
      fos.flush();
      fos.close();
    }
  } catch (FileNotFoundException e) {
    e.printStackTrace();
  } catch (IOException e) {
    e.printStackTrace();
  }
 
  // 其次把文件插入到系统图库
  try {
    MediaStore.Images.Media.insertImage(context.getContentResolver(),
        outfile.getAbsolutePath(), fname, null);
  } catch (FileNotFoundException e) {
    e.printStackTrace();
  }
  // 最后通知图库更新
  context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fname)));
 
 
  return fname;
   }
}