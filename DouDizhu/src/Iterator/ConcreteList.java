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
 * 具体聚合类
 */
public class ConcreteList extends AbstractList {

    public ConcreteList(List list) {
        super(list);
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }
}
