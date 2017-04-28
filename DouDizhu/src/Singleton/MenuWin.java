/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;


import Polymorphic_Factory.ScoreInfor;
import Polymorphic_Factory.exitGame;
import Polymorphic_Factory.helpMenu;
import Polymorphic_Factory.startGame;
import doudizhu.BackgroundLabel;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * @author 聂娜
 * 完成时间：2017/04/08
 * 1.单例模式，懒汉式和饿汉式结合
 * 2.模式3命令模式的请求调用者和请求接受者之一
 */
public class MenuWin extends JFrame implements ActionListener {
    private JLayeredPane layeredPane;
    private JPanel jp;
    private BackgroundLabel bgp;
    private JButton[] Button = new JButton[4];
    private startGame start;
    private exitGame exit;
    private helpMenu help;
    private ScoreInfor score;

    private MenuWin() {
        initMenuWin();
    }

    private static class HolderClass {

        private final static MenuWin instance = new MenuWin();
    }

    public static MenuWin getIntance() {
        return HolderClass.instance;
    }

    public void setButton() {
        start = new startGame();
        Button[0] = start.initButton();
        exit = new exitGame();
        Button[3] = exit.initButton();
        help = new helpMenu();
        Button[2] = help.initButton();
        score = new ScoreInfor();
        Button[1] = score.initButton();
        for (int i = 0; i < 4; i++) {
            //注册监听器。
            Button[i].addActionListener(this);
        }
    }

    public void initMenuWin() {
        this.setSize(830, 620);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner()); // 屏幕居中
        jp = new JPanel();
        jp.setBounds(320, 280, 300, 300);
        BoxLayout layout = new BoxLayout(jp, BoxLayout.Y_AXIS);  //让按钮纵向排列
        jp.setLayout(layout);
        jp.setOpaque(false); //面板不可见
        setButton();
        for (int i = 0; i < 4; i++) {
            jp.add(Button[i]);
            jp.add(Box.createVerticalStrut(20));           //设置button之间的间距
        }

        bgp = new BackgroundLabel((new ImageIcon("images\\menu.jpg")).getImage());
        bgp.setBounds(0, 0, 830, 620);
        layeredPane = getLayeredPane();
        layeredPane.add(bgp, JLayeredPane.DEFAULT_LAYER);  //背景置于底层
        layeredPane.add(jp, JLayeredPane.MODAL_LAYER);     //按钮置于上一层
        Container cp = getContentPane();
        ((JPanel) cp).setOpaque(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //当输入的为数字的时候
        if (e.getActionCommand() == "开始游戏") {
            start.execute();
        } else if (e.getActionCommand() == "退出游戏") {
            exit.execute();
        } else if (e.getActionCommand() == "游戏帮助") {
            help.execute();
        }
        else if (e.getActionCommand() == "查看得分") {
            score.execute();
        }
    }

    public static void main(String[] args) {
        MenuWin c;
        c = MenuWin.getIntance();
    }
}

