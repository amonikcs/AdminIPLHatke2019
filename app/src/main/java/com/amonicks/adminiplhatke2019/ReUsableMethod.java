package com.amonicks.adminiplhatke2019;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;

public class ReUsableMethod {
    private Context context;
    private static int PICK_IMAAGE_REQUEST = 1;
    public static String getFileExtension(Uri uri, Activity activity){
        ContentResolver contentResolver = activity.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public static void openFileChooser(Activity activity){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent,PICK_IMAAGE_REQUEST);
    }

    public static int openFileChooserTeam1(Activity activity){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent,PICK_IMAAGE_REQUEST);
        return 1;
    }

    public static int openFileChooserTeam2(Activity activity){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent,PICK_IMAAGE_REQUEST);
        return 2;
    }
}
