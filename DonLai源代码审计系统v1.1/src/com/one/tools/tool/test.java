package com.one.tools.tool;

import javax.swing.*;

public class test {
    private void showLocationLineDialog(){
        JTextArea jTextArea1=new JTextArea();
//ȡ��������
        int totalLineCount = jTextArea1.getLineCount();
        if(totalLineCount <= 1){
            return ;
        }
        String title = "��ת���У�(1..."+totalLineCount+")";
        String line = JOptionPane.showInputDialog(this,title);
        if(line==null||"".equals(line.trim())){
            return;
        }
        try {
            int intLine = Integer.parseInt(line);
            if(intLine > totalLineCount){
                return;
            }
//JTextArea��ʼ�к���0�����Դ˴�����һ����
            int selectionStart = jTextArea1.getLineStartOffset(intLine-1);
            int selectionEnd = jTextArea1.getLineEndOffset(intLine-1);

//����ǲ������һ�У�selectionEnd����һ������Ϊ��ʹ�����ѡ������ͬһ��
            if(intLine != totalLineCount){
                selectionEnd--;

            }

            jTextArea1.requestFocus(); //��ý���
            jTextArea1.setSelectionStart(selectionStart);
            jTextArea1.setSelectionEnd(selectionEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//
//import javax.swing.JFrame;
//import javax.swing.JTextPane;
//import java.io.IOException;
//
//public class test extends JFrame{
//
//
//        JTextPane tp = new JTextPane();
//        public test() throws IOException {
//            String fileStr = "<?php\n" +
//                    "function bigclass($b){\n" +
//                    "$str=\"\";\n" +
//                    "$n=1;\n" +
//                    "$sql=\"select classname,classid,classzm from zzcms_zsclass where parentid=0 order by xuhao\";\n" +
//                    "$rs=query($sql);\n" +
//                    "$row=num_rows($rs);\n" +
//                    "if (!$row){\n" +
//                    "$str=\"���޷���\";\n" +
//                    "}else{\n" +
//                    "\n" +
//                    "while ($row=fetch_array($rs)){\n" +
//                    "$str=$str.\"<li>\";\n" +
//                    "\tif($row['classzm']==$b){\n" +
//                    "\t$str=$str.\"<a href='\".getpageurl2(\"dl\",$row[\"classzm\"],\"\").\"' class='current'>\".$row[\"classname\"].\"</a>\";\n" +
//                    "\t}else{\n" +
//                    "\t$str=$str.\"<a href='\".getpageurl2(\"dl\",$row[\"classzm\"],\"\").\"'>\".$row[\"classname\"].\"</a>\";\n" +
//                    "\t}\n" +
//                    "\t$str=$str.\"</li>\";\n" +
//                    "$n=$n+1;\t\t\n" +
//                    "}\n" +
//                    "}\n" +
//                    "return $str;\n" +
//                    "}\n" +
//                    "\n" +
//                    "function showdl($style,$num,$strnum,$b,$editor,$saver,$keyword,$cpid){\n" +
//                    "\tif ($b!=\"\") {\n" +
//                    "\t$sql=\"select * from zzcms_dl where passed=1 and classid=$b\";\n" +
//                    "\t}else{\n" +
//                    "\t$sql=\"select * from zzcms_dl where passed=1 \";\n" +
//                    "\t}\n" +
//                    "\tif ($keyword!='') {$sql=$sql. \" and cp like '%\".$keyword.\"%' \";}\n" +
//                    "\tif ($cpid!='') {$sql=$sql. \" and id<>$cpid \";}\n" +
//                    "\tif ($editor!='') {$sql=$sql. \" and editor ='\".$editor.\"' \";}\n" +
//                    "\tif ($saver!='') {$sql=$sql. \" and saver ='\".$saver.\"' \";}\t\n" +
//                    "\t$sql=$sql.\"order by id desc \";\n" +
//                    "\t$sql=$sql.\" limit 0,$num\";\n" +
//                    "//echo $sql;\n" +
//                    "$rs=query($sql);\n" +
//                    "$row=num_rows($rs);\n" +
//                    "if ($row){\t\n" +
//                    "switch ($style){\n" +
//                    "\tcase 1;\t\n" +
//                    "\t$str=\"<table width='100%' border='0' cellpadding='5' cellspacing='1' class='bgcolor1'>\";\n" +
//                    "\t$str=$str.\"<tr class='bgcolor3'> \";\n" +
//                    "\t$str=$str.\"<td width='22%' height='25'><strong> �����Ʒ</strong></td>\";\n" +
//                    "\t$str=$str.\"<td width='10%' align='center' ><strong>��ϵ��</strong></td>\";\n" +
//                    "    $str=$str.\"<td width='10%' align='center' ><strong>��������</strong></td>\";\n" +
//                    "    $str=$str.\"<td width='10%' align='center' ><strong>�绰</strong></td>\";\n" +
//                    "    $str=$str.\"<td width='28%' align='center' ><strong>��ϸ����</strong></td>\";\n" +
//                    "    $str=$str.\"<td width='20%' align='center' ><strong>��������</strong></td>\";\n" +
//                    "    $str=$str.\"</tr>\";\n" +
//                    "    $n=1;\n" +
//                    "\twhile($row=fetch_array($rs)){\n" +
//                    "\t$str=$str.\" <tr class='bgcolor2'>\";\n" +
//                    "\t$str=$str.\" <td height='25'>\";\n" +
//                    "\t$str=$str.\" <a href='\".getpageurl(\"zs\",$row[\"cpid\"]).\"'>\".cutstr($row[\"cp\"],16).\"</a>\" ;\n" +
//                    "\t$str=$str.\"</td>\";\n" +
//                    "    $str=$str.\"<td align='center' >\".$row[\"dlsname\"].\"</td>\";\n" +
//                    "    $str=$str.\"<td align='center' >\".$row[\"city\"].\"</td>\";\n" +
//                    "    $str=$str.\"<td align='center' style='color:red'>\";\n" +
//                    "\tif (isshowcontact==\"Yes\"){\n" +
//                    "\t$str=$str. $row[\"tel\"]; }else {$str=$str.\"<a href='\".getpageurl(\"dl\",$row[\"id\"]).\"' style=\\\"color:red\\\">VIP����ɲ鿴</a>\";}\n" +
//                    "    $str=$str.\"</td>\";\n" +
//                    "\t$str=$str.\"<td align='center'>\".cutstr($row[\"content\"],16).\"</td>\";\n" +
//                    "    $str=$str.\"<td align='center'>\".date(\"Y-m-d\",strtotime($row[\"sendtime\"])).\"</td>\";\n" +
//                    "    $str=$str.\"</tr>\\r\\n\";\n" +
//                    "\t$n++;\n" +
//                    "\t}\n" +
//                    "\t$str=$str.\"</table>\";\n" +
//                    "\tbreak;\n" +
//                    "case 2;\n" +
//                    "\t$str=\"<ul>\";\n" +
//                    "\t$n=1;\n" +
//                    "\twhile ($row=fetch_array($rs)){\n" +
//                    "\t$str=$str.\"<li>\";\n" +
//                    "\t$str=$str.\"<span style='float:right' title=����ʱ��>\".date(\"Y-m-d\",strtotime($row[\"sendtime\"])).\"</span>\";\n" +
//                    "\t$str=$str.\"<a href='\".siteurl.getpageurl(\"dl\",$row[\"id\"]).\"' target='_blank' title='\".$row[\"cp\"].\"'>\";\n" +
//                    "\t$str=$str.cutstr($row[\"cp\"],$strnum);\n" +
//                    "\t$str=$str.\"</a>\";\n" +
//                    "\t$str=$str.\"</li>\\r\\n\";\t\n" +
//                    "\t$n++;\n" +
//                    "\t}        \n" +
//                    "\t$str=$str. \"</ul>\";\n" +
//                    "\tbreak;\n" +
//                    "}\n" +
//                    "\n" +
//                    "}else{\n" +
//                    "$str=\"������Ϣ\";\n" +
//                    "}\n" +
//                    "return $str;\n" +
//                    "}\n" +
//                    "?>";
//            tp.setText(fileStr);
//            this.add(tp);
//            DecorateKeyWords dc = new DecorateKeyWords();
//
//            this.setBounds(300, 200, 400, 300);
//            this.setVisible(true);
//            dc.decorateKeyWords(tp);
//        }
//        /**
//         * @param args
//         */
//        public static void main(String[] args) throws IOException {
//            new test();
//        }
//    }
//
//////
////
////import java.awt.BorderLayout;
////import java.awt.Color;
////import java.awt.event.ActionEvent;
////import java.awt.event.ActionListener;
////import java.io.File;
////
////import javax.swing.BorderFactory;
////import javax.swing.Box;
////import javax.swing.ImageIcon;
////import javax.swing.JButton;
////import javax.swing.JComboBox;
////import javax.swing.JFileChooser;
////import javax.swing.JFrame;
////import javax.swing.JLabel;
////import javax.swing.JScrollPane;
////import javax.swing.JTextField;
////import javax.swing.JTextPane;
////import javax.swing.UIManager;
////import javax.swing.text.BadLocationException;
////import javax.swing.text.SimpleAttributeSet;
////import javax.swing.text.StyleConstants;
////import javax.swing.text.StyledDocument;
////
////
////public class test extends JFrame {
////
////    private static final long serialVersionUID = -2397593626990759111L;
////
////    private JScrollPane scrollPane = null; // ����
////
////    private JTextPane text = null; // ����˵�ˣ��������ʶ�Ļ���û��Ҫ������    ��������
////
////    private Box box = null; // ���������������    ������ϵ�����
////
////    private JButton b_insert = null, b_remove = null, b_icon = null; // ���밴ť;�����ť;����ͼƬ��ť��ť
////
////    private JTextField addText = null; // ����������ı���
////
////    private JComboBox fontName = null, fontSize = null, fontStyle = null, fontColor = null,
////            fontBackColor = null; // ��������;�ֺŴ�С;������ʽ;������ɫ;���ֱ�����ɫ
//////    ������ѡ����ɫ
////
////    private StyledDocument doc = null; // �ǳ���Ҫ����������ʽ�Ϳ����� //����������ʽ
////
////    public test() {             //���캯��
////        super("JTextPane Test");
////        try { // ʹ��Windows�Ľ�����
////            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        text = new JTextPane();
////        text.setEditable(false); // ����¼��
//////        ����TEXT����,Ȼ��doc��ȡ��������
//////        Ȼ���doc���и���,�ͻ��JTextPane������֮����
////
////        doc = text.getStyledDocument(); // ���JTextPane��Document
////        scrollPane = new JScrollPane(text);
////        addText = new JTextField(18);
////        /**
////         * ����������
////         */
////        String[] str_name = {"����", "����", "Dialog", "Gulim"};
////        String[] str_Size = {"12", "14", "18", "22", "30", "40"};
////        String[] str_Style = {"����", "б��", "����", "��б��"};
////        String[] str_Color = {"��ɫ", "��ɫ", "��ɫ", "��ɫ", "��ɫ"};
////        String[] str_BackColor = {"��ɫ", "��ɫ", "����", "����", "����", "����"};
////        fontName = new JComboBox(str_name); // ��������
////        fontSize = new JComboBox(str_Size); // �ֺ�
////        fontStyle = new JComboBox(str_Style); // ��ʽ
////        fontColor = new JComboBox(str_Color); // ��ɫ
////        fontBackColor = new JComboBox(str_BackColor); // ������ɫ
////        b_insert = new JButton("����"); // ����
////        b_remove = new JButton("���"); // ���
////        b_icon = new JButton("ͼƬ"); // ����ͼƬ
////
////        /**
////         * �������ֵ��¼�������
////         */
////        b_insert.addActionListener(new ActionListener() { // �������ֵ��¼�
////            public void actionPerformed(ActionEvent e) {
////                insert(getFontAttrib());
////                addText.setText("");
////            }
////        });
////        /**
////         * ɾ�����¼�������
////         */
////        b_remove.addActionListener(new ActionListener() { // ����¼�
////            public void actionPerformed(ActionEvent e) {
////                text.setText("");
////            }
////        });
////        /**
////         * ����ͼƬ�¼�������
////         */
////        b_icon.addActionListener(new ActionListener() { // ����ͼƬ�¼�
////            public void actionPerformed(ActionEvent arg0) {
////                JFileChooser f = new JFileChooser(); // �����ļ�
////                f.showOpenDialog(null);
////                insertIcon(f.getSelectedFile()); // ����ͼƬ
////            }
////        });
////
////
////        /**
////         * ��������װ��ͼ
////         */
////        box = Box.createVerticalBox(); // ���ṹ
////        Box box_1 = Box.createHorizontalBox(); // ��ṹ
////        Box box_2 = Box.createHorizontalBox(); // ��ṹ
////        box.add(box_1);
////        box.add(Box.createVerticalStrut(8)); // ���еļ��
////        box.add(box_2);
////        box.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); // 8���ı߾�
////        // ��ʼ�����������������
////        box_1.add(new JLabel("���壺")); // �����ǩ
////        box_1.add(fontName); // �������
////        box_1.add(Box.createHorizontalStrut(8)); // ���
////        box_1.add(new JLabel("��ʽ��"));
////        box_1.add(fontStyle);
////        box_1.add(Box.createHorizontalStrut(8));
////        box_1.add(new JLabel("�ֺţ�"));
////        box_1.add(fontSize);
////        box_1.add(Box.createHorizontalStrut(8));
////        box_1.add(new JLabel("��ɫ��"));
////        box_1.add(fontColor);
////        box_1.add(Box.createHorizontalStrut(8));
////        box_1.add(new JLabel("������"));
////        box_1.add(fontBackColor);
////        box_1.add(Box.createHorizontalStrut(8));
////        box_1.add(b_icon);
////        box_2.add(addText);
////        box_2.add(Box.createHorizontalStrut(8));
////        box_2.add(b_insert);
////        box_2.add(Box.createHorizontalStrut(8));
////        box_2.add(b_remove);
////        this.getRootPane().setDefaultButton(b_insert); // Ĭ�ϻس���ť
////        this.getContentPane().add(scrollPane);
////        this.getContentPane().add(box, BorderLayout.SOUTH);
////        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        this.setSize(600, 400);
////        this.setLocationRelativeTo(null);
////        this.setVisible(true);
////        addText.requestFocus();
////    }
////
////    /**
////     * ����ͼƬ
////     *
////     * @paramicon
////     */
////    private void insertIcon(File file) {
////        text.setCaretPosition(doc.getLength()); // ���ò���λ��
////        text.insertIcon(new ImageIcon(file.getPath())); // ����ͼƬ
////        insert(new FontAttrib()); // ���������Ի���
////    }
////
////    /**
////     * ���ı��ַ���ʽ�����ݲ���JTextPane
////     *
////     * @param attrib
////     */
////    private void insert(FontAttrib attrib) {
////        try { // �����ı�
//////            ����doc����,��ʼλ��,Ҫ������ı�,�������ԵĶ���,Ҳ����˵ͨ�������õ���������һЩֵ���������ֵ
//////            doc.insertString(doc.getLength(), );
////            doc.insertString(doc.getLength(), attrib.getText() + "\r\n", attrib.getAttrSet());
////        } catch (BadLocationException e) {
////            e.printStackTrace();
////        }
////    }
////
////    /**
////     * ��ȡ����Ҫ����������
////     *
////     * @return FontAttrib
////     */
////    private FontAttrib getFontAttrib() {
////        FontAttrib att = new FontAttrib();        //����һ���ڲ������
////        att.setText(addText.getText());             //��ȡ�ı�����
////        att.setName((String) fontName.getSelectedItem());    //�õ�ѡ�ж��������
//////        att.setSize(Integer.parseInt((String) fontSize.getSelectedItem()));       //�õ�ѡ�ж���Ĵ�С
//////        String temp_style = (String) fontStyle.getSelectedItem();       //�õ�ѡ�ж����������
//////        ����������
//////        if (temp_style.equals("����")) {
//////            att.setStyle(FontAttrib.GENERAL);
//////        } else if (temp_style.equals("����")) {
//////            att.setStyle(FontAttrib.BOLD);
//////        } else if (temp_style.equals("б��")) {
//////            att.setStyle(FontAttrib.ITALIC);
//////        } else if (temp_style.equals("��б��")) {
//////            att.setStyle(FontAttrib.BOLD_ITALIC);
//////        }
//////       �õ���ɫ��
////        String temp_color = (String) fontColor.getSelectedItem();
//////        ������ɫ
////        if (temp_color.equals("��ɫ")) {
////            att.setColor(new Color(0, 0, 0));
////        } else if (temp_color.equals("��ɫ")) {
////            att.setColor(new Color(255, 0, 0));
////        } else if (temp_color.equals("��ɫ")) {
////            att.setColor(new Color(0, 0, 255));
////        } else if (temp_color.equals("��ɫ")) {
////            att.setColor(new Color(255, 255, 0));
////        } else if (temp_color.equals("��ɫ")) {
////            att.setColor(new Color(0, 255, 0));
////        }
////////        �õ�����ɫ
//////        String temp_backColor = (String) fontBackColor.getSelectedItem();
//////        ���ñ���ɫ
//////        if (!temp_backColor.equals("��ɫ")) {
//////            if (temp_backColor.equals("��ɫ")) {
//////                att.setBackColor(new Color(200, 200, 200));
//////            } else if (temp_backColor.equals("����")) {
//////                att.setBackColor(new Color(255, 200, 200));
//////            } else if (temp_backColor.equals("����")) {
//////                att.setBackColor(new Color(200, 200, 255));
//////            } else if (temp_backColor.equals("����")) {
//////                att.setBackColor(new Color(255, 255, 200));
//////            } else if (temp_backColor.equals("����")) {
//////                att.setBackColor(new Color(200, 255, 200));
//////            }
//////        }
//////        ��������ı��Ķ���
////        return att;
////    }
////
////    public static void main(String args[]) {
////        new test();
////    }
////
////    /**
////     * �����������
////     */
////    private class FontAttrib {
////        public static final int GENERAL = 0; // ����
////
////        public static final int BOLD = 1; // ����
////
////        public static final int ITALIC = 2; // б��
////
////        public static final int BOLD_ITALIC = 3; // ��б��
////
////        private SimpleAttributeSet attrSet = null; // ���Լ�
////
////        private String text = null, name = null; // Ҫ������ı�����������
////
////        private int style = 0, size = 0; // ��ʽ���ֺ�
////
////        private Color color = null, backColor = null; // ������ɫ�ͱ�����ɫ
////
////        /**
////         * һ���յĹ��죨�ɵ�������ʹ�ã�
////         */
////        public FontAttrib() {
////        }
////
////        /**
////         * �������Լ�
////         *
////         * @return
////         */
////        public SimpleAttributeSet getAttrSet() {
////            attrSet = new SimpleAttributeSet();
//////            ����һ���������Ե���
////
////            StyleConstants.setFontFamily(attrSet, "����");
////
////            StyleConstants.setForeground(attrSet, new Color(0, 0, 0));
////            return attrSet;
////
////////            ��������������ַ��,����ȥ��
//////            if (style == FontAttrib.GENERAL) {
//////                StyleConstants.setBold(attrSet, false);
//////                StyleConstants.setItalic(attrSet, false);
//////            } else if (style == FontAttrib.BOLD) {
//////                StyleConstants.setBold(attrSet, true);
//////                StyleConstants.setItalic(attrSet, false);
//////            } else if (style == FontAttrib.ITALIC) {
//////                StyleConstants.setBold(attrSet, false);
//////                StyleConstants.setItalic(attrSet, true);
//////            } else if (style == FontAttrib.BOLD_ITALIC) {
//////                StyleConstants.setBold(attrSet, true);
//////                StyleConstants.setItalic(attrSet, true);
//////            }
//////            StyleConstants.setFontSize(attrSet, size);
//////            ������������ɫ
////
//////            ���������ñ���ɫ
//////            if (backColor != null)
//////                StyleConstants.setBackground(attrSet, backColor);
////
////        }
////        /**
////         * �������Լ�
////         *
////         * @param attrSet
////         */
////        public void setAttrSet(SimpleAttributeSet attrSet) {
////            this.attrSet = attrSet;
////        }
////
////        /* �����ע�;Ͳ�д�ˣ�һ�������� */
////
////        public String getText() {
////            return text;
////        }
////
////        public void setText(String text) {
////            this.text = text;
////        }
////
////        public Color getColor() {
////            return color;
////        }
////
////        public void setColor(Color color) {
////            this.color = color;
////        }
////
////        public Color getBackColor() {
////            return backColor;
////        }
////
////        public void setBackColor(Color backColor) {
////            this.backColor = backColor;
////        }
////
////        public String getName() {
////            return name;
////        }
////
////        public void setName(String name) {
////            this.name = name;
////        }
////
////        public int getSize() {
////            return size;
////        }
////
////        public void setSize(int size) {
////            this.size = size;
////        }
////
////        public int getStyle() {
////            return style;
////        }
////
////        public void setStyle(int style) {
////            this.style = style;
////        }
////    }
////
////}
////
//////import javax.swing.*;
//////
////////
////////import java.io.*;
////////import java.io.FileNotFoundException;
////////import java.io.FileOutputStream;
////////import java.io.IOException;
////////import java.io.RandomAccessFile;
////////import java.io.UnsupportedEncodingException;
////////import java.security.InvalidParameterException;
////////import java.util.Random;
////////import java.util.UUID;
////////import java.util.concurrent.ExecutorService;
////////import java.util.concurrent.Executors;
////////import java.util.concurrent.atomic.AtomicInteger;
////////import java.util.regex.Matcher;
////////import java.util.regex.Pattern;
////////////
////////////
//////////import java.io.File;
//////////import java.io.IOException;
//////////
////////
//////////8
//////////\${{0,1}\$\w{1,20}((\[["']|\[)\${0,1}[\w\[\]"']{0,30}){0,1}\s{0,4}=\s{0,4}.{0,20}\$\w{1,20}((\[["']|\[)\${0,1}[\w\[\]"']{0,30}){0,1}
//////////˫$$���ſ��ܴ��ڱ�������©��
//////public class test extends Thread{
//////
//////        JProgressBar progressBar;
//////        JButton button;
//////        //�������ϵ�����
//////        int[] progressValues={6,18,27,39,51,66,81,100};
//////        test(JProgressBar progressBar,JButton button)
//////        {
//////            this.progressBar=progressBar;
//////            this.button=button;
//////        }
//////        public void run()
//////        {
//////            for(int i=0;i<progressValues.length;i++)
//////            {
//////                try
//////                {
//////                    Thread.sleep(3000);
//////                }
//////                catch(InterruptedException e)
//////                {
//////                    e.printStackTrace();
//////                }
//////                //���ý�������ֵ
//////                progressBar.setValue(progressValues[i]);
//////            }
//////            progressBar.setIndeterminate(false);
//////            progressBar.setString("������ɣ�");
//////            button.setEnabled(true);
//////        }
//////    }
//////
//////
////////    public static void main(String[] args) {
////////        String text="if (!move_uploaded_file($tempName, $newName)) {";
////////        String patt="\\b(include|require)(_once){0,1}(\\s{1,5}|\\s{0,5}\\().{0,60}\\$(?!.*(this->))\\w{1,20}((\\[[\"']|\\[)\\${0,1}[\\w\\[\\]\"']{0,30}){0,1}";
//////////                   \\b(include|require)(_once){0,1}(\\s{1,5}|\\s{0,5}\\().{0,60}\\$(?!.*(this->))\\w{1,20}((\\[[\"']|\\[)\\${0,1}[\\w\\[\\]\"']{0,30}){0,1}
////////        Pattern r=Pattern.compile(patt,Pattern.CASE_INSENSITIVE);
////////
////////        Matcher m= r.matcher(text);
////////        while(m.find()){
////////            System.out.println(1);
////////        }
//////////  \s{1,3}set\s{1,5}.{1,60}\$\w{1,20}((\[["']|\[)\${0,1}[\w\[\]"']{0,30}){0,1}
////////
////////    }
////////}
//////////  \b(include|require)(_once){0,1}(\s{1,5}|\s{0,5}\().{0,60}\$(?!.*(this->))\w{1,20}((\[["']|\[)\${0,1}[\w\[\]"']{0,30}){0,1}
//////////  \b(include|require)(_once){0,1}(\s{1,5}|\s{0,5}\().{0,60}\$(?!.*(this->))\w{1,20}((\[["']|\[)\${0,1}[\w\[\]"']{0,30}){0,1}
//////////  \b(include|require)(_once){0,1}(\s{1,5}|\s{0,5}\().{0,60}\$(?!.*(this->))\w{1,20}((\[["']|\[)\${0,1}[\w\[\]"']{0,30}){0,1}
//////////        // ģ������
//////////        private static void writeData() throws FileNotFoundException, IOException {
//////////            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\lianghaohui\\Desktop\\test.txt");
//////////            Random random = new Random();
//////////            for (int n = 0; n < 1000000; n++) {
//////////                int count = random.nextInt(10) + 1;
//////////                StringBuilder builder = new StringBuilder();
//////////
//////////                for (int i = 0; i < count; i++) {
//////////                    builder.append(UUID.randomUUID().toString());
//////////                }
//////////
//////////                builder.append("\n");
//////////                fileOutputStream.write(builder.toString().getBytes());
//////////            }
//////////            fileOutputStream.close();
//////////            System.out.println("ok");
//////////        }
//////////
//////////        private static AtomicInteger atomicInteger = new AtomicInteger(0);
//////////
//////////        // 231498��ʱ 3�߳�(����2��4�߳�)
//////////        // 278592��ʱ 2�߳�
//////////        // 397115��ʱ ���߳�
//////////        // 245657��ʱ cpu�߳�����4�̣߳�
//////////        public static void main(String[] args) throws Exception {
//////////            long beginTime = System.currentTimeMillis();
//////////            test helper = new test();
//////////            helper.read("C:\\Users\\lianghaohui\\Desktop\\test.txt", Runtime.getRuntime().availableProcessors(), '\n', new StringCallback("UTF-8") {
//////////                @Override
//////////                void callback(String data) {
//////////                    int count = atomicInteger.incrementAndGet();
//////////                    System.out.println(count);
//////////                    if (count == 1000000) {
//////////                        System.out.println("�ܺ�ʱ���룺" + (System.currentTimeMillis() - beginTime));
//////////                        System.out.println(data);
//////////                    }
//////////                }
//////////            });
//////////
//////////
//////////        }
//////////
//////////        public void read(String path, int threadCount, char separator, StringCallback callback) throws IOException {
//////////
//////////            if (threadCount < 1) {
//////////                throw new InvalidParameterException("The threadCount can not be less than 1");
//////////            }
//////////
//////////            if (path == null || path.isEmpty()) {
//////////                throw new InvalidParameterException("The path can not be null or empty");
//////////            }
//////////
//////////            if (callback == null) {
//////////                throw new InvalidParameterException("The callback can not be null");
//////////            }
//////////
//////////            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
//////////
//////////            long fileTotalLength = randomAccessFile.length();
//////////            long gap = fileTotalLength / threadCount;
//////////            long checkIndex = 0;
//////////            long[] beginIndexs = new long[threadCount];
//////////            long[] endIndexs = new long[threadCount];
//////////
//////////            for (int n = 0; n < threadCount; n++) {
//////////                beginIndexs[n] = checkIndex;
//////////                if (n + 1 == threadCount) {
//////////                    endIndexs[n] = fileTotalLength;
//////////                    break;
//////////                }
//////////                checkIndex += gap;
//////////                long gapToEof = getGapToEof(checkIndex, randomAccessFile, separator);
//////////
//////////                checkIndex += gapToEof;
//////////                endIndexs[n] = checkIndex;
//////////            }
//////////
//////////            ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
//////////            executorService.execute(() -> {
//////////                try {
//////////                    readData(beginIndexs[0], endIndexs[0], path, randomAccessFile, separator, callback);
//////////                } catch (Exception e) {
//////////                    e.printStackTrace();
//////////                }
//////////            });
//////////
//////////            for (int n = 1; n < threadCount; n++) {
//////////                long begin = beginIndexs[n];
//////////                long end = endIndexs[n];
//////////                executorService.execute(() -> {
//////////                    try {
//////////                        readData(begin, end, path, null, separator, callback);
//////////                    } catch (Exception e) {
//////////                        e.printStackTrace();
//////////                    }
//////////                });
//////////            }
//////////        }
//////////
//////////        private long getGapToEof(long beginIndex, RandomAccessFile randomAccessFile, char separator) throws IOException {
//////////            randomAccessFile.seek(beginIndex);
//////////            long count = 0;
//////////
//////////            while (randomAccessFile.read() != separator) {
//////////                count++;
//////////            }
//////////
//////////            count++;
//////////
//////////            return count;
//////////        }
//////////
//////////        private void readData(long begin, long end, String path, RandomAccessFile randomAccessFile, char separator, StringCallback callback) throws FileNotFoundException, IOException {
//////////            System.out.println("��ʼ����" + Thread.currentThread().getName());
//////////            if (randomAccessFile == null) {
//////////                randomAccessFile = new RandomAccessFile(path, "r");
//////////            }
//////////
//////////            randomAccessFile.seek(begin);
//////////            StringBuilder builder = new StringBuilder();
//////////
//////////            while (true) {
//////////                int read = randomAccessFile.read();
//////////                begin++;
//////////                if (separator == read) {
//////////                    if (callback != null) {
//////////                        callback.callback0(builder.toString());
//////////                    }
//////////                    builder = new StringBuilder();
//////////                } else {
//////////                    builder.append((char) read);
//////////                }
//////////
//////////                if (begin >= end) {
//////////                    break;
//////////                }
//////////            }
//////////            randomAccessFile.close();
//////////        }
//////////
//////////        public static abstract class StringCallback {
//////////            private String charsetName;
//////////            private ExecutorService executorService = Executors.newSingleThreadExecutor();
//////////
//////////            public StringCallback(String charsetName) {
//////////                this.charsetName = charsetName;
//////////            }
//////////
//////////            private void callback0(String data) {
//////////                executorService.execute(() -> {
//////////                    try {
//////////                        callback(new String(data.getBytes("ISO-8859-1"), charsetName));
//////////                    } catch (UnsupportedEncodingException e) {
//////////                        e.printStackTrace();
//////////                    }
//////////                });
//////////
//////////            }
//////////
//////////            abstract void callback(String data);
//////////        }
//////////
//////////    }
//////////
////////////    //    ���������·�����ܶ�ȡ���ļ�
////////////    FileReader config;
////////////
////////////    {
////////////        try {
////////////            config = new FileReader("config\\rule.txt");
////////////        } catch (FileNotFoundException e) {
////////////            e.printStackTrace();
////////////        }
////////////    }
////////////
////////////    BufferedReader bufferedReader = new BufferedReader(config);
////////////    String line;
////////////
////////////    public void sc() throws IOException {
////////////        line = bufferedReader.readLine();
////////////        while (line != null) {
////////////            String[] strings = line.split("\\:", 3);
////////////            if (strings.length == 3) {
////////////                for (int i = 0; i < strings.length; i++) {
////////////                    System.out.println(strings[i]);
////////////                }
////////////
////////////            }
////////////
////////////            line = bufferedReader.readLine();
////////////
////////////        }
////////////    }
////////////
////////////    public static void main(String[] args) throws IOException {
////////////        test TEST = new test();
////////////        TEST.sc();
//////////////        System.out.println(TEST.config.getAbsolutePath());
//////////    }
//////////
////////
////////
//////////
//////////    //����ĳĿ¼�µ�����Ŀ¼
//////////    public static void listDirectoryFile(File dir) throws IOException{
//////////
//////////        if(!dir.exists()){//dir�����ڣ��׳��쳣
//////////            throw new IllegalArgumentException("Ŀ¼"+dir+"������");
//////////        }
//////////        if(!dir.isDirectory()){//dir����Ŀ¼�׳��쳣
//////////            throw new IllegalArgumentException(dir+"����Ŀ¼");
//////////        }
//////////        File[] files = dir.listFiles();
//////////        if(files!=null&&files.length>0){//������¼�Ŀ¼
//////////            for(File file : files){
//////////                if(!file.isDirectory()){//�������Ŀ¼��ֱ�Ӵ�ӡ����
//////////                    System.out.println(file.getAbsolutePath());
//////////                }else{//�����Ŀ¼���ݹ���ñ�����
//////////                    System.out.println(file.getAbsolutePath());
//////////                    listDirectoryFile(file);
//////////                }
//////////            }
//////////        }}
//////////
//////////    public static void main(String[] args) throws IOException {
//////////        listDirectoryFile(new File("C:\\WINDOWS"));
//////////
//////////    }
//////////}
//////////
////////////import java.io.File;
////////////
/////////////**
//////////// * @author ��ΰ��
//////////// * Belong to DongLaiԴ�������ϵͳ
//////////// * @date 2021-04-10-15:34
//////////// * @description �Ը�����Ŀ¼�ļ�������б�����
//////////// * @return ����·�����ļ�������
//////////// */
////////////public class test {
////////////    static File filePathTraverse;
////////////
////////////    /**
////////////     *���캯����������Ǵ��������ļ�Ŀ¼���ַ�����ʽ
////////////     */
////////////    public test(File filePathTraverse) {
////////////        this.filePathTraverse = filePathTraverse;
////////////    }
////////////    public  void Traverse(File file) {
////////////        File[] files = filePathTraverse.listFiles();
////////////        for (int i = 0; i < files.length; i++) {
////////////
////////////            if (files[i].isDirectory()) {
////////////                Traverse(files[i]);
////////////            } else {
////////////                System.out.println(files[i].getName());
////////////            }
////////////        }
////////////
////////////    }
////////////
////////////    public static void main(String[] args) {
////////////        File fc=new File("D:\\��͸���Գ�������͹���\\�������\\zzcms201910\\zzcms201910");
////////////        new test(fc).Traverse(fc);
////////////    }
////////////
////////////}
////////////
////////////import java.awt.Container;
////////////import javax.swing.JFrame;
////////////import javax.swing.JScrollPane;
////////////import javax.swing.JTree;
////////////import javax.swing.event.TreeSelectionEvent;
////////////import javax.swing.event.TreeSelectionListener;
////////////import javax.swing.tree.DefaultMutableTreeNode;
////////////
////////////public class test extends JFrame implements TreeSelectionListener {
////////////    JTree tree;
//////////////      ���캯��
////////////    test() {
////////////        setSize(300, 250);
////////////        setVisible(true);
////////////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////////////        Container con = getContentPane();
////////////        DefaultMutableTreeNode root = new DefaultMutableTreeNode("�����");
////////////        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode("���");
////////////        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode("���");
////////////        DefaultMutableTreeNode t1_1 = new DefaultMutableTreeNode("Ҷ���");
////////////        DefaultMutableTreeNode t1_2 = new DefaultMutableTreeNode("Ҷ���");
////////////        root.add(t1);
////////////        root.add(t2);
////////////        t1.add(t1_1);
////////////        t2.add(t1_2);
////////////        tree = new JTree(root); //������Ϊroot����
////////////        tree.addTreeSelectionListener(this);//����������ѡ�����
////////////        JScrollPane scrollPane = new JScrollPane(tree);
////////////        con.add(scrollPane);
////////////    }
////////////
////////////    public static void main(String[] args) {
////////////        test win = new test();
////////////        win.setVisible(true);
////////////    }
////////////
////////////    public void valueChanged(TreeSelectionEvent tse) {
////////////        DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
////////////        if (selectionNode.isLeaf()) { //Ҷ�ӽڵ�ļ���
////////////            System.out.println(selectionNode.toString());
////////////        }
////////////    }
////////////}
//////////
//////////
////////////        newProject.addActionListener(new ActionListener() {
////////////            @Override
////////////            public void actionPerformed(ActionEvent e) {
//////////////                FileDialog fileDialog = new FileDialog(mainFrame, "ѡ��Ҫ�򿪵��ļ�", FileDialog.LOAD);
//////////////                fileDialog.setVisible(true);
//////////////                String path=fileDialog.getDirectory();
////////////                JFileChooser fc=new JFileChooser("D://");
////////////                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
////////////                fc.showOpenDialog(null);
////////////                File Path = fc.getSelectedFile();
////////////                String stringPath=Path.getAbsolutePath();
////////////                file_dir_tree=new FileeRgodic(stringPath).Path2Tree();
////////////                panelTree.add(file_dir_tree);
////////////                panelTree.updateUI();
////////////            }
////////////        });
/////////**
//////// * public ArrayList<String[]> match(ArrayList<String[]> strings,int count) throws IOException {
//////// *         String Line;           �����ַ�������
//////// *         Line = bufferedReader.readLine();           ��ȡһ������
//////// *         while (Line != null) {                ����������ݲ��ǿ�ֵ
//////// *             for (int j = 0; j < strings.size(); j++) {          �����������б���
//////// *                 {
//////// *                     temp_string = strings.get(j);                //�õ������������
//////// *                     patt = temp_string[1];                        //��ֵ������ģʽ
//////// *                     Pattern re = Pattern.compile(patt, Pattern.CASE_INSENSITIVE);//������ģʽ���б���
//////// *                     Matcher m = re.matcher(Line);                ����Matcher����
//////// *                     if (m.find()) {
//////// *                         record[0]=String.valueOf(count);
//////// *                         record[1] = temp_string[2];
//////// *                         record[2]=file.getPath();
//////// *                         record[3] = Line.trim();
//////// *                         count++;
//////// *                         flag = true;
//////// *                     }
//////// *                     if (flag) {
//////// *                         return_list.add(record);
//////// *                         flag = false;
//////// *                         break;
//////// *                     }
//////// *                 }
//////// *             }
//////// *             Line = bufferedReader.readLine();
//////// *         }
//////// *         return return_list;
//////// *     }
//////// *
//////// */
////////
////////
////////
//////////  query("update zzcms_looked_dls_number_oneday set looked_dls_number_oneday=looked_dls_number_oneday+".$i." where username='".$username."'");