package products;


public class Product implements IProduct {

    private String title;
    private double prise;
    private int quantity;

    public Product(String title, double prise, int quantity) {
        this.title = title;
        this.prise = prise;
        this.quantity = quantity;
    }

    @Override
    public double getPrise() {
        return prise;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.prise, prise) != 0) return false;
        return quantity == product.quantity && (title != null ? title.equals(product.title) : product.title == null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        temp = Double.doubleToLongBits(prise);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return title + " quantity : " + quantity + " prise:  " + prise;
    }
}