package com.example.genshin_imp.Dao_Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MonsterManager {
    private DBHelper dbHelper;

    public MonsterManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insertMonster(String name, int attack, int defense, int level, String element, int health) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("attack", attack);
        values.put("defense", defense);
        values.put("level", level);
        values.put("element", element);
        values.put("health", health);
        long id = db.insert("monsters", null, values);
        db.close();
        return id;
    }

    public void deleteMonster(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("monsters", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateMonster(long id, String name, int attack, int defense, int level, String element, int health) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("attack", attack);
        values.put("defense", defense);
        values.put("level", level);
        values.put("element", element);
        values.put("health", health);
        db.update("monsters", values, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getAllMonstersByCurrsor() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query("monsters", null, null, null, null, null, null);
    }
    public List<Monster> getAllMonsters() {
        List<Monster> monstersList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("monsters", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int attack = cursor.getInt(cursor.getColumnIndex("attack"));
                int defense = cursor.getInt(cursor.getColumnIndex("defense"));
                int level = cursor.getInt(cursor.getColumnIndex("level"));
                String element = cursor.getString(cursor.getColumnIndex("element"));
                int health = cursor.getInt(cursor.getColumnIndex("health"));

                // 创建 Monster 对象并添加到列表中
                Monster monster = new Monster(id, name, attack, defense, level, element, health);
                monstersList.add(monster);
            } while (cursor.moveToNext());
        }

        // 关闭 Cursor 和数据库连接
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return monstersList;
    }


//        public Cursor getMonsterById(long id) {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        return db.query("monsters", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
//    }

    public Cursor searchMonsterByName(String searchText) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM monsters WHERE name LIKE '%" + searchText + "%'";
        return db.rawQuery(query, null);
    }

    public Monster getMonsterById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Monster monster = null;

        Cursor cursor = db.query("monsters", // 表名
                new String[]{"id", "name", "attack", "defense", "level", "element", "health"}, // 要检索的列
                "id=?", // WHERE子句
                new String[]{String.valueOf(id)}, // WHERE子句的值
                null, // GROUP BY
                null, // HAVING
                null); // ORDER BY

        if (cursor != null && cursor.moveToFirst()) {
            int monsterId = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int attack = cursor.getInt(cursor.getColumnIndex("attack"));
            int defense = cursor.getInt(cursor.getColumnIndex("defense"));
            int level = cursor.getInt(cursor.getColumnIndex("level"));
            String element = cursor.getString(cursor.getColumnIndex("element"));
            int health = cursor.getInt(cursor.getColumnIndex("health"));

            // 创建 Monster 对象
            monster = new Monster(monsterId, name, attack, defense, level, element, health);
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return monster;
    }


    public void updateMonster(Monster selectedMonster) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        // 将更新后的属性放入 ContentValues 对象中
        values.put("attack", selectedMonster.getAttack());
        values.put("defense", selectedMonster.getDefense());
        values.put("health", selectedMonster.getHealth());
        values.put("level", selectedMonster.getLevel());

        // 更新数据库中的对应条目
        db.update("monsters", values, "id=?", new String[]{String.valueOf(selectedMonster.getId())});
        db.close();
    }

    private static class DBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "MonstersDB";
        private static final int DATABASE_VERSION = 1;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_MONSTER_TABLE_SQL = "CREATE TABLE IF NOT EXISTS monsters (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "attack INTEGER," +
                    "defense INTEGER," +
                    "level INTEGER," +
                    "element TEXT," +
                    "health INTEGER" +
                    ");";
            db.execSQL(CREATE_MONSTER_TABLE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS monsters");
            onCreate(db);
        }
    }
}
