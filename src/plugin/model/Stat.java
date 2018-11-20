package plugin.model;

import java.io.Serializable;

public class Stat implements Serializable {
    static final long serialVersionUID = 1L;

    //힘(%)
    private int power;
    //마력(마나)
    private int mana;
    //손재주
    private int dexterity;
    //행운(%)
    private int luck;
    //이동속도(%)
    private int speed;
    //돈
    private long money;

    public Stat() {
        power = 100;
        mana = 10;
        dexterity = 10;
        luck = 0;
        speed = 100;
        money = 0;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getMana() {
        return mana;
    }

    public int getLuck() {
        return luck;
    }

    public int getPower() {
        return power;
    }

    public int getSpeed() {
        return speed;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
