/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import doudizhu.playWin;

/**
 *
 * @author 聂娜 
 * 完成时间：2017/04/18
 * 环境类
 */
public class Context_setLord {

    private AbstractStrategy_setLord strategy;

    public void setStrategy(AbstractStrategy_setLord strategy) {
        this.strategy = strategy;
    }

    public int algorithm(playWin main, int lordTurn) {
        return strategy.setLordalrorithm(main, lordTurn);
    }
}
