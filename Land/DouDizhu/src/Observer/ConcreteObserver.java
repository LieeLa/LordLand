/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import doudizhu.Card;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/16
 * 具体观察者：记录当前玩家的排数，当玩家的牌数少于3张，则通知队列中的其余玩家
 */
public class ConcreteObserver implements Observer_Cardleft {

    int listNum;
    static int counttime = 0;
    //实现响应方法
    List<Card> playerList = new ArrayList();

    public ConcreteObserver(List<Card> player, int listNum) {
        this.playerList = player;
        this.listNum = listNum;
    }

    @Override
    public int getListNum() {
        return this.listNum;
    }

    //只剩下最后一张牌
    public String update1() {
        counttime++;
        String oneOnly = "";
        if (counttime < 2) {
            oneOnly = "我只剩最后一张牌了!";
            // System.out.println(oneOnly);
        }
        return oneOnly;
    }

    //只剩下最后两张牌
    public String update2() {
        counttime++;
        String twoOnly = "";
        if (counttime < 3) {
            twoOnly = "我只剩最后两张牌了!";
            // System.out.println(twoOnly);
        }
        return twoOnly;
    }

    public String CardLeft(ConcreteSuject sub, List<Card> player, int playerNumber) {
        String message = "";
        if (player.size() == 1) {
            sub.notifyObserver1(playerNumber);
            message = playerNumber + "号玩家只剩一张牌了！";

        }
        if (player.size() == 2) {
            message = sub.notifyObserver2(playerNumber);
            message = playerNumber + "号玩家只剩两张牌了！";
        }
        return message;
    }
}
