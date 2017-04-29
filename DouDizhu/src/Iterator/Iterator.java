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
 * 抽象迭代器类
 */
public interface Iterator {
    public Object next();              //将游标指向下一个元素
    public boolean hasNext();        //判断是否存在下一个元素
}





