package com.zhuo.newsmanag.xwxz;

import static com.zhuo.utils.Constant.SCREEN_HEIGHT;
import static com.zhuo.utils.Constant.SCREEN_WIDTH;
import static com.zhuo.utils.Constant.bpicPath;
import static com.zhuo.utils.Constant.C_END;
import static com.zhuo.utils.Constant.C_START;
import static com.zhuo.utils.Constant.dataGeted;
import static com.zhuo.utils.Constant.subtitle;
import static com.zhuo.utils.Constant.jltitle;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sunking.swing.JDatePicker;
import com.zhuo.newsmanag.LoginWindow;
import com.zhuo.pcnews.NewPC;
import com.zhuo.pcnews.NewPC1;
import com.zhuo.pcnews.NewPC2;
import com.zhuo.pcnews.NewPC3;
import com.zhuo.utils.DateChooserJButton;
import com.zhuo.utils.PicUtils;
import com.zhuo.utils.SocketUtil;

public class XWXZPanel extends JPanel {
    private JTextField jtfLine = new JTextField();
    private JLabel jlTitle = new JLabel("新增新闻");
    private JLabel jlXWBT = new JLabel("新闻标题:");
    private JLabel jlSZTP = new JLabel("标题图片:");
    private JLabel jlXWLY = new JLabel("新闻来源:");
    private JLabel jlFBSJ = new JLabel("发布时间:");
    private JLabel jlXWNR = new JLabel("新闻内容:");
    private JLabel jlXWGS = new JLabel("新闻概述:");

    public int BSid = 1;
    private String xwbt;
    private String xwgs;
    private String xwly;
    private String fbsj;
    private String xwnr;
    private String ygid;
    private String picTitelPath;
    private String pic1Path=bpicPath+"pic.jpg";
    private String pic1MS="图片描述";
    private String pic2Path=bpicPath+"pic.jpg";
    private String pic2MS="图片描述";

    Style1Panel sytle1=null;
    Style2Panel sytle2=null;
    Style3Panel sytle3=null;



    private JTextField jtfXWBT = new JTextField(1);// 新闻标题
    private JTextField jtfXWLY = new JTextField(1);// 新闻来源
    private DateChooserJButton dateChooser=new DateChooserJButton();// 发布时间
    private JTextArea jtaXWGS = new JTextArea();
    private JScrollPane jspXWGS = new JScrollPane(jtaXWGS);// 新闻概述
    public JScrollPane jspXWNR = new JScrollPane();
    private JButton jbSave = new JButton("保存草稿");
    private JButton jbSubmit = new JButton("提交审核");
    private JButton jbPic = new JButton("请选择图片");
    private JFileChooser jfc = new JFileChooser();

    public XWXZPanel(String ygid, int BSid) {
        this.ygid = ygid;
        this.BSid = BSid;

        // 新增新闻标题
        this.setLayout(null);
        jlTitle.setBounds(25, 15, 100, 20);
        jlTitle.setFont(subtitle);
        this.add(jlTitle);

        // 保存草稿按钮
        jbSave.setBounds(900, 15, 90, 30);
        jbSave.setOpaque(false);
        this.add(jbSave);

        // 提交审核按钮
        jbSubmit.setBounds(1020, 15, 90, 30);
        jbSubmit.setOpaque(false);
        this.add(jbSubmit);

        // 分割线jtf
        jtfLine.setBounds(20, 60 - 8, 1140, 4);
        jtfLine.setEnabled(false);
        this.add(jtfLine);

        // 新闻标题
        jlXWBT.setBounds(25 + 755, 80, 100, 20);
        jtfXWBT.setBounds(95 + 755, 80, 310, 20);
        jlXWBT.setFont(jltitle);
        this.add(jlXWBT);
        this.add(jtfXWBT);

        // 新闻概述
        jlXWGS.setBounds(780, 110, 100, 20);
        jspXWGS.setBounds(850, 110, 310, 40);
        jtaXWGS.setLineWrap(true);
        jlXWGS.setFont(jltitle);
        this.add(jlXWGS);
        this.add(jspXWGS);

        // 新闻来源
        jlXWLY.setBounds(780, 110 + 50, 100, 20);
        jtfXWLY.setBounds(850, 110 + 50, 150, 20);
        jlXWLY.setFont(jltitle);
        this.add(jlXWLY);
        this.add(jtfXWLY);

        // 发布时间
        jlFBSJ.setBounds(780, 110 + 30 + 50, 100, 20);
        dateChooser.setBounds(850, 110 + 30 + 50, 150, 20);
        dateChooser.setEnabled(false);
        jlFBSJ.setFont(jltitle);
        this.add(jlFBSJ);
        this.add(dateChooser);

        // 设置图片
        jlSZTP.setBounds(780, 110 + 30 + 30 + 50, 100, 20);
        jbPic.setBounds(850, 110 + 30 + 30 + 50, 150, 22);
        jbPic.setOpaque(false);
        jlSZTP.setFont(jltitle);
        this.add(jlSZTP);
        this.add(jbPic);

        // 新闻内容
        jlXWNR.setBounds(25, 60, 100, 20);
        jspXWNR.setBounds(25, 80, 710, 600);
        jspXWNR.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jspXWNR.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jspXWNR.setWheelScrollingEnabled(true);
        jlXWNR.setFont(jltitle);
        this.add(jlXWNR);
        this.add(jspXWNR);

        // 文件选择器
        jfc.removeChoosableFileFilter(jfc.getChoosableFileFilters()[0]);
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("JPG,JEPG图片文件",
                "jpg", "jpeg"));

