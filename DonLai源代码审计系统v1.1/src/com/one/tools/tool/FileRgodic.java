package com.one.tools.tool;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

/**
 * @author 乐伟超
 * Belong to DongLai源代码审计系统
 * @date 2021-04-11-8:47
 * @description 根据给出的文件对象，生成文件树，并返回JTree类型的容器
 */
public class FileRgodic {
    static JTree tree;                          //声明树结构
    static DefaultTreeModel newModel;           //树的model模型
    static DefaultMutableTreeNode Node;
    static DefaultMutableTreeNode temp;        //两个默认的树节点
    static String path = null;                   //需要遍历的目录，这里输入进来的要遍历的目录

    public FileRgodic(String path) {
        if (path != null) {
            this.path = path;
        }
    }

    public static JTree Path2Tree() {
        Node = traverseFolder(path);    //这个Node已经是挂满了子节点的树
        newModel = new DefaultTreeModel(Node);   //利用这个树声明一个新的树的模式
        tree = new JTree(newModel);
        return tree;
    }


    //  这个函数的返回值就是树节点
    public static DefaultMutableTreeNode traverseFolder(String path) {
//        声明节点对象，获取目录的名字，也就是得到了根节点所在的目录返回值是字符串类型
        DefaultMutableTreeNode fujiedian = new DefaultMutableTreeNode(new File(path).getName());
//        用这个传进来的路径创造一个新的文件对象
        File file = new File(path);

//      如果这个文件对象存在，也就是能创建成功
        if (file.exists()) {
//          得到这个文件夹下面的文件，并且要存储为数组形式
                File[] files = file.listFiles();
                if(files!=null){if (files.length == 0) {//也就是说这个路径是个空文件夹
                    if (file.isDirectory()) {//如果是空文件夹，还得判断他是个文件夹
//                    获取到这个文件夹的名字，作为一个新的树的节点，并且还不能有子节点，将这个新声明好的节点返回
                        DefaultMutableTreeNode dn = new DefaultMutableTreeNode(file.getName(), false);
                        return dn;
                    }
                } else //也就是说这个目录下面有文件
                {
//                    对这个文件数组进行遍历
                    for (File file2 : files) {
                        if (file2.isDirectory()) {
//                        如果是目录的话，递归调用
                            //是目录的话，生成节点，并添加里面的节点，把所有文件都加载在他的父节点上面
                            fujiedian.add(traverseFolder(file2.getAbsolutePath()));
                        } else {

                            //是文件的话直接生成节点，并把该节点加到对应父节点上
//                            这里就只有名字被存储了进来，并没有将节点的绝对路径存进来
                            temp = new DefaultMutableTreeNode(file2.getName());
                            fujiedian.add(temp);
                        }
                    }
                }}




        } else {//文件不存在
            return null;
        }
        return fujiedian;
    }


}
