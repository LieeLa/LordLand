/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;
import java.util.ArrayList;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/16
 * 观察者模式中的抽象目标
 */
public abstract class Observer_Subject {

    protected ArrayList observers = new <Observer_Cardleft>ArrayList();

    //注册方法，用于观察者集合中增加一个观察者
    public void attach(Observer_Cardleft observer) {
        observers.add(observer);
    }

    //注销方法,用于在观察者集合中删除一个观察者
    public void detach(Observer_Cardleft observer) {
        observers.remove(observer);
    }

    //申明抽象通知方法
    public abstract String notifyObserver1(int ListNum);

    public abstract String notifyObserver2(int ListNum);

}
