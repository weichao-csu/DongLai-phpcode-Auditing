package com.one.tools.tool;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

/**
 * @author ��ΰ��
 * Belong to DongLaiԴ�������ϵͳ
 * @date 2021-04-11-8:47
 * @description ���ݸ������ļ����������ļ�����������JTree���͵�����
 */
public class FileRgodic {
    static JTree tree;                          //�������ṹ
    static DefaultTreeModel newModel;           //����modelģ��
    static DefaultMutableTreeNode Node;
    static DefaultMutableTreeNode temp;        //����Ĭ�ϵ����ڵ�
    static String path = null;                   //��Ҫ������Ŀ¼���������������Ҫ������Ŀ¼

    public FileRgodic(String path) {
        if (path != null) {
            this.path = path;
        }
    }

    public static JTree Path2Tree() {
        Node = traverseFolder(path);    //���Node�Ѿ��ǹ������ӽڵ����
        newModel = new DefaultTreeModel(Node);   //�������������һ���µ�����ģʽ
        tree = new JTree(newModel);
        return tree;
    }


    //  ��������ķ���ֵ�������ڵ�
    public static DefaultMutableTreeNode traverseFolder(String path) {
//        �����ڵ���󣬻�ȡĿ¼�����֣�Ҳ���ǵõ��˸��ڵ����ڵ�Ŀ¼����ֵ���ַ�������
        DefaultMutableTreeNode fujiedian = new DefaultMutableTreeNode(new File(path).getName());
//        �������������·������һ���µ��ļ�����
        File file = new File(path);

//      �������ļ�������ڣ�Ҳ�����ܴ����ɹ�
        if (file.exists()) {
//          �õ�����ļ���������ļ�������Ҫ�洢Ϊ������ʽ
                File[] files = file.listFiles();
                if(files!=null){if (files.length == 0) {//Ҳ����˵���·���Ǹ����ļ���
                    if (file.isDirectory()) {//����ǿ��ļ��У������ж����Ǹ��ļ���
//                    ��ȡ������ļ��е����֣���Ϊһ���µ����Ľڵ㣬���һ��������ӽڵ㣬������������õĽڵ㷵��
                        DefaultMutableTreeNode dn = new DefaultMutableTreeNode(file.getName(), false);
                        return dn;
                    }
                } else //Ҳ����˵���Ŀ¼�������ļ�
                {
//                    ������ļ�������б���
                    for (File file2 : files) {
                        if (file2.isDirectory()) {
//                        �����Ŀ¼�Ļ����ݹ����
                            //��Ŀ¼�Ļ������ɽڵ㣬���������Ľڵ㣬�������ļ������������ĸ��ڵ�����
                            fujiedian.add(traverseFolder(file2.getAbsolutePath()));
                        } else {

                            //���ļ��Ļ�ֱ�����ɽڵ㣬���Ѹýڵ�ӵ���Ӧ���ڵ���
//                            �����ֻ�����ֱ��洢�˽�������û�н��ڵ�ľ���·�������
                            temp = new DefaultMutableTreeNode(file2.getName());
                            fujiedian.add(temp);
                        }
                    }
                }}




        } else {//�ļ�������
            return null;
        }
        return fujiedian;
    }


}
