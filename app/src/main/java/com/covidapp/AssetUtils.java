package com.covidapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class AssetUtils {

    private static final String TAG = "AssetHelper";

    public void copyAsset(Context context, File path, String filename) throws IOException {

        AssetManager assetManager = context.getAssets();
        InputStream in = null;
        OutputStream out = null;

        // Copy files from asset folder to application folder
        try {
            in = assetManager.open(filename);
            out = new FileOutputStream(path.toString() + "/" + filename);
            copyFile(in, out);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            throw e;
        } finally {
            // Reclaim resources
            if (in != null) {
                in.close();
                in = null;
            }
            if (out != null) {
                out.flush();
                out.close();
                out = null;
            }
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {

        byte[] buffer = new byte[1024];
        int read;

        // Copy from input stream to output stream
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
