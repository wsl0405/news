package com.zhuo.newsmanag.lmgl;

import com.zhuo.newsmanag.MainJFrame;
import com.zhuo.newsmanag.xwxz.Style1Panel;
import com.zhuo.newsmanag.xwxz.Style2Panel;
import com.zhuo.newsmanag.xwxz.Style3Panel;
import com.zhuo.pcnews.PicObject;
import com.zhuo.utils.DateChooserJButton;
import com.zhuo.utils.PicUtils;
import com.zhuo.utils.SocketUtil;

import static com.zhuo.utils.Constant.C_END;
import static com.zhuo.utils.Constant.C_START;
import static com.zhuo.utils.Constant.GET_NEW_By_XWID;
import static com.zhuo.utils.Constant.GET_PIC;
import static com.zhuo.utils.Constant.SCREEN_HEIGHT;
import static com.zhuo.utils.Constant.SCREEN_WIDTH;
import static com.zhuo.utils.Constant.bpicPath;
import static com.zhuo.utils.Constant.subtitle;
import static com.zhuo.utils.Constant.jltitle;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class XWCKPanel extends JPanel
{

    private JTextField jtfLine = new JTextField();
    private JLabel jlTitle = new JLabel("查看新闻");
    private JLabel jlXWBT = new JLabel("新闻标题:");
    private JLabel jlSZTP = new JLabel("标题图片:");
    private JLabel jlXWLY = new JLabel("新闻来源:");
    private JLabel jlFBSJ = new JLabel("发布时间:");
    private JLabel jlXWNR = new JLabel("新闻内容:");
    private JLabel jlXWGS = new JLabel("新闻概述:");

    Style1Panel sytle1 = null;
    Style2Panel sytle2 = null;
    Style3Panel sytle3 = null;

    private JTextField jtfXWBT = new JTextField(1);// 新闻标题
    private JTextField jtfXWLY = new JTextField(1);// 新闻来源
    private DateChooserJButton dateChooser = new DateChooserJButton();// 发布时间
    private JTextArea jtaXWGS = new JTextArea();
    private JScrollPane jspXWGS = new JScrollPane(jtaXWGS);// 新闻概述
    public JScrollPane jspXWNR = new JScrollPane();
    private JButton jbPic = new JButton("查看标题图片");



    private JButton jbBack = new JButton("返回");

    String shid;
    int bsid;
    String xwnr;
    String xwid;
    String[] data;
    MainJFrame mf;
    public boolean backlm=true;

    byte[] picTitle;
    byte[] pic1;
    byte[] pic2;
    String pic1MS;
    String pic2MS;

    public XWCKPanel(MainJFrame mf) {
        this.mf = mf;

        // 新增新闻标题
        this.setLayout(null);
        jlTitle.setBounds(25, 15, 100, 20);
        jlTitle.setFont(subtitle);
        this.add(jlTitle);

        // 返回按钮
        jbBack.setBounds(1020, 15, 90, 30);
        jbBack.setOpaque(false);
        this.add(jbBack);

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

        addButtonListener();
    }



    private void addButtonListener() {

        // 返回
        jbBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(backlm)
                {
                    mf.gotoBackLMGL();
                }else
                {
                    mf.gotoBackFBXWCK();
                }
            }

        });


        // 设置图片按钮
        jbPic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JdialogShowPic(PicUtils.bytesToImage(picTitle));
            }

        });
    }


    //获得新闻
    public void getNewById(String xwid)
    {
        //发送消息获得数据
        String msg = GET_NEW_By_XWID;
        StringBuilder sb = new StringBuilder();
        sb.append(msg);
        sb.append(xwid);
        sb.append(msg);
        String result = SocketUtil.sendAndGetMsg(sb.toString());
        final List<String[]> list = SocketUtil.strToList(result);
        data=list.get(0);
        //xw.xwbt, xw.xwgs, xw.xwly, xw.fbsj, xw.xwnr, xw.ztid ,xw.bsid
    }

    //初始化新闻修改界面
    public void flushData(String xwid)
    {
        this.xwid=xwid;
        getNewById(xwid);
        jtfXWBT.setText(data[0]);
        jtaXWGS.setText(data[1]);
        jtfXWLY.setText(data[2]);
        this.dateChooser.setText(data[3]);
        xwnr = data[4];
        //ztid=data[5];
        bsid = Integer.parseInt(data[6]);
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    if (bsid == 1) {
                        if (sytle1 == null) {
                            sytle1 = new Style1Panel();
                        }
                        sytle1.flushContent(xwnr);
                        jspXWNR.setViewportView(sytle1);
                    } else if (bsid == 2) {
                        if (sytle2 == null) {
                            sytle2 = new Style2Panel();
                            sytle2.isListened(false);
                        }
                        sytle2.flushContent(xwnr);
                        jspXWNR.setViewportView(sytle2);
                    } else if (bsid == 3) {
                        if (sytle3 == null) {
                            sytle3 = new Style3Panel();
                            sytle3.isListened(false);
                        }
                        sytle3.flushContent(xwnr);
                        jspXWNR.setViewportView(sytle3);

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //获得图片的相关方法
    /*
     * 根据板式信息启动不同个数的线程开始获得图片数据，并且更新显示
     */
    public void flushPics()
    {
        new Thread()
        {
            public void run() {
                flushDataPic(0);
            }
        }.start();

        if(bsid==2)
        {
            new Thread()
            {
                public void run() {
                    flushDataPic(1);
                }
            }.start();

        }else if(bsid==3)
        {
            new Thread()
            {
                public void run() {
                    flushDataPic(1);
                }
            }.start();
            new Thread()
            {
                public void run() {
                    flushDataPic(2);
                }
            }.start();
        }
    }


    /*
     * 获得指定图片，并更新此图片的显示（通用方法）
     * 1.要判断图片类型
     * 2.要判断板式
     */
    public void flushDataPic(final int picLX)
    {
        getPic(xwid,picLX);
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run()
                {
                    if(picLX==1)
                    {
                        if(bsid==2)
                        {
                            sytle2.flushPic1(PicUtils.bytesToImage(pic1), pic1MS);
                        }else if(bsid==3)
                        {
                            sytle3.flushPic1(PicUtils.bytesToImage(pic1), pic1MS);
                        }
                    }else if(picLX==2)
                    {
                        sytle3.flushPic2(PicUtils.bytesToImage(pic2), pic2MS);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //获得指定图片信息
    /*
     * 获得指定图片，并把图片相关数据保存到了成员变量中
     */
    public void getPic(String xwid,int picLX) {
        // 发送消息获得数据
        String msg = GET_PIC;
        StringBuilder sb = new StringBuilder();
        sb.append(msg);
        sb.append(xwid+"<->");
        sb.append(picLX);
        sb.append(msg);
        PicObject pico=SocketUtil.sendAndGetPic(sb.toString());
        if(picLX==0)
        {
            this.picTitle=pico.pic;
        }else if(picLX==1)
        {
            this.pic1=pico.pic;
            this.pic1MS=pico.picMs;

        }else if(picLX==2)
        {
            this.pic2=pico.pic;
            this.pic2MS=pico.picMs;
        }
    }





    //显示标题图片的JDialog（每次new新的出来）
    class JdialogShowPic extends JDialog implements ActionListener {
        JLabel jlabelTip = new JLabel("提示：请选择宽高比接近5:4的图片！");
        Image img = new ImageIcon(bpicPath+"pic.jpg").getImage();
        JPanel jpPic = new JPanel() {
            public void paint(Graphics g) {
                g.drawImage(img, 0, 0, 400, 320, this);
            };
        };
        JButton jbuttonOK = new JButton("确定");

        public JdialogShowPic(Image img) {
            if(img!=null)
            {
                this.img=img;
            }
            jpPic.setLayout(null);
            this.setLayout(null);
            jlabelTip.setBounds(20, 10, 360, 20);
            this.add(jlabelTip);
            jpPic.setBounds(20, 40, 400, 320);
            this.add(jpPic);
            jbuttonOK.setBounds(20, 370 + 5, 80, 20);
            this.add(jbuttonOK);
            this.setSize(445, 440);
            this.setLocation((int) (SCREEN_WIDTH - 230) / 2,
                    (int) (SCREEN_HEIGHT - 400) / 2);
            this.setResizable(false);
            this.setTitle("标题图片预览");
            this.setModal(true);
            jbuttonOK.addActionListener(this);
            this.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            this.dispose();
        }

    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // 绘制渐变 起始坐标 起始颜色
        g2.setPaint(new GradientPaint(0, 0, C_START, 0, getHeight(), C_END));
        g2.fillRect(0, 0, getWidth(), getHeight());
    }




}
