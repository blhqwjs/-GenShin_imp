//package com.example.genshin_imp.Attack;
//
//import com.example.genshin_imp.Dao_Controller.Monster;
//
//public class GameLogic {
//    public void attackMonster(Character character, Monster monster) {
//        // 计算伤害
//        double damage = calculateDamage(character, monster);
//
//        // 更新怪物生命值
//        int updatedHealth = monster.getHealth() - (int) damage;
//        monster.setHealth(updatedHealth);
//
//        // 检查怪物生命值
//        if (updatedHealth <= 0) {
//            // 怪物死亡，可能触发奖励等
//        }
//    }
//
//    private double calculateDamage(Character character, Monster monster) {
//        // 计算伤害
//        int characterAttack = character.getAttack();
//        double damageBonus = character.getElementalDamageBonus(); // 伤害倍率
//        double elementalReaction = 1.0; // 元素反应倍率，这里暂时设置为1.0
//
//        int monsterDefense = monster.getDefense();
//        double monsterResistance = monster.getResistance() * (1 + 0.001 * monster.getLevel()); // 怪物抗性
//
//        // 计算伤害值
//        double damage = characterAttack * damageBonus * elementalReaction - (monsterDefense * monsterResistance);
//        return damage > 0 ? damage : 0; // 伤害不能为负值
//    }
//}
