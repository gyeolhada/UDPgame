import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
/*
"人机对战"普通模式
*/
public class ThirdActivity extends JFrame{
    // 按钮数组，表示棋盘上的九个位置
    protected JButton[]buttons=new JButton[9];
    // 棋盘状态:0-未触发,1-我方触发,5-敌方触发
    protected int[]boom=new int[9];
    protected int judge=2;// 初始回合数
    protected ImageIcon a; // 我方棋子图标
    protected ImageIcon b;// 敌方棋子图标
    protected JButton jButton;// 标题按钮
    protected JButton jButton1;// 退出按钮
    public static  String str;// 玩家姓名
    public ThirdActivity() {
        a=new ImageIcon("image/img2.PNG");
        b=new ImageIcon("image/img3.PNG");
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(800,800);
        jButton=new JButton(str);
        jButton.setBounds(240,50,200,50);
        jButton.setBackground(Color.cyan);
        jButton1=new JButton("退出");
        jButton1.setBounds(280,630,100,50);
        jButton1.setBackground(Color.BLUE);
        jButton1.addMouseListener(new MouseListener() {
            @Override // 点击退出按钮关闭当前界面
            public void mouseClicked(MouseEvent e) {
                dispose();
                new FirstActivity();// 返回主界面
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
        this.add(jButton1);
        this.add(jButton);
        this.setButtons();// 设置棋盘按钮
        this.addM();// 添加鼠标事件监听器
    }
    public void setButtons(){// 设置棋盘按钮
        for(int i=0;i<9;i++)
            buttons[i]=new JButton();
        buttons[0].setBounds(100,150,150,150);
        buttons[1].setBounds(260,150,150,150);
        buttons[2].setBounds(420,150,150,150);
        buttons[3].setBounds(100,310,150,150);
        buttons[4].setBounds(260,310,150,150);
        buttons[5].setBounds(420,310,150,150);
        buttons[6].setBounds(100,470,150,150);
        buttons[7].setBounds(260,470,150,150);
        buttons[8].setBounds(420,470,150,150);
    }
    public void addM(){ // 添加鼠标事件监听器
        for(int i=0;i<9;i++){
            int j=i;
            buttons[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}
                @Override
                public void mousePressed(MouseEvent e) {
                    if(judge%2==0&&boom[j]==0){// 如果是我方回合且按钮未触发
                        buttons[j].setIcon(a);// 设置我方棋子图标
                        boom[j]=1;// 更新棋盘状态
                        if(win()){// 判断我方是否胜利
                            JOptionPane.showConfirmDialog(null, "you win", null, JOptionPane.CLOSED_OPTION);
                            judge=1;
                            boom=new int[9];
                            for(JButton jButton:buttons)
                                jButton.setIcon(null);
                        }else if(noWin()){// 判断是否和棋
                            JOptionPane.showConfirmDialog(null, "和棋", null, JOptionPane.CLOSED_OPTION);
                            judge=1;
                            boom=new int[9];
                            for(JButton jButton:buttons)
                                jButton.setIcon(null);
                        }
                        judge++;// 更新回合数
                    }
                    if(judge%2!=0){// 如果是敌方回合
                        computer();// 让电脑下棋
                        if(win1()){// 判断敌方是否胜利
                            JOptionPane.showConfirmDialog(null, "computer win", null, JOptionPane.CLOSED_OPTION);
                            judge= 1;
                            boom=new int[9];
                            for(JButton jButton:buttons)
                                jButton.setIcon(null);
                        }
                        judge++;// 更新回合数
                    }
                }
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}
            });
            this.add(buttons[i]);
        }
    }
    public void computer(){// 让电脑下棋
        while(true){// 循环直到找到合适的位置下棋
            Random ran = new Random();// 创建一个随机数生成器对象
            int a=ran.nextInt(9);// 随机生成一个0到8的整数
            System.out.println(a);//dbug
            if(boom[a]==0){// 如果随机生成的位置未被触发
                System.out.println("aa");//dbug
                boom[a]=5;// 将该位置标记为敌方触发状态
                buttons[a].setIcon(b);// 在按钮上显示敌方棋子图标
                repaint();// 刷新界面，显示更新后的按钮状态
                break;// 退出循环，结束电脑下棋过程
            }
        }
    }
    public boolean win() {// 判断我方是否胜利
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
    public boolean win1() {// 判断敌方是否胜利
        if(boom[0]+boom[1]+boom[2]==15||
                boom[3]+boom[4]+boom[5]==15||
                boom[6]+boom[7]+boom[8]==15)
            return true;
        if(boom[0]+boom[3]+boom[6]==15||
                boom[1]+boom[4]+boom[7]==15||
                boom[2]+boom[5]+boom[8]==15)
            return true;
        if(boom[0]+boom[4]+boom[8]==15||
                boom[2]+boom[4]+boom[6]==15)
            return true;
        return false;
    }
    public  boolean noWin() {// 判断是否和棋
        for (int i : boom)
            if (i == 0) return false;
        return true;
    }
}
