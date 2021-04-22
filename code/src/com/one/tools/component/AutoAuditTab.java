package com.one.tools.component;


import com.one.tools.listener.ActionDoneListener;
import com.one.tools.tool.FileTraverse;
import com.one.tools.tool.once_match_oneFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author 乐伟超
 * Belong to DongLai源代码审计系统
 * @date 2021-04-17-14:19
 * @description 新建JAVA类来实现组装自动审计页面，后面添加到页面中
 */
public class AutoAuditTab {
    //    整体页面
    JPanel TotalPanel = new JPanel();
    //    开始按钮
    JButton start = new JButton("开始");
    //    生成报告按钮
    JButton OutPaper = new JButton("生成报告");
    DefaultTableModel model = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    JTable detailTable = new JTable(model);
    ArrayList<File> fileArrayList = new ArrayList<>();
    int count = 1;
    ArrayList<String[]> result = new ArrayList<>();
    JLabel ScanState_waiting = new JLabel("状态：等待扫描");


//    返回给主类的变量
    Box returnBox;


    //    主类里面获得的依赖变量
    String stringPath;
    ArrayList<String[]> arrayList;
    ActionDoneListener Listener;
    String codings;

    public AutoAuditTab(String stringPath, ArrayList<String[]> arrayList, ActionDoneListener Listener,String codings) {
        this.stringPath = stringPath;
        this.arrayList = arrayList;
        this.Listener=Listener;
        this.codings=codings;


    }


    public void addActionListenerForButton() {

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long starttime = new Date().getTime();

                        if (detailTable.getRowCount() != 0) {
                            model.setRowCount(0);
                        }
                        ScanState_waiting.setText("状态：正在扫描中");
                        File file = new File(stringPath);
                        try {
                            fileArrayList = new FileTraverse().listDirectoryFile(file);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
//              调用函数，输入进来正则数组还有文件对象
                        int totalNumberBug=0;

                        for (int m = 0; m < fileArrayList.size(); m++) {
//                            在这里之前先判断文件的后缀，后缀是php的才进去审计，
                            String[] str=fileArrayList.get(m).getPath().split("\\\\");
                            String  Filename=str[str.length-1];
                            if(Filename.contains(".")){
                                String suffix = Filename.substring(Filename.lastIndexOf("."));
                                if(suffix.equals(".php")){
                                    try {
                                        result = new once_match_oneFile(fileArrayList.get(m),codings).match(arrayList, count);

                                        if (result.size() > 0) {
                                            int num = result.size() - 1;
                                            String[] strings = result.get(num);
                                            count = Integer.parseInt(strings[0]) + 1;
                                        }

                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }
                                    if (result != null) {
                                        totalNumberBug=totalNumberBug+result.size();
                                        for (int s = 0; s < result.size(); s++) {
                                            String[] strings = result.get(s);

                                            model.addRow(strings);
                                            try {
                                                Thread.sleep(50);
                                            } catch (InterruptedException interruptedException) {
                                                interruptedException.printStackTrace();
                                            }

                                        }
                                    } else {
                                        return;
                                    }
                                }
                            }



                        }
                        long finishTime=new Date().getTime();
                        ScanState_waiting.setText("状态：扫描结束，"+"共扫描到"+totalNumberBug+"个可疑漏洞，"+"花费时间"+(finishTime-starttime)/1000.0+"s");
                        System.out.println(Thread.currentThread().getName());
                    }
                }).start();

            }
        });

        OutPaper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser selectSaveDir = new JFileChooser("D:\\");
                selectSaveDir.setFileFilter(new FileNameExtensionFilter("html(*.html)", ".html"));
                selectSaveDir.setFileSelectionMode(JFileChooser.FILES_ONLY);
                selectSaveDir.showSaveDialog(TotalPanel);
                File saveHtmlFile = selectSaveDir.getSelectedFile();
                try {
                    int totalCount = model.getRowCount();
                    BufferedWriter out = new BufferedWriter(new FileWriter(saveHtmlFile));
//                    对表格进行遍历,将每一行的内容输出到文件中
                    out.write("<html>\n" +
                            "<head>\n" +
                            "<title>DongLai代码审计系统漏洞报告 --</title>\n" +
                            "<style>body,td,th {font-family: Arial, Helvetica, sans-serif, 宋体;font-size: 12px;color:#50696d;line-height: 150%;}</style>\n" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\n" +
                            "</head>\n" +
                            "<body><br /><center><b>\n" +
                            "<font size=4>DongLai代码审计系统漏洞报告</font>\n" +
                            "</b></center><br>\n" +
                            "审计结果：发现可疑漏洞总数:" + totalCount +
                            "<font color=\"#FF0000\">17</font>个<br/>\n" +
                            "<table width=\"100%\" border=\"1\" bordercolor=\"#b1bebf\" style=\"padding:5px;line-height:170%;clear:both;font-size:12px;word-break:break-all;border-collapse:collapse\">\n" +
                            "<tr><td width=\"5%\">ID</td><td width=\"20%\">漏洞描述</td><td width=\"30%\">文件路径</td><td width=\"45%\">漏洞详细</td></tr>");
//                    上面先写入题头
                    for (int row = 0; row < totalCount; row++) {
//                            下面开始逐行读取,逐行写入

                        Object bug = detailTable.getValueAt(row, 1);
                        Object path = detailTable.getValueAt(row, 2);
                        Object bugDetail = detailTable.getValueAt(row, 3);

                        String string_row = String.valueOf(row);
                        String string_bug = String.valueOf(bug);
                        String string_path = String.valueOf(path);
                        String string_bugDetail = String.valueOf(bugDetail);
                        out.write("<tr><td width=\"5%\">");
                        out.write(string_row);
                        out.write("</td><td width=\"20%\">");
                        out.write(string_path);
                        out.write("</td><td width=\"30%\">");
                        out.write(string_bug);
                        out.write("</td><td width=\"45%\">");
                        out.write(string_bugDetail);
                        out.write("</td></tr>");


                    }

                    out.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        detailTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    //                这里获取文件名字，
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); //获得行位置
                    //              获取文件路径
                    String cellVal;
                    cellVal = (String) (model.getValueAt(row, 2));
