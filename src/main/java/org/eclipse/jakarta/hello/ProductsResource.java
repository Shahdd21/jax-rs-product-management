package org.eclipse.jakarta.hello;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("/products")
public class ProductsResource {


    private static Map<String, Product> products = new HashMap<>(Map.of(
            "shaan body milk", new Product("shaan body milk", 200),
            "eva lotion", new Product("eva lotion", 150),
            "care and more cream", new Product("care and more cream", 50),
            "pure cream", new Product("pure cream", 20)
    ) );



    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Collection<Product> getProducts() {

        return products.values();
    }

    @POST
    @Produces({MediaType.TEXT_HTML})
    public String addProduct(@QueryParam("name")String name,
                           @QueryParam("price")Integer price){


        if(products.containsKey(name.toLowerCase())) return "<p> Product already exists ! </p>";

        else {
            Product product = new Product(name, price);
            products.put(name, product);

            return "<p> Product is added successfully ! </p>";
        }
    }

    @PUT
    @Produces({MediaType.TEXT_HTML})
    public String updateProduct(@QueryParam("name")String name,
                           @QueryParam("price")Integer price){


        if(!products.containsKey(name.toLowerCase())) return "<p> Product does not exist ! </p>";

        else {
            Product product = products.get(name);
            product.setPrice(price);

            products.put(name, product);

            return "<p> Product is updated successfully ! </p>";
        }
    }

    @DELETE
    @Produces({MediaType.TEXT_HTML})
    public String deleteProduct(@QueryParam("name")String name){

        if(!products.containsKey(name.toLowerCase())) return "<p> Product does not exist ! </p>";

        else {
            products.remove(name);
            return "<p> Product is deleted successfully ! </p>";
        }
    }
}
