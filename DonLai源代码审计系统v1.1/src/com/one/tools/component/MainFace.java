package com.one.tools.component;

import com.one.tools.listener.ActionDoneListener;
import com.one.tools.tool.ReadFileBySplit;
import com.one.tools.tool.ScreenUtils;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author ��ΰ��
 * Belong to DongLaiԴ�������ϵͳ
 * @date 2021-04-17-11:13
 * @description  �������ҳ�����
 */
public class MainFace {


    JFrame ProgramMainPage = new JFrame("DongLaiԴ�������ϵͳ");
    //    �����˵���JBar
    JMenuBar mainMenu = new JMenuBar();
    //    �����˵�����panel
    JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    //    �Ƚ���һ���˵�����
    JMenuItem newProject = new JMenuItem("�½���Ŀ", new ImageIcon("images\\fileopen.jpg"));
    JMenuItem closeProject = new JMenuItem("�ر���Ŀ", new ImageIcon("images\\closefile.png"));
    JMenuItem autoAudit = new JMenuItem("�Զ����", new ImageIcon("images\\auto.png"));
    JMenuItem searchAll = new JMenuItem("ȫ������", new ImageIcon("images\\search.png"));
    JMenu settings = new JMenu("ϵͳ����");
    JMenu about = new JMenu("����ϵͳ");
    //    ���������˵�
    JMenuItem settings_auditRule = new JMenuItem("��ƹ���");
    JMenu settings_coding = new JMenu("����");
    JMenuItem UTF_8 = new JMenuItem("UTF-8");
    JMenuItem GBK = new JMenuItem("GBK");
    JMenuItem editor=new JMenuItem("�༭������");
    JMenuItem about_help = new JMenuItem("ʹ�ð���");
    JMenuItem about_author = new JMenuItem("���߲���");
//    JMenuItem about_soft = new JMenuItem("�������");
    //�Ǻ���Ҫ����ȫ��ʹ�õģ�����ֱ�����������������
    JPanel panelFor_LeftTree = new JPanel();
    JTree fileDirTree = new JTree();
    JTabbedPane right = new JTabbedPane(SwingConstants.NORTH, JTabbedPane.SCROLL_TAB_LAYOUT);
    String stringPath;//��¼���ļ���·��
    ArrayList<String[]> arrayList = new ArrayList<>();
    String Codings = "UTF-8";

    //    ����һ������,��������Ƿ��ļ����Ѿ�����
    private boolean isHaveDirTree = false;
    //    ����һ��������������Զ���Ʊ�ǩ�Ƿ��Ѿ���
    private boolean isHaveAutoAudit = false;

    //    ��װ�����˵�
    public void topMenu_func() {

        /******************************************/
        /*            ����Ĵ�����������ϲ�˵�����   */
        /*            ���JMenuBar JPanel(�����)   */
        /******************************************/
        //        ��һ����װ�����˵�

        settings.add(settings_auditRule);
        settings_coding.add(UTF_8);
        settings_coding.add(GBK);
        settings.add(settings_coding);
        settings.add(editor);
        about.add(about_help);
        about.add(about_author);
//        about.add(about_soft);
        //        ��װ�˵�����δ��ӹ���
        mainMenu.add(newProject);
        mainMenu.add(closeProject);
        mainMenu.add(autoAudit);
        mainMenu.add(searchAll);
        mainMenu.add(settings);
        mainMenu.add(about);
        menuPanel.add(mainMenu);
    }

