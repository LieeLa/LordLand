/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flyweight;

/**
 *
 * @author 聂娜 
 * 非共享具体享元类：给各个按钮设置坐标类
 * 完成时间：2017/04/14
 */
public class Buttonlocation {

    private int l1;     //横坐标
    private int l2;     //纵坐标

    public Buttonlocation(int l1, int l2) {
        this.l1 = l1;
        this.l2 = l2;
    }

    //获取横坐标
    public int getL1() {
        return this.l1;
    }

    //获取纵坐标
    public int getL2() {
        return this.l2;
    }

}
