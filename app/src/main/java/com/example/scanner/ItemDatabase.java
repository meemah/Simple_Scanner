package com.example.scanner;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Item.class},version = 1)
public abstract class ItemDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();
    private static ItemDatabase instance;

    public static synchronized ItemDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ItemDatabase.class,"item_database").addCallback(roomCallback).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{
        private ItemDao itemDao;

        public PopulateAsyncTask(ItemDatabase itemDatabase) {
            this.itemDao = itemDatabase.itemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.insert(new Item(20,"book","sweet"));
            itemDao.insert(new Item(10,"cup","big"));
            return null;
        }
    }
}
