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
 * @author ��ΰ��
 * Belong to DongLaiԴ�������ϵͳ
 * @date 2021-04-17-17:16
 * @description ˫���Զ����ҳ��򿪶�Ӧ��php�ļ�ҳ��, Ŀ���ǲ���һ��Panel
 */
public class NewPhpFilePage {
    //    ������һ�����洫���Ĳ���
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

    //    ���ļ��������������������ҳ����
    Box total_in_panel = Box.createVerticalBox();

    //    չʾphp������ı���
    JTextArea php_code = new JTextArea();
    //    ҳ���һ��˵�
    JPopupMenu popupMenu_belong_to_phpcode = new JPopupMenu();
    //    ȫ�������˵���
    JMenuItem Full_text_search = new JMenuItem("ȫ��׷��");
    //    ���Ʋ˵���
    JMenuItem copy = new JMenuItem("����");
    //    ȫ�������˵���
    JMenuItem search_overall_situation = new JMenuItem("ȫ������");
    //  ���notepad++�򿪵��һ��˵�
    JMenuItem openWithNotePad = new JMenuItem("�ı��༭����");
    //    �����ȫ��׷�ٱ��
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
//        ����ȫ��׷�ٱ�������
        String[] title_inter = {"ID", "�������"};
        int[] width_inter = {10, 1200};
        xiangqing_model.setColumnCount(2);
        xiangqing_model.setRowCount(0);
        xiangqing.setRowHeight(20);
        for (int h = 0; h < 2; h++) {
            TableColumn tableColumn = xiangqing.getColumnModel().getColumn(h);
            tableColumn.setHeaderValue(title_inter[h]);
            tableColumn.setPreferredWidth(width_inter[h]);
        }
//        ��װ�һ��˵�
        popupMenu_belong_to_phpcode.add(Full_text_search);
        popupMenu_belong_to_phpcode.add(copy);
        popupMenu_belong_to_phpcode.add(search_overall_situation);
        popupMenu_belong_to_phpcode.add(openWithNotePad);
    }

    public void addListenerForJPopupMenu() {
//        ��ִ�ж�popupmenu����װ
        initPopupMenu();

        php_code.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                boolean popupTrigger = e.isPopupTrigger();//�ǲ���Ҫ�ٻ����˵�����ֵ��BOOL���ͱ���
                if (popupTrigger) {
                    popupMenu_belong_to_phpcode.show(php_code, e.getX(), e.getY());
                    //��������һ���µķ�������������ν֮���ǵ���������������λ��
                }
            }
        });
//        �����¼�������ť
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
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); //�õ�ϵͳ������
                String text = php_code.getSelectedText();  //��ȡ�ı��������
                StringSelection selection = new StringSelection(text);
                clipboard.setContents(selection, null);
            }
        });

        search_overall_situation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchContent = php_code.getSelectedText();
//                �ж���ѡ�в�ִ������Ĳ���
                if (searchContent != null) {
                    newSearch_overall_situation newSearch_overall_situation = new newSearch_overall_situation(codings);
                    newSearch_overall_situation.setIshaveValue_afferent(true);
                    newSearch_overall_situation.setSearchContent(searchContent);
                    newSearch_overall_situation.setStringPath(filePath);
                    newSearch_overall_situation.setListener(Listener);
                    searchPage = newSearch_overall_situation.initSearchPage();
                    Listener.addPage("ȫ������", searchPage);
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
                    JOptionPane.showMessageDialog(total_in_panel,"�Ҳ���ָ���ı༭��������������","��ʾ",JOptionPane.WARNING_MESSAGE);
                }


            }
        });


    }


    public Box initPanel() {
//        ��װ֮ǰ������������ť
        addListenerForJPopupMenu();
//        ��������


//        ������Ҫ��װ����������ı������������ȫ���������б�

        /**
         * ����װ������ı�����
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
//        ������ɹ���ʼ��ȡ
//        ���濪ʼѭ��д��
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
//        ������ɹ���php����д���ı�����
//        ���濪ʼ����ѡ������
//        ������������ȷ������ļ�����������ĳһ��ѡ��

        php_code.setFont(new Font("����", Font.BOLD, 15));
        php_code.requestFocus(); //��ý���
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
