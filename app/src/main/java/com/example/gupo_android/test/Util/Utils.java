package com.example.gupo_android.test.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : whx
 * @date : 2018/12/13 14:38
 */
public class Utils {

    static class ImageMessage {
        Uri imageFileThumb;
        Uri imageFileSource;

        ImageMessage(Uri imageFileThumb, Uri imageFileSource) {
            this.imageFileThumb = imageFileThumb;
            this.imageFileSource = imageFileSource;
        }

        public static ImageMessage obtain(Uri imageFileThumb, Uri imageFileSource) {
            return new ImageMessage(imageFileThumb, imageFileSource);
        }
    }

    /**
     * 获取图片信息
     */
    private ImageMessage acquireImage(Context context) {
        File imageFileSource = new File(context.getCacheDir(), "source.jpg");
        File imageFileThumb = new File(context.getCacheDir(), "thumb.jpg");

        InputStream inputStream = null;
        FileOutputStream fosSource = null;
        FileOutputStream fosThumb = null;
        BufferedOutputStream bufSource = null;
        BufferedOutputStream bufThumb = null;

        try {
            // 读取图片
            inputStream = context.getAssets().open("opera.jpg");
//            getAssets().close();

            Bitmap bmpSource = BitmapFactory.decodeStream(inputStream);
            imageFileSource.createNewFile();
            fosSource = new FileOutputStream(imageFileSource);
            bufSource = new BufferedOutputStream(fosSource);
            // 保存原始图片
            bmpSource.compress(Bitmap.CompressFormat.JPEG, 100, bufSource);

            // 生成缩略图
            Matrix matrix = new Matrix();
            matrix.setRectToRect(new RectF(0, 0, bmpSource.getWidth(), bmpSource.getHeight()),
                    new RectF(0, 0, 240, 240), Matrix.ScaleToFit.CENTER);
            Bitmap bmpThumb = Bitmap.createBitmap(bmpSource, 0, 0, bmpSource.getWidth(), bmpSource.getHeight(), matrix, true);
            imageFileThumb.createNewFile();
            fosThumb = new FileOutputStream(imageFileThumb);
            bufThumb = new BufferedOutputStream(fosThumb);
            // 保存缩略图
            bmpThumb.compress(Bitmap.CompressFormat.JPEG, 60, bufThumb);

            ImageMessage imageMessage = ImageMessage.obtain(Uri.fromFile(imageFileThumb), Uri.fromFile(imageFileSource));
            return imageMessage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (fosSource != null)
                try {
                    fosSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bufSource != null)
                try {
                    bufSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (fosThumb != null)
                try {
                    fosThumb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bufThumb != null)
                try {
                    bufThumb.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
