import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/*
"双人对战"游戏程序
*/
public class FivethActivity extends ThirdActivity {// 继承简单模式
    public FivethActivity(){
        jButton.setBounds(0,0,0,0);
    }
    @Override
    public void addM() {
       for(int i=0;i<9;i++){
           int j=i;
           buttons[i].addMouseListener(new MouseListener() {
               @Override
               public void mouseClicked(MouseEvent e) {}
               @Override
               public void mousePressed(MouseEvent e) {
                   if (judge % 2 == 0 && boom[j] == 0) {// 我方下棋
                       buttons[j].setIcon(a);// 设置图标为我方棋子
                       boom[j] = 1;// 标记我方棋子位置
                       judge++;// 回合数加1
                       if (win()) {// 若我方获胜
                           JOptionPane.showConfirmDialog(null, "you win", null, JOptionPane.CLOSED_OPTION);
                           for (JButton jButton : buttons) jButton.setIcon(null);// 清空棋盘
                           boom = new int[9];// 重置游戏状态
                           judge = 2;}// 重置回合数
                   } else if(boom[j]==0){// 敌方下棋
                       buttons[j].setIcon(b);// 设置图标为敌方棋子
                       boom[j] = 5;// 标记敌方棋子位置
                       judge++;// 回合数加1
                       if (win1()) {// 若敌方获胜
                           JOptionPane.showConfirmDialog(null, "敌方胜利", null, JOptionPane.CLOSED_OPTION);
                           for (JButton jButton : buttons) jButton.setIcon(null);
                           boom = new int[9];
                           judge = 2;}
                   }
                   if(noWin()){// 若为和棋
                       JOptionPane.showConfirmDialog(null, "和棋", null, JOptionPane.CLOSED_OPTION);
                       for (JButton jButton : buttons) jButton.setIcon(null);
                       boom = new int[9];
                       judge = 2;}
               }
               @Override
               public void mouseReleased(MouseEvent e) {}
               @Override
               public void mouseEntered(MouseEvent e) {}
               @Override
               public void mouseExited(MouseEvent e) {}
           });
           this.add(buttons[j]);
       }
    }
}
