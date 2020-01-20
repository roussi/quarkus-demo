package org.aroussi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Path("/v1")
public class ExampleResource {

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    List<Product> products = new ArrayList<>();

    public ExampleResource() {
        products.add(new Product("p1", 200.0));
        products.add(new Product("p2", 300.0));
    }

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> hello() {
        return products;
    }

    @GET
    @Path("/products/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Product> getProduct(@PathParam("ref") String ref) {
        Optional<Product> first = products.stream()
                .filter(product -> ref.equalsIgnoreCase(product.getRef()))
                .findFirst();
        return first;
    }

    @PUT
    @Path("/products/{ref}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateProduct(@PathParam("ref") String ref,@Valid Product product) {
        logger.info("update product who's ref = " + ref + " Product is : "+product.toString());
        return "UPDATED";
    }
}

@Data
@ToString
@AllArgsConstructor
class Product {
    private String ref;
    private Double price;
}