package com.example.appcovid.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.appcovid.network.dto.CreateAccDto;
import com.example.appcovid.sqllite.bean.Note;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "User_Manager";

    // Table name: Note.
    private static final String TABLE_NOTE = "User";

    private static final String COLUMN_USER_ID = "User_Id";
    private static final String COLUMN_USERNAME = "User_name";
    private static  Date COLUMN_BIRTHDAY;
    private static final String COLUMN_CMTND = "CMTND";
    //private static final String COLUMN_GENDER = "Gender";
    private static final String COLUMN_PHONE= "Phone";
    private static final String COLUMN_ID_COMMUNE = "Id_Commune";
    private static final String COLUMN_ADDRESS = "Address";

    public DatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME + " TEXT," + COLUMN_CMTND + " TEXT,"
                + COLUMN_PHONE + " TEXT," + COLUMN_ID_COMMUNE + " TEXT," + COLUMN_BIRTHDAY + " DATE," + COLUMN_ADDRESS + " TEXT" + ")";
        // Execute Script.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);

        // Create tables again
        onCreate(db);
    }

    public void addUser(CreateAccDto user) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + user.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, "70");
        values.put(COLUMN_USERNAME,user.getName());
        values.put(COLUMN_CMTND, user.getCmt());
        values.put(COLUMN_PHONE,user.getPhone());
        //values.put(COLUMN_BIRTHDAY,);
        values.put(COLUMN_ID_COMMUNE,user.getIdCommune());
        values.put(COLUMN_ADDRESS,user.getAddress());
        // Inserting Row
        db.insert(TABLE_NOTE, null, values);

        // Closing database connection
        db.close();
    }

    public CreateAccDto getUser(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTE, new String[] { COLUMN_USER_ID,COLUMN_USERNAME,
                         COLUMN_CMTND,COLUMN_PHONE,COLUMN_ID_COMMUNE,COLUMN_ADDRESS }, COLUMN_USER_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        CreateAccDto accDto = new CreateAccDto(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getLong(4), cursor.getString(5));
        // return accDto
        return accDto;
    }

    public int getUserCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public void deleteUser(int id ) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + id );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTE, COLUMN_USER_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
}
