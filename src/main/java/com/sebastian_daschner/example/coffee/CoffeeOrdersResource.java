package com.sebastian_daschner.example.coffee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoffeeOrdersResource {

    @GET
    public List<CoffeeOrder> order() {
        CoffeeOrder order = new CoffeeOrder();
        order.setType(CoffeeType.CAPPUCCINO);
        return List.of(order);
    }

    @POST
    public void createOrder(CoffeeOrder order) {
        System.out.println("order = " + order);
    }

}
