/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Memento;

import doudizhu.PlayerInfor;
import java.sql.SQLException;

/**
 *
 * @author 聂娜 
 * 完成时间：2017/04/21
 * 备忘录模式 原发器类
 */
public class Originator {

    private PlayerInfor infor;

    public Originator() throws SQLException {
        infor = new PlayerInfor();
    }

    //创建一个备忘录对象
    public Memento createMemento() {
        return new Memento(this);
    }

    public PlayerInfor restoreMemento(Memento m) {
        infor = m.getInfor();
        return infor;
    }

    public void setInfor(PlayerInfor state) {
        this.infor = infor;
    }

    public PlayerInfor getInfor() {
        return this.infor;
    }
}
