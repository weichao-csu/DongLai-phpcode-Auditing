package com.one.tools.tool;


import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 乐伟超
 * Belong to DongLai源代码审计系统
 * @date 2021-04-11-14:14
 * @description 每次利用规则数组匹配一个文件，输入的值是这个正则的数组和要进行匹配的文件
 * 构造的时候给出文件，调用函数时给出正则的数组
 */
public class once_match_oneFile {
    File file;
    BufferedReader bufferedReader;
    InputStreamReader isr;
    String patt;
    boolean flag = false;
    String codings;
    public once_match_oneFile(File file,String codings) throws FileNotFoundException, UnsupportedEncodingException {
        this.file = file;
        this.codings=codings;

        isr= new InputStreamReader(new FileInputStream(file),codings);
        bufferedReader = new BufferedReader(isr);
    }

    String[] temp_string = {"", ""};
    ArrayList<String[]> return_list = new ArrayList<>();

    int row=1;



    /**
     * 输入的值是这个正则的数组
     * 返回值是匹配成功的字符串数组集合，数组里面有返回的文本行，和漏洞描述
     */
    public ArrayList<String[]> match(ArrayList<String[]> strings,int count) throws IOException {
        String Line;
        Line = bufferedReader.readLine();
        while (Line != null) {
            for (int j = 0; j < strings.size(); j++) {
                {
                    String[] record = {"", "","","",""};
                    temp_string = strings.get(j);//得到正则规则数组
                    patt = temp_string[0];    //赋值给正则模式
                    Pattern re = Pattern.compile(patt, Pattern.CASE_INSENSITIVE);//对正则模式进行编译
                    Matcher m = re.matcher(Line);
                    while (m.find()) {
//                        这里目前出现的问题就是当对record这个数组进行更新的时候，集合也会重新进行更新
//                        好的解决方案就是不能让record是全局变量，每次用的时候都重新声明，这样就不会导致动态更新的问题
                        record[0]=String.valueOf(count);
                        record[1] = temp_string[1];
                        record[2]=file.getPath();
                        record[3] = Line.trim();
                        record[4]=String.valueOf(row);
                        flag = true;
                        break;
                    }
                    if (flag) {
                        return_list.add(record);
                        count++;
                        flag = false;
                        break;
                    }

                }
            }
            Line = bufferedReader.readLine();
            row++;
        }
        isr.close();
        bufferedReader.close();

        return return_list;

    }


}
