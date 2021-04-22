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
 * @author 乐伟超
 * Belong to DongLai源代码审计系统
 * @date 2021-04-18-14:18
 * @description 在这个里面组装全局搜索的视图，提供给多个页面进行添加，
 * 构造方法是要输入要搜索的字符，然后以列表的形式展示在这个表格里面
 */
public class newSearch_overall_situation {

    //    将整个目录传进来，用遍历目录的函数去进行遍历
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

    JLabel tip = new JLabel("请输入要搜索的内容(支持正则表达式)");
    JTextField search_content = new JTextField(30);
    JButton start = new JButton("搜索");
    JCheckBox support_re = new JCheckBox("正则表达式");
    JCheckBox big_small = new JCheckBox("不区分大小写(提示：不是正则匹配时不能忽略大小写)");

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
    JLabel progress = new JLabel("等待搜索");
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
                        progress.setText("正在搜索");
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
//                到上面就得到了所选择目录的文件集合
                        int id = 1;
                        boolean open_re = support_re.isSelected();
                        boolean IgnoreBigOrSmall = big_small.isSelected();

                        for (File file1 : fileArrayList) {
//                            标记这行代码的行数
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
                        progress.setText("搜索完成，" + "共搜索到" + id + "处相同");


                    }
                }).start();


            }
        });
        search_result.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); //获得行位置
                    //              获取文件路径
                    String cellVal;
                    cellVal = (String) (search_result_model.getValueAt(row, 1));
//                得到这个漏洞所在的行号
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
        progress.setFont(new Font("宋体", Font.BOLD, 20));
        search_result_model.setColumnCount(4);
        search_result_model.setRowCount(1);
        String[] title_search_page = {"ID", "文件路径", "语句详情", "所在行号"};
//                        int[] width_search_page={10,100,1200};
        /**
         * 声明完组件下面进行格式设置以及组装
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
