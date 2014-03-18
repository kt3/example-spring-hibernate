package example.json.transform;

import com.google.gson.Gson;
import spark.ResponseTransformerRoute;

public abstract class JsonTransformer extends ResponseTransformerRoute {

    private Gson gson=new Gson();

    protected JsonTransformer(String route){
        super(route, "application/json");
    }

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
