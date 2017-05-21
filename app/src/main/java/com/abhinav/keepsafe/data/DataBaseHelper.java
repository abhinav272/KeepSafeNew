package com.abhinav.keepsafe.data;

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
import com.abhinav.keepsafe.pojo.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhinav on 21/05/17.
 */
public class DataBaseHelper extends SQLiteOpenHelper implements IDatabaseHelper {

    private static final String TAG = "DataBaseHelper";
    private static volatile DataBaseHelper sInstance;

    public static final class TableEntries implements BaseColumns {
        /**
         * table details for t_categories
         */
        public static final String T_CATEGORY = "CATEGORIES";
        public static final String COL_CATEGORY_NAME = "CATEGORY_NAME";

        /**
         * table details for t_bank
         */

        /**
         * table details for t_email
         */
    }

    private final String QUERY_CREATE_CATEGORY_TABLE = "CREATE TABLE " + TableEntries.T_CATEGORY +
            "("
            + TableEntries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TableEntries.COL_CATEGORY_NAME + " TEXT "
            + " )";

    private final String QUERY_GET_ALL_CATEGORIES = String.format("SELECT * FROM %s", TableEntries.T_CATEGORY);

    static DataBaseHelper getsInstance() {
        if (sInstance == null) {
            synchronized (DataBaseHelper.class) {
                if (sInstance == null)
                    sInstance = new DataBaseHelper(KeepSafe.getInstance());
            }
        }
        return sInstance;
    }

    private DataBaseHelper(Context context) {
        super(context, Constants.Database.DATABASE_NAME, null, Constants.Database.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /** add all create table queries here */
        sqLiteDatabase.execSQL(QUERY_CREATE_CATEGORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: ");
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QUERY_GET_ALL_CATEGORIES, null);

        try {
            if (cursor.moveToFirst()) {
                Category c = new Category();
                c.mCategoryId = cursor.getInt(cursor.getColumnIndex(TableEntries._ID));
                c.mCategoryName = cursor.getString(cursor.getColumnIndex(TableEntries.COL_CATEGORY_NAME));
                categories.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return categories;
    }

    @Override
    public void addCategory(Category category) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(TableEntries.COL_CATEGORY_NAME, category.mCategoryName);
            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TableEntries.T_CATEGORY, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }
}
