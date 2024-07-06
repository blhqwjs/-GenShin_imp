package com.example.genshin_imp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.genshin_imp.Dao_Controller.CharacterManager;

public class AddCharacterActivity extends AppCompatActivity {
    private EditText nameEditText, attributeEditText, abilityEditText, elementEditText, likeEditText;
    private EditText attackEditText, elementalDamageEditText, criticalRateEditText, criticalDamageEditText;
    private Button saveButton;
    private CharacterManager characterManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_character);
        // 实例化布局中的各个视图元素
        nameEditText = findViewById(R.id.edit_text_name);
        attributeEditText = findViewById(R.id.edit_text_attribute);
        abilityEditText = findViewById(R.id.edit_text_ability);
        elementEditText = findViewById(R.id.edit_text_element);
        likeEditText = findViewById(R.id.edit_text_like);
        attackEditText = findViewById(R.id.edit_text_attack);
        elementalDamageEditText = findViewById(R.id.edit_text_elemental_damage);
        criticalRateEditText = findViewById(R.id.edit_text_critical_rate);
        criticalDamageEditText = findViewById(R.id.edit_text_critical_damage);
        saveButton = findViewById(R.id.button_save);
        characterManager = new CharacterManager(this); // 初始化 CharacterManager
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入的角色信息
                String name = nameEditText.getText().toString().trim();
                String attribute = attributeEditText.getText().toString().trim();
                String ability = abilityEditText.getText().toString().trim();
                String element = elementEditText.getText().toString().trim();
                String like = likeEditText.getText().toString().trim();
                int attack = Integer.parseInt(attackEditText.getText().toString().trim());
                double elementalDamage = Double.parseDouble(elementalDamageEditText.getText().toString().trim());
                double criticalRate = Double.parseDouble(criticalRateEditText.getText().toString().trim());
                double criticalDamage = Double.parseDouble(criticalDamageEditText.getText().toString().trim());
                // 插入角色信息到数据库中
                long id = characterManager.insertCharacter(name, attribute, ability, element, like, attack, elementalDamage, criticalRate, criticalDamage);

                if (id != -1) {
                    Toast.makeText(AddCharacterActivity.this, "角色添加成功", Toast.LENGTH_SHORT).show();
                    finish(); // 结束当前 Activity
                } else {
                    Toast.makeText(AddCharacterActivity.this, "添加失败，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
