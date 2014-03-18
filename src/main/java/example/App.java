package example;

import example.dao.entity.Item;
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
import static spark.Spark.post;


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

        get(new VelocityRoute("/edit/:id") {
            @Override
            public Object handle(Request request, Response response) {
                ItemService itemService = getContext().getBean(ItemService.class);
                long id = Long.parseLong(request.params(":id"));
                Map<String, Object> attributes = new HashMap<String, Object>();
                attributes.put("item", itemService.findItem(id));
                return modelAndView(attributes, "views/edit.vm");
            }
        });

        get(new VelocityRoute("/") {
            @Override
            public Object handle(Request request, Response response) {
                ItemService itemService = getContext().getBean(ItemService.class);
                Map<String, Object> attributes = new HashMap<String, Object>();
                attributes.put("items", itemService.getAllItems());
                return modelAndView(attributes, "views/main.vm");
            }
        });

        get(new Route("/start") {
            @Override
            public Object handle(Request request, Response response) {
                ItemService itemService = getContext().getBean(ItemService.class);
                createData(itemService);
                response.redirect("/");
                return null;
            }
        });

        post(new Route("/save/:id") {
            @Override
            public Object handle(Request request, Response response) {
                ItemService itemService = getContext().getBean(ItemService.class);
                long id = Long.parseLong(request.params(":id"));
                String description = request.queryMap("description").value();
                Item item = itemService.findItem(id);
                item.setDescription(description.getBytes());
                itemService.update(item);
                response.redirect("/");
                return null;
            }
        });

    }

    private static void createData(ItemService itemService) {
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
