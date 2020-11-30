package com.wang;

import com.wang.threads.ServerThread;
import com.wang.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerMain extends JFrame implements ActionListener {
    BorderLayout borderLayout1=new BorderLayout();
    BorderLayout borderLayout2= new BorderLayout();

    JPanel jPanel1=new JPanel();
    JPanel jPanel2=new JPanel();
    JButton jButton1=new JButton();
    JButton jButton2=new JButton();
    JScrollPane jScrollPane1=new JScrollPane();

    static JTextArea jTextArea1=new JTextArea();


    public ServerMain()
    {
        super("Server");
        getContentPane().setLayout(borderLayout1);

        jButton1.setText("启动服务器");
        jButton2.setText("关闭服务器");
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);


        jPanel1.add(jButton1);
        jPanel1.add(jButton2);

        jTextArea1.setText("");
        getContentPane().add(jPanel2, BorderLayout.CENTER);
        jPanel2.setLayout(borderLayout2);
        jPanel2.add(jScrollPane1, BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jTextArea1);
        this.setSize(400, 400);

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ServerMain server=new ServerMain();
        server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==jButton1)
        {
            Constants.stop=true;
            new ServerThread().run();
            jTextArea1.setText("服务器已启动");
        }else if(e.getSource()==jButton2)
        {
            Constants.stop=false;
            this.setVisible(false);
            this.dispose();
            System.exit(0);
        }
    }
}
