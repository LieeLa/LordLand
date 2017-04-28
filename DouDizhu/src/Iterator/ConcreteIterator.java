/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iterator;

import java.util.List;

/**
 *
 * @author 聂娜 
 * 完成时间：2017/04/15
 * 具体迭代器类
 */
public class ConcreteIterator implements Iterator {

    private ConcreteList object;
    private List list;
    private int cursor;    //游标，用于记录当前访问的位置

    public ConcreteIterator(ConcreteList ls) {
        this.object = ls;
        this.list = ls.getObject();
        cursor = 0;                     //设置游标的初始值
    }

    @Override
    public boolean hasNext() {
        if (cursor < list.size()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        Object obj = null;
        obj = list.get(cursor);
        cursor++;
        return obj;
    }
}
