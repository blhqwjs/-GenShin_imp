package com.example.genshin_imp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshin_imp.Dao_Controller.CharacterManager;

public class CharacterListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private CharacterManager characterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        characterManager = new CharacterManager(this);
        recyclerView = findViewById(R.id.recycler_view_characters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor = characterManager.getAllCharacters(); // 获取数据库中的角色数据
        adapter = new CharacterAdapter(cursor); // 使用 Cursor 初始化适配器
        recyclerView.setAdapter(adapter);
        final EditText searchEditText = findViewById(R.id.edit_text_search);
        Button searchButton = findViewById(R.id.button_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = searchEditText.getText().toString().trim();
                Cursor cursor;

                if (!searchQuery.isEmpty()) {
                    // 根据搜索查询从数据库中获取结果
                    cursor = characterManager.getCharacterByName(searchQuery);
                } else {
                    // 获取所有角色数据
                    cursor = characterManager.getAllCharacters();
                }

                adapter.changeCursor(cursor); // 更新适配器中的 Cursor 数据
            }
        });

        adapter.setOnItemClickListener(new CharacterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 获取点击的角色 ID
                Cursor cursor = adapter.getCursor(position);
                int characterId = cursor.getInt(cursor.getColumnIndex("id"));

                // 启动角色详情页面并传递角色 ID
                Intent intent = new Intent(CharacterListActivity.this, CharacterDetailsActivity.class);
                intent.putExtra("character_id", characterId);
                startActivity(intent);
            }
        });
    }
}


