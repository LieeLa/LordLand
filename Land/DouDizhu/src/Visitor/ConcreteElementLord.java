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
 * 具体元素:地主
 */
public class ConcreteElementLord implements Element {

    private int score;
    private int goldenBean;
    private int playid;
    private int winFlag;

    public ConcreteElementLord(PlayerInfor player, int id, int winFlag) {
        this.score = player.getSingelPlayerScore(id);
        this.goldenBean = player.getSingelPlayerJinDou(id);
        this.playid = id;
        this.winFlag = winFlag;
    }

    //获取玩家的得分
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

    //接受访问者
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

