package example.service;

import example.dao.entity.Item;

import java.util.List;

public interface ItemService {

    void createItem(Item item);

    Item findItem(long id);

    List<Item> getAllItems();
}
