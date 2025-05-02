package org.eclipse.jakarta.hello;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("/products")
public class ProductsResource {


    private static Map<String, Product> products = new HashMap<>(Map.of(
            "Shaan body milk", new Product("Shaan body milk", 200),
            "Eva lotion", new Product("Eva lotion", 150),
            "Care and More cream", new Product("Care and More cream", 50),
            "Pure cream", new Product("Pure cream", 20)
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


        Product product = new Product(name, price);
        products.put(name, product);

        return "<p> Product is added successfully ! </p>";
    }

    @PUT
    @Produces({MediaType.TEXT_HTML})
    public String updateProduct(@QueryParam("name")String name,
                           @QueryParam("price")Integer price){


        Product product = products.get(name);
        product.setPrice(price);

        products.put(name, product);

       return "<p> Product is updated successfully ! </p>";
    }

    @DELETE
    @Produces({MediaType.TEXT_HTML})
    public String deleteProduct(@QueryParam("name")String name){

        products.remove(name);

        return"<p> Product is deleted successfully ! </p>";
    }
}
