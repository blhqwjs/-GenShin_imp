package com.example.genshin_imp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshin_imp.Dao_Controller.CharacterManager;
import com.example.genshin_imp.monster.AddMonsterActivity;
import com.example.genshin_imp.monster.ManageMonsterActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private CharacterManager characterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        characterManager = new CharacterManager(this); // 实例化CharacterManager

        Button viewCharactersButton = findViewById(R.id.button_view_characters);
        Button addCharacterButton = findViewById(R.id.button_add_character);
        Button manageEquipmentButton = findViewById(R.id.button_manage_monster);
        Button addMonsterButton = findViewById(R.id.button_add_monster);

        viewCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到查看角色页面
                Intent viewCharactersIntent = new Intent(MainActivity.this, CharacterListActivity.class);
                startActivity(viewCharactersIntent);
            }
        });

        addCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到角色管理页面
                Intent addCharacterIntent = new Intent(MainActivity.this, AddCharacterActivity.class);
                startActivity(addCharacterIntent);
            }
        });

        manageEquipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到管理怪物页面
                Intent manageEquipmentIntent = new Intent(MainActivity.this, ManageMonsterActivity.class);
                 startActivity(manageEquipmentIntent);
            }
        });
        addMonsterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到增加怪物页面
                Intent addMonsterButton = new Intent(MainActivity.this, AddMonsterActivity.class);
                 startActivity(addMonsterButton);
            }
        });


//        recyclerView = findViewById(R.id.recycler_view_characters);
        // 找到RecyclerView并初始化
        recyclerView = findViewById(R.id.recycler_view_characters);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 获取角色数据
//        List<Character> characterList = getCharacterData(); // 假设这是你获取角色数据的方法
//        adapter = new CharacterAdapter(characterList); // 将获取到的数据传递给 Adapter
        // 从数据库中获取 Cursor 对象 **可用**
//        Cursor cursor = characterManager.getAllCharacters();
        // 初始化适配器并传递 Cursor
//        adapter = new CharacterAdapter(cursor);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
    }

    // 这是一个模拟获取角色数据的方法，你需要替换成你自己的方法
    private List<Character> getCharacterData() {
        List<Character> characters = new ArrayList<>();

        // 假设这里是从数据库或其他地方获取角色数据的逻辑，将数据加入 characters 列表中

        return characters;
    }
}




