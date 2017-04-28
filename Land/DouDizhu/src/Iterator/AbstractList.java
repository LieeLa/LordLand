/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iterator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/15
 * 抽象聚合类
 */
public abstract class AbstractList {

    protected List<Object> objects = new ArrayList<Object>();

    public AbstractList(List list) {
        this.objects = list;
    }

    public void add(Object obj) {
        this.objects.add(obj);
    }

    public void remove(Object obj) {
        this.objects.remove(obj);
    }

    public Object get(int i) {
        return this.objects.get(i);
    }
    
    public List getObject() {
        return this.objects;
    }

    public int size() {
        return this.objects.size();
    }

    public void removeAll(List list) {
        this.objects.removeAll(list);
    }

    public void clear(List list) {
        this.objects.clear();
    }

    //实现创建迭代器对象的具体工厂方法
    public abstract Iterator createIterator();
}
