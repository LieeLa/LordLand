/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polymorphic_Factory;

import Singleton.MenuWin;
import doudizhu.playWin;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/07
 * 1.工厂方法模式的具体工厂：开始游戏
 * 2.命令模式的具体命令类
 */
public class startGame extends ButtonCommand {

    private JButton startBnt;

    //开始游戏
    @Override
    public void execute() {
        try {
            MenuWin c;
            c = MenuWin.getIntance();
            c.dispose();
            new playWin();
        } catch (SQLException ex) {
            Logger.getLogger(startGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JButton initButton() {
        ButtonType button = (ButtonType) new BntType1();
        startBnt = button.initButton("开始游戏");
        return startBnt;
    }
}
