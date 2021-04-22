package com.one.tools.tool;

import java.io.*;
import java.util.ArrayList;

/**
 * @author ��ΰ��
 * Belong to DongLaiԴ�������ϵͳ
 * @date 2021-04-10-20:44
 * @description ���ݴ��������ļ����֣������ַ������ϣ���������þ��������������ļ�
 */
public class ReadFileBySplit {
    /**
     * ����Ϊ�ļ�����ͷָ��ַ���
     * ����ֵ�ǰ�������������������ݵļ���
     */
    FileReader config ;

    {
        try {
            config = new FileReader("config\\rule.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    BufferedReader bufferedReader = new BufferedReader(config);
    String line;
    String[] strings;
    ArrayList<String[]> arrayList=new ArrayList<>();

    public ReadFileBySplit() throws FileNotFoundException {
    }

    public ArrayList<String[]> SplitFileToList(File aimfile) throws IOException {


        line = bufferedReader.readLine();
        while (line != null) {
            strings = line.split("\\:");
            if (strings.length == 2) {
                arrayList.add(strings);
            }
            line = bufferedReader.readLine();
        }

        return arrayList;
    }
}