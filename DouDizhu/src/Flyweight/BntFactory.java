/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flyweight;

import java.util.Hashtable;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/14
 * 按钮工厂类
 */
public class BntFactory {

    private static Hashtable ht;
    private BntFactory() {
        ht = new Hashtable();
        AbstractButton loard, noloard, play, noplay;
        loard = new getloardLand();
        //loard.setName();
        ht.put("loard", loard);
        noloard = new No_getLoardLand();
        // noloard.setName();
        ht.put("noloard", noloard);
        play = new playCard();
        //play.setName();
        ht.put("play", play);
        noplay = new No_playCard();
        //noplay.setName();
        ht.put("noplay", noplay);
    }

    private static class HolderClass {

        private final static BntFactory instance = new BntFactory();
    }

    public static BntFactory getInstance() {
        return HolderClass.instance;
    }

    public static AbstractButton getFlyweightButton(String name) {
        return (AbstractButton) ht.get(name);
    }
}
