package com.example.genshin_imp.monster;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.genshin_imp.Dao_Controller.Monster;
import com.example.genshin_imp.R;

import java.util.ArrayList;
import java.util.List;

public class MonsterAdapter extends ArrayAdapter<Monster> {

    private Context context;
    private ArrayList<Monster> monsters;

    public MonsterAdapter(Context context, List<Monster> monsters) {
        super(context, 0, monsters);
        this.context = context;
        this.monsters = (ArrayList<Monster>) monsters;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_monster, parent, false);
        }

        final Monster currentMonster = monsters.get(position);

        TextView monsterNameTextView = listItemView.findViewById(R.id.text_view_monster_name);
        Button viewMonsterButton = listItemView.findViewById(R.id.button_view_monster);

        monsterNameTextView.setText(currentMonster.getName());

        viewMonsterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理按钮点击事件
                // 获取当前怪物的信息
                int monsterId = currentMonster.getId();
                String monsterName = currentMonster.getName();
                int monsterAttack = currentMonster.getAttack();
                int monsterDefense = currentMonster.getDefense();
                int monsterHealth = currentMonster.getHealth();
                int monsterLevel = currentMonster.getLevel();
                // 启动新的活动（ViewEditMonsterActivity）来显示和修改怪物信息
                Intent intent = new Intent(context, ViewEditMonsterActivity.class);
                intent.putExtra("monster_id", monsterId);
                intent.putExtra("monster_name", monsterName);
                intent.putExtra("monster_attack", monsterAttack);
                intent.putExtra("monster_defense", monsterDefense);
                intent.putExtra("monster_health", monsterHealth);
                intent.putExtra("monster_level", monsterLevel);

                context.startActivity(intent);
            }
        });

        return listItemView;
    }
}
