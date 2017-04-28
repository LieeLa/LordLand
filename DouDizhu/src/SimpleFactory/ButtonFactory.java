/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleFactory;

import javax.swing.JButton;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/09
 * 按钮工厂类
 */
public class ButtonFactory {
    private playButton button;
    private JButton bnt;

    public JButton setButton(String name, int location) {
        if (name.equalsIgnoreCase("抢地主") || name.equalsIgnoreCase("不抢")) {
            button = new getLandlord();
            bnt = button.initButton(name, location);
        } else if (name.equalsIgnoreCase("出牌") || name.equalsIgnoreCase("不要")) {
            button = new ChuPai();
            bnt = button.initButton(name, location);
        }
        return bnt;
    }
}