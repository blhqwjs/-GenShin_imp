package com.example.genshin_imp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.genshin_imp.Dao_Controller.CharacterManager;

//修改角色属性
public class EditPropertiesActivity extends AppCompatActivity {
    private CharacterManager characterManager;
    private int characterId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        characterManager = new CharacterManager(this);
        // 获取传递的角色ID
        characterId = getIntent().getIntExtra("character_id", 0);

        // 其他视图组件获取...
        setContentView(R.layout.activity_edit_properties);
        final EditText newAttributeEditText = findViewById(R.id.edit_text_new_attribute);
//        final EditText name = findViewById(R.id.edit_text_name);
        final EditText attackEditText = findViewById(R.id.edit_text_attack);
        final EditText elementalDamageEditText = findViewById(R.id.edit_text_elemental_damage);
        final EditText criticalRateEditText = findViewById(R.id.edit_text_critical_rate);
        final EditText criticalDamageEditText = findViewById(R.id.edit_text_critical_damage);
        Button confirmModifyButton = findViewById(R.id.button_confirm_modify);

        confirmModifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入的新属性值
                String newAttribute = newAttributeEditText.getText().toString().trim();
//                String newName = name.getText().toString().trim();
                int attack = Integer.parseInt(attackEditText.getText().toString().trim());
                double elementalDamage = Double.parseDouble(elementalDamageEditText.getText().toString().trim());
                double criticalRate = Double.parseDouble(criticalRateEditText.getText().toString().trim());
                double criticalDamage = Double.parseDouble(criticalDamageEditText.getText().toString().trim());

                // 将这些值应用到数据库中
                 characterManager.updateCharacter(characterId,newAttribute, attack, elementalDamage, criticalRate, criticalDamage);

                // 完成修改后，结束当前活动
                finish();
            }
        });
    }
}
