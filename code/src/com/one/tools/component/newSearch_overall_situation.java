package com.one.tools.component;

import com.one.tools.listener.ActionDoneListener;
import com.one.tools.tool.FileTraverse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.BoxView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ��ΰ��
 * Belong to DongLaiԴ�������ϵͳ
 * @date 2021-04-18-14:18
 * @description �����������װȫ����������ͼ���ṩ�����ҳ�������ӣ�
 * ���췽����Ҫ����Ҫ�������ַ���Ȼ�����б����ʽչʾ������������
 */
public class newSearch_overall_situation {

    //    ������Ŀ¼���������ñ���Ŀ¼�ĺ���ȥ���б���
    String StringPath;
    ActionDoneListener Listener;

    public void setListener(ActionDoneListener listener) {
        Listener = listener;
    }

    String codings;

    public newSearch_overall_situation(String codings) {
        this.codings = codings;
    }

    public void setStringPath(String stringPath) {
        StringPath = stringPath;
    }

    Box total_search_page = Box.createVerticalBox();
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JLabel tip = new JLabel("������Ҫ����������(֧��������ʽ)");
    JTextField search_content = new JTextField(30);
    JButton start = new JButton("����");
    JCheckBox support_re = new JCheckBox("������ʽ");
    JCheckBox big_small = new JCheckBox("�����ִ�Сд(��ʾ����������ƥ��ʱ���ܺ��Դ�Сд)");

    DefaultTableModel search_result_model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    JTable search_result = new JTable(search_result_model);
    boolean ishaveValue_afferent = false;
    String searchContent;
    Box southJprogressBox = Box.createHorizontalBox();
    ArrayList<File> fileArrayList = new ArrayList<>();
    BufferedReader bufferedReader;
    InputStreamReader isr;
    JLabel progress = new JLabel("�ȴ�����");
    Box returnBox;

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public void setIshaveValue_afferent(boolean ishaveValue_afferent) {
        this.ishaveValue_afferent = ishaveValue_afferent;
    }

    public void addActionlistener() {

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        if (StringPath == null) {
                            return;
                        }
                        if (search_result_model.getRowCount() != 0) {
                            search_result_model.setRowCount(0);
                        }
                        progress.setText("��������");
                        File file = new File(StringPath);
                        String Line = null;
                        if (searchContent == null) {
                            searchContent = search_content.getText();
                        }
                        if (searchContent == null) {
                            return;
                        }

                        String[] strings = {"", "", "", ""};
                        try {
                            fileArrayList = new FileTraverse().listDirectoryFile(file);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
//                ������͵õ�����ѡ��Ŀ¼���ļ�����
                        int id = 1;
                        boolean open_re = support_re.isSelected();
                        boolean IgnoreBigOrSmall = big_small.isSelected();

                        for (File file1 : fileArrayList) {
//                            ������д��������
                            int rowNum = 1;
                            try {
                                isr = new InputStreamReader(new FileInputStream(file1), "UTF-8");
                            } catch (UnsupportedEncodingException | FileNotFoundException unsupportedEncodingException) {
                                unsupportedEncodingException.printStackTrace();
                            }
                            bufferedReader = new BufferedReader(isr);
                            try {
                                Line = bufferedReader.readLine();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            if (open_re) {
                                Pattern re;
                                if (IgnoreBigOrSmall) {
                                    re = Pattern.compile(searchContent, Pattern.CASE_INSENSITIVE);
                                } else {
                                    re = Pattern.compile(searchContent);
                                }
                                while (Line != null) {
                                    Matcher m = re.matcher(Line);
                                    if (m.find()) {
                                        strings[0] = String.valueOf(id);
                                        strings[1] = file1.getPath();
                                        strings[2] = Line.trim();
                                        strings[3] = String.valueOf(rowNum);
                                        search_result_model.addRow(strings);
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException interruptedException) {
                                            interruptedException.printStackTrace();
                                        }
                                        id++;

                                    }
                                    try {
                                        Line = bufferedReader.readLine();
                                        rowNum++;
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }

                                }
                            } else {
                                while (Line != null) {
                                    if (Line.contains(searchContent)) {
                                        strings[0] = String.valueOf(id);
                                        strings[1] = file1.getPath();
                                        strings[2] = Line.trim();
                                        strings[3] = String.valueOf(rowNum);
                                        search_result_model.addRow(strings);
                                        try {
                                            Thread.sleep(50);
                                        } catch (InterruptedException interruptedException) {
                                            interruptedException.printStackTrace();
                                        }
                                        id++;

                                    }
                                    try {
                                        Line = bufferedReader.readLine();
                                        rowNum++;
                                    } catch (IOException ioException) {
                                        ioException.printStackTrace();
                                    }

                                }

                            }
                        }
                        progress.setText("������ɣ�" + "��������" + id + "����ͬ");


                    }
                }).start();


            }
        });
        search_result.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); //�����λ��
                    //              ��ȡ�ļ�·��
                    String cellVal;
                    cellVal = (String) (search_result_model.getValueAt(row, 1));
//                �õ����©�����ڵ��к�
                    int intLine = Integer.parseInt((String) (search_result_model.getValueAt(row, 3)));
                    NewPhpFilePage newPhpFilePage = new NewPhpFilePage(cellVal, StringPath,codings);
                    newPhpFilePage.setListener(Listener);
                    newPhpFilePage.setIntLine(intLine);
                    returnBox = newPhpFilePage.initPanel();
                    Listener.addPage(cellVal, returnBox);
                }


            }
        });


    }

    public String getStringPath() {
        return StringPath;
    }

    public Box initSearchPage() {
        addActionlistener();
        progress.setFont(new Font("����", Font.BOLD, 20));
        search_result_model.setColumnCount(4);
        search_result_model.setRowCount(1);
        String[] title_search_page = {"ID", "�ļ�·��", "�������", "�����к�"};
//                        int[] width_search_page={10,100,1200};
        /**
         * ���������������и�ʽ�����Լ���װ
         *
         */
        search_result_model.setRowCount(0);

        topPanel.add(tip);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(search_content);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(start);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(support_re);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(big_small);
        for (int x = 0; x < 4; x++) {
            TableColumn tableColumns = search_result.getColumnModel().getColumn(x);
            tableColumns.setHeaderValue(title_search_page[x]);
        }
        search_content.setText(searchContent);
        topPanel.setPreferredSize(new Dimension(1000, 50));
        JScrollPane jScrollPane = new JScrollPane(search_result);
        jScrollPane.setPreferredSize(new Dimension(1000, 1000));

        total_search_page.add(topPanel);
        total_search_page.add(jScrollPane);
        southJprogressBox.add(progress);
        southJprogressBox.add(Box.createHorizontalGlue());
        total_search_page.add(southJprogressBox);
        return total_search_page;
    }
}
