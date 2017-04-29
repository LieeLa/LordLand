/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doudizhu;

/**
 *
 * @author 聂娜
 * 牌的类型
 * 完成时间：2017/04/08
 */
public enum CardType {
	c1,//单牌。
	c2,//对子。
	c3,//3不带。
	c4,//炸弹。
	c31,//3带1。
	c32,//3带2。
	c411,//4带2个单，或者一对
	c422,//4带2对
	c123,//连子。
	c1122,//连队。
	c111222,//飞机。
	c11122234,//飞机带单排.
	c1112223344,//飞机带对子.
	c0//不能出牌
}