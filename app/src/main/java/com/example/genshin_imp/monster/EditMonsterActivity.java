package com.example.genshin_imp.monster;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.genshin_imp.Dao_Controller.Monster;
import com.example.genshin_imp.Dao_Controller.MonsterManager;
import com.example.genshin_imp.R;

public class EditMonsterActivity extends AppCompatActivity {

    private MonsterManager monsterManager;
    private int monsterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_monster);
        monsterManager = new MonsterManager(this); // 初始化 MonsterManager
        monsterId = getIntent().getIntExtra("id", -1);
//        System.out.println(monsterId);
        final Monster selectedMonster = monsterManager.getMonsterById(monsterId);

        if (selectedMonster != null) {
            // 将怪物信息显示在 TextView 或 EditText 中，并允许用户修改
            TextView monsterNameTextView = findViewById(R.id.text_view_monster_name);
            monsterNameTextView.setText(selectedMonster.getName());

            // 添加其他 TextView 或 EditText 显示和编辑怪物信息
        }
        Button viewEditButton = findViewById(R.id.button_view_monster);
        viewEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 保存用户所做的修改并更新数据库
                // 返回到前一个界面或者执行其他操作
                finish();
            }
        });
        final EditText searchEditText = findViewById(R.id.edit_text_search);
        Button searchButton = findViewById(R.id.button_search_monster);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchEditText.getText().toString().trim();
                performSearch(searchText);
            }
        });

    }
    private void performSearch(String searchText) {
        // 在这里执行根据怪物名称进行搜索的操作
        // 可以通过查询数据库或其他方式来实现
//         Intent searchIntent = new Intent(EditMonsterActivity.this, EditMonsterActivity.class);
//         searchIntent.putExtra("searchText", searchText);
//         startActivity(searchIntent);
        Cursor searchResult = monsterManager.searchMonsterByName(searchText);

    }

}