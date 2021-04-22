package com.one.tools.component;

import com.one.tools.tool.FileRgodic;

import javax.swing.*;
import java.io.File;

/**
 * @author ��ΰ��
 * Belong to DongLaiԴ�������ϵͳ
 * @date 2021-04-17-12:18
 * @description �򿪶Ի���, ������ѡ�ļ������ļ���
 */
public class SelectNewFlie {
    String stringPath;

    public SelectNewFlie() {
    }

    public JTree generateTree() {

        JTree file_dir_tree = new JTree();
        JFileChooser fc = new JFileChooser("D:\\");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showOpenDialog(null);
        File Path = fc.getSelectedFile();
        if(Path!=null){
            stringPath = Path.getAbsolutePath();
//                ÿ�λ���´򿪵�Ŀ¼��·�������������ݸ�AutoAuditDemo����࣬���Ժ���start�����ťÿ�ο�ʼ��Ƶ�ʱ����ȷ�������´򿪵�Ŀ¼
            FileRgodic fileRgodic = new FileRgodic(stringPath);
            file_dir_tree = fileRgodic.Path2Tree();
//            ��������ڵ�Ӧ������·����
            file_dir_tree.setOpaque(false);
            return file_dir_tree;
        }
        else {

            return null;
        }



    }

    public String getStringPath() {
        return stringPath;
    }
}
