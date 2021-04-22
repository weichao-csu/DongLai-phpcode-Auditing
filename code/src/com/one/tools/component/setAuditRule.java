package com.one.tools.component;

import com.one.tools.tool.ReadFileBySplit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
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
 * @date 2021-04-20-16:45
 * @description ����������ƹ���ҳ����࣬����PANELͬʱʵ�ֶ���ƹ����ļ����޸�
 */
public class setAuditRule {
    Box mainPanel = Box.createVerticalBox();
    DefaultTableModel showTableModel = new DefaultTableModel() {
        @
                Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    JTable showTable = new JTable(showTableModel);
    JTextField ruleField = new JTextField(100);
    JTextField descriptionField = new JTextField(100);
    JButton verification = new JButton("��֤");
    JButton update = new JButton("�޸�");
    JButton add = new JButton("���");
    ArrayList<String[]> arrayList = new ArrayList<>();
    int selectedRow = -1;
    JTextArea jTextArea = new JTextArea(20, 50);

    public void addListenerForPanel() {
        showTable.addMouseListener(new MouseAdapter() {
            @
                    Override
            public void mouseClicked(MouseEvent e) {
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); //�����λ��
                selectedRow = row;
                //              ��ȡ�ļ�·��
                if (row < 0 || row > arrayList.size()) {
                    return;
                }
                //                selectedRow=(int) (showTableModel.getValueAt(row, 0));
                String rule = (String) (showTableModel.getValueAt(row, 0));
                String description = (String) (showTableModel.getValueAt(row, 1));
                ruleField.setText(rule);
                descriptionField.setText(description);
                System.out.println(row);
            }
        });
        verification.addActionListener(new ActionListener() {
            @
                    Override
            public void actionPerformed(ActionEvent e) {
                String verificationRule = ruleField.getText();
                String verificationContent = jTextArea.getText();
                if (verificationContent.equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "��֤���͹��������ݲ���Ϊ��ֵ", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Pattern re = Pattern.compile(verificationRule, Pattern.CASE_INSENSITIVE);
                Matcher m = re.matcher(verificationContent);
                if (m.find()) {
                    JOptionPane.showMessageDialog(mainPanel, "ƥ��ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "ƥ��ʧ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        update.addActionListener(new ActionListener() {
            @
                    Override
            public void actionPerformed(ActionEvent e) {
                //                ��ѡ���в��ܽ��ж�Ӧ�ĸ���
                if (selectedRow < 0) {
                    return;
                } else {
                    //                    �жϸ��º������û�п�ֵ�����ܽ������
                    String UpdateRule = ruleField.getText();
                    String UpdateDescription = descriptionField.getText();
                    if (UpdateRule.equals("") || UpdateDescription.equals("")) {
                        JOptionPane.showMessageDialog(mainPanel, "���������������Ϊ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    //                    ȷ���Ƿ����
                    int sure = JOptionPane.showConfirmDialog(mainPanel, "���������Ϥ������򲻽��������£���ȷ��Ҫ���и��£�", "����", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (sure == 1 || sure == 2 || sure == -1) {
                        return;
                    }
                    //                    ���ѡ�����У����ܸ��ݶ�Ӧ���кŽ��и���
                    //                    �������
                    showTable.setValueAt(UpdateRule, selectedRow, 0);
                    showTable.setValueAt(UpdateDescription, selectedRow, 1);
                    showTable.updateUI();
                }
                //              ��д�����Ժ󣬶��ı����и���,����һ�����ı���һ��
                File saveHtmlFile = new File("config\\rule.txt");
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(saveHtmlFile));
                    out.write("id ��ϸ���� ��������");
                    out.write("\r\n");
                    for (int j = 0; j < showTableModel.getRowCount(); j++) {
                        Object valueAt = showTable.getValueAt(j, 0);
                        Object valueAt1 = showTable.getValueAt(j, 1);
                        out.write(valueAt + ":" + valueAt1);
                        out.write("\r\n");
                    }
                    out.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        add.addActionListener(new ActionListener() {
            @
                    Override
            public void actionPerformed(ActionEvent e) {
                //                �Ȼ�ȡ���û�����������
                String addRule = ruleField.getText();
                String addDescription = descriptionField.getText();
                //                �ж�û�п�ֵ���ٽ������
                if (addRule.equals("") || addDescription.equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "���������������Ϊ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                //                ȷ���Ƿ�Ҫִ����ӵĲ���
                int sure = JOptionPane.showConfirmDialog(mainPanel, "���������Ϥ������򲻽�����������ӣ���ȷ��Ҫ������ӣ�", "����", JOptionPane.YES_NO_CANCEL_OPTION);
                if (sure == 1 || sure == 2 || sure == -1) {
                    return;
                }
                //                ȷ����Ӻ�д���ļ�
                String[] addRuleArray = {
                        "", ""
                };
                addRuleArray[0] = addRule;
                addRuleArray[1] = addDescription;
                showTableModel.addRow(addRuleArray);
                //                ��Ӻ������ı�������ݱ�ɿ�ֵ
                ruleField.setText("");
                descriptionField.setText("");
            }
        });
    }

    public void ReadFileToShowTable() throws IOException {
        showTableModel.setRowCount(arrayList.size());
        arrayList = new ReadFileBySplit().SplitFileToList(new File("config\\rule.txt"));
        String[] ruleArray;
        for (int a = 0; a < arrayList.size(); a++) {
            ruleArray = arrayList.get(a);
            showTableModel.addRow(ruleArray);
        }
    }

    public Box init_setAuditRule() {
        addListenerForPanel();
        String[] title = {
                "��ϸ����", "��������"
        };
        int[] width = {
                300, 400
        };
        showTableModel.setColumnCount(2);
        for (int i = 0; i < 2; i++) {
            TableColumn tableColumn = showTable.getColumnModel().getColumn(i);
            tableColumn.setHeaderValue(title[i]);
            tableColumn.setPreferredWidth(width[i]);
        }
        JLabel rule = new JLabel("����:");
        JLabel description = new JLabel("����:");
        JPanel panelForShowTable = new JPanel(new GridLayout(1, 1));
        showTable.setPreferredSize(new Dimension(600, 700));
        panelForShowTable.add(new JScrollPane(showTable));
        panelForShowTable.setBorder(BorderFactory.createTitledBorder("������Ϣ"));
        mainPanel.add(panelForShowTable);
        //        �м�Ĳ���Ҫ����һ����ֱ���еĺ���
        Box center = Box.createVerticalBox();
        //        ��ֱ���е�Box��ˮƽ��panel����
        JPanel HorizontalPanel_above = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel HorizontalPanel_under = new JPanel(new FlowLayout(FlowLayout.LEFT));
        HorizontalPanel_above.add(rule);
        HorizontalPanel_above.add(ruleField);
        HorizontalPanel_above.add(Box.createHorizontalGlue());
        HorizontalPanel_under.add(description);
        HorizontalPanel_under.add(descriptionField);
        HorizontalPanel_above.add(Box.createHorizontalGlue());
        center.add(HorizontalPanel_above);
        center.add(HorizontalPanel_under);
        mainPanel.add(center);
        //        �ı������ֱ�Ӽӵ��Ӵ�������Ļ����Ͳ��ܼӱ����ˣ����Ի�����һ��panel������
        Box BoxForjTextArea = Box.createHorizontalBox();
        BoxForjTextArea.add(jTextArea);
        jTextArea.setSize(800, 300);
        BoxForjTextArea.setBorder(BorderFactory.createTitledBorder("�������"));
        BoxForjTextArea.setBackground(Color.white);
        mainPanel.add(BoxForjTextArea);
        //        �����ٷ���һ��Panel������һ����������������ť
        JPanel ButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ButtonPanel.add(verification);
        ButtonPanel.add(update);
        ButtonPanel.add(add);
        mainPanel.add(ButtonPanel);
        try {
            ReadFileToShowTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mainPanel;
    }
}