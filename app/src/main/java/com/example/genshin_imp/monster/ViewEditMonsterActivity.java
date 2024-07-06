package com.example.genshin_imp.monster;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.genshin_imp.Dao_Controller.Monster;
import com.example.genshin_imp.Dao_Controller.MonsterManager;
import com.example.genshin_imp.R;

//这是具体的编辑页面
public class ViewEditMonsterActivity extends AppCompatActivity {
    private MonsterManager monsterManager;
    private Monster selectedMonster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_monster);

        monsterManager = new MonsterManager(this);
        int selectedMonsterId = getIntent().getIntExtra("monster_id", 0);
        selectedMonster = monsterManager.getMonsterById(selectedMonsterId);


        if (selectedMonster != null) {
            TextView nameTextView = findViewById(R.id.text_view_monster_name);
            nameTextView.setText(selectedMonster.getName());

            final EditText attackEditText = findViewById(R.id.edit_text_attack);
            final EditText defenseEditText = findViewById(R.id.edit_text_defense);
            final EditText healthEditText = findViewById(R.id.edit_text_health);
            final EditText levelEditText = findViewById(R.id.edit_text_level);

            attackEditText.setText(String.valueOf(selectedMonster.getAttack()));
            defenseEditText.setText(String.valueOf(selectedMonster.getDefense()));
            healthEditText.setText(String.valueOf(selectedMonster.getHealth()));
            levelEditText.setText(String.valueOf(selectedMonster.getLevel()));

            Button saveButton = findViewById(R.id.button_save_changes);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String editedAttack = attackEditText.getText().toString();
                    String editedDefense = defenseEditText.getText().toString();
                    String editedHealth = healthEditText.getText().toString();
                    String editedLevel = levelEditText.getText().toString();

                    selectedMonster.setAttack(Integer.parseInt(editedAttack));
                    selectedMonster.setDefense(Integer.parseInt(editedDefense));
                    selectedMonster.setHealth(Integer.parseInt(editedHealth));
                    selectedMonster.setLevel(Integer.parseInt(editedLevel));
                    monsterManager.updateMonster(selectedMonster);
                    finish();
                }
            });
        }
        Button deleteButton = findViewById(R.id.button_delete_monster);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 弹出对话框以确认删除
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewEditMonsterActivity.this);
                builder.setMessage("确定删除怪物？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteMonster(); // 执行删除操作
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }
        private void deleteMonster(){
            if (selectedMonster != null) {
                int monsterId = selectedMonster.getId(); // 获取怪物的 ID

                // 在这里执行删除操作（例如：从数据库中删除怪物）
                if (monsterManager != null) {
                    monsterManager.deleteMonster(monsterId);
                    finish(); // 删除完成后关闭当前活动
                }
            }
        }

}

