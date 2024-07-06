package com.example.genshin_imp.Dao_Controller;

public class Character {
    private int id;
    private String name;
    private String element;
    private String ability;//技能
    private String weapon;//武器
    private String attribute;//属性
    private int attack; // 攻击力
    private double elementalDamageBonus; // 元素伤害加成
    private double criticalRate; // 暴击率
    private double criticalDamage; // 暴击伤害

    public Character(int id, String name, String element, String ability, String weapon, String attribute, int attack, double elementalDamageBonus, double criticalRate, double criticalDamage) {
        this.id = id;
        this.name = name;
        this.element = element;
        this.ability = ability;
        this.weapon = weapon;
        this.attribute = attribute;
        this.attack = attack;
        this.elementalDamageBonus = elementalDamageBonus;
        this.criticalRate = criticalRate;
        this.criticalDamage = criticalDamage;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public double getElementalDamageBonus() {
        return elementalDamageBonus;
    }

    public void setElementalDamageBonus(double elementalDamageBonus) {
        this.elementalDamageBonus = elementalDamageBonus;
    }

    public double getCriticalRate() {
        return criticalRate;
    }

    public void setCriticalRate(double criticalRate) {
        this.criticalRate = criticalRate;
    }

    public double getCriticalDamage() {
        return criticalDamage;
    }

    public void setCriticalDamage(double criticalDamage) {
        this.criticalDamage = criticalDamage;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
// 添加构造函数、Getter 和 Setter 方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
    // ...
}
