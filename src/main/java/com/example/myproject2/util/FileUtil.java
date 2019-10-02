package com.example.myproject2.util;

import java.io.File;

public class FileUtil {
    public static void delete(File file) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                delete(file1);
            }
        }
        file.delete();
    }
}
