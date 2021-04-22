package com.one.tools.tool;

import java.io.*;
import java.util.ArrayList;

/**
 * @author 乐伟超
 * Belong to DongLai源代码审计系统
 * @date 2021-04-10-20:44
 * @description 根据传进来的文件名字，返回字符串集合，这个的作用就是来加载配置文件
 */
public class ReadFileBySplit {
    /**
     * 参数为文件对象和分割字符串
     * 返回值是包含了所有正则规则数据的集合
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