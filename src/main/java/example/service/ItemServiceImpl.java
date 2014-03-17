package example.service;

import example.dao.ItemDao;
import example.dao.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public void createItem(Item item) {
        itemDao.persist(item);
    }

    @Override
    public Item findItem(long id) {
        return itemDao.read(id);
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.list();
    }
}
