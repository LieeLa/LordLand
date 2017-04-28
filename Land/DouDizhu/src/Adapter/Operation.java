/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import BuilderPattern.Model1;
import doudizhu.Card;
import doudizhu.CardType;
import doudizhu.playWin;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/12
 * 操作类：目标接口类
 */
public interface Operation {

    //判断牌的类型
    public CardType jugdeType(List<Card> list);

    //对list排序
    public void order(List<Card> list);

    //重新定位 flag代表电脑1 ,2 或者是我
    public void rePosition(playWin m, List<Card> list, int flag);

    //地主牌权值，看是否抢地主
    public int getScore(List<Card> list);

    //拆牌，决定电脑每次出的牌
    public Model1 getModel(List<Card> list);

    //隐藏之前出过的牌
    public void hideCards(List<Card> list);

    //检查牌的是否能出
    public int checkCards(List<Card> c, List<Card>[] current);

    //按照重复次数排序
    public List getOrder2(List<Card> list);

    //移动效果的函数,用于发牌,添加都适配器中
    public void move(Card card, Point from, Point to);

    //返回花色,移到适配器中
    public int getColor(Card card);

    //返回值
    public int getValue(Card card);
}

 