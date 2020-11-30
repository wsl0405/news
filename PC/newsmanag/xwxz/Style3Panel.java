package com.zhuo.newsmanag.xwxz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import static com.zhuo.utils.Constant.SCREEN_HEIGHT;
import static com.zhuo.utils.Constant.SCREEN_WIDTH;
import static com.zhuo.utils.Constant.bpicPath;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Style3Panel extends JPanel
{
    public JLabel jlPic1=new JLabel(new ImageIcon(bpicPath+"pic.jpg"), JLabel.CENTER);
    //public JLabel jlPic1=new JLabel(new ImageIcon(bpicPath+"addPic.png"), JLabel.CENTER);
    public JLabel jlPic1Describtion=new JLabel("此处为图片描述！",JLabel.CENTER);

    public JLabel jlPic2=new JLabel(new ImageIcon(bpicPath+"pic.jpg"), JLabel.CENTER);
    public JLabel jlPic2Describtion=new JLabel("此处为图片描述！",JLabel.CENTER);

    public JLabel jlabelContent = new JLabel("以下录入新闻正文！", new ImageIcon(
            bpicPath + "text.png"), JLabel.CENTER);
    public JTextArea jtaContent=new JTextArea();
    public JScrollPane jsp=new JScrollPane(jtaContent);
    private JFileChooser jfc=new JFileChooser();

    private String pic1=bpicPath+"pic.jpg";
    String pic1PathTemp=null;
    private String pic1Description=null;

    private String pic2=bpicPath+"pic.jpg";
    String pic2PathTemp=null;
    private String pic2Description=null;

    private String content=null;
    //private String patternStr="\\n";
    private boolean pic1Changed=false;
    private boolean pic2Changed=false;

    MouseAdapter mouseAdapter1=null;
    MouseAdapter mouseAdapter2=null;



    public Style3Panel()
    {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(710,1750));
        this.setBackground(Color.WHITE);
        jlPic1.setBounds(155,10 , 400, 320);
        this.add(jlPic1);
        jlPic1Describtion.setBounds(0, 330, 710, 20);
        this.add(jlPic1Describtion);

        jlPic2.setBounds(155,340+15 , 400, 320);
        this.add(jlPic2);
        jlPic2Describtion.setBounds(0, 660+15, 710, 20);
        this.add(jlPic2Describtion);

        jlabelContent.setBounds(0, 710, 710, 40);
        this.add(jlabelContent);
        jtaContent.setLineWrap(true);
        jsp.setBounds(0,750, 690, 1000);
        jsp.setWheelScrollingEnabled(true);
        this.add(jsp);


        //文件选择器
        jfc.removeChoosableFileFilter(jfc.getChoosableFileFilters()[0]);
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("JPG,JEPG图片文件","jpg","jpeg"));

        jlPic1.addMouseListener(mouseAdapter1=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(e.getClickCount()==2)
                {
                    if(pic1PathTemp==null)
                    {
                        int result=jfc.showOpenDialog(null);
                        File pic=jfc.getSelectedFile();
                        if(pic!=null&&result==JFileChooser.APPROVE_OPTION )
                        {
                            pic1PathTemp=pic.getPath();
                            new JdialogShowPic1(pic1PathTemp,pic1Description);
                            System.out.println(pic.getPath());
                        }
                    }else
                    {
                        new JdialogShowPic1(pic1,pic1Description);
                    }
                }
            }
        });

        jlPic2.addMouseListener(mouseAdapter2=new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(e.getClickCount()==2)
                {
                    if(pic2PathTemp==null)
                    {
                        int result=jfc.showOpenDialog(null);
                        File pic=jfc.getSelectedFile();
                        if(pic!=null&&result==JFileChooser.APPROVE_OPTION )
                        {
                            pic2PathTemp=pic.getPath();
                            new JdialogShowPic2(pic2PathTemp,pic2Description);
                            System.out.println(pic.getPath());
                        }
                    }else
                    {
                        new JdialogShowPic2(pic2,pic2Description);
                    }
                }
            }
        });
    }

    public String getContent()
    {
        content=jtaContent.getText();
        return content;
    }
    public String getPic1()
    {
        if(pic1Changed==true)
        {
            return pic1;
        }else
        {
            return null;
        }
    }

    public String getPic1Decrition()
    {
        pic1Description=jlPic1Describtion.getText();
        return pic1Description;
    }
    public String getPic2()
    {
        if(pic2Changed==true)
        {
            return pic2;
        }else
        {
            return null;
        }
    }

    public String getPic2Decrition()
    {
        pic2Description=jlPic2Describtion.getText();
        return pic2Description;
    }



    //接受了数据之后，更新各控件显示的方法
    public void flushContent(String content)
    {
        jtaContent.setText(content);
        this.content=content;
    }
    public void flushPic1(Image pic1,String pic1Des)
    {
        jlPic1.setIcon(new ImageIcon(pic1));
        jlPic1Describtion.setText(pic1Des);
    }
    public void flushPic2(Image pic2,String pic2Des)
    {
        jlPic2.setIcon(new ImageIcon(pic2));
        jlPic2Describtion.setText(pic2Des);
    }




    public void clear()
    {
        pic1=bpicPath+"pic.jpg";
        jlPic1.setIcon(new ImageIcon(pic1));
        String pic1PathTemp=null;
        String pic1Description=null;
        jlPic1Describtion.setText(pic1Description);

        pic2=bpicPath+"pic.jpg";
        jlPic2.setIcon(new ImageIcon(pic2));
        String pic2PathTemp=null;
        String pic2Description=null;
        jlPic2Describtion.setText(pic2Description);
        content=null;
        jtaContent.setText(content);

        pic1PathTemp=null;
        pic2PathTemp=null;

        pic1Changed=false;
        pic2Changed=false;

    }


    //添加监听，和去除监听
    public void isListened(boolean flag)
    {
        if(!flag)
        {
            jlPic1.removeMouseListener(mouseAdapter1);
            jlPic2.removeMouseListener(mouseAdapter2);

        }else
        {
            jlPic1.addMouseListener(mouseAdapter1);
            jlPic2.addMouseListener(mouseAdapter2);
        }
    }




    class JdialogShowPic1 extends JDialog implements ActionListener
    {
        JLabel jlabelTip=new JLabel("提示：请选择宽高比接近5:4的图片！");
        Image img=null;
        JPanel jpPic=new JPanel()
        {
            public void paint(Graphics g) {
                g.drawImage(img, 0, 0, 400,320,  this);
            };
        };
        JLabel jlPicDescription=new JLabel("图片描述:");
        JTextField jtf=new JTextField(1);
        JButton jbuttonOK=new JButton("确定");
        JButton jbuttonChange=new JButton("修改");

        public JdialogShowPic1(String path,String description)
        {
            img=this.getToolkit().getImage(path);

            jpPic.setLayout(null);
            this.setLayout(null);
            jlabelTip.setBounds(20, 10, 360, 20);
            this.add(jlabelTip);
            jpPic.setBounds(20, 40, 400,320 );
            this.add(jpPic);

            jlPicDescription.setBounds(20, 370, 80, 20);
            this.add(jlPicDescription);
            jtf.setBounds(80, 370, 340, 20);
            this.add(jtf);
            jtf.setText(description);
            jbuttonOK.setBounds(20,370+5+30 , 80, 20);
            jbuttonChange.setBounds(120,370+5+30 , 80, 20);
            this.add(jbuttonOK);
            this.add(jbuttonChange);
            this.setSize(445, 440+30);
            this.setLocation((int) (SCREEN_WIDTH -230 ) / 2,   (int) (SCREEN_HEIGHT -400) / 2);
            this.setResizable(false);
            this.setTitle("标题图片预览");
            this.setModal(true);

            jbuttonOK.addActionListener(this);
            jbuttonChange.addActionListener(this);

            this.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==jbuttonOK)
            {
                pic1=pic1PathTemp;
                pic1Changed=true;
                Image imgTemp=Toolkit.getDefaultToolkit().getImage(pic1PathTemp);
                Image img=imgTemp.getScaledInstance(400, 320, Image.SCALE_SMOOTH);
                jlPic1.setIcon(new ImageIcon(img));
                pic1Description=jtf.getText();
                jlPic1Describtion.setText(pic1Description);
                this.dispose();
            }else
            {
                int result=jfc.showOpenDialog(null);
                File pic=jfc.getSelectedFile();
                this.dispose();
                if(pic!=null&&result==JFileChooser.APPROVE_OPTION )
                {
                    pic1PathTemp=pic.getPath();
                    new JdialogShowPic1(pic1PathTemp,pic1Description);
                }
            }
        }
    }




    class JdialogShowPic2 extends JDialog implements ActionListener
    {
        JLabel jlabelTip=new JLabel("提示：请选择宽高比接近5:4的图片！");
        Image img=null;
        JPanel jpPic=new JPanel()
        {
            public void paint(Graphics g) {
                g.drawImage(img, 0, 0, 400,320,  this);
            };
        };
        JLabel jlPicDescription=new JLabel("图片描述:");
        JTextField jtf=new JTextField(1);
        JButton jbuttonOK=new JButton("确定");
        JButton jbuttonChange=new JButton("修改");

        public JdialogShowPic2(String path,String description)
        {
            img=this.getToolkit().getImage(path);

            jpPic.setLayout(null);
            this.setLayout(null);
            jlabelTip.setBounds(20, 10, 360, 20);
            this.add(jlabelTip);
            jpPic.setBounds(20, 40, 400,320 );
            this.add(jpPic);

            jlPicDescription.setBounds(20, 370, 80, 20);
            this.add(jlPicDescription);
            jtf.setBounds(80, 370, 340, 20);
            this.add(jtf);
            jtf.setText(description);
            jbuttonOK.setBounds(20,370+5+30 , 80, 20);
            jbuttonChange.setBounds(120,370+5+30 , 80, 20);
            this.add(jbuttonOK);
            this.add(jbuttonChange);
            this.setSize(445, 440+30);
            this.setLocation((int) (SCREEN_WIDTH -230 ) / 2,   (int) (SCREEN_HEIGHT -400) / 2);
            this.setResizable(false);
            this.setTitle("标题图片预览");
            this.setModal(true);

            jbuttonOK.addActionListener(this);
            jbuttonChange.addActionListener(this);

            this.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==jbuttonOK)
            {
                pic2=pic2PathTemp;
                pic2Changed=true;
                Image imgTemp=Toolkit.getDefaultToolkit().getImage(pic2PathTemp);
                Image img=imgTemp.getScaledInstance(400, 320, Image.SCALE_SMOOTH);
                jlPic2.setIcon(new ImageIcon(img));
                pic2Description=jtf.getText();
                jlPic2Describtion.setText(pic2Description);
                this.dispose();
            }else
            {
                int result=jfc.showOpenDialog(null);
                File pic=jfc.getSelectedFile();
                this.dispose();
                if(pic!=null&&result==JFileChooser.APPROVE_OPTION )
                {
                    pic2PathTemp=pic.getPath();
                    new JdialogShowPic2(pic2PathTemp,pic2Description);
                }
            }
        }
    }



}
