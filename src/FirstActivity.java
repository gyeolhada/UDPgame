import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
/*
游戏入口界面
*/
public class FirstActivity extends JFrame {
    private JButton jButton01;
    private JButton jButton02;
    private JButton jButton03;
    private JButton jButton04;// 按钮声明
    public FirstActivity(){
        this.setLayout(null);//取消布局管理器
        this.setVisible(true);
        this.setLocation(500,500);
        this.setSize(700,500);
        jButton01=new JButton("网络对战");
        jButton02=new JButton("人机对战");
        jButton03=new JButton("双人对战");
        jButton04=new JButton("帮助"); // 初始化按钮并设置位置
        jButton01.setBounds(200,200,200,50);
        jButton02.setBounds(200,260,200,50);
        jButton03.setBounds(200,320,200,50);
        jButton04.setBounds(450,260,100,50);
        jButton01.addMouseListener(new MouseListener() {
            @Override // 点击"网络对战"按钮后创建第二个活动并关闭当前活动
            public void mouseClicked(MouseEvent e) {
                new SecondActivity();
                dispose();
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
        jButton02.addMouseListener(new MouseListener() {
            @Override // 点击"人机对战"按钮后创建对话框并关闭当前活动
            public void mouseClicked(MouseEvent e) {
                new Dialog();
                dispose();
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
        jButton03.addMouseListener(new MouseListener() {
            @Override // 点击"双人对战"按钮后创建第五个活动并关闭当前活动
            public void mouseClicked(MouseEvent e) {
                new FivethActivity();
                dispose();
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
        jButton04.addMouseListener(new MouseListener() {
            @Override // 点击“帮助”按钮后打开帮助文件
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().open(new File("image/LittleGame.chm"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
        this.add(jButton04);
        this.add(jButton01);
        this.add(jButton02);
        this.add(jButton03);// 添加按钮到界面
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override // 绘制方法，用于绘制界面背景和文字
    public void paint(Graphics g) {
        super.paint(g);
        String path = "image/img01.PNG";// 背景图片
        ImageIcon background = new ImageIcon(path);
        JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
        // 把标签的大小位置设置为图片刚好填充整个面板
        label.setBounds(0, 0, this.getWidth(), this.getHeight());
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        // 把背景图片添加到分层窗格的最底层作为背景
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString("Little Game", 200, 120);
    }
}
