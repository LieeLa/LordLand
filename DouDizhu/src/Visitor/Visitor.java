/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 聂娜 
 *  完成时间：2017/04/22
 * 访问者模式
 * 地主和农民为两类元素
 * 游戏结束的时候，根据胜利情况给加分
 * 地主赢了：分数+2分，金豆数+10;
 * 农民赢了：分数+1分，金豆数+5.
 * 抽象访问者
 */
public abstract class Visitor {
    //实现对地主得分情况的操作
    public abstract void visit(ConcreteElementLord lord);
    //实现对农名得分情况的操作
    public abstract void visit(ConcreteElementFarmer farmer);
}



