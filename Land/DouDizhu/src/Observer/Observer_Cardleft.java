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
 * 观察者模式的抽象观察者
 */
public interface Observer_Cardleft {

    public int getListNum();

    //声明响应方法
    public String update1();

    //声明响应方法
    public String update2();

    public String CardLeft(ConcreteSuject sub, List<Card> player, int playerNumber);
}




