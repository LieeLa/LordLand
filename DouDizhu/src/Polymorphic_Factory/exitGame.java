/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polymorphic_Factory;

import Singleton.MenuWin;
import javax.swing.JButton;

/**
 *
 * @author 聂娜 
 * 完成时间：2017/04/09
 * 1.工厂方法模式的具体工厂：退出游戏 
 * 2.模式3命令模式的具体命令类
 */
public class exitGame extends ButtonCommand {

    private JButton exitBnt;

    //退出游戏
    @Override
    public void execute() {
        MenuWin c;
        c = MenuWin.getIntance();
        c.dispose();
        System.exit(0);
    }

    public JButton initButton() {
        ButtonType button = (ButtonType) new BntType4();
        exitBnt = button.initButton("退出游戏");
        return exitBnt;
    }
}
