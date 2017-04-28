/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

import Strategy.AbstractStrategy_setLord;
import Strategy.Context_setLord;
import doudizhu.playWin;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/16
 * 抽象状态类
 */
public abstract class Lord {

    protected Context_setLord context = new Context_setLord();
    protected AbstractStrategy_setLord strategy;

    public abstract int changeLoard(playWin main, int lordTurn);
}
