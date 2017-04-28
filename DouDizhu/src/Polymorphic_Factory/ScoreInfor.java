/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polymorphic_Factory;

import doudizhu.InforWin;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author 聂娜
 *1.工厂方法模式的具体工厂：帮助菜单
 * 完成时间：2017/04/07
 *2.命令模式的具体命令类
 * 完成时间：2017/04/09
 */
public class ScoreInfor extends ButtonCommand {

    private JButton scoreBnt;

    //打开游戏帮助
    @Override
    public void execute() {
         InforWin infor =  new InforWin();
        try {
                infor.init();
        } catch (SQLException ex) {
            Logger.getLogger(ScoreInfor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JButton initButton() {
        ButtonType button = (ButtonType) new BntType3();
        scoreBnt = button.initButton("查看得分");
        return scoreBnt;
    }
}


