/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitor;

import java.util.ArrayList;

/**
 *
 * @author 聂娜 
 *  完成时间：2017/04/22
 * 具体元素集合类
 */
public class EmlementList {

    private ArrayList<Element> list = new ArrayList<Element>();

    public void addElement(Element elem) {
        list.add(elem);
    }

    public void accept(Visitor handler) {
        for (Object obj : list) {
            ((Element) obj).accept(handler);
        }
    }
}
