/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

import doudizhu.PlayerInfor;

/**
 *
 * @author 聂娜 
 * 完成时间：2017/04/21
 * 备忘录类
 */
public class Memento {

    private PlayerInfor infor;

    public Memento(Originator o) {
        infor = o.getInfor();
    }

    public void setInfor(PlayerInfor infor) {
        this.infor = infor;
    }

    public PlayerInfor getInfor() {
        return this.infor;
    }
}
