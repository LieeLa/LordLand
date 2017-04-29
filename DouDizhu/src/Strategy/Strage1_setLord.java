/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import doudizhu.playWin;

/**
 * @author 聂娜 
 * 完成时间：2017/04/18
 * 具体策略类：玩家抢地主算法
 */
public class Strage1_setLord extends AbstractStrategy_setLord {

    public int setLordalrorithm(playWin main, int lordTurn) {
        int lord = lordTurn;
        if (main.getTime(1).getText().equals("抢地主")) {
            // 得到地主牌
            lord = 1;
        } 
        System.out.println("当前地主："+ lord+"号玩家");
        return lord;
    }
}
