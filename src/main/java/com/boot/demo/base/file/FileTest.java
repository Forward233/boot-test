package com.boot.demo.base.file;

import java.io.File;

/**
 * @author: yhl
 * @DateTime: 2020/11/7 10:10
 * @Description:
 */
public class FileTest {


    public static void main(String[] args) {
        File file = new File("D:\\download-pan\\面试突击");
        getFile(file);
    }

    public static void getFile(File file) {
        File[] files = file.listFiles(pathname -> !pathname.getName().contains("zip"));
        String fileName;
        for (File f : files) {
            if (f.isDirectory()) {
                getFile(f);
            }
            if (f.getName().endsWith("docx")) {
                try {
                    File file1 = new File("D:\\haha1\\" + f.getParentFile().getName() + f.getName());
                    try {
                        PdfUtils.convertDocxToPDF(f.getPath(),file1.getPath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    Files.copy(f,file1);
//                    FileUtils.copyFile(f, file1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            System.out.println(f.getName());
        }
    }
}
