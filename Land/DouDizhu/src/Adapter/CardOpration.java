/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import doudizhu.Card;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/12
 * 适配者2：单张牌操纵类
 */
public class CardOpration {

    //移动效果的函数,用于发牌,添加都适配器中
    public static void move(Card card, Point from, Point to) {
        if (to.x != from.x) {
            double k = (1.0) * (to.y - from.y) / (to.x - from.x);
            double b = to.y - to.x * k;
            int flag = 0;//判断向左还是向右移动步幅
            if (from.x < to.x) {
                flag = 20;
            } else {
                flag = -20;
            }
            for (int i = from.x; Math.abs(i - to.x) > 20; i += flag) {
                double y = k * i + b;//这里主要用的数学中的线性函数

                card.setLocation(i, (int) y);
                try {
                    Thread.sleep(1); //延迟，可自己设置
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        //位置校准
        card.setLocation(to);
    }

    //返回花色,移到适配器中
    public static int getColor(Card card) {
        return Integer.parseInt(card.getName().substring(0, 1));
    }

    //返回单张牌的值
    public static int getValue(Card card) {
        String name = card.getName();
        int i = Integer.parseInt(name.substring(2, name.length()));
        if (name.substring(2, card.getName().length()).equals("2")) {
            i += 13;
        }
        if (name.substring(2, name.length()).equals("1")) {
            i += 13;
        }
        if (CardOpration.getColor(card) == 5) {
            i += 2;//是王
        }
        return i;
    }
}




