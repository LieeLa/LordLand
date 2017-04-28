/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polymorphic_Factory;

import doudizhu.heplWin;
import javax.swing.JButton;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/07
 * 1.工厂方法模式的具体工厂：帮助菜单
 * 2.命令模式的具体命令类
 */
public class helpMenu extends ButtonCommand {

    private JButton helpBnt;

    //打开游戏帮助
    @Override
    public void execute() {
        heplWin help = new heplWin();
    }

    public JButton initButton() {
        ButtonType button = (ButtonType) new BntType2();
        helpBnt = button.initButton("游戏帮助");
        return helpBnt;
    }
}