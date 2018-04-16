package com.ayhalo.ecampus.utils;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class FileUtils {

    public static boolean isExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取指定文件大小
     *
     * @throws Exception
     */
    private static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
            fis.close();
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return size;
    }


    /**
     * 获取指定文件夹
     *
     * @throws Exception
     */
    private static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }


    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     *
     * @param filePath 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public static String getAutoFileOrFilesSize(Context context, String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Formatter.formatFileSize(context, blockSize);
    }

    public static String getAutoFileOrFilesSize(Context context, String... filePaths) {
        long totalSize = 0;
        for (String filePath : filePaths) {
            File file = new File(filePath);
            long blockSize = 0;
            try {
                if (file.isDirectory()) {
                    blockSize = getFileSizes(file);
                } else {
                    blockSize = getFileSize(file);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            totalSize = totalSize + blockSize;
        }
        return Formatter.formatFileSize(context, totalSize);
    }

    /**
     * 如果存在外部SD卡则返回外部缓存路径
     *
     * @param context
     * @return
     */
    public static String getAppCacheDir(Context context) {
        if (context.getExternalCacheDir() != null && isExistSDCard()) {
            return context.getExternalCacheDir().toString();
        } else {
            return context.getCacheDir().toString();
        }
    }

    public static String getInternalCacheDir(Context context) {
        return context.getCacheDir().toString();
    }

    public static String getExternalCacheDir(Context context) {
        if (context.getExternalCacheDir() != null && isExistSDCard()) {
            return context.getExternalCacheDir().toString();
        } else
            return null;
    }

    public static boolean delete(String path) {
        return delete(new File(path));
    }

    public static boolean delete(File file) {
        if (file.isFile()) {
            return file.delete();
        }
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                return file.delete();
            }
            for (File childFile : childFiles) {
                delete(childFile);
            }
            return file.delete();
        }
        return false;
    }

    public static String getFolderName(String filePath) {

        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (TextUtils.isEmpty(folderName)) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

    public static boolean writeFile(String filePath, String content, boolean append) {
        if (TextUtils.isEmpty(content)) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.write("\r\n");
            fileWriter.write("\r\n");
            fileWriter.close();
            return true;
        } catch (IOException e) {
            Log.e("IOException occurred. ", e.getMessage(), e);
            return false;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    Log.e("IOException occurred. ", e.getMessage(), e);
                }
            }
        }
    }

    public static String readFile(String filePath) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (!file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file));
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
            return fileContent.toString();
        } catch (IOException e) {
            Log.e("IOException occurred. ", e.getMessage(), e);
            return "";
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("IOException occurred. ", e.getMessage(), e);
                }
            }
        }
    }


}
