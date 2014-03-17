package example.dao;

import example.dao.entity.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Item> list() {
        return em.createQuery("select item from Item item").getResultList();
    }

    @Override
    public Item read(long id) {
        return em.find(Item.class,id);
    }

    @Override
    public void persist(Item item) {
        em.persist(item);
    }

    @Override
    public void update(Item item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Item item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
