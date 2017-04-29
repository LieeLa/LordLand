/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import Adapter.Adapter_Card;
import Adapter.Operation;
import doudizhu.playWin;
import javax.swing.JTextField;

/**
 *
 * @author 聂娜 
 * 完成时间：2017/04/18
 * 具体策略类：玩家（电脑)0抢地主算法
 */
public class Strage0_setLord extends AbstractStrategy_setLord {

    public int setLordalrorithm(playWin main, int lordTurn) {
        JTextField time = main.getTime(0);
        int lord = lordTurn;
        time.setText("不抢");   //默认为不抢
        Operation operation = Adapter_Card.getIntance();
        //前面没有玩家抢地主
        if (lord == 0) {
            time.setText("抢地主");
            lord = 0;
        } else //前面有玩家抢地主
         if (operation.getScore(main.getPlayerList(0)) > operation.getScore(main.getPlayerList(2))) {
                double ram = Math.random();
                if (ram < 0.5) {
                    // 电脑选地主,选择电脑0
                    time.setText("抢地主");
                    lord = 0;
                }
            }
        time.setVisible(true);
        System.out.println("当前地主："+lord+"号玩家");
        return lord;
    }
}
