package com.one.tools.component;

import com.one.tools.tool.FileRgodic;

import javax.swing.*;
import java.io.File;

/**
 * @author 乐伟超
 * Belong to DongLai源代码审计系统
 * @date 2021-04-17-12:18
 * @description 打开对话框, 根据所选文件生成文件树
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
//                每次获得新打开的目录的路径，就立即传递给AutoAuditDemo这个类，所以后面start这个按钮每次开始审计的时候都能确保是最新打开的目录
            FileRgodic fileRgodic = new FileRgodic(stringPath);
            file_dir_tree = fileRgodic.Path2Tree();
//            这里的树节点应该是有路径的
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
