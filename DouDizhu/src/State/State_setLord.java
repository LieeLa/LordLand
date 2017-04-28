/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

import Adapter.Adapter_Card;
import Adapter.Operation;
import doudizhu.Card;
import doudizhu.playWin;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/16
 * 环境类：调用状态类，决定最终的地主
 *
 */
public class State_setLord {

    private Lord lord;
    private int lordflag = 0;

    //设置状态对象
    public void setState(playWin main, Lord lord) {
        this.lord = lord;
        this.lordflag = lord.changeLoard(main, lordflag);
    }

    //获取最后的地主情况
    public int lordFinal(playWin main) {
        Operation operation = Adapter_Card.getIntance();
        //如果是1号玩家当地主的话
        if (lordflag == 1) {
            List<Card> list1 = main.getPlayerList(1);
            list1.addAll(main.getLordList());
            openlord(true, main);
            second(2);// 等待2秒
            operation.order(main.getPlayerList(1));
            operation.rePosition(main, main.getPlayerList(1), 1);
            setlord(1, main);
        } else {
            //如果是电脑0号或电脑2号当地主的话
            setlord(lordflag, main);// 设定地主
            openlord(true, main);
            second(1);
            List<Card> playlist_other = main.getPlayerList(lordflag);
            playlist_other.addAll(main.getLordList());
            operation.order(playlist_other);
            operation.rePosition(main,playlist_other, lordflag);
            openlord(false, main);
        }
        return this.lordflag;
    }

    // 地主牌翻看
    public void openlord(boolean is, playWin main) {
        for (int i = 0; i < 3; i++) {
            Card card = main.getLordList().get(i);
            if (is) {
                card.turnFront(); // 地主牌翻看
            } else {
                card.turnRear(); // 地主牌闭合
            }
             card.setCanlick(true);// 可被点击
        }
    }

    // 等待i秒
    public void second(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 设定地主（不是最后结果）
    public void setlord(int i, playWin main) {
        Point point = new Point();
        int DizhuFlag = main.getDizhuFlag();
        if (i == 1)// 我是地主
        {
            point.x = 80;
            point.y = 430;
            DizhuFlag = 1;// 设定地主
        }
        if (i == 0) {
            point.x = 80;
            point.y = 20;
            DizhuFlag = 0;
        }
        if (i == 2) {
            point.x = 700;
            point.y = 20;
            DizhuFlag = 2;
        }
        main.getDizhu().setLocation(point);
        main.getDizhu().setVisible(true);
    }
}
