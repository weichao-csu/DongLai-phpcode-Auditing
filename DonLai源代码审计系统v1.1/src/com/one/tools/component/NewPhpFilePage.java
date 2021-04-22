package com.one.tools.component;

import com.one.tools.listener.ActionDoneListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/**
 * @author 乐伟超
 * Belong to DongLai源代码审计系统
 * @date 2021-04-17-17:16
 * @description 双击自动审计页面打开对应的php文件页面, 目标是产生一个Panel
 */
public class NewPhpFilePage {
    //    来自上一个界面传来的参数
    String FileName;
    int intLine = 0;
    String filePath;

    public void setIntLine(int intLine) {
        this.intLine = intLine;
    }

    public NewPhpFilePage(String FileName, String filePath, String codings) {
        this.FileName = FileName;
        this.filePath = filePath;
        this.codings = codings;


    }

    //    将文件名传进来就能生成这个页面了
    Box total_in_panel = Box.createVerticalBox();

    //    展示php代码的文本域
    JTextArea php_code = new JTextArea();
    //    页面右击菜单
    JPopupMenu popupMenu_belong_to_phpcode = new JPopupMenu();
    //    全局搜索菜单项
    JMenuItem Full_text_search = new JMenuItem("全文追踪");
    //    复制菜单项
    JMenuItem copy = new JMenuItem("复制");
    //    全局搜索菜单项
    JMenuItem search_overall_situation = new JMenuItem("全局搜索");
    //  添加notepad++打开的右击菜单
    JMenuItem openWithNotePad = new JMenuItem("文本编辑器打开");
    //    下面的全文追踪表格
    DefaultTableModel xiangqing_model = new DefaultTableModel();
    JTable xiangqing = new JTable(xiangqing_model);

    //
    Box searchPage;
    //
    ActionDoneListener Listener;

    public void setListener(ActionDoneListener listener) {
        Listener = listener;
    }

    String codings;


    public void initPopupMenu() {
//        设置全文追踪表格的属性
        String[] title_inter = {"ID", "语句详情"};
        int[] width_inter = {10, 1200};
        xiangqing_model.setColumnCount(2);
        xiangqing_model.setRowCount(0);
        xiangqing.setRowHeight(20);
        for (int h = 0; h < 2; h++) {
            TableColumn tableColumn = xiangqing.getColumnModel().getColumn(h);
            tableColumn.setHeaderValue(title_inter[h]);
            tableColumn.setPreferredWidth(width_inter[h]);
        }
//        组装右击菜单
        popupMenu_belong_to_phpcode.add(Full_text_search);
        popupMenu_belong_to_phpcode.add(copy);
        popupMenu_belong_to_phpcode.add(search_overall_situation);
        popupMenu_belong_to_phpcode.add(openWithNotePad);
    }

    public void addListenerForJPopupMenu() {
//        先执行对popupmenu的组装
        initPopupMenu();

        php_code.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                boolean popupTrigger = e.isPopupTrigger();//是不是要召唤出菜单返回值是BOOL类型变量
                if (popupTrigger) {
                    popupMenu_belong_to_phpcode.show(php_code, e.getX(), e.getY());
                    //这里用了一个新的方法给窗口蛇者谓之就是点击这个动作发生的位置
                }
            }
        });
//        建立事件监听按钮
        Full_text_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = php_code.getSelectedText();

                if (string != null) {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (xiangqing.getRowCount() != 0) {
                                xiangqing_model.setRowCount(0);
                            }
                            String[] strings = {"", ""};
                            File this_file = new File(FileName);
                            InputStreamReader this_isr = null;
                            BufferedReader this_bufferedReader;
                            try {
                                this_isr = new InputStreamReader(new FileInputStream(this_file), "UTF-8");
                            } catch (UnsupportedEncodingException | FileNotFoundException unsupportedEncodingException) {
                                unsupportedEncodingException.printStackTrace();
                            }
                            this_bufferedReader = new BufferedReader(this_isr);
                            String temp_Line = "";
                            try {
                                temp_Line = this_bufferedReader.readLine();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            int id = 1;
                            while (temp_Line != null) {
                                if (temp_Line.indexOf(string) != -1) {
                                    strings[0] = String.valueOf(id);
                                    strings[1] = temp_Line.trim();
                                    xiangqing_model.addRow(strings);
                                    id++;
                                }

                                try {
                                    temp_Line = this_bufferedReader.readLine();
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }
                        }
                    }).start();
                } else {
                    return;
                }


            }
        });
        copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //得到系统剪贴板
                String text = php_code.getSelectedText();  //获取文本框的内容
                StringSelection selection = new StringSelection(text);
                clipboard.setContents(selection, null);
            }
        });

        search_overall_situation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchContent = php_code.getSelectedText();