    //��װ�м�Ĳ���
    public void mid_func() {
        right.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selecttab = right.getSelectedIndex();
                    if (selecttab == 0) {
                        return;
                    } else {
                        right.remove(selecttab);
                    }
                    right.setSelectedIndex(right.getTabCount() - 1);

                }
            }
        });
        Box homePage=Box.createVerticalBox();
        Box boxFordefault_text=Box.createHorizontalBox();
        JLabel default_text = new JLabel("DongLai   Դ�������ϵͳ");
        default_text.setFont(new Font("�����п�",0,80));
        default_text.setForeground(new Color(0,139,69));
        boxFordefault_text.add(Box.createHorizontalGlue());
        boxFordefault_text.add(default_text);
        boxFordefault_text.add(Box.createHorizontalGlue());



        Box boxFordefault_text2=Box.createHorizontalBox();
        JLabel default_text2=new JLabel("һ�����  ȫ������  ��������  ��Ʊ���");
        default_text2.setFont(new Font("�����п�",0,40));
        default_text2.setForeground(new Color(0,139,69));
        boxFordefault_text2.add(Box.createHorizontalGlue());
        boxFordefault_text2.add(default_text2);
        boxFordefault_text2.add(Box.createHorizontalGlue());

        Box boxFordefault_text3=Box.createHorizontalBox();
        JLabel default_text3=new JLabel("����ת��  ���ñ༭��  ������֤  �ļ��༭");
        default_text3.setFont(new Font("�����п�",0,40));
        default_text3.setForeground(new Color(0,139,69));
        boxFordefault_text3.add(Box.createHorizontalGlue());
        boxFordefault_text3.add(default_text3);
        boxFordefault_text3.add(Box.createHorizontalGlue());

        Box boxFordefault_text4=Box.createHorizontalBox();
        JLabel default_text4=new JLabel("��ϵ����:lweichao_csu@163.com");
        default_text4.setFont(new Font("�����п�",0,40));
        default_text4.setForeground(new Color(0,139,69));
        boxFordefault_text4.add(Box.createHorizontalGlue());
        boxFordefault_text4.add(default_text4);
        boxFordefault_text4.add(Box.createHorizontalGlue());



        homePage.add(Box.createVerticalStrut(200));
        homePage.add(boxFordefault_text);
        homePage.add(boxFordefault_text2);
        homePage.add(boxFordefault_text3);
        homePage.add(boxFordefault_text4);
        homePage.add(Box.createVerticalGlue());


        right.setPreferredSize(new Dimension(1000, 40));
        right.addTab("��ҳ", homePage);

    }

    //    �����¼�������
    public void addListenerForTopMenuBar() {
//        �½���Ŀ���¼�������
        newProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isHaveDirTree) {
                    panelFor_LeftTree.remove(fileDirTree);
                }

                SelectNewFlie selectNewFlie = new SelectNewFlie();
                fileDirTree = selectNewFlie.generateTree();
                stringPath = selectNewFlie.getStringPath();
                if (fileDirTree == null) {
                    return;
                } else {
                    panelFor_LeftTree.add(fileDirTree);
                    isHaveDirTree = true;
                    panelFor_LeftTree.updateUI();
//              �½���Ŀ��ʱ��ͽ��ļ�Ŀ¼���ݸ�ȫ����������
//                �½���Ŀ����������¼���������˫���������µı�ǩ�д�����ļ�
                    fileDirTree.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
//                            Ŀǰ������˫���ļ���ʱ������ȥ��ȡ������һ������·��
//                            ���ڻ�ȡ���Ļ���ȱ��һ��·������������ʱ��ȱ��·�����������
                            DefaultMutableTreeNode node = (DefaultMutableTreeNode) fileDirTree.getLastSelectedPathComponent();

                            boolean leaf = node.isLeaf();
                            if (leaf) {
                                TreeNode[] path = node.getPath();
                                String absolutePath="";
                                for (int i=1;i<path.length-1;i++) {

                                    absolutePath=absolutePath+"\\"+path[i]+"\\";


                                }
                                String newPhpFilePath = stringPath + absolutePath +"\\"+ node;
//                                ������ͻ�ȡ��������ڵ�ľ���·�����������ֱ�Ӵ�
                                if (e.getClickCount() == 2) {
                                    NewPhpFilePage newPhpFilePage = new NewPhpFilePage(newPhpFilePath, stringPath, Codings);
//                            ����Ҫ������ӵĻ���Ҫ�������ļ������ݸ���ҳ��
                                    newPhpFilePage.setListener(new addNewTab());

                                    Box boxFor_newPhpFilePage = newPhpFilePage.initPanel();
                                    right.addTab(newPhpFilePath, boxFor_newPhpFilePage);
                                    right.setSelectedIndex(right.getTabCount() - 1);

                                } else {
                                    return;
                                }
                            }

                        }
                    });
                }


            }
        });
