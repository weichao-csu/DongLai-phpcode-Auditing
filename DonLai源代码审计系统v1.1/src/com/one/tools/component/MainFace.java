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
 * @author 乐伟超
 * Belong to DongLai源代码审计系统
 * @date 2021-04-17-11:13
 * @description  程序的主页面入口
 */
public class MainFace {


    JFrame ProgramMainPage = new JFrame("DongLai源代码审计系统");
    //    声明菜单栏JBar
    JMenuBar mainMenu = new JMenuBar();
    //    声明菜单栏的panel
    JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    //    先建立一级菜单就行
    JMenuItem newProject = new JMenuItem("新建项目", new ImageIcon("images\\fileopen.jpg"));
    JMenuItem closeProject = new JMenuItem("关闭项目", new ImageIcon("images\\closefile.png"));
    JMenuItem autoAudit = new JMenuItem("自动审计", new ImageIcon("images\\auto.png"));
    JMenuItem searchAll = new JMenuItem("全局搜索", new ImageIcon("images\\search.png"));
    JMenu settings = new JMenu("系统设置");
    JMenu about = new JMenu("关于系统");
    //    建立二级菜单
    JMenuItem settings_auditRule = new JMenuItem("审计规则");
    JMenu settings_coding = new JMenu("编码");
    JMenuItem UTF_8 = new JMenuItem("UTF-8");
    JMenuItem GBK = new JMenuItem("GBK");
    JMenuItem editor=new JMenuItem("编辑器配置");
    JMenuItem about_help = new JMenuItem("使用帮助");
    JMenuItem about_author = new JMenuItem("作者博客");
//    JMenuItem about_soft = new JMenuItem("关于软件");
    //是后面要进行全局使用的，所以直接声明在这个类里面
    JPanel panelFor_LeftTree = new JPanel();
    JTree fileDirTree = new JTree();
    JTabbedPane right = new JTabbedPane(SwingConstants.NORTH, JTabbedPane.SCROLL_TAB_LAYOUT);
    String stringPath;//记录打开文件的路径
    ArrayList<String[]> arrayList = new ArrayList<>();
    String Codings = "UTF-8";

    //    声明一个变量,用来标记是否文件树已经生成
    private boolean isHaveDirTree = false;
    //    声明一个变量用来标记自动审计标签是否已经打开
    private boolean isHaveAutoAudit = false;

    //    组装顶部菜单
    public void topMenu_func() {

        /******************************************/
        /*            下面的代码是完成最上侧菜单布局   */
        /*            组件JMenuBar JPanel(左对齐)   */
        /******************************************/
        //        第一步组装二级菜单

        settings.add(settings_auditRule);
        settings_coding.add(UTF_8);
        settings_coding.add(GBK);
        settings.add(settings_coding);
        settings.add(editor);
        about.add(about_help);
        about.add(about_author);
//        about.add(about_soft);
        //        组装菜单栏，未添加功能
        mainMenu.add(newProject);
        mainMenu.add(closeProject);
        mainMenu.add(autoAudit);
        mainMenu.add(searchAll);
        mainMenu.add(settings);
        mainMenu.add(about);
        menuPanel.add(mainMenu);
    }

    //组装中间的部分
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
        JLabel default_text = new JLabel("DongLai   源代码审计系统");
        default_text.setFont(new Font("华文行楷",0,80));
        default_text.setForeground(new Color(0,139,69));
        boxFordefault_text.add(Box.createHorizontalGlue());
        boxFordefault_text.add(default_text);
        boxFordefault_text.add(Box.createHorizontalGlue());



        Box boxFordefault_text2=Box.createHorizontalBox();
        JLabel default_text2=new JLabel("一键审计  全局搜索  规则配置  审计报告");
        default_text2.setFont(new Font("华文行楷",0,40));
        default_text2.setForeground(new Color(0,139,69));
        boxFordefault_text2.add(Box.createHorizontalGlue());
        boxFordefault_text2.add(default_text2);
        boxFordefault_text2.add(Box.createHorizontalGlue());

        Box boxFordefault_text3=Box.createHorizontalBox();
        JLabel default_text3=new JLabel("编码转换  配置编辑器  规则验证  文件编辑");
        default_text3.setFont(new Font("华文行楷",0,40));
        default_text3.setForeground(new Color(0,139,69));
        boxFordefault_text3.add(Box.createHorizontalGlue());
        boxFordefault_text3.add(default_text3);
        boxFordefault_text3.add(Box.createHorizontalGlue());

