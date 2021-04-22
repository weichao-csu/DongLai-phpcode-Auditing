package com.one.tools.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ��ΰ��
 * Belong to DongLaiԴ�������ϵͳ
 * @date 2021-04-10-15:34
 * @description �Ը�����Ŀ¼�ļ�������б�����
 */
public class FileTraverse {
    /**
     * ����Ҫ���й��죬ֱ�ӵ������������������������
     * ����ֵ��File�ļ�����
     */
    ArrayList<File> fileArrayList = new ArrayList<>();

    public ArrayList<File> listDirectoryFile(File dir) throws IOException {

        if (!dir.exists()) {//dir�����ڣ��׳��쳣
            throw new IllegalArgumentException("Ŀ¼" + dir + "������");
        }
        if (!dir.isDirectory()) {//dir����Ŀ¼�׳��쳣
            throw new IllegalArgumentException(dir + "����Ŀ¼");
        }
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {//������¼�Ŀ¼
            for (File file : files) {
                if (!file.isDirectory()) {//�������Ŀ¼��ֱ�Ӵ�ӡ����
                    fileArrayList.add(file);
                } else {//�����Ŀ¼���ݹ���ñ�����
                    listDirectoryFile(file);
                }
            }
        }
        return fileArrayList;
    }


}
