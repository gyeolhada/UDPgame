import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
/*
"网络对战"游戏设置显示
*/
public class SecondActivity extends JFrame{
    public TextField textField01;
    public TextField textField02;
    private JButton jButton;
    public String iP;
    public String port;
    private JComboBox<String>faceCombo;// 下拉框
    public SecondActivity(){
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(700,500);
        textField01=new TextField("IP地址",20);
        faceCombo=new JComboBox<>();
        faceCombo.addItem("我方先行");
        faceCombo.addItem("敌方先行");
        jButton=new JButton("开始游戏");
        JButton jButton1=new JButton("查看本机IP");
        textField01.setBounds(200,200,200,25);
        faceCombo.setBounds(200,250,150,25);
        faceCombo.addActionListener(new AbstractAction() {
            @Override // 设置先手方,如果是下拉框的第一项则为“我方先行”,第二项则为“敌方先行”
            public void actionPerformed(ActionEvent e) {
              GameActivity.signal=faceCombo.getSelectedIndex();}
        });
        jButton1.setBounds(200,300,200,25);
        jButton.setBounds(220,400,150,25);
        this.add(faceCombo);
        this.add(jButton);
        this.add(textField01);
        this.add(jButton1);
         jButton1.addMouseListener(new MouseListener() {
             @Override // 点击"查看本机IP"按钮即显示IP
             public void mouseClicked(MouseEvent e) {
                 showIp();
             }
             @Override // 其他鼠标事件方法留空
             public void mousePressed(MouseEvent e) {}
             @Override
             public void mouseReleased(MouseEvent e) {}
             @Override
             public void mouseEntered(MouseEvent e) {}
             @Override
             public void mouseExited(MouseEvent e) {}
         });
        jButton.addMouseListener(new MouseListener() {
            @Override // 点击"开始游戏"按钮即启动游戏活动线程并关闭当前界面
            public void mouseClicked(MouseEvent e) {
                iP=textField01.getText(); // 获取输入的对方IP地址
                GameActivity.ip=iP; // 设置对方IP地址
                try { new Thread(new GameActivity()).start();
                    dispose();} catch (SocketException e1) {
                    e1.printStackTrace();}
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
    public void showIp(){  // 显示本机IP地址的方法
        InetAddress addr= null;
        try { addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) { e.printStackTrace();}
        JOptionPane.showConfirmDialog(null, addr.getHostAddress(),
                null, JOptionPane.CLOSED_OPTION);}// 弹出对话框显示本机IP地址
    @Override // 绘制方法，用于绘制界面背景
    public void paint(Graphics g) {
        super.paint(g);
        String path = "image/img01.PNG";
        ImageIcon background = new ImageIcon(path);
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, this.getWidth(), this.getHeight());
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
    }
}
