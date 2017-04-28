/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doudizhu;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JLabel;

/**
 *
 * @author 聂娜
 * 图片背景设置类
 * 完成时间：2017/04/08
 */
public class BackgroundLabel extends JLabel {

    Image im;

    public BackgroundLabel(Image im) {
        this.im = im;
        this.setOpaque(true);
    }

    //Draw the back ground.
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);

    }
}