        addButtonListener();


    }

    // 设置板式
    public void setSytle() {
        if (BSid == 1) {
            if(sytle1==null)
            {
                sytle1=new Style1Panel();
            }
            this.jspXWNR.setViewportView(sytle1);
        } else if (BSid == 2) {
            if(sytle2==null)
            {
                sytle2=new Style2Panel();
            }
            this.jspXWNR.setViewportView(sytle2);
        } else if(BSid==3){
            if(sytle3==null)
            {
                sytle3=new Style3Panel();
            }
            this.jspXWNR.setViewportView(sytle3);
        }
    }


    private void addButtonListener() {
        // 保存为草稿
        jbSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputCheck()) {
                    add_new(0,BSid);// 0未提交审核
                }
            }
        });

        // 提交审核
        jbSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputCheck()) {
                    add_new(1,BSid);// 1提交未审核
                }

            }

        });

        // 设置图片按钮
        jbPic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (picTitelPath == null) {
                    int result = jfc.showOpenDialog(XWXZPanel.this);
                    File pic = jfc.getSelectedFile();
                    if (pic != null && result == JFileChooser.APPROVE_OPTION) {
                        jbPic.setText(pic.getName());
                        picTitelPath = pic.getPath();
                        new JdialogShowPic(picTitelPath);
                        System.out.println(pic.getPath());
                    }
                } else {
                    new JdialogShowPic(picTitelPath);
                }
            }

        });

    }

    class JdialogShowPic extends JDialog implements ActionListener {
        JLabel jlabelTip = new JLabel("提示：请选择宽高比接近5:4的图片！");
        Image img = null;
        JPanel jpPic = new JPanel() {
            public void paint(Graphics g) {
                g.drawImage(img, 0, 0, 400, 320, this);
            };
        };
        JButton jbuttonOK = new JButton("确定");
        JButton jbuttonChange = new JButton("修改");

        public JdialogShowPic(String path) {
            img = this.getToolkit().getImage(path);

            jpPic.setLayout(null);
            this.setLayout(null);
            jlabelTip.setBounds(20, 10, 360, 20);
            this.add(jlabelTip);
            jpPic.setBounds(20, 40, 400, 320);
            this.add(jpPic);
            jbuttonOK.setBounds(20, 370 + 5, 80, 20);
            jbuttonChange.setBounds(120, 370 + 5, 80, 20);
            this.add(jbuttonOK);
            this.add(jbuttonChange);
            this.setSize(445, 440);
            this.setLocation((int) (SCREEN_WIDTH - 230) / 2,
                    (int) (SCREEN_HEIGHT - 400) / 2);
            this.setResizable(false);
            this.setTitle("标题图片预览");
            this.setModal(true);

            jbuttonOK.addActionListener(this);
            jbuttonChange.addActionListener(this);

            this.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbuttonOK) {
                this.dispose();
            } else {
                int result = jfc.showOpenDialog(XWXZPanel.this);
                File pic = jfc.getSelectedFile();
                this.dispose();
                if (pic != null && result == JFileChooser.APPROVE_OPTION) {
                    jbPic.setText(pic.getName());
                    picTitelPath = pic.getPath();
                    new JdialogShowPic(picTitelPath);
                }
            }
        }
    }

    // 输入验证
    private boolean inputCheck() {

        if (jtfXWBT.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(XWXZPanel.this, "新闻标题不能为空！", "提示",
                    JOptionPane.INFORMATION_MESSAGE);
            jtfXWBT.requestFocus();
            return false;
        }
        xwbt = jtfXWBT.getText().trim();

        if (jtaXWGS.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(XWXZPanel.this, "新闻概述不能为空！", "提示",
                    JOptionPane.INFORMATION_MESSAGE);
            jtaXWGS.requestFocus();
            return false;
        }
        xwgs = jtaXWGS.getText().trim();

        if (jtfXWLY.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(XWXZPanel.this, "新闻来源不能为空！", "提示",
                    JOptionPane.INFORMATION_MESSAGE);
            jtfXWLY.requestFocus();
            return false;
        }
        xwly = jtfXWLY.getText().trim();

        fbsj = this.dateChooser.getText();

        // 标题图片路径
        if (picTitelPath == null) {
            JOptionPane.showMessageDialog(XWXZPanel.this, "图片路径不能为空！", "提示",
                    JOptionPane.INFORMATION_MESSAGE);
            jbPic.requestFocus();
            return false;
        }

        if(BSid==1)
        {
            if (sytle1.getContent().trim().length() <= 0) {
                JOptionPane.showMessageDialog(XWXZPanel.this, "新闻内容不能为空！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            xwnr=sytle1.getContent();
            sytle1.clear();

        }else if(BSid==2)
        {
            //pic1
            if(sytle2.getPic1()!=null)
            {
                pic1Path=sytle2.getPic1();
            }else
            {
                JOptionPane.showMessageDialog(XWXZPanel.this, "新闻插图不能为空！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if (sytle2.getPic1Decrition().trim().length() <= 0) {
                JOptionPane.showMessageDialog(XWXZPanel.this, "新闻插图描述不能为空！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            pic1MS=sytle2.getPic1Decrition();
            //content
            if (sytle2.getContent().trim().length() <= 0) {
                JOptionPane.showMessageDialog(XWXZPanel.this, "新闻内容不能为空！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            xwnr=sytle2.getContent();
            sytle2.clear();

        }else if(BSid==3)
        {
            //pic1
            if(sytle3.getPic1()!=null)
            {
                pic1Path=sytle3.getPic1();
            }else
            {
                JOptionPane.showMessageDialog(XWXZPanel.this, "新闻插图不能为空！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if (sytle3.getPic1Decrition().trim().length() <= 0) {
                JOptionPane.showMessageDialog(XWXZPanel.this, "新闻插图描述不能为空！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            pic1MS=sytle3.getPic1Decrition();
            //pic2
            if(sytle3.getPic2()!=null)
            {
                pic2Path=sytle3.getPic2();
            }else
            {
                JOptionPane.showMessageDialog(XWXZPanel.this, "新闻插图不能为空！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if (sytle3.getPic2Decrition().trim().length() <= 0) {
                JOptionPane.showMessageDialog(XWXZPanel.this, "新闻插图描述不能为空！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            pic2MS=sytle3.getPic2Decrition();
            //content
            if (sytle3.getContent().trim().length() <= 0) {
                JOptionPane.showMessageDialog(XWXZPanel.this, "新闻内容不能为空！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            xwnr=sytle3.getContent();
            sytle3.clear();
        }
        return true;
    }

    // 添加新闻,并提交审核（在服务器端把fbztid=0）
    private void add_new(final int ztid,final int bsid) {
        // 任务线程
        dataGeted = false;
        new Thread() {
            public void run() {
                NewPC newpc=null;
                if(bsid==1)
                {
                    //System.out.println("picpaht========"+picTitelPath);
                    newpc=new NewPC1(xwbt,xwgs,xwly,fbsj,xwnr,ygid,ztid,bsid
                            , PicUtils.getBytePic(picTitelPath));
                }else if(bsid==2)
                {
                    newpc=new NewPC2(xwbt,xwgs,xwly,fbsj,xwnr,ygid,ztid,bsid
                            ,PicUtils.getBytePic(picTitelPath),PicUtils.getBytePic(pic1Path),pic1MS);
                }else if(bsid==3)
                {
                    newpc=new NewPC3(xwbt,xwgs,xwly,fbsj,xwnr,ygid,ztid,bsid
                            ,PicUtils.getBytePic(picTitelPath),PicUtils.getBytePic(pic1Path),
                            pic1MS,PicUtils.getBytePic(pic2Path),pic2MS);
                }
                String msg= SocketUtil.sendNewObject(newpc,true,null);
                dataGeted = true;
                if (msg.equals("ok")) {
                    if (ztid == 0) {
                        JOptionPane.showMessageDialog(XWXZPanel.this,
                                "恭喜，保存草稿成功,如需再对草稿进行编辑,请到个人新闻管理界面！", "提示",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(XWXZPanel.this,
                                "恭喜，新闻添加成功,请耐心等待审核！", "提示",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    setDefault();
                }
            }
        }.start();
        // 监视线程
        LoginWindow.watchThread();

    }

    //恢复到默认状态
    public void setDefault()
    {
        jtfXWBT.setText(null);
        jtfXWLY.setText(null);
        jfc.setSelectedFile(null);
        jtaXWGS.setText(null);
        jbPic.setText("请选择图片");
        picTitelPath=null;
        dateChooser.setDate(new Date());
    }



    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // 绘制渐变 起始坐标 起始颜色
        g2.setPaint(new GradientPaint(0, 0, C_START, 0, getHeight(), C_END));
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

}