        Box boxFordefault_text4=Box.createHorizontalBox();
        JLabel default_text4=new JLabel("联系邮箱:lweichao_csu@163.com");
        default_text4.setFont(new Font("华文行楷",0,40));
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
        right.addTab("首页", homePage);

    }

    //    建立事件监听器
    public void addListenerForTopMenuBar() {
//        新建项目的事件监听器
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
//              新建项目的时候就将文件目录传递给全局搜索部分
//                新建项目后给树建立事件监听器，双击即可在新的标签中打开这个文件
                    fileDirTree.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
//                            目前问题是双击文件的时候，怎样去获取到他的一个绝对路径
//                            现在获取到的还是缺了一级路径，或许更深的时候，缺的路径级数会更多
                            DefaultMutableTreeNode node = (DefaultMutableTreeNode) fileDirTree.getLastSelectedPathComponent();

                            boolean leaf = node.isLeaf();
                            if (leaf) {
                                TreeNode[] path = node.getPath();
                                String absolutePath="";
                                for (int i=1;i<path.length-1;i++) {

                                    absolutePath=absolutePath+"\\"+path[i]+"\\";


                                }
                                String newPhpFilePath = stringPath + absolutePath +"\\"+ node;
//                                到上面就获取到了这个节点的绝对路径，后面就能直接打开
                                if (e.getClickCount() == 2) {
                                    NewPhpFilePage newPhpFilePage = new NewPhpFilePage(newPhpFilePath, stringPath, Codings);
//                            这里要进行添加的话就要把这个类的监听传递给新页面
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
//        关闭项目的事件监听器
        closeProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isHaveDirTree == false) {
                    JOptionPane.showMessageDialog(ProgramMainPage, "请先打开文件再进行关闭", "错误提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    panelFor_LeftTree.remove(fileDirTree);
                    isHaveDirTree = false;
                    panelFor_LeftTree.updateUI();
                }

            }

        });
//        建立自动审计按钮的事件监听器
//        这个还是独立出一个类来吧，全都放在这个组件里面确实有些冗杂
        autoAudit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isHaveDirTree == false) {
                    JOptionPane.showMessageDialog(ProgramMainPage, "请先新建项目再进行自动审计", "错误提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (isHaveAutoAudit == true) {
                    JOptionPane.showMessageDialog(ProgramMainPage, "已经打开自动审计标签，不能重复打开", "错误提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
//                当点击了自动审计这个按钮，首先就应该组装好视图，建立好监听器

                    AutoAuditTab autoAuditTab = new AutoAuditTab(stringPath, arrayList, new addNewTab(), Codings);
                    autoAuditTab.addActionListenerForButton();
                    right.addTab("自动审计", autoAuditTab.init());
                    right.setSelectedIndex(right.getTabCount() - 1);
                }
            }
        });
//      全局搜索的事件监听器

        searchAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stringPath != null) {
                    newSearch_overall_situation newSearch_overall_situation = new newSearch_overall_situation(Codings);
                    newSearch_overall_situation.setStringPath(stringPath);
                    Box box = newSearch_overall_situation.initSearchPage();
                    right.addTab("全局搜索", box);
                    right.setSelectedIndex(right.getTabCount() - 1);
                } else {
                    JOptionPane.showMessageDialog(ProgramMainPage, "请先打开项目，再进行全局搜索", "提示", JOptionPane.INFORMATION_MESSAGE);
                }


            }
        });
//        编码菜单设置监听
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
//        审计规则设置建立监听
        settings_auditRule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                可以在这个里面写代码，也可以新建一个JAVA类，然后声明类对象来实现，
//                为了避免主类太过于冗杂，可以新建一个类出来，后续还要再读写文件
                setAuditRule setAuditRule = new setAuditRule();
                Box box = setAuditRule.init_setAuditRule();
                right.addTab("规则设置", box);
                right.setSelectedIndex(right.getTabCount() - 1);

            }
        });

//      编辑器设置建立监听器
        editor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//          新建一个类，然后组装好一个视图，展示
//          或者是直接弹窗设置，比较简单
                String editorPath = JOptionPane.showInputDialog(ProgramMainPage, "请输入有效的文本编辑器的路径", "提示", JOptionPane.INFORMATION_MESSAGE);
                if(editorPath==null){
                    return;
                }
                //              打开配置文件，进行重写
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
//      打开使用帮助
        about_help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime runtime = Runtime.getRuntime();
                String[] cmd={"C:\\Windows\\notepad.exe","config\\使用帮助.txt"};
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
                String osName = System.getProperty("os.name", "");// 获取操作系统的名字
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
                    for (int count = 0; count < browsers.length && browser == null; count++) { // 执行代码，在brower有值后跳出，
                        // 这里是如果进程创建成功了，==0是表示正常结束。
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
                        throw new RuntimeException("未找到任何可用的浏览器");
                    } else {// 这个值在上面已经成功的得到了一个进程。
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

    //  加载配置文件
    public void loadConfig() throws IOException {
        arrayList = new ReadFileBySplit().SplitFileToList(new File("config\\ruleTest.txt"));

    }

    //  重写接口函数
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

    //    组装主页面视图
    public void init() {
        try {
            loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final int frameWide = 1600;
        final int frameHeight = 900;
//        组装顶部的菜单
        topMenu_func();
//        新建一个水平排列的Panel，将菜单栏放进去
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.add(mainMenu);
//        创建一个水平分割的pane，组装下面的部分
        JSplitPane center = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        //    左侧面板
        JPanel leftPanel = new JPanel();
        JLabel TreeTipLable = new JLabel("文件结构" + "                            ");

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        TreeTipLable.setIcon(new ImageIcon("images\\filestruct.png"));
        leftPanel.add(TreeTipLable);
        panelFor_LeftTree.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelFor_LeftTree.setBackground(Color.white);
        leftPanel.add(new JScrollPane(panelFor_LeftTree));
        center.setLeftComponent(leftPanel);
//        右侧面板展示首页进行初始化
        mid_func();
        center.setRightComponent(right);

        center.setDividerLocation(180);
        center.setDividerSize(7);


//        最终组装之前进行事件监听器的建立
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
