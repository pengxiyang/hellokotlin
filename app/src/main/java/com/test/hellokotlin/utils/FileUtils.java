package com.test.hellokotlin.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {


    /**
     * 保存图片
     *
     * @param context
     * @param imagePath
     * @param title
     * @param desc
     */
    private void saveImage(Context context, String imagePath, String title, String desc) {
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), imagePath, title, desc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveImage(Context context, Bitmap bitmap, String title, String desc) {
        MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, title, desc);
    }
    private void  test(Context context){
        //保存图片
        saveFile(context,MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/png","myImage","","http://xxx.png");
        //保存视频
        saveFile(context,MediaStore.Video.Media.EXTERNAL_CONTENT_URI,"video/mp4","myVideo","","http://xxx.mp4");
        //保存音频
        saveFile(context,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,"audio/mpeg","nyAudio","","http://xxx.mp3");
        //Android Q新增的下载目录
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.Q){
            saveFile(context,MediaStore.Downloads.EXTERNAL_CONTENT_URI,"text/plain","myText","","http://www.xxx.txt");
        }
    }


    /**
     * 其他文件的保存
     * @param context
     * @param extUri
     * @param mimeType
     * @param saveName
     * @param desc
     * @param netUrl
     */
    public void saveFile(Context context, final Uri extUri, final String mimeType, final String saveName, final String desc, final String netUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.DISPLAY_NAME, saveName);
                    values.put(MediaStore.Images.Media.TITLE, saveName);
                    values.put(MediaStore.Images.Media.DESCRIPTION, desc);
                    values.put(MediaStore.Images.Media.MIME_TYPE, mimeType);
                    ContentResolver contentResolver = context.getContentResolver();
                    Uri uri = contentResolver.insert(extUri, values);
                    byte[] buffer = new byte[1024];
                    ParcelFileDescriptor parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "w");
                    FileOutputStream fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
                    URL url = new URL(netUrl);
                    InputStream inputStream = url.openStream();
                    while (true) {
                        int numRead = inputStream.read(buffer);
                        if (numRead == -1) {
                            break;
                        }
                        fileOutputStream.write(buffer, 0, numRead);
                    }
                    fileOutputStream.close();
                    parcelFileDescriptor.close();
                    inputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        }).start();
    }

    /**
     * 获取全部图片
     * @param context
     * @return
     */
    public static List<Uri> loadPhotoFiles(Context context){
        List<Uri> photoUris =new ArrayList<>();
        Cursor cursor =context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,new String[]{MediaStore.Images.Media._ID},null,null,null);
        while (cursor.moveToNext()){
            int id =cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));
            Uri photoUri =Uri.parse(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString()+ File.separator+id);
            photoUris.add(photoUri);
        }
        return  photoUris;

    }

    /**
     * uri 转bitmap
     * @param context
     * @param uri
     * @return
     * @throws IOException
     */
    public  static Bitmap getBitmapFromUri(Context context,Uri uri) throws IOException{
        ParcelFileDescriptor parcelFileDescriptor =context.getContentResolver().openFileDescriptor(uri,"r");
        FileDescriptor fileDescriptor =parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        return  image;
    }

    /**
     * 根据title获取图片
     * MediaStore的DATA在androidQ之前表示文件的真实路径，在android A 被废弃，通过——ID获取Uri，
     * 通过 ContentUris.withAppendedId(external, cursor.getLong(0)); 获取。
     * @param  context
     *
     * @param title
     * @return
     */
    private Bitmap getImage(Context context,String title){
        Uri external = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver resolver =context.getContentResolver();
        String selection =MediaStore.Images.Media.TITLE+"=?";//查询条件
        String[] args =new String[]{title};//上面？的值
        String[] projection =new String[]{MediaStore.Images.Media._ID};//查询的内容
        Cursor cursor =resolver.query(external,projection,selection,args,null);
        Uri imageUri =null;
        if(cursor!=null&&cursor.moveToFirst()){
            imageUri = ContentUris.withAppendedId(external,cursor.getLong(0));
            cursor.close();
        }
        if(imageUri ==null){
            return null;
        }
        ParcelFileDescriptor pfd =null;
        try {
            pfd =context.getContentResolver().openFileDescriptor(imageUri,"r");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(pfd!=null){
            Bitmap bitmap =BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor());
            try {
                pfd.close();
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void  deleteFile(Context context,Uri uri){
        //删除文件，需要先查询出uri
        context.getContentResolver().delete(uri,null,null);

        //修改文件，修改的内容以键值对放到ContentValues中
        ContentValues values =new ContentValues();
        values.put("title","new title");
        context.getContentResolver().update(uri,values,null,null);
    }
    /**-----------------------文件上传下载---------------------------------------------**/

    public static InputStream getInputStream(Context context,Uri uri){
        InputStream inputStream =null;

        try {
            inputStream =context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    /**
     * 拷贝文件，将uri拷贝到App专属目录下
     * @param uri 要拷贝的文件Uri
     * @param saveName 保存到专属目录下的文件名
     * @return  拷贝后新的文件
     */
    public static File getFile(Context context,Uri uri,String saveName){
        File rootFile =context.getExternalFilesDir(null);
        File file =new File(rootFile,saveName);

        byte[] buffer =new byte[1024];
        try {
            FileOutputStream fileOutputStream =new FileOutputStream(file);
            InputStream inputStream =context.getContentResolver().openInputStream(uri);
            while (true){
                int numRead =inputStream.read(buffer);
                if(numRead==-1){
                    break;
                }
                fileOutputStream.write(buffer,0,numRead);

            }
            fileOutputStream.close();
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            file =null;
            e.printStackTrace();

        }
        return file;
    }
}
