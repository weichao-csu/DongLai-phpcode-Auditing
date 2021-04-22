package com.one.tools.tool;


import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ��ΰ��
 * Belong to DongLaiԴ�������ϵͳ
 * @date 2021-04-11-14:14
 * @description ÿ�����ù�������ƥ��һ���ļ��������ֵ���������������Ҫ����ƥ����ļ�
 * �����ʱ������ļ������ú���ʱ�������������
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
     * �����ֵ��������������
     * ����ֵ��ƥ��ɹ����ַ������鼯�ϣ����������з��ص��ı��У���©������
     */
    public ArrayList<String[]> match(ArrayList<String[]> strings,int count) throws IOException {
        String Line;
        Line = bufferedReader.readLine();
        while (Line != null) {
            for (int j = 0; j < strings.size(); j++) {
                {
                    String[] record = {"", "","","",""};
                    temp_string = strings.get(j);//�õ������������
                    patt = temp_string[0];    //��ֵ������ģʽ
                    Pattern re = Pattern.compile(patt, Pattern.CASE_INSENSITIVE);//������ģʽ���б���
                    Matcher m = re.matcher(Line);
                    while (m.find()) {
//                        ����Ŀǰ���ֵ�������ǵ���record���������и��µ�ʱ�򣬼���Ҳ�����½��и���
//                        �õĽ���������ǲ�����record��ȫ�ֱ�����ÿ���õ�ʱ�����������������Ͳ��ᵼ�¶�̬���µ�����
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
