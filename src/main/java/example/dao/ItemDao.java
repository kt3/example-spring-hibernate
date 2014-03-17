package example.dao;

import example.dao.entity.Item;

import java.util.List;


public interface ItemDao {

    List list();

    Item read(long id);

    void persist(Item item);

    void update(Item item);

    void delete(Item item);


}
