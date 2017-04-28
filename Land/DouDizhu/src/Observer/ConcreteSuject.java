/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import java.util.ArrayList;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/16
 * 具体观察者类
 */

public class ConcreteSuject extends Observer_Subject{

    private ArrayList observers = new <Observer_Cardleft>ArrayList();

    //注册方法，用于观察者集合中增加一个观察者
    public void attach(Observer_Cardleft observer) {
        observers.add(observer);
    }

    //注销方法,用于在观察者集合中删除一个观察者
    public void detach(Observer_Cardleft observer) {
        observers.remove(observer);
    }

    //通知“我只剩一张牌了
    @Override
    public String notifyObserver1(int ListNum) {
        String message = "";
        for (Object obs : observers) {
            {
                if (((Observer_Cardleft) obs).getListNum() != ListNum) {
                    message = ((Observer_Cardleft) obs).update1();
                }
            }
        }
        return message;
    }

    //通知“我只剩两张牌了
    @Override
    public String notifyObserver2(int ListNum) {
        String message = "";
        for (Object obs : observers) {
            if (((Observer_Cardleft) obs).getListNum() != ListNum) {
                message = ((Observer_Cardleft) obs).update2();
            }
        }
        return message;
    }
}

