/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitor;

import doudizhu.PlayerInfor;

/**
 *
 * @author 聂娜 
 *  完成时间：2017/04/22
 * 具体元素:农民
 * 
 */
public class ConcreteElementFarmer implements Element {

    private int score;
    private int goldenBean;
    private int playid;
    private int winFlag;

    public ConcreteElementFarmer(PlayerInfor player, int id, int winFlag) {
        this.score = player.getSingelPlayerScore(id);
        this.goldenBean = player.getSingelPlayerJinDou(id);
        this.playid = id;
        this.winFlag = winFlag;
    }

    //获取玩家的得分情况
    public int getScore() {
        return this.score;
    }

    //获取玩家的金豆数
    public int getGoldenBean() {
        return this.goldenBean;
    }

    //获取玩家的ID
    public int getPlayerID() {
        return this.playid;
    }

    //获取玩家输赢情况
    public int getWinFlag() {
        return this.winFlag;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
