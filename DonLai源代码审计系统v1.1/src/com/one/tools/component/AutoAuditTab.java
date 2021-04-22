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
 * @author ��ΰ��
 * Belong to DongLaiԴ�������ϵͳ
 * @date 2021-04-17-14:19
 * @description �½�JAVA����ʵ����װ�Զ����ҳ�棬������ӵ�ҳ����
 */
public class AutoAuditTab {
    //    ����ҳ��
    JPanel TotalPanel = new JPanel();
    //    ��ʼ��ť
    JButton start = new JButton("��ʼ");
    //    ���ɱ��水ť
    JButton OutPaper = new JButton("���ɱ���");
    DefaultTableModel model = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    JTable detailTable = new JTable(model);
    ArrayList<File> fileArrayList = new ArrayList<>();
    int count = 1;
    ArrayList<String[]> result = new ArrayList<>();
    JLabel ScanState_waiting = new JLabel("״̬���ȴ�ɨ��");


//    ���ظ�����ı���
    Box returnBox;


    //    ���������õ���������
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
                        ScanState_waiting.setText("״̬������ɨ����");
                        File file = new File(stringPath);
                        try {
                            fileArrayList = new FileTraverse().listDirectoryFile(file);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
//              ���ú�������������������黹���ļ�����
                        int totalNumberBug=0;

                        for (int m = 0; m < fileArrayList.size(); m++) {
//                            ������֮ǰ���ж��ļ��ĺ�׺����׺��php�ĲŽ�ȥ��ƣ�
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
                        ScanState_waiting.setText("״̬��ɨ�������"+"��ɨ�赽"+totalNumberBug+"������©����"+"����ʱ��"+(finishTime-starttime)/1000.0+"s");
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
//                    �Ա����б���,��ÿһ�е�����������ļ���
                    out.write("<html>\n" +
                            "<head>\n" +
                            "<title>DongLai�������ϵͳ©������ --</title>\n" +
                            "<style>body,td,th {font-family: Arial, Helvetica, sans-serif, ����;font-size: 12px;color:#50696d;line-height: 150%;}</style>\n" +
                            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gbk\" />\n" +
                            "</head>\n" +
                            "<body><br /><center><b>\n" +
                            "<font size=4>DongLai�������ϵͳ©������</font>\n" +
                            "</b></center><br>\n" +
                            "��ƽ�������ֿ���©������:" + totalCount +
                            "<font color=\"#FF0000\">17</font>��<br/>\n" +
                            "<table width=\"100%\" border=\"1\" bordercolor=\"#b1bebf\" style=\"padding:5px;line-height:170%;clear:both;font-size:12px;word-break:break-all;border-collapse:collapse\">\n" +
                            "<tr><td width=\"5%\">ID</td><td width=\"20%\">©������</td><td width=\"30%\">�ļ�·��</td><td width=\"45%\">©����ϸ</td></tr>");
//                    ������д����ͷ
                    for (int row = 0; row < totalCount; row++) {
//                            ���濪ʼ���ж�ȡ,����д��

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
                    //                �����ȡ�ļ����֣�
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); //�����λ��
                    //              ��ȡ�ļ�·��
                    String cellVal;
                    cellVal = (String) (model.getValueAt(row, 2));
//                �õ����©�����ڵ��к�
                    int intLine = Integer.parseInt((String) (model.getValueAt(row, 4)));

                    NewPhpFilePage newPhpFilePage = new NewPhpFilePage(cellVal,stringPath,codings);
//                �Զ���ƽ��������͸��µ��Ǹ�ҳ��
                    newPhpFilePage.setListener(Listener);
                    newPhpFilePage.setIntLine(intLine);
                    returnBox = newPhpFilePage.initPanel();
                    Listener.addPage(cellVal,returnBox);



                }

            }
        });
    }


    public JPanel init() {
        //      ���������������ı�ǩ
        Box southJprogressBox = Box.createHorizontalBox();
        int[] width = {10, 200, 170, 500, 0};
        //    ˮƽ�ĺ��ӣ�����������ť
        Box ButtonPanel = Box.createHorizontalBox();

        Object[] title = {"ID", "©������", "�ļ�·��", "©������", "�����к�"};
//        ������Ӽ������ĺ���

        //        ��װ��ͼ����İ�ť
        ButtonPanel.add(start);
        ButtonPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        ButtonPanel.add(OutPaper);
        ButtonPanel.add(Box.createHorizontalGlue());
//          ��������Ľ�����ʾ
        ScanState_waiting.setFont(new Font("����", Font.BOLD, 20));
        southJprogressBox.add(ScanState_waiting);
        southJprogressBox.add(Box.createHorizontalGlue());
//          ���ñ������
//        detailTable.setEnabled(false);
        detailTable.setRowHeight(25);
        detailTable.setShowHorizontalLines(true);
        detailTable.setShowVerticalLines(true);

        model.setColumnCount(5);
        //        ����������
        for (int i = 0; i < 5; i++) {
            TableColumn tableColumn = detailTable.getColumnModel().getColumn(i);
            tableColumn.setHeaderValue(title[i]);
            tableColumn.setPreferredWidth(width[i]);
        }
//        ��װ����ͼ
        TotalPanel.setLayout(new BoxLayout(TotalPanel, BoxLayout.Y_AXIS));
        TotalPanel.add(ButtonPanel);
        TotalPanel.add(new JScrollPane(detailTable));
        TotalPanel.add(southJprogressBox);

        return TotalPanel;
    }


}
