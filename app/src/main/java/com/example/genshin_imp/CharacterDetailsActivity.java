package com.example.genshin_imp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.genshin_imp.Dao_Controller.Character;
import com.example.genshin_imp.Dao_Controller.CharacterManager;
import com.example.genshin_imp.Dao_Controller.Monster;
import com.example.genshin_imp.Dao_Controller.MonsterManager;
import com.example.genshin_imp.Videos.VideosActivity;
import com.example.genshin_imp.WebView.WebViewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class CharacterDetailsActivity extends AppCompatActivity {
    private CharacterManager characterManager;
    private MonsterManager monsterManager;
    private Cursor selectedCharacter;
    private Character selectedCharacter_attack;
    private Spinner spinnerMonsters;
    private Button buttonFightMonster;
    private Button playVideos;
    private Button webView;
    private TextView textViewDamageResult;
    private String name;
    private String element;
    private int attack;
    private double elementalDamageBonus;
    private double criticalRate ;
    private double criticalDamage;
    // 假设角色和怪物数据已经填充到对应的列表中
    private List<Monster> monstersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        characterManager = new CharacterManager(this);
        monsterManager = new MonsterManager(this);
        monstersList = monsterManager.getAllMonsters();
        final int selectedCharacterId = getIntent().getIntExtra("character_id", 0);
        characterManager = new CharacterManager(this);
        selectedCharacter = characterManager.getCharacterById(selectedCharacterId);
        selectedCharacter_attack = characterManager.getCharacterById_Char(selectedCharacterId);
        if (selectedCharacter != null && selectedCharacter.moveToFirst()) {
            // 获取角色的各项属性值
            name = selectedCharacter.getString(selectedCharacter.getColumnIndex("name"));
            String attribute = selectedCharacter.getString(selectedCharacter.getColumnIndex("attribute"));
            String ability = selectedCharacter.getString(selectedCharacter.getColumnIndex("ability"));
            element = selectedCharacter.getString(selectedCharacter.getColumnIndex("element"));
            String like = selectedCharacter.getString(selectedCharacter.getColumnIndex("liked"));
            attack = selectedCharacter.getInt(selectedCharacter.getColumnIndex("attack"));
            elementalDamageBonus = selectedCharacter.getDouble(selectedCharacter.getColumnIndex("elementalDamageBonus"));
            criticalRate = selectedCharacter.getDouble(selectedCharacter.getColumnIndex("criticalRate"));
            criticalDamage = selectedCharacter.getDouble(selectedCharacter.getColumnIndex("criticalDamage"));


            // 填充布局视图中的 TextView（示例中的文本视图 ID 根据实际布局文件中的定义修改）
            TextView nameTextView = findViewById(R.id.text_view_name);
            TextView attributeTextView = findViewById(R.id.text_view_attribute);
            TextView abilityTextView = findViewById(R.id.text_view_ability);
            TextView elementTextView = findViewById(R.id.text_view_element);
            TextView likeTextView = findViewById(R.id.text_view_like);
            TextView attackTextView = findViewById(R.id.text_view_attack);
            TextView elementalDamageBonusTextView = findViewById(R.id.text_view_elemental_damage);
            TextView criticalRateTextView = findViewById(R.id.text_view_critical_rate);
            TextView criticalDamageTextView = findViewById(R.id.text_view_critical_damage);


            // 设置 TextView 显示角色详细信息
            nameTextView.setText("角色名：" + name);
            attributeTextView.setText("属性：" + attribute);
            abilityTextView.setText("技能：" + ability);
            elementTextView.setText("元素：" + element);
            likeTextView.setText("喜欢程度：" + like);
            attackTextView.setText("攻击力：" + attack);
            elementalDamageBonusTextView.setText("元素伤害加成：" + elementalDamageBonus);
            criticalRateTextView.setText("暴击率：" + criticalRate);
            criticalDamageTextView.setText("暴击伤害：" + criticalDamage);
        }

        //shang hai
        // 初始化视图
        spinnerMonsters = findViewById(R.id.spinner_monsters);
        buttonFightMonster = findViewById(R.id.button_fight_monster);
        textViewDamageResult = findViewById(R.id.text_view_damage_result);
        playVideos =findViewById(R.id.button_play_videos);
        webView =findViewById(R.id.button_web_view);

        //官网webView
        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CharacterDetailsActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });


        // 看角色pv处理:
        playVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharacterDetailsActivity.this, VideosActivity.class);
                //为实现看的是对应的当前角色的pv,要获取名字并传递
                intent.putExtra("character_name", name); // 将角色名字传递到 VideosActivity
                startActivity(intent);
            }
        });

        // 假设已填充角色和怪物列表数据
        List<String> monsterNames = new ArrayList<>();
        for (Monster monster : monstersList) {
            monsterNames.add(monster.getName()); // 假设 Monster 类中有一个方法 getName() 返回怪物名字
        }
        // 设置怪物列表适配器以显示怪物名字
        ArrayAdapter<String> monsterAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, monsterNames);
        monsterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonsters.setAdapter(monsterAdapter);

        // 设置点击事件
        buttonFightMonster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDamage(); // 计算伤害
            }
        });

        Button modifyPropertiesButton = findViewById(R.id.button_modify_properties);

        modifyPropertiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharacterDetailsActivity.this, EditPropertiesActivity.class);
                intent.putExtra("character_id", selectedCharacterId); // 传递选定的角色ID
                startActivity(intent);
            }
        });
        Button deleteButton = findViewById(R.id.button_delete_character);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 弹出对话框以确认删除
                AlertDialog.Builder builder = new AlertDialog.Builder(CharacterDetailsActivity.this);
                builder.setMessage("确定删除角色？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteCharacter(); // 执行删除操作
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }

    private void deleteCharacter() {
        // 检查是否选择了角色
        if (selectedCharacter != null && selectedCharacter.moveToFirst()) {
            int characterId = selectedCharacter.getInt(selectedCharacter.getColumnIndex("id")); // 获取角色的 ID

            // 在这里执行删除操作（例如：从数据库中删除角色）
            if (characterManager != null) {
                characterManager.deleteCharacter(characterId);
                finish(); // 删除完成后关闭当前活动
            }
        }
    }

    private void calculateDamage() {
        String selectedMonsterName = (String) spinnerMonsters.getSelectedItem();
        Monster selectedMonster = null;
        for (Monster monster : monstersList) {
            if (monster.getName().equals(selectedMonsterName)) {
                selectedMonster = monster;
                break;
            }
        }
        String damage = calculateDamageValue(selectedMonster);

        textViewDamageResult.setText(damage);
//            System.out.println(damage);
        // 输出到日志
        Log.d("DamageLog",  damage);
    }

    private String calculateDamageValue( Monster monster) {
        Random random=new Random();
        if (monster.getName().contains("史莱姆")&&element.equals(monster.getElement()))
        {
            return "免疫！ 免疫！ ";
        }
        // 火冰 融化
        else if(element.equals("火")&&monster.getElement().equals("冰")||element.equals("火")&&monster.getElement().equals("bing")||(element.equals("冰")&&monster.getElement().equals("火"))||(element.equals("bing")&&monster.getElement().equals("火"))){
            if (criticalRate >= random.nextDouble()) {
                double damage = attack * (1 + elementalDamageBonus) * criticalDamage * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发融化！ 暴击了！！" + "伤害值: " + 2*damage;
            } else {
                double damage = attack * (1 + elementalDamageBonus) * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发融化！  普通一击" + "伤害值: " + 2*damage;
            }
        }
        //水冰 冻结
        else if(element.equals("水")&&monster.getElement().equals("冰")||(element.equals("冰")&&monster.getElement().equals("水"))){
            if (criticalRate >= random.nextDouble()) {
                double damage = attack * (1 + elementalDamageBonus) * criticalDamage * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发冻结！ 暴击了！！" + "伤害值: " + 1.1*damage;
            } else {
                double damage = attack * (1 + elementalDamageBonus) * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发冻结！  普通一击" + "伤害值: " + 1.1*damage;
            }
        }
        // 水火蒸发
        else if(element.equals("火")&&monster.getElement().equals("水")||(element.equals("水")&&monster.getElement().equals("火"))){
            if (criticalRate >= random.nextDouble()) {
                double damage = attack * (1 + elementalDamageBonus) * criticalDamage * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发蒸发！ 暴击了！！" + "伤害值: " + 1.75*damage;
            } else {
                double damage = attack * (1 + elementalDamageBonus) * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发蒸发！  普通一击" + "伤害值: " + 1.75*damage;
            }
        }
        // 雷火超载
        else if(element.equals("火")&&monster.getElement().equals("雷")||(element.equals("雷")&&monster.getElement().equals("火"))){
            if (criticalRate >= random.nextDouble()) {
                double damage = attack * (1 + elementalDamageBonus) * criticalDamage * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发超载！ 暴击了！！" + "伤害值: " + 1.35*damage;
            } else {
                double damage = attack * (1 + elementalDamageBonus) * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发超载！  普通一击" + "伤害值: " + 1.35*damage;
            }
        }
        // 雷水导电
        else if(element.equals("水")&&monster.getElement().equals("雷")||(element.equals("雷")&&monster.getElement().equals("水"))){
            if (criticalRate >= random.nextDouble()) {
                double damage = attack * (1 + elementalDamageBonus) * criticalDamage * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发导电！ 暴击了！！" + "伤害值: " + 1.25*damage;
            } else {
                double damage = attack * (1 + elementalDamageBonus) * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发导电！  普通一击" + "伤害值: " + 1.25*damage;
            }
        }
        // 雷冰超导
        else if(element.equals("冰")&&monster.getElement().equals("雷")||(element.equals("雷")&&monster.getElement().equals("冰"))){
            if (criticalRate >= random.nextDouble()) {
                double damage = attack * (1 + elementalDamageBonus) * criticalDamage * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发超导！ 暴击了！！" + "伤害值: " + 1.32*damage;
            } else {
                double damage = attack * (1 + elementalDamageBonus) * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发超导！  普通一击" + "伤害值: " + 1.32*damage;
            }
        }
        // 雷草激化
        else if(element.equals("草")&&monster.getElement().equals("雷")||(element.equals("雷")&&monster.getElement().equals("草"))){
            if (criticalRate >= random.nextDouble()) {
                double damage = attack * (1 + elementalDamageBonus) * criticalDamage * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发激化！ 暴击了！！" + "伤害值: " + 1.82*damage;
            } else {
                double damage = attack * (1 + elementalDamageBonus) * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发激化！  普通一击" + "伤害值: " + 1.82*damage;
            }
        }
        // 水草绽放
        else if(element.equals("草")&&monster.getElement().equals("水")||(element.equals("水")&&monster.getElement().equals("草"))){
            if (criticalRate >= random.nextDouble()) {
                double damage = attack * (1 + elementalDamageBonus) * criticalDamage * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发绽放！ 暴击了！！" + "伤害值: " + damage +"  形成草原核 ： 绽放···"+" 伤害值： "+0.66*damage;
            } else {
                double damage = attack * (1 + elementalDamageBonus) * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "触发绽放！  普通一击" + "伤害值: " + damage+"  形成草原核 ： 绽放···" + " 伤害值： "+0.66*damage;
            }
        }
        else {
            if (criticalRate >= random.nextDouble()) {
                double damage = attack * (1 + elementalDamageBonus) * criticalDamage * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "暴击了！！" + "伤害值: " + damage;
            } else {
                double damage = attack * (1 + elementalDamageBonus) * 2.01 - (monster.getDefense() * (1 + 0.001 * monster.getLevel()));
                return "普通一击！！" + "伤害值: " + damage;
            }
        }
    }

//    private void calculateDamage() {
//        // 获取选定的怪物名字和角色
//        String selectedMonsterName = (String) spinnerMonsters.getSelectedItem();
//        // 根据选定的怪物名字在列表中找到对应的怪物对象
//        Monster selectedMonster = null;
//        for (Monster monster : monstersList) {
//            if (monster.getName().equals(selectedMonsterName)) {
//                selectedMonster = monster;
//                break;
//            }
//        }
//        if (selectedCharacter_attack != null && selectedMonster != null) {
//            // 计算伤害值
//            double damage = calculateDamageValue(selectedCharacter_attack, selectedMonster);
//            // 显示伤害值
//            textViewDamageResult.setText("伤害值: " + damage);
//        }
//    }
//
//    private double calculateDamageValue(Character character, Monster monster) {
//        // 根据角色属性和怪物属性计算伤害值，并返回结果
//        // 假设这里进行了伤害值的计算
//        // 伤害值=角色攻击力*伤害倍率*元素反应倍率-（怪物防御力*怪物抗性（1+0.001*怪物等级））
//        double damage = character.getAttack() * character.getElementalDamageBonus() * 2.01-(monster.getDefense()*(1+0.001*monster.getLevel()));
//        return damage;
//    }


}

