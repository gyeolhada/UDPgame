import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/*
"人机对战"游戏设置显示
*/
public class Dialog extends JFrame{
    String str;
    TextField textField01;
    private JComboBox<String>faceCombo;// 下拉框
    public Dialog(){
        this.setVisible(true);
        this.setLayout(null);
        this.setLocation(600,550);
        this.setSize(400,300);
        textField01=new TextField("请输入你的姓名",20);
        textField01.setBounds(100,50,200,25);
        faceCombo=new JComboBox<>();
        faceCombo.addItem("普通模式");
        faceCombo.addItem("困难模式");
        JButton jButton=new JButton("开始游戏");
        jButton.setBounds(120,150,100,25);
        faceCombo.setBounds(120,80,100,25);
        jButton.addMouseListener(new MouseListener() {
            @Override // 点击"开始游戏"按钮
            public void mouseClicked(MouseEvent e) {
                ThirdActivity.str=textField01.getText(); // 获取输入的姓名
                // 根据下拉框选择，创建不同的游戏活动界面，并关闭当前界面
                if(faceCombo.getSelectedIndex()==0)
                    new ThirdActivity();//"人机对战"普通模式
                else new SixActivity();//"人机对战"困难模式
                dispose();
            } // 其他鼠标事件方法留空
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.add(textField01);
        this.add(jButton);
        this.add(faceCombo);
    }
}
