/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleFactory;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author 聂娜 
 * 完成时间：2017/04/09
 * 具体产品类
 */
public class ChuPai implements playButton {

    JButton bnt;

    @Override
    public JButton initButton(String name, int location) {
        bnt = new JButton(name);
        bnt.setName(name);
        bnt.setBounds(320 + location * 100, 400, 60, 20);
        bnt.setBackground(Color.white);
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border border2 = BorderFactory.createEtchedBorder();
        bnt.setBorder(BorderFactory.createCompoundBorder(border1, border2));
        //设置Buton字体大:隶书，粗体，20号
        bnt.setFont(new java.awt.Font("隶书", 1, 20));
        bnt.setVisible(false);
        return bnt;
    }
}
