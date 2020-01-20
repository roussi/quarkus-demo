package org.aroussi;

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

    @Path("/products/{ref}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateProduct(@PathParam("ref") String ref,@Valid Product product) {
        logger.info("update product who's ref = " + ref + " Product is : "+product.toString());
        return "UPDATED";
    }
}

class Product {

    private String ref;
    private Double price;

    @Override
    public String toString() {
        return "Product{" +
                "ref='" + ref + '\'' +
                ", price=" + price +
                '}';
    }

    public Product(String ref, Double price) {
        this.ref = ref;
        this.price = price;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}