/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polymorphic_Factory;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/07
 * 工厂方法模式的具体产品类：按钮类型3
 */
public class BntType3 implements ButtonType {

    JButton button;

    @Override
    public JButton initButton(String content) {
        button = new JButton(content);
        button.setBackground(Color.ORANGE);
        button.setBounds(0, 0, 100, 50);
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 3);
        Border border2 = BorderFactory.createEtchedBorder();
        button.setBorder(BorderFactory.createCompoundBorder(border1, border2));
        //设置Buton字体大:隶书，粗体，40号
        button.setFont(new java.awt.Font("隶书", 1, 40));
        return button;
    }
}
