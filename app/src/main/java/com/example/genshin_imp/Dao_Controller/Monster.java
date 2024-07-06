package com.example.genshin_imp.Dao_Controller;

public class Monster {
    private int id;
    private String name;
    private int attack;
    private int defense;
    private int level;
    private String element;
    private int health;

    public Monster(int id, String name, int attack, int defense, int level, String element, int health) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.level = level;
        this.element = element;
        this.health = health;
    }

    // Getters and setters for all properties

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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
