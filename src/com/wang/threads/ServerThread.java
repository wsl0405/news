package com.wang.threads;

import com.wang.utils.Constants;

import java.net.ServerSocket;
import java.net.Socket;

/*线程*/
public class ServerThread {

    public ServerSocket ss;
    public void run()
    {
        try
        {
            ss=new ServerSocket(31418);
            System.out.println("服务器启动成功, 端口号：31418");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        while(Constants.stop)  //为true时，一直运行，直到服务停止
        {
            try
            {
                Socket sc = ss.accept(); //等待客户端的socket请求
                System.out.println("客户端请求到达："+sc.getInetAddress());
                ServerAgentThread sat= new ServerAgentThread(sc);  //新的线程为刚刚接收的客户端Socket服务
                sat.start();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
