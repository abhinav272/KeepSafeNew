package com.abhinav.keepsafe.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.KeepSafe;
import com.abhinav.keepsafe.dao.BankDao;
import com.abhinav.keepsafe.dao.EmailDao;
import com.abhinav.keepsafe.entities.Bank;
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.pojo.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhinav on 21/05/17.
 */

@Database(entities = {Bank.class, Email.class}, version = 1)
abstract class DataBaseHelper extends RoomDatabase {

    private static final String TAG = "DataBaseHelper";
    private static volatile DataBaseHelper instance;

    static DataBaseHelper getInstance() {
        if (instance == null) {
            synchronized (DataBaseHelper.class) {
                if (instance == null)
                    instance = Room.databaseBuilder(KeepSafe.getInstance(),
                            DataBaseHelper.class, Constants.Database.DATABASE_NAME).build();
            }
        }
        return instance;
    }

    public abstract BankDao getBankDao();
    public abstract EmailDao getEmailDao();
}
