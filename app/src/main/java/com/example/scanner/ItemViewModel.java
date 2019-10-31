package com.example.scanner;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemRepository itemRepository;
    private LiveData<List<Item>> allItems;
    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
        allItems = itemRepository.getAllItem();
    }
    public void insert(Item item){
        itemRepository.insert(item);
    }
    public void delete(Item item){
        itemRepository.delete(item);;
    }
    public void update(Item item){
        itemRepository.update(item);;
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }
}
