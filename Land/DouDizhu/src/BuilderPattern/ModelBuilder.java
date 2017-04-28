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
 * 建造者模式中的抽象建造者：牌的类型的抽象建造者
 */
public abstract class ModelBuilder {

    protected Model1 model = new Model1();
    public abstract void builA1(List<Card> list);
    public abstract void buildA2(List<Card> list);
    public abstract void buildA3(List<Card> list);
    public abstract void buildA123(List<Card> list);
    public abstract void buildA112233(List<Card> list);
    public abstract void buildA111222(List<Card> list);
    public abstract void buildA4(List<Card> list);
    public Model1 createModel() {
        return model;
    }
}