/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitor;

/**
 *
 * @author 聂娜
 *  完成时间：2017/04/22
 * 抽象元素
 */
public interface Element {
    public void accept(Visitor visitor);
}
