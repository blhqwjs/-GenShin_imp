package com.example.genshin_imp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "genshin";
    private static final int DATABASE_VERSION = 3;

    private static final String CREATE_CHARACTER_TABLE_SQL = "CREATE TABLE IF NOT EXISTS characters (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT NOT NULL," +
            "attribute TEXT NOT NULL," +
            "ability TEXT," +
            "element TEXT NOT NULL," +
            "liked INTEGER," +
            "attack INTEGER NOT NULL," +
            "elemental_damage_bonus REAL," +
            "critical_rate REAL," +
            "critical_damage REAL" +
            ");";
//怪物表
    private static final String CREATE_MONSTER_TABLE_SQL = "CREATE TABLE IF NOT EXISTS monsters (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT NOT NULL ," +
            "attack INTEGER NOT NULL," +
            "defense INTEGER NOT NULL," +
            "level INTEGER," +
            "element TEXT," +
            "health INTEGER" +
            ");";


    private static final String CREATE_WEAPON_TABLE_SQL = "CREATE TABLE IF NOT EXISTS weapons (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT," +
            "character_used TEXT," +
            "base_attribute TEXT" +
            ");";

    private static final String CREATE_ARTIFACT_TABLE_SQL = "CREATE TABLE IF NOT EXISTS artifacts (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "equipped_character TEXT," +
            "artifact_attribute TEXT" +
            ");";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CHARACTER_TABLE_SQL);
        db.execSQL(CREATE_WEAPON_TABLE_SQL);
        db.execSQL(CREATE_ARTIFACT_TABLE_SQL);
        db.execSQL(CREATE_MONSTER_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // 旧版本小于2时的更新
            db.execSQL("DROP TABLE IF EXISTS characters");
            db.execSQL("DROP TABLE IF EXISTS weapons");
            db.execSQL("DROP TABLE IF EXISTS artifacts");
            onCreate(db);
        } else if (oldVersion == 2) {
            // 旧版本等于2时的更新
            db.execSQL("ALTER TABLE characters ADD COLUMN attack INTEGER DEFAULT 0");
            db.execSQL("ALTER TABLE characters ADD COLUMN elemental_damage_bonus REAL DEFAULT 0");
            db.execSQL("ALTER TABLE characters ADD COLUMN critical_rate REAL DEFAULT 0");
            db.execSQL("ALTER TABLE characters ADD COLUMN critical_damage REAL DEFAULT 0");
        }
    }

}
