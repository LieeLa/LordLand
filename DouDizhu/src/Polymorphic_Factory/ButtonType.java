/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polymorphic_Factory;

import doudizhu.InforWin;
import doudizhu.heplWin;
import doudizhu.playWin;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author 聂娜 
 * 完成时间：2017/04/07
 * 工厂方法模式的抽象产品类：ButtonType
 * 
 */
public interface ButtonType {

    public JButton initButton(String content);
}