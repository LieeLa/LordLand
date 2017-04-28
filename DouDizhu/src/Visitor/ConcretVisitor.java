/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visitor;

import doudizhu.PlayerInfor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/22
 * 具体访问者
 */
public class ConcretVisitor extends Visitor {
    int score;
    int goldenBean;
    int id;
    int winFlag;

    @Override
    public void visit(ConcreteElementLord lord) {
        score = lord.getScore();
        goldenBean = lord.getGoldenBean();
        id = lord.getPlayerID();
        winFlag = lord.getWinFlag();
        if (winFlag == 1) {
            score += 2;
            goldenBean += 20;
            try {
                PlayerInfor player = new PlayerInfor();
                player.setSingelInfo(id, goldenBean, score);
            } catch (SQLException ex) {
                Logger.getLogger(ConcretVisitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void visit(ConcreteElementFarmer farmer) {
        score = farmer.getScore();
        goldenBean = farmer.getGoldenBean();
        id = farmer.getPlayerID();
        winFlag = farmer.getWinFlag();
        if (winFlag == 1) {
            score += 1;
            goldenBean += 10;
            try {
                PlayerInfor player = new PlayerInfor();
                player.setSingelInfo(id, goldenBean, score);
            } catch (SQLException ex) {
                Logger.getLogger(ConcretVisitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

