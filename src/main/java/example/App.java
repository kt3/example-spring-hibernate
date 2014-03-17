package example;

import example.dao.entity.Item;
import example.json.transform.JsonTransformer;
import example.service.ItemService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.velocity.VelocityRoute;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Hello world!
 */
public class App {

    private static volatile ApplicationContext applicationContext;

    private static ApplicationContext getContext() {
        if (applicationContext == null) {
            synchronized (App.class) {
                if (applicationContext == null) {
                    applicationContext = new ClassPathXmlApplicationContext("appContext.xml");
                }
            }
        }
        return applicationContext;
    }

    public static void main(String[] args) {
        get(new Route("/example") {
            @Override
            public Object handle(Request request, Response response) {
                return "<h1>Hello world!</h1>";
            }
        });

        get(new JsonTransformer("/items") {
            @Override
            public Object handle(Request request, Response response) {
                ItemService itemService = getContext().getBean(ItemService.class);
                createData(itemService);
                return itemService.getAllItems();
            }
        });

        get(new VelocityRoute("/edit/:id") {
            @Override
            public Object handle(Request request, Response response) {
                ItemService itemService = getContext().getBean(ItemService.class);
                long id = Long.parseLong(request.params(":id"));
                Map<String, Object> attributes = new HashMap<String, Object>();
                attributes.put("item", itemService.findItem(id));
                return modelAndView(attributes, "edit.vm");
            }
        });

        get(new VelocityRoute("/") {

            @Override
            public Object handle(Request request, Response response) {
                ItemService itemService = getContext().getBean(ItemService.class);
                createData(itemService);
                Map<String, Object> attributes = new HashMap<String, Object>();
                attributes.put("items", itemService.getAllItems());
                return modelAndView(attributes, "main.vm");
            }
        });


    }

    private static void createData(ItemService itemService)  {
        Item item = new Item();
        item.setName("bzzz");
        item.setDescription("the description of the item".getBytes());
        item.setDate(new Date());

        itemService.createItem(item);

        item = new Item();
        item.setName("baa");
        item.setDate(new Date());

        itemService.createItem(item);

        item = new Item();
        item.setName("zzzz");
        item.setDate(new Date());

        itemService.createItem(item);

        item = new Item();
        item.setName("cccc");
        item.setDate(new Date());

        itemService.createItem(item);

    }
}
