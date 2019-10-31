package com.example.scanner;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {

    private ItemDao itemDao;
    private LiveData<List<Item>> allItem;

    public ItemRepository(Application application) {
        ItemDatabase db = ItemDatabase.getInstance(application);
       itemDao = db.itemDao();
        allItem = itemDao.getAllItems();
    }

    public LiveData<List<Item>> getAllItem() {
        return allItem;
    }

    public void insert(Item item){
        new InsertItemSync(itemDao).execute(item);
    }

    public void update(Item item){
        new UpdateItemSync(itemDao).execute(item);
    }

    public void delete(Item item){
        new DeleteItemSync(itemDao).execute(item);
    }
    private static class InsertItemSync extends AsyncTask<Item,Void,Void> {
        private  ItemDao itemDao;

        public InsertItemSync(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.insert(items[0]);
            return null;
        }
    }

    private static class UpdateItemSync extends AsyncTask<Item,Void,Void> {
        private  ItemDao itemDao;

        public UpdateItemSync(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.update(items[0]);
            return null;
        }
    }

    private static class DeleteItemSync extends AsyncTask<Item,Void,Void> {
        private  ItemDao itemDao;

        public DeleteItemSync(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.delete(items[0]);
            return null;
        }
    }


}
