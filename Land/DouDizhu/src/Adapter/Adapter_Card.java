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
import java.util.List;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/12
 * 操作适配器：适配器
 */
public class Adapter_Card implements Operation {

    ListOperation listopration;
    CardOpration cardopration;

    private Adapter_Card() {
        listopration = new ListOperation();
        cardopration = new CardOpration();
    }

    private static class HolderClass {

        private final static Adapter_Card instance = new Adapter_Card();
    }

    public static Adapter_Card getIntance() {
        return HolderClass.instance;
    }

    @Override
    public CardType jugdeType(List<Card> list) {
        return listopration.jugdeType(list);
    }

    @Override
    public void order(List<Card> list) {
        listopration.order(list);
    }

    @Override
    public void rePosition(playWin m, List<Card> list, int flag) {
        listopration.rePosition(m, list, flag);
    }

    @Override
    public int getScore(List<Card> list) {
        return listopration.getScore(list);
    }

    @Override
    public Model1 getModel(List<Card> list) {
        return listopration.getModel(list);
    }

    @Override
    public void hideCards(List<Card> list) {
        listopration.hideCards(list);
    }

    @Override
    public int checkCards(List<Card> c, List<Card>[] current) {
        return listopration.checkCards(c, current);
    }

    @Override
    public List getOrder2(List<Card> list) {
        return listopration.getOrder2(list);
    }

    @Override
    public void move(Card card, Point from, Point to) {
        CardOpration.move(card, from, to);
    }

    @Override
    public int getColor(Card card) {
        return CardOpration.getColor(card);
    }

    @Override
    public int getValue(Card card) {
        return CardOpration.getValue(card);
    }
}