//        �ر���Ŀ���¼�������
        closeProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isHaveDirTree == false) {
                    JOptionPane.showMessageDialog(ProgramMainPage, "���ȴ��ļ��ٽ��йر�", "������ʾ", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    panelFor_LeftTree.remove(fileDirTree);
                    isHaveDirTree = false;
                    panelFor_LeftTree.updateUI();
                }

            }

        });
//        �����Զ���ư�ť���¼�������
//        ������Ƕ�����һ�������ɣ�ȫ����������������ȷʵ��Щ����
        autoAudit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isHaveDirTree == false) {
                    JOptionPane.showMessageDialog(ProgramMainPage, "�����½���Ŀ�ٽ����Զ����", "������ʾ", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (isHaveAutoAudit == true) {
                    JOptionPane.showMessageDialog(ProgramMainPage, "�Ѿ����Զ���Ʊ�ǩ�������ظ���", "������ʾ", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
//                ��������Զ���������ť�����Ⱦ�Ӧ����װ����ͼ�������ü�����

                    AutoAuditTab autoAuditTab = new AutoAuditTab(stringPath, arrayList, new addNewTab(), Codings);
                    autoAuditTab.addActionListenerForButton();
                    right.addTab("�Զ����", autoAuditTab.init());
                    right.setSelectedIndex(right.getTabCount() - 1);
                }
            }
        });
//      ȫ���������¼�������

        searchAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stringPath != null) {
                    newSearch_overall_situation newSearch_overall_situation = new newSearch_overall_situation(Codings);
                    newSearch_overall_situation.setStringPath(stringPath);
                    Box box = newSearch_overall_situation.initSearchPage();
                    right.addTab("ȫ������", box);
                    right.setSelectedIndex(right.getTabCount() - 1);
                } else {
                    JOptionPane.showMessageDialog(ProgramMainPage, "���ȴ���Ŀ���ٽ���ȫ������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });
//        ����˵����ü���
        UTF_8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Codings = "UTF-8";
            }
        });
        GBK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Codings = "GBK";
            }
        });
//        ��ƹ������ý�������
        settings_auditRule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                �������������д���룬Ҳ�����½�һ��JAVA�࣬Ȼ�������������ʵ�֣�
//                Ϊ�˱�������̫�������ӣ������½�һ���������������Ҫ�ٶ�д�ļ�
                setAuditRule setAuditRule = new setAuditRule();
                Box box = setAuditRule.init_setAuditRule();
                right.addTab("��������", box);
                right.setSelectedIndex(right.getTabCount() - 1);

            }
        });

//      �༭�����ý���������
        editor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          �½�һ���࣬Ȼ����װ��һ����ͼ��չʾ
//          ������ֱ�ӵ������ã��Ƚϼ�
                String editorPath = JOptionPane.showInputDialog(ProgramMainPage, "��������Ч���ı��༭����·��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                if(editorPath==null){
                    return;
                }
                //              �������ļ���������д
                File editorConfig=new File("config\\EditorPath.txt");
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(editorConfig));
                    out.write(editorPath);
                    out.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
//      ��ʹ�ð���
        about_help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime runtime = Runtime.getRuntime();
                String[] cmd={"C:\\Windows\\notepad.exe","config\\ʹ�ð���.txt"};
                try {
                    runtime.exec(cmd);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
//
        about_author.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String osName = System.getProperty("os.name", "");// ��ȡ����ϵͳ������
                String url="https://blog.csdn.net/weixin_43460070?spm=1010.2135.3001.5343";
                if (osName.startsWith("Windows")) {// windows
                    try {
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if (osName.startsWith("Mac OS")) {// Mac
                    Class fileMgr = null;
                    try {
                        fileMgr = Class.forName("com.apple.eio.FileManager");

                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                    Method openURL = null;
                    try {
                        openURL = fileMgr.getDeclaredMethod("openURL", String.class);
                    } catch (NoSuchMethodException noSuchMethodException) {
                        noSuchMethodException.printStackTrace();
                    }
                    try {
                        openURL.invoke(null, url);
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    } catch (InvocationTargetException invocationTargetException) {
                        invocationTargetException.printStackTrace();
                    }
                } else {// Unix or Linux
                    String[] browsers = {"firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"};
                    String browser = null;
                    for (int count = 0; count < browsers.length && browser == null; count++) { // ִ�д��룬��brower��ֵ��������
                        // ������������̴����ɹ��ˣ�==0�Ǳ�ʾ����������
                        try {
                            if (Runtime.getRuntime().exec(new String[]{"which", browsers[count]}).waitFor() == 0) {
                                browser = browsers[count];
                            }
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }

                    if (browser == null) {
                        throw new RuntimeException("δ�ҵ��κο��õ������");
                    } else {// ���ֵ�������Ѿ��ɹ��ĵõ���һ�����̡�
                        try {
                            Runtime.getRuntime().exec(new String[]{browser, url});
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }

            }
        });

    }

    //  ���������ļ�
    public void loadConfig() throws IOException {
        arrayList = new ReadFileBySplit().SplitFileToList(new File("config\\ruleTest.txt"));

    }

    //  ��д�ӿں���
    class addNewTab implements ActionDoneListener {
        @Override
        public void addPage(String name, Box box) {
            right.add(name, box);
            right.setSelectedIndex(right.getTabCount() - 1);
        }

        @Override
        public void addPage(String name, JPanel panel) {
            right.add(name, panel);
            right.setSelectedIndex(right.getTabCount() - 1);
        }
    }

    //    ��װ��ҳ����ͼ
    public void init() {
        try {
            loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final int frameWide = 1600;
        final int frameHeight = 900;
//        ��װ�����Ĳ˵�
        topMenu_func();
//        �½�һ��ˮƽ���е�Panel�����˵����Ž�ȥ
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(mainMenu);
//        ����һ��ˮƽ�ָ��pane����װ����Ĳ���
        JSplitPane center = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        //    ������
        JPanel leftPanel = new JPanel();
        JLabel TreeTipLable = new JLabel("�ļ��ṹ" + "                            ");

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        TreeTipLable.setIcon(new ImageIcon("images\\filestruct.png"));
        leftPanel.add(TreeTipLable);
        panelFor_LeftTree.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelFor_LeftTree.setBackground(Color.white);
        leftPanel.add(new JScrollPane(panelFor_LeftTree));
        center.setLeftComponent(leftPanel);
//        �Ҳ����չʾ��ҳ���г�ʼ��
        mid_func();
        center.setRightComponent(right);

        center.setDividerLocation(180);
        center.setDividerSize(7);


//        ������װ֮ǰ�����¼��������Ľ���
        addListenerForTopMenuBar();
        ProgramMainPage.add(menuPanel, BorderLayout.NORTH);
        ProgramMainPage.add(center, BorderLayout.CENTER);
        ProgramMainPage.setBounds((ScreenUtils.getScreenWidth() - frameWide) / 2, (ScreenUtils.getScreenHeight() - frameHeight) / 2, frameWide, frameHeight);
        ProgramMainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ProgramMainPage.setVisible(true);

    }


    public static void main(String[] args) {
        MainFace mainFace = new MainFace();
        mainFace.init();
    }
}
