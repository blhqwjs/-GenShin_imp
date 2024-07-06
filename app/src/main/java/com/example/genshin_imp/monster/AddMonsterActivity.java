package com.example.genshin_imp.monster;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.genshin_imp.Dao_Controller.MonsterManager;
import com.example.genshin_imp.R;

public class AddMonsterActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText attackEditText;
    private EditText defenseEditText;
    private EditText levelEditText;
    private EditText elementTypeEditText;
    private EditText healthEditText;
    private MonsterManager monsterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_monster);

        monsterManager = new MonsterManager(this);

        nameEditText = findViewById(R.id.edit_text_name);
        attackEditText = findViewById(R.id.edit_text_attack);
        defenseEditText = findViewById(R.id.edit_text_defense);
        levelEditText = findViewById(R.id.edit_text_level);
        elementTypeEditText = findViewById(R.id.edit_text_element_type);
        healthEditText = findViewById(R.id.edit_text_health);

        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMonster();
            }
        });
    }

    private void addMonster() {
        String name = nameEditText.getText().toString();
        int attack = Integer.parseInt(attackEditText.getText().toString());
        int defense = Integer.parseInt(defenseEditText.getText().toString());
        int level = Integer.parseInt(levelEditText.getText().toString());
        String elementType = elementTypeEditText.getText().toString();
        int health = Integer.parseInt(healthEditText.getText().toString());

        long id = monsterManager.insertMonster(name, attack, defense, level, elementType, health);
        if (id != -1) {
            // 添加成功，可以显示成功提示或返回上一个界面
            Toast.makeText(this, "怪物添加成功", Toast.LENGTH_SHORT).show();
            finish(); // 结束当前 Activity 返回上一个界面
        } else {
            // 添加失败，可以显示失败提示
            Toast.makeText(this, "怪物添加失败", Toast.LENGTH_SHORT).show();
        }
    }
}
