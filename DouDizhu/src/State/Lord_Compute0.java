/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

import Strategy.Strage0_setLord;
import doudizhu.playWin;

/**
 *
 * @author 聂娜 
 * 完成时间：2017/04/16
 * 具体状态类：电脑0当地主类
 */
public class Lord_Compute0 extends Lord {

    @Override
    public int changeLoard(playWin main, int lordTurn) {
        this.context = super.context;
        this.strategy = new Strage0_setLord();    //调用电脑0抢地主算法
        this.context.setStrategy(this.strategy);
        int lord = this.context.algorithm(main, lordTurn);
        return lord;
    }
}
