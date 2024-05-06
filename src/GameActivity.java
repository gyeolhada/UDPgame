import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
/*
井字棋游戏程序
*/
public class GameActivity extends JFrame implements Runnable{
    public static int signal=0; // 游戏信号，0表示我方先行，1表示敌方先行
    public static  String ip;// 对手IP地址
    private  JButton[]buttons=new JButton[9]; // 按钮数组
    private int[]boom=new int[9];// 棋盘状态:0-未触发,1-我方触发,2-敌方触发
    private int judge=2;// 胜负判断，初始值为2，表示未结束
    private ImageIcon a;// 我方棋子图标
    private ImageIcon b;// 敌方棋子图标
    private DatagramSocket client; // 客户端数据报套接字(8888)
    private int serverPort=6666;// 服务器端口号
    private int clientPort=8888;// 客户端端口号
    private int outPort=9999;// 对手端口号
    private DatagramSocket server;// 服务器数据报套接字(6666)
    public GameActivity() throws SocketException {
        a=new ImageIcon("image/img2.PNG");
        b=new ImageIcon("image/img3.PNG");
        if(signal==1){// 若信号为1，表示敌方先行，交换端口号和棋子图标
            serverPort=7777;
            clientPort=9999;
            outPort=8888;
            ImageIcon c=a;
            a=b;
            b=c;
        } // 设置界面可见、布局管理器为空、大小
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(800,800);
        this.setButtons(); // 设置按钮
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setButtons(){ // 设置按钮方法
        for(int i=0;i<9;i++) // 循环初始化按钮数组
            buttons[i]=new JButton();
        // 设置按钮位置
        buttons[0].setBounds(100,150,150,150);
        buttons[1].setBounds(260,150,150,150);
        buttons[2].setBounds(420,150,150,150);
        buttons[3].setBounds(100,310,150,150);
        buttons[4].setBounds(260,310,150,150);
        buttons[5].setBounds(420,310,150,150);
        buttons[6].setBounds(100,470,150,150);
        buttons[7].setBounds(260,470,150,150);
        buttons[8].setBounds(420,470,150,150);
        for(int i=0;i<9;i++){ // 遍历按钮数组
            int j=i;
            buttons[i].addMouseListener(new MouseListener() { // 为每个按钮添加鼠标事件监听器
                @Override
                public void mousePressed(MouseEvent e) { // 鼠标按下事件
                    if(myJudge()&&boom[j]==0){ // 如果轮到我方并且当前按钮未被点击过
                        buttons[j].setIcon(a); // 在按钮上显示我方棋子图标
                        boom[j]=1; // 更新棋盘状态，表示我方在该位置落子
                        try { // 尝试执行以下代码
                            server=new DatagramSocket(serverPort); // 1.创建服务器DatagramSocket对象
                            String msg; // 准备发送的消息
                            if(win()) msg=j+","+1; // 如果我方胜利，则发送胜利消息
                            else if(noWin()) msg=j+","+2; // 如果和棋，则发送和棋消息
                            else msg=j+","+0; // 否则发送普通落子消息
                            byte[] data =msg.getBytes(); // 将消息字符串转换为字节数组
                            //3.打包（发送地点 及其端口）
                            DatagramPacket packet=new DatagramPacket(data,data.length,new InetSocketAddress(ip,outPort));
                            server.send(packet);//4.发送数据包
                            System.out.println("白方发送成功"); // 输出发送成功消息
                            if(win()){ // 如果我方胜利
                                JOptionPane.showConfirmDialog(null, "you win",
                                        null, JOptionPane.CLOSED_OPTION); // 弹出胜利消息框
                                boom=new int[9]; // 重置棋盘状态
                                for(JButton jButton:buttons) jButton.setIcon(null); // 清空按钮图标
                                repaint(); // 重新绘制界面
                            } server.close(); // 关闭服务器套接字
                        } catch(Exception ec) {System.out.println("白方异常");}
                        judge++; // 更新回合数
                        if(noWin()){ // 如果和棋
                            JOptionPane.showConfirmDialog(null, "和棋",
                                    null, JOptionPane.CLOSED_OPTION); // 弹出和棋消息框
                            judge=2; // 重置回合数
                            boom=new int[9]; // 重置棋盘状态
                            for(JButton jButton:buttons) jButton.setIcon(null); // 清空按钮图标
                        }
                    }
                }
                @Override
                public void mouseClicked(MouseEvent e) {} // 鼠标点击事件
                @Override
                public void mouseReleased(MouseEvent e) {} // 鼠标释放事件
                @Override
                public void mouseEntered(MouseEvent e) {} // 鼠标进入事件
                @Override
                public void mouseExited(MouseEvent e) {} // 鼠标退出事件
            });
            this.add(buttons[i]); // 将按钮添加到窗体中
        }
    }
    @Override
    public void run() {  // 线程运行方法
        while(true) { // 无限循环，持续监听网络数据包
            try { Thread.sleep(1000); // 线程休眠1秒钟，减少CPU占用
            } catch (InterruptedException e) {e.printStackTrace();}
            if(myJudge()) continue; // 如果是我方回合，则继续循环等待
            try { client=new DatagramSocket(clientPort); // 1.创建客户端数据报套接字
                byte[] container=new byte[1024]; // 2.准备接收容器
                DatagramPacket packet=new DatagramPacket(container,container.length); // 3.封装成包
                client.receive(packet); // 4.接收数据包
                byte[] data=packet.getData(); // 获取接收到的数据
                int len=packet.getLength(); // 获取数据长度
                String str=new String(data,0,len); // 将字节数组转换为字符串
                Integer integer=new Integer(str.split(",")[0]); // 解析收到的数据，获取对方下棋的位置
                Integer integer1=new Integer(str.split(",")[1]); // 解析收到的数据，获取对方的判断结果
                boom[integer]=5; // 在对应位置更新棋盘状态，表示对方落子
                buttons[integer].setIcon(b); // 在对应按钮上显示敌方棋子图标
                System.out.println(integer1); // 输出对方的判断结果
                judge++; // 更新回合数
                repaint(); // 重新绘制界面
                client.close(); // 关闭客户端套接字
                // 根据对手的判断结果弹出相应的消息框
                if(integer1==1){ // 如果对方获胜
                    JOptionPane.showConfirmDialog(null, "you default",
                            null, JOptionPane.CLOSED_OPTION);
                    boom=new int[9]; // 重置棋盘状态
                    for(JButton jButton:buttons){
                        jButton.setIcon(null); // 清空按钮上的图标
                        judge=2; }// 重置回合数
                    repaint(); // 重新绘制界面
                } else if(integer1==2){ // 如果是和棋
                    JOptionPane.showConfirmDialog(null, "和棋",
                            null, JOptionPane.CLOSED_OPTION);
                    boom=new int[9]; // 重置棋盘状态
                    for(JButton jButton:buttons){
                        jButton.setIcon(null); // 清空按钮上的图标
                        judge=2; }// 重置回合数
                    repaint(); // 重新绘制界面
                }
            } catch(Exception ec) { System.out.println("异常");}
        }
    }
    public boolean win() { // 胜负判断方法
        if(boom[0]+boom[1]+boom[2]==3||
           boom[3]+boom[4]+boom[5]==3||
           boom[6]+boom[7]+boom[8]==3)
            return true;
        if(boom[0]+boom[3]+boom[6]==3||
           boom[1]+boom[4]+boom[7]==3||
           boom[2]+boom[5]+boom[8]==3)
            return true;
        if(boom[0]+boom[4]+boom[8]==3||
           boom[2]+boom[4]+boom[6]==3)
            return true;
       return false;
    }
    public  boolean noWin() { // 和棋判断方法
        for (int i : boom) {
            if (i == 0) return false;
        }
        return true;
    }
    public boolean myJudge(){ // 我方回合判断方法
        if(signal==0){
            if(judge%2==0)return true;
            else return false;
        }else{
            if(judge%2!=0)return true;
            else return false;
        }
    }
}