//                得到这个漏洞所在的行号
                    int intLine = Integer.parseInt((String) (model.getValueAt(row, 4)));

                    NewPhpFilePage newPhpFilePage = new NewPhpFilePage(cellVal,stringPath,codings);
//                自动审计将监听发送给新的那个页面
                    newPhpFilePage.setListener(Listener);
                    newPhpFilePage.setIntLine(intLine);
                    returnBox = newPhpFilePage.initPanel();
                    Listener.addPage(cellVal,returnBox);



                }

            }
        });
    }


    public JPanel init() {
        //      这个用来放置下面的标签
        Box southJprogressBox = Box.createHorizontalBox();
        int[] width = {10, 200, 170, 500, 0};
        //    水平的盒子，放置两个按钮
        Box ButtonPanel = Box.createHorizontalBox();

        Object[] title = {"ID", "漏洞概述", "文件路径", "漏洞详情", "所在行号"};
//        运行添加监听器的函数

        //        组装视图上面的按钮
        ButtonPanel.add(start);
        ButtonPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        ButtonPanel.add(OutPaper);
        ButtonPanel.add(Box.createHorizontalGlue());
//          设置下面的进度显示
        ScanState_waiting.setFont(new Font("宋体", Font.BOLD, 20));
        southJprogressBox.add(ScanState_waiting);
        southJprogressBox.add(Box.createHorizontalGlue());
//          设置表格属性
//        detailTable.setEnabled(false);
        detailTable.setRowHeight(25);
        detailTable.setShowHorizontalLines(true);
        detailTable.setShowVerticalLines(true);

        model.setColumnCount(5);
        //        表格外观设置
        for (int i = 0; i < 5; i++) {
            TableColumn tableColumn = detailTable.getColumnModel().getColumn(i);
            tableColumn.setHeaderValue(title[i]);
            tableColumn.setPreferredWidth(width[i]);
        }
//        组装总视图
        TotalPanel.setLayout(new BoxLayout(TotalPanel, BoxLayout.Y_AXIS));
        TotalPanel.add(ButtonPanel);
        TotalPanel.add(new JScrollPane(detailTable));
        TotalPanel.add(southJprogressBox);

        return TotalPanel;
    }


}
