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
 * @author 乐伟超
 * Belong to DongLai源代码审计系统
 * @date 2021-04-20-16:45
 * @description 用来生成审计规则页面的类，返回PANEL同时实现对审计规则文件的修改
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
    JButton verification = new JButton("验证");
    JButton update = new JButton("修改");
    JButton add = new JButton("添加");
    ArrayList<String[]> arrayList = new ArrayList<>();
    int selectedRow = -1;
    JTextArea jTextArea = new JTextArea(20, 50);

    public void addListenerForPanel() {
        showTable.addMouseListener(new MouseAdapter() {
            @
                    Override
            public void mouseClicked(MouseEvent e) {
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); //获得行位置
                selectedRow = row;
                //              获取文件路径
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
                    JOptionPane.showMessageDialog(mainPanel, "验证栏和规则栏数据不能为空值", "提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Pattern re = Pattern.compile(verificationRule, Pattern.CASE_INSENSITIVE);
                Matcher m = re.matcher(verificationContent);
                if (m.find()) {
                    JOptionPane.showMessageDialog(mainPanel, "匹配成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "匹配失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        update.addActionListener(new ActionListener() {
            @
                    Override
            public void actionPerformed(ActionEvent e) {
                //                有选中行才能进行对应的更新
                if (selectedRow < 0) {
                    return;
                } else {
                    //                    判断更新后的输入没有空值，才能进行添加
                    String UpdateRule = ruleField.getText();
                    String UpdateDescription = descriptionField.getText();
                    if (UpdateRule.equals("") || UpdateDescription.equals("")) {
                        JOptionPane.showMessageDialog(mainPanel, "规则或者描述不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    //                    确认是否添加
                    int sure = JOptionPane.showConfirmDialog(mainPanel, "如果您不熟悉正则规则不建议您更新，您确定要进行更新？", "警告", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (sure == 1 || sure == 2 || sure == -1) {
                        return;
                    }
                    //                    如果选中了行，就能根据对应的行号进行更新
                    //                    进行添加
                    showTable.setValueAt(UpdateRule, selectedRow, 0);
                    showTable.setValueAt(UpdateDescription, selectedRow, 1);
                    showTable.updateUI();
                }
                //              重写完表格以后，对文本进行更新,先拿一个空文本试一下
                File saveHtmlFile = new File("config\\rule.txt");
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(saveHtmlFile));
                    out.write("id 详细规则 规则描述");
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
                //                先获取到用户的输入内容
                String addRule = ruleField.getText();
                String addDescription = descriptionField.getText();
                //                判断没有空值后再进行添加
                if (addRule.equals("") || addDescription.equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "规则或者描述不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                //                确认是否要执行添加的操作
                int sure = JOptionPane.showConfirmDialog(mainPanel, "如果您不熟悉正则规则不建议您进行添加，您确定要进行添加？", "警告", JOptionPane.YES_NO_CANCEL_OPTION);
                if (sure == 1 || sure == 2 || sure == -1) {
                    return;
                }
                //                确认添加后写入文件
                String[] addRuleArray = {
                        "", ""
                };
                addRuleArray[0] = addRule;
                addRuleArray[1] = addDescription;
                showTableModel.addRow(addRuleArray);
                //                添加后将两个文本域的内容变成空值
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
                "详细规则", "规则描述"
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
        JLabel rule = new JLabel("规则:");
        JLabel description = new JLabel("描述:");
        JPanel panelForShowTable = new JPanel(new GridLayout(1, 1));
        showTable.setPreferredSize(new Dimension(600, 700));
        panelForShowTable.add(new JScrollPane(showTable));
        panelForShowTable.setBorder(BorderFactory.createTitledBorder("规则信息"));
        mainPanel.add(panelForShowTable);
        //        中间的部分要放置一个垂直排列的盒子
        Box center = Box.createVerticalBox();
        //        垂直排列的Box，水平的panel两个
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
        //        文本域如果直接加到从窗口里面的话，就不能加标题了，所以还是用一个panel放起来
        Box BoxForjTextArea = Box.createHorizontalBox();
        BoxForjTextArea.add(jTextArea);
        jTextArea.setSize(800, 300);
        BoxForjTextArea.setBorder(BorderFactory.createTitledBorder("规则测试"));
        BoxForjTextArea.setBackground(Color.white);
        mainPanel.add(BoxForjTextArea);
        //        下面再放置一个Panel，，另一个用来放置三个按钮
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