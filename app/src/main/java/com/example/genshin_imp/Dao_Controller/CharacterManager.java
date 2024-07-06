//package com.example.genshin.Dao_Controller;
//
//import android.content.Context;
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class CharacterManager {
//    private DBHelper dbHelper;
//    // 无参数构造函数
//    public CharacterManager() {
//        // 可以为空，或者进行一些初始化操作
//    }
//    public CharacterManager(Context context) {
//        dbHelper = new DBHelper(context);
//    }
//    // 插入角色数据
//    public long insertCharacter(String name, String attribute, String ability, String element, String like) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("attribute", attribute);
//        values.put("ability", ability);
//        values.put("element", element);
//        values.put("liked", like);
//        long id = db.insert("Character", null, values);
//        db.close();
//        return id;
//    }
//    // 删除角色数据
//    public void deleteCharacter(long id) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        db.delete("Character", "id=?", new String[]{String.valueOf(id)});
//        db.close();
//    }
//    // 更新角色数据
//    public void updateCharacter(long id, String name, String attribute, String ability, String element, String like) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("attribute", attribute);
//        values.put("ability", ability);
//        values.put("element", element);
//        values.put("liked", like);
//        db.update("Character", values, "id=?", new String[]{String.valueOf(id)});
//        db.close();
//    }
//    // 查询所有角色数据
//    public Cursor getAllCharacters() {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        return db.query("Character", null, null, null, null, null, null);
//    }
//    // 查询单个角色数据
//    public Cursor getCharacterById(long id) {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        return db.query("Character", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
//    }
//    // 根据名称查询角色数据
//    public Cursor getCharacterByName(String name) {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        return db.query("Character", null, "name=?", new String[]{name}, null, null, null);
//    }
//
//    private static class DBHelper extends SQLiteOpenHelper {
//        private static final String DATABASE_NAME = "GenshinImpact";
//        private static final int DATABASE_VERSION = 1;
//
//        public DBHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        }
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//            // 创建角色表
//            db.execSQL("CREATE TABLE Character (" +
//                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    "name TEXT," +
//                    "attribute TEXT," +
//                    "ability TEXT," +
//                    "element TEXT," +
//                    "liked TEXT)");
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL("DROP TABLE IF EXISTS Character");
//            onCreate(db);
//        }
//    }
//}
//
//
package com.example.genshin_imp.Dao_Controller;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class CharacterManager {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public CharacterManager(Context context) {
        this.context=context;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    public long insertCharacter(String name, String attribute, String ability, String element, String like,
                                int attack, double elementalDamageBonus, double criticalRate, double criticalDamage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("attribute", attribute);
        values.put("ability", ability);
        values.put("element", element);
        values.put("liked", like);
        values.put("attack", attack);
        values.put("elementalDamageBonus", elementalDamageBonus);
        values.put("criticalRate", criticalRate);
        values.put("criticalDamage", criticalDamage);
        long id = db.insert("Character", null, values);
        db.close();
        return id;
    }
    public void deleteCharacter(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Character", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateCharacter(long id, String attribute,
                                int attack, double elementalDamageBonus, double criticalRate, double criticalDamage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("name", name);
        values.put("attribute", attribute);
//        values.put("ability", ability);
//        values.put("element", element);
//        values.put("liked", like);
        values.put("attack", attack);
        values.put("elementalDamageBonus", elementalDamageBonus);
        values.put("criticalRate", criticalRate);
        values.put("criticalDamage", criticalDamage);
        db.update("Character", values, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }


    public Cursor getAllCharacters() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query("Character", null, null, null, null, null, null);
    }

    public Cursor getCharacterById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query("Character", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
    }

    public Character getCharacterById_Char(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Character", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String attribute = cursor.getString(cursor.getColumnIndex("attribute"));
            String ability = cursor.getString(cursor.getColumnIndex("ability"));
            String element = cursor.getString(cursor.getColumnIndex("element"));
            String liked = cursor.getString(cursor.getColumnIndex("liked"));
            int attack = cursor.getInt(cursor.getColumnIndex("attack"));
            double elementalDamageBonus = cursor.getDouble(cursor.getColumnIndex("elementalDamageBonus"));
            double criticalRate = cursor.getDouble(cursor.getColumnIndex("criticalRate"));
            double criticalDamage = cursor.getDouble(cursor.getColumnIndex("criticalDamage"));

            // 创建 Character 对象并返回
            return new Character(id, name, attribute, ability, element, liked, attack, elementalDamageBonus, criticalRate, criticalDamage);
        }
        return null;
    }
    public Cursor getCharacterByName(String name) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query("Character", null, "name=?", new String[]{name}, null, null, null);
    }
    private static class DBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "GenshinImpact";
        private static final int DATABASE_VERSION = 1;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Character (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "attribute TEXT," +
                    "ability TEXT," +
                    "element TEXT," +
                    "liked TEXT," +
                    "attack INTEGER," +
                    "elementalDamageBonus REAL," +
                    "criticalRate REAL," +
                    "criticalDamage REAL)");
        }

        //        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL("DROP TABLE IF EXISTS Character");
//
//            onCreate(db);
//        }
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
}

