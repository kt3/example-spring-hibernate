package example.dao;

import example.dao.entity.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ContextConfiguration("classpath:appContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ItemDaoImplTest {

    @PersistenceContext
    protected EntityManager em;

    @Resource
    private ItemDao subj;

    @Test
    public void testRead() {
        Item item = createItem();

        Item itemFromDb=subj.read(item.getId());
        assertNotNull(itemFromDb);
    }


    @Test
    public void testDelete(){
        Item item=createItem();

        item=em.find(Item.class, item.getId());
        subj.delete(item);

        em.flush();
        em.clear();

        assertNull(em.find(Item.class,item.getId()));
    }

    @Test
    public void testUpdate(){
        Item item=createItem();

        Item itemToEdit=new Item();
        itemToEdit.setId(item.getId());
        String name = "newName";
        itemToEdit.setName(name);

        subj.update(itemToEdit);

        item=em.find(Item.class,item.getId());
        assertEquals(name, item.getName());

    }

    @Test
    public void testList(){
        Item item1=new Item();
        Item item2=new Item();
        Item item3=new Item();
        subj.persist(item1);
        subj.persist(item2);
        subj.persist(item3);

        em.flush();
        em.clear();

        List<Item> itemList=subj.list();
        assertEquals(3,itemList.size());
    }

    private Item createItem() {
        Item item = new Item();
        item.setName("item-name");
        item.setDate(new Date());

        em.persist(item);

        em.flush();
        em.clear();

        return item;
    }

}