//                判断有选中才执行下面的操作
                if (searchContent != null) {
                    newSearch_overall_situation newSearch_overall_situation = new newSearch_overall_situation(codings);
                    newSearch_overall_situation.setIshaveValue_afferent(true);
                    newSearch_overall_situation.setSearchContent(searchContent);
                    newSearch_overall_situation.setStringPath(filePath);
                    newSearch_overall_situation.setListener(Listener);
                    searchPage = newSearch_overall_situation.initSearchPage();
                    Listener.addPage("全局搜索", searchPage);
                } else {
                    return;
                }
            }
        });

        openWithNotePad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime runtime = Runtime.getRuntime();
                String editorPath = "C:\\Program Files (x86)\\notepade++\\Notepad++\\notepad++.exe";
                BufferedReader bufferedReader;
                InputStreamReader isr;
                try {
                    isr = new InputStreamReader(new FileInputStream("config\\EditorPath.txt"), codings);
                    bufferedReader = new BufferedReader(isr);
                    try {
                        editorPath = bufferedReader.readLine();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    unsupportedEncodingException.printStackTrace();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

                String[] cmd = {editorPath, FileName};
                try {
                    runtime.exec(cmd);
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(total_in_panel,"找不到指定的编辑器，请重新设置","提示",JOptionPane.WARNING_MESSAGE);
                }


            }
        });


    }


    public Box initPanel() {
//        组装之前建立监听器按钮
        addListenerForJPopupMenu();
//        声明变量


//        这里面要组装的是上面的文本区域还有下面的全局搜索的列表

        /**
         * 先组装上面的文本区域
         */

        File phpaim_file = new File(FileName);
        InputStreamReader isr = null;
        BufferedReader bufferedReader;
        try {
            isr = new InputStreamReader(new FileInputStream(phpaim_file), codings);
        } catch (UnsupportedEncodingException | FileNotFoundException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
        bufferedReader = new BufferedReader(isr);
        String temp_Line = "";
        try {
            temp_Line = bufferedReader.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
//        到上面成功开始读取
//        下面开始循环写入
        int row_count = 1;
        while (temp_Line != null) {
            php_code.append(row_count + "  ");
            php_code.append(temp_Line);
            php_code.append("\r\n");

            try {
                temp_Line = bufferedReader.readLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            row_count++;
        }
//        到上面成功将php代码写入文本区域
//        下面开始设置选中区域
//        到上面是能正确打开这个文件，下面设置某一行选中

        php_code.setFont(new Font("楷体", Font.BOLD, 15));
        php_code.requestFocus(); //获得焦点
        JScrollPane jScrollPane = new JScrollPane(php_code);
        JScrollBar scrollBar = jScrollPane.getVerticalScrollBar();
        if (intLine != 0) {
            int totalLineCount = php_code.getLineCount();
            int selectionStart = 0;
            int selectionEnd = 0;
            try {
                selectionStart = php_code.getLineStartOffset(intLine - 1);
                selectionEnd = php_code.getLineEndOffset(intLine - 1);
            } catch (BadLocationException badLocationException) {
                badLocationException.printStackTrace();
            }
            if (intLine != totalLineCount) {
                selectionEnd--;
            }
            scrollBar.setValue(selectionStart);
            php_code.setSelectionStart(selectionStart);
            php_code.setSelectionEnd(selectionEnd);
        }

        jScrollPane.setPreferredSize(new Dimension(1000, 1300));
        total_in_panel.add(jScrollPane);
        total_in_panel.add(new JScrollPane(xiangqing));


        return total_in_panel;
    }


}
