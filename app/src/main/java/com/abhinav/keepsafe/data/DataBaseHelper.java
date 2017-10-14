package com.abhinav.keepsafe.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

import com.abhinav.keepsafe.Constants;
import com.abhinav.keepsafe.KeepSafe;
import com.abhinav.keepsafe.dao.BankDao;
import com.abhinav.keepsafe.dao.ECommerceDao;
import com.abhinav.keepsafe.dao.EmailDao;
import com.abhinav.keepsafe.dao.SocialNetworkDao;
import com.abhinav.keepsafe.entities.Bank;
import com.abhinav.keepsafe.entities.ECommerce;
import com.abhinav.keepsafe.entities.Email;
import com.abhinav.keepsafe.entities.SocialNetwork;

/**
 * Created by Abhinav on 21/05/17.
 */

@Database(entities = {Bank.class, Email.class, SocialNetwork.class, ECommerce.class}, version = 2)
abstract class DataBaseHelper extends RoomDatabase {

    private static volatile DataBaseHelper instance;

    static DataBaseHelper getInstance() {
        if (instance == null) {
            synchronized (DataBaseHelper.class) {
                if (instance == null)
                    instance = Room.databaseBuilder(KeepSafe.getInstance(),
                            DataBaseHelper.class, Constants.Database.DATABASE_NAME)
                            .addMigrations(migration1_2)
                            .build();
            }
        }
        return instance;
    }

    /**
     * Adding Migration Script for schema changes
     * Older Version 1 : having Bank and Email tables only
     * Newer Version 2 : include new table for Social Network and ECommerce
     */

    private static final Migration migration1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE " + Constants.Database.T_SOCIAL_NETWORK + " (id INTEGER NOT NULL, " +
                    "platform_name TEXT, " +
                    "email_id_used TEXT, " +
                    "password TEXT, " +
                    "username TEXT, " +
                    "PRIMARY KEY (id))");

            database.execSQL("CREATE TABLE " + Constants.Database.T_ECOMMERCE + " (id INTEGER NOT NULL, " +
                    "platform_name TEXT, " +
                    "email_id_used TEXT, " +
                    "password TEXT, " +
                    "username TEXT, " +
                    "PRIMARY KEY (id))");
        }
    };

    public abstract BankDao getBankDao();

    public abstract EmailDao getEmailDao();

    public abstract ECommerceDao getECommerceDao();

    public abstract SocialNetworkDao getSocialNetworkDao();
}
