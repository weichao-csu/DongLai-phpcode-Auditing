package com.one.tools.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 乐伟超
 * Belong to DongLai源代码审计系统
 * @date 2021-04-10-15:34
 * @description 对给出的目录文件对象进行遍历，
 */
public class FileTraverse {
    /**
     * 不需要进行构造，直接调用这个类里面的这个方法即可
     * 返回值是File文件集合
     */
    ArrayList<File> fileArrayList = new ArrayList<>();

    public ArrayList<File> listDirectoryFile(File dir) throws IOException {

        if (!dir.exists()) {//dir不存在，抛出异常
            throw new IllegalArgumentException("目录" + dir + "不存在");
        }
        if (!dir.isDirectory()) {//dir不是目录抛出异常
            throw new IllegalArgumentException(dir + "不是目录");
        }
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {//如果有下级目录
            for (File file : files) {
                if (!file.isDirectory()) {//如果不是目录则直接打印出来
                    fileArrayList.add(file);
                } else {//如果是目录，递归调用本方法
                    listDirectoryFile(file);
                }
            }
        }
        return fileArrayList;
    }


}
