package inventory;

import products.IProduct;

public class Inventory<T extends IProduct> {

    private static int batch = 0;
    private T product;


    public Inventory(T product) {
        this.product = product;
    }

    public T getProduct() {
        return product;
    }

    public int getBatch() {
        return ++batch;
    }
}