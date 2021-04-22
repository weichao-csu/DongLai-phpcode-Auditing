package com.one.tools.tool;

import javax.swing.*;

public class test {
    private void showLocationLineDialog(){
        JTextArea jTextArea1=new JTextArea();
//取得总行数
        int totalLineCount = jTextArea1.getLineCount();
        if(totalLineCount <= 1){
            return ;
        }
        String title = "跳转至行：(1..."+totalLineCount+")";
        String line = JOptionPane.showInputDialog(this,title);
        if(line==null||"".equals(line.trim())){
            return;
        }
        try {
            int intLine = Integer.parseInt(line);
            if(intLine > totalLineCount){
                return;
            }
//JTextArea起始行号是0，所以此处做减一处理
            int selectionStart = jTextArea1.getLineStartOffset(intLine-1);
            int selectionEnd = jTextArea1.getLineEndOffset(intLine-1);

//如果是不是最后一行，selectionEnd做减一处理，是为了使光标与选中行在同一行
            if(intLine != totalLineCount){
                selectionEnd--;

            }

            jTextArea1.requestFocus(); //获得焦点
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
//                    "$str=\"暂无分类\";\n" +
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
//                    "\t$str=$str.\"<td width='22%' height='25'><strong> 意向产品</strong></td>\";\n" +
//                    "\t$str=$str.\"<td width='10%' align='center' ><strong>联系人</strong></td>\";\n" +
//                    "    $str=$str.\"<td width='10%' align='center' ><strong>意向区域</strong></td>\";\n" +
//                    "    $str=$str.\"<td width='10%' align='center' ><strong>电话</strong></td>\";\n" +
//                    "    $str=$str.\"<td width='28%' align='center' ><strong>详细内容</strong></td>\";\n" +
//                    "    $str=$str.\"<td width='20%' align='center' ><strong>发布日期</strong></td>\";\n" +
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
//                    "\t$str=$str. $row[\"tel\"]; }else {$str=$str.\"<a href='\".getpageurl(\"dl\",$row[\"id\"]).\"' style=\\\"color:red\\\">VIP点击可查看</a>\";}\n" +
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
//                    "\t$str=$str.\"<span style='float:right' title=更新时间>\".date(\"Y-m-d\",strtotime($row[\"sendtime\"])).\"</span>\";\n" +
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
//                    "$str=\"暂无信息\";\n" +
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
////    private JScrollPane scrollPane = null; // 滚动
////
////    private JTextPane text = null; // 不用说了，如果不认识的话就没必要往后看了    声明窗口
////
////    private Box box = null; // 放输入组件的容器    声明组合的容器
////
////    private JButton b_insert = null, b_remove = null, b_icon = null; // 插入按钮;清除按钮;插入图片按钮按钮
////
////    private JTextField addText = null; // 文字输入框文本框
////
////    private JComboBox fontName = null, fontSize = null, fontStyle = null, fontColor = null,
////            fontBackColor = null; // 字体名称;字号大小;文字样式;文字颜色;文字背景颜色
//////    下拉框选择颜色
////
////    private StyledDocument doc = null; // 非常重要插入文字样式就靠它了 //插入文字样式
////
////    public test() {             //构造函数
////        super("JTextPane Test");
////        try { // 使用Windows的界面风格
////            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        text = new JTextPane();
////        text.setEditable(false); // 不可录入
//////        声明TEXT对象,然后doc获取到风格控制
//////        然后对doc进行更新,就会把JTextPane进行随之更新
////
////        doc = text.getStyledDocument(); // 获得JTextPane的Document
////        scrollPane = new JScrollPane(text);
////        addText = new JTextField(18);
////        /**
////         * 几个下拉框
////         */
////        String[] str_name = {"宋体", "黑体", "Dialog", "Gulim"};
////        String[] str_Size = {"12", "14", "18", "22", "30", "40"};
////        String[] str_Style = {"常规", "斜体", "粗体", "粗斜体"};
////        String[] str_Color = {"黑色", "红色", "蓝色", "黄色", "绿色"};
////        String[] str_BackColor = {"无色", "灰色", "淡红", "淡蓝", "淡黄", "淡绿"};
////        fontName = new JComboBox(str_name); // 字体名称
////        fontSize = new JComboBox(str_Size); // 字号
////        fontStyle = new JComboBox(str_Style); // 样式
////        fontColor = new JComboBox(str_Color); // 颜色
////        fontBackColor = new JComboBox(str_BackColor); // 背景颜色
////        b_insert = new JButton("插入"); // 插入
////        b_remove = new JButton("清空"); // 清除
////        b_icon = new JButton("图片"); // 插入图片
////
////        /**
////         * 插入文字的事件监听器
////         */
////        b_insert.addActionListener(new ActionListener() { // 插入文字的事件
////            public void actionPerformed(ActionEvent e) {
////                insert(getFontAttrib());
////                addText.setText("");
////            }
////        });
////        /**
////         * 删除的事件监听器
////         */
////        b_remove.addActionListener(new ActionListener() { // 清除事件
////            public void actionPerformed(ActionEvent e) {
////                text.setText("");
////            }
////        });
////        /**
////         * 插入图片事件监听器
////         */
////        b_icon.addActionListener(new ActionListener() { // 插入图片事件
////            public void actionPerformed(ActionEvent arg0) {
////                JFileChooser f = new JFileChooser(); // 查找文件
////                f.showOpenDialog(null);
////                insertIcon(f.getSelectedFile()); // 插入图片
////            }
////        });
////
////
////        /**
////         * 下面是组装视图
////         */
////        box = Box.createVerticalBox(); // 竖结构
////        Box box_1 = Box.createHorizontalBox(); // 横结构
////        Box box_2 = Box.createHorizontalBox(); // 横结构
////        box.add(box_1);
////        box.add(Box.createVerticalStrut(8)); // 两行的间距
////        box.add(box_2);
////        box.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); // 8个的边距
////        // 开始将所需组件加入容器
////        box_1.add(new JLabel("字体：")); // 加入标签
////        box_1.add(fontName); // 加入组件
////        box_1.add(Box.createHorizontalStrut(8)); // 间距
////        box_1.add(new JLabel("样式："));
////        box_1.add(fontStyle);
////        box_1.add(Box.createHorizontalStrut(8));
////        box_1.add(new JLabel("字号："));
////        box_1.add(fontSize);
////        box_1.add(Box.createHorizontalStrut(8));
////        box_1.add(new JLabel("颜色："));
////        box_1.add(fontColor);
////        box_1.add(Box.createHorizontalStrut(8));
////        box_1.add(new JLabel("背景："));
////        box_1.add(fontBackColor);
////        box_1.add(Box.createHorizontalStrut(8));
////        box_1.add(b_icon);
////        box_2.add(addText);
////        box_2.add(Box.createHorizontalStrut(8));
////        box_2.add(b_insert);
////        box_2.add(Box.createHorizontalStrut(8));
////        box_2.add(b_remove);
////        this.getRootPane().setDefaultButton(b_insert); // 默认回车按钮
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
////     * 插入图片
////     *
////     * @paramicon
////     */
////    private void insertIcon(File file) {
////        text.setCaretPosition(doc.getLength()); // 设置插入位置
////        text.insertIcon(new ImageIcon(file.getPath())); // 插入图片
////        insert(new FontAttrib()); // 这样做可以换行
////    }
////
////    /**
////     * 将文本字符形式的数据插入JTextPane
////     *
////     * @param attrib
////     */
////    private void insert(FontAttrib attrib) {
////        try { // 插入文本
//////            利用doc插入,起始位置,要插入的文本,设置属性的对象,也就是说通过你设置的这个对象的一些值来给这个赋值
//////            doc.insertString(doc.getLength(), );
////            doc.insertString(doc.getLength(), attrib.getText() + "\r\n", attrib.getAttrSet());
////        } catch (BadLocationException e) {
////            e.printStackTrace();
////        }
////    }
////
////    /**
////     * 获取所需要的文字设置
////     *
////     * @return FontAttrib
////     */
////    private FontAttrib getFontAttrib() {
////        FontAttrib att = new FontAttrib();        //声明一个内部类对象
////        att.setText(addText.getText());             //获取文本内容
////        att.setName((String) fontName.getSelectedItem());    //得到选中对象的名字
//////        att.setSize(Integer.parseInt((String) fontSize.getSelectedItem()));       //得到选中对象的大小
//////        String temp_style = (String) fontStyle.getSelectedItem();       //得到选中对象的字体风格
//////        设置字体风格
//////        if (temp_style.equals("常规")) {
//////            att.setStyle(FontAttrib.GENERAL);
//////        } else if (temp_style.equals("粗体")) {
//////            att.setStyle(FontAttrib.BOLD);
//////        } else if (temp_style.equals("斜体")) {
//////            att.setStyle(FontAttrib.ITALIC);
//////        } else if (temp_style.equals("粗斜体")) {
//////            att.setStyle(FontAttrib.BOLD_ITALIC);
//////        }
//////       得到颜色·
////        String temp_color = (String) fontColor.getSelectedItem();
//////        设置颜色
////        if (temp_color.equals("黑色")) {
////            att.setColor(new Color(0, 0, 0));
////        } else if (temp_color.equals("红色")) {
////            att.setColor(new Color(255, 0, 0));
////        } else if (temp_color.equals("蓝色")) {
////            att.setColor(new Color(0, 0, 255));
////        } else if (temp_color.equals("黄色")) {
////            att.setColor(new Color(255, 255, 0));
////        } else if (temp_color.equals("绿色")) {
////            att.setColor(new Color(0, 255, 0));
////        }
////////        得到背景色
//////        String temp_backColor = (String) fontBackColor.getSelectedItem();
//////        设置背景色
//////        if (!temp_backColor.equals("无色")) {
//////            if (temp_backColor.equals("灰色")) {
//////                att.setBackColor(new Color(200, 200, 200));
//////            } else if (temp_backColor.equals("淡红")) {
//////                att.setBackColor(new Color(255, 200, 200));
//////            } else if (temp_backColor.equals("淡蓝")) {
//////                att.setBackColor(new Color(200, 200, 255));
//////            } else if (temp_backColor.equals("淡黄")) {
//////                att.setBackColor(new Color(255, 255, 200));
//////            } else if (temp_backColor.equals("淡绿")) {
//////                att.setBackColor(new Color(200, 255, 200));
//////            }
//////        }
//////        返回这个文本的对象
////        return att;
////    }
////
////    public static void main(String args[]) {
////        new test();
////    }
////
////    /**
////     * 字体的属性类
////     */
////    private class FontAttrib {
////        public static final int GENERAL = 0; // 常规
////
////        public static final int BOLD = 1; // 粗体
////
////        public static final int ITALIC = 2; // 斜体
////
////        public static final int BOLD_ITALIC = 3; // 粗斜体
////
////        private SimpleAttributeSet attrSet = null; // 属性集
////
////        private String text = null, name = null; // 要输入的文本和字体名称
////
////        private int style = 0, size = 0; // 样式和字号
////
////        private Color color = null, backColor = null; // 文字颜色和背景颜色
////
////        /**
////         * 一个空的构造（可当做换行使用）
////         */
////        public FontAttrib() {
////        }
////
////        /**
////         * 返回属性集
////         *
////         * @return
////         */
////        public SimpleAttributeSet getAttrSet() {
////            attrSet = new SimpleAttributeSet();
//////            声明一个设置属性的类
////
////            StyleConstants.setFontFamily(attrSet, "宋体");
////
////            StyleConstants.setForeground(attrSet, new Color(0, 0, 0));
////            return attrSet;
////
////////            下面就是设置文字风格,不用去管
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
//////            下面是设置颜色
////
//////            下面是设置背景色
//////            if (backColor != null)
//////                StyleConstants.setBackground(attrSet, backColor);
////
////        }
////        /**
////         * 设置属性集
////         *
////         * @param attrSet
////         */
////        public void setAttrSet(SimpleAttributeSet attrSet) {
////            this.attrSet = attrSet;
////        }
////
////        /* 后面的注释就不写了，一看就明白 */
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
//////////双$$符号可能存在变量覆盖漏洞
//////public class test extends Thread{
//////
//////        JProgressBar progressBar;
//////        JButton button;
//////        //进度条上的数字
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
//////                //设置进度条的值
//////                progressBar.setValue(progressValues[i]);
//////            }
//////            progressBar.setIndeterminate(false);
//////            progressBar.setString("升级完成！");
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
//////////        // 模拟数据
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
//////////        // 231498耗时 3线程(本机2核4线程)
//////////        // 278592耗时 2线程
//////////        // 397115耗时 单线程
//////////        // 245657耗时 cpu线程数（4线程）
//////////        public static void main(String[] args) throws Exception {
//////////            long beginTime = System.currentTimeMillis();
//////////            test helper = new test();
//////////            helper.read("C:\\Users\\lianghaohui\\Desktop\\test.txt", Runtime.getRuntime().availableProcessors(), '\n', new StringCallback("UTF-8") {
//////////                @Override
//////////                void callback(String data) {
//////////                    int count = atomicInteger.incrementAndGet();
//////////                    System.out.println(count);
//////////                    if (count == 1000000) {
//////////                        System.out.println("总耗时毫秒：" + (System.currentTimeMillis() - beginTime));
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
//////////            System.out.println("开始工作" + Thread.currentThread().getName());
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
////////////    //    这样的相对路径就能读取到文件
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
//////////    //遍历某目录下的所有目录
//////////    public static void listDirectoryFile(File dir) throws IOException{
//////////
//////////        if(!dir.exists()){//dir不存在，抛出异常
//////////            throw new IllegalArgumentException("目录"+dir+"不存在");
//////////        }
//////////        if(!dir.isDirectory()){//dir不是目录抛出异常
//////////            throw new IllegalArgumentException(dir+"不是目录");
//////////        }
//////////        File[] files = dir.listFiles();
//////////        if(files!=null&&files.length>0){//如果有下级目录
//////////            for(File file : files){
//////////                if(!file.isDirectory()){//如果不是目录则直接打印出来
//////////                    System.out.println(file.getAbsolutePath());
//////////                }else{//如果是目录，递归调用本方法
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
//////////// * @author 乐伟超
//////////// * Belong to DongLai源代码审计系统
//////////// * @date 2021-04-10-15:34
//////////// * @description 对给出的目录文件对象进行遍历，
//////////// * @return 绝对路径的文件名数组
//////////// */
////////////public class test {
////////////    static File filePathTraverse;
////////////
////////////    /**
////////////     *构造函数的输入就是代遍历的文件目录的字符串形式
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
////////////        File fc=new File("D:\\渗透测试常见插件和工具\\代码审计\\zzcms201910\\zzcms201910");
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
//////////////      构造函数
////////////    test() {
////////////        setSize(300, 250);
////////////        setVisible(true);
////////////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////////////        Container con = getContentPane();
////////////        DefaultMutableTreeNode root = new DefaultMutableTreeNode("根结点");
////////////        DefaultMutableTreeNode t1 = new DefaultMutableTreeNode("结点");
////////////        DefaultMutableTreeNode t2 = new DefaultMutableTreeNode("结点");
////////////        DefaultMutableTreeNode t1_1 = new DefaultMutableTreeNode("叶结点");
////////////        DefaultMutableTreeNode t1_2 = new DefaultMutableTreeNode("叶结点");
////////////        root.add(t1);
////////////        root.add(t2);
////////////        t1.add(t1_1);
////////////        t2.add(t1_2);
////////////        tree = new JTree(root); //创建根为root的树
////////////        tree.addTreeSelectionListener(this);//给整个树加选择监听
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
////////////        if (selectionNode.isLeaf()) { //叶子节点的监听
////////////            System.out.println(selectionNode.toString());
////////////        }
////////////    }
////////////}
//////////
//////////
////////////        newProject.addActionListener(new ActionListener() {
////////////            @Override
////////////            public void actionPerformed(ActionEvent e) {
//////////////                FileDialog fileDialog = new FileDialog(mainFrame, "选择要打开的文件", FileDialog.LOAD);
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
//////// *         String Line;           声明字符串对象
//////// *         Line = bufferedReader.readLine();           读取一行数据
//////// *         while (Line != null) {                如果这行数据不是空值
//////// *             for (int j = 0; j < strings.size(); j++) {          对正则规则进行遍历
//////// *                 {
//////// *                     temp_string = strings.get(j);                //得到正则规则数组
//////// *                     patt = temp_string[1];                        //赋值给正则模式
//////// *                     Pattern re = Pattern.compile(patt, Pattern.CASE_INSENSITIVE);//对正则模式进行编译
//////// *                     Matcher m = re.matcher(Line);                建立Matcher对象
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