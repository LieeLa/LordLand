/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuilderPattern;

import doudizhu.Card;
import java.util.List;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/09
 * 建造者模式中的指挥者：牌的类型创建指挥者
 */
public class ModelController {
    public Model1 construct(ModelBuilder ab, List<Card> list) {
        Model1 model;
        //------先拆炸弹
        ab.buildA4(list);
        //------拆3带
        ab.buildA3(list);
        //拆飞机
        ab.buildA111222(list);
        //------拆对子
        ab.buildA2(list);
        //拆连队
        ab.buildA112233(list);
        //拆顺子
        ab.buildA123(list);
        //拆单
        ab.builA1(list);
        model = ab.createModel();
        return model;
    }
}


