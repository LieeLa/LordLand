/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flyweight;

import java.awt.Color;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/14
 * 抽象享元类：按钮类
 */
public abstract class AbstractButton {
     private JButton bnt = new JButton();
    
    public AbstractButton(){
       // setName();
    }

    public abstract String getName();

    public JButton initButton(Buttonlocation location) {
         bnt.setText(getName());
        //bnt = new JButton(setName());
        int l1 = location.getL1();
        int l2 = location.getL2();
        bnt.setBounds(320 + l1 * 100, 400, l2, 20);
        bnt.setBackground(new Color(255,165,0));
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border border2 = BorderFactory.createEtchedBorder();
        bnt.setBorder(BorderFactory.createCompoundBorder(border1, border2));
        //设置Buton字体大:隶书，粗体，40号
        bnt.setFont(new java.awt.Font("隶书", 1, 20));
        bnt.setVisible(true);
        return bnt;
    }

    public JButton getButton() {
        return bnt;
    }
}








