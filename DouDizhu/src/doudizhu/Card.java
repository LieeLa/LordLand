/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doudizhu;

import Adapter.Adapter_Card;
import Adapter.Operation;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author 聂娜 
 * 单张牌类
 * 完成时间：2017/04/08
 */
public class Card extends JLabel implements MouseListener {

    playWin main;   //Main类的引用
    String name;    //图片url名字
    boolean up;     //是否正反面
    boolean canClick = false;   //是否可被点击
    boolean clicked = false;    //是否点击过
    //关联适配器类，实现对单张牌的操作
    private Operation operation = Adapter_Card.getIntance();   

    //获取牌的名字
    public String getName() {
        return this.name;
    }

    //获取牌是否可点击的情况
    public boolean getCanlick() {
        return this.canClick;
    }

    //获取牌的正反面情况
    public boolean getUp() {
        return this.up;
    }

    //获取牌的点击情况
    public boolean getClicked() {
        return this.clicked;
    }

    //设置牌的是否可点击情况
    public void setCanlick(boolean canClick) {
        this.canClick = canClick;
    }

    //翻转牌
    public Card(playWin m, String name, boolean up) {
        this.main = m;
        this.name = name;
        this.up = up;
        if (this.up) {
            this.turnFront();
        } else {
            this.turnRear();
        }
        this.initCard();

    }

    //初始化牌
    public void initCard() {
        this.setSize(71, 96);
        this.setVisible(true);
        this.addMouseListener(this);
    }

    //正面
    public void turnFront() {
        this.setIcon(new ImageIcon("images/" + name + ".gif"));
        this.up = true;
    }

    //反面
    public void turnRear() {
        this.setIcon(new ImageIcon("images/rear.gif"));
        this.up = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if (canClick) {
            Point from = this.getLocation();
            int step; //移动的距离
            if (clicked) {
                step = -20;
            } else {
                step = 20;
            }
            clicked = !clicked; //反向
            //当被选中的时候，向前移动一步/后退一步
            operation.move(this, from, new Point(from.x, from.y - step));
        }
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent arg0) {
    }

    public void mouseReleased(MouseEvent arg0) {
    }

}
