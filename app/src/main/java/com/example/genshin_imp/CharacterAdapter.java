package com.example.genshin_imp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.genshin_imp.Dao_Controller.Character;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<Character> characterList= new ArrayList<>();
    private List<Character> characters = new ArrayList<>(); // 在 Adapter 中声明并初始化数据源

    private Cursor cursor;
    private OnItemClickListener listener;


    // 构造函数和其他方法
//    public CharacterAdapter(List<Character> characterList) {
//        this.characterList = characterList;
//    }
    public CharacterAdapter(Cursor cursor) {
        this.cursor = cursor;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Cursor getCursor(int position) {
        cursor.moveToPosition(position);
        return cursor;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void changeCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close(); // 关闭旧的 Cursor
        }

        cursor = newCursor;
        notifyDataSetChanged(); // 通知 RecyclerView 数据已更改
    }


    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_item, parent, false);
        return new CharacterViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull

    @Override
//    public void onBindViewHolder(CharacterViewHolder holder, int position) {
////        if (!cursor.moveToPosition(position)) {
////            return;
////        }
////
////        // 从 Cursor 中获取数据
////        String name = cursor.getString(cursor.getColumnIndex("name"));
////        String attribute = cursor.getString(cursor.getColumnIndex("attribute"));
////
////        // 设置视图数据
////        holder.nameTextView.setText(name);
////        holder.attributeTextView.setText(attribute);
////    }
    public void onBindViewHolder(CharacterViewHolder holder, final int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }

        // 从 Cursor 中获取数据
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String attribute = cursor.getString(cursor.getColumnIndex("attribute"));
        // 其他角色信息

        // 设置视图数据
        holder.nameTextView.setText(name);
        holder.attributeTextView.setText(attribute);

        // 在这里设置角色图片
//        int imageRes = getImageResourceBasedOnCharacter(name); // 获取对应角色的图片资源
        int imageRes = R.drawable.default_character_image; // 获取对应角色的图片资源
        holder.characterImageView.setImageResource(imageRes);

        // 为ImageView设置点击事件监听器
        holder.characterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取点击的角色 ID 或其他必要的信息
                Cursor cursor = getCursor(position);
                int characterId = cursor.getInt(cursor.getColumnIndex("id"));

                // 启动角色详情页面并传递角色 ID
                Intent intent = new Intent(view.getContext(), CharacterDetailsActivity.class);
                intent.putExtra("character_id", characterId);
                view.getContext().startActivity(intent);
            }
        });
    }

    private int getImageResourceBasedOnCharacter(String name) {
        switch (name) {
            case "CharacterName1":
//                return R.drawable.character_image_1;
            case "CharacterName2":
//                return R.drawable.character_image_2;
            // Add cases for other character names
            default:
                return R.drawable.default_character_image;
        }
    }


    @Override
    public int getItemCount() {
        if (cursor != null) {
            return cursor.getCount(); // 返回数据库中记录的数量
        } else {
            return 0;
        }
    }


    public class CharacterViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView attributeTextView;
        private ImageView characterImageView;

        public CharacterViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            attributeTextView = itemView.findViewById(R.id.text_view_attribute);
            characterImageView = itemView.findViewById(R.id.image_view_character); // 假设你的 ImageView ID 是 image_view_character

        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void bind(Character character) {
            nameTextView.setText(character.getName());
            attributeTextView.setText(character.getAttribute());

            // 在这里绑定其他角色信息
        }
    }
}

