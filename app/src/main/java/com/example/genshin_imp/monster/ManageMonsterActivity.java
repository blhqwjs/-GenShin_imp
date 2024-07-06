package com.example.genshin_imp.monster;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.genshin_imp.Dao_Controller.Monster;
import com.example.genshin_imp.Dao_Controller.MonsterManager;
import com.example.genshin_imp.R;

import java.util.ArrayList;
import java.util.List;

public class ManageMonsterActivity extends AppCompatActivity {

    private ListView monsterListView;
    private MonsterManager monsterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_monster);

        monsterManager = new MonsterManager(this);
        monsterListView = findViewById(R.id.monster_list_view);

        // 获取怪物数据
        final List<Monster> monsters = new ArrayList<>();
        monsterManager = new MonsterManager(this);
        Cursor cursor = monsterManager.getAllMonstersByCurrsor();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // 从 Cursor 中获取数据并创建 Monster 对象
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int attack = cursor.getInt(cursor.getColumnIndex("attack"));
                int defense = cursor.getInt(cursor.getColumnIndex("defense"));
                int level = cursor.getInt(cursor.getColumnIndex("level"));
                String element = cursor.getString(cursor.getColumnIndex("element"));
                int health = cursor.getInt(cursor.getColumnIndex("health"));

                // 创建 Monster 对象并添加到列表中
                Monster monster = new Monster(id, name, attack, defense, level, element, health);
                monsters.add(monster);
            } while (cursor.moveToNext());
        }


        // 使用自定义适配器将怪物数据绑定到ListView
        MonsterAdapter adapter = new MonsterAdapter(this, monsters);
        monsterListView.setAdapter(adapter);

        // 设置列表项点击事件
        monsterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Monster selectedMonster = monsters.get(position);
                Intent intent = new Intent(ManageMonsterActivity.this, EditMonsterActivity.class);
                intent.putExtra("monster_id", selectedMonster.getId());
                startActivity(intent);
            }
        });
    }
}
