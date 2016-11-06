package inventory;

import products.IProduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InventoryCalculator {

    private static final String header = "\n==============IN TOTAL===============\n";
    private static final String footer = "\n=====================================\n";
    private static Map<Integer, Inventory<IProduct>> batchMap = new HashMap<>();

    public static void printResult(List<Inventory<IProduct>> list) {
        System.out.println(header);
        calculatePriceOfAllProducts(list);
        calculateNumberOfAllProducts(list);
        calculateTotalPriceAndNumberOfParticularProduct(list);
        System.out.println(footer);
    }

    private static void calculatePriceOfAllProducts(List<Inventory<IProduct>> list) {
        double prise = 0;
        for (Inventory<IProduct> product : list) {
            prise += product.getProduct().getPrise() * product.getProduct().getQuantity();
        }
        System.out.printf("Total prise = %.2f $.\n", prise);
    }

    private static void calculateNumberOfAllProducts(List<Inventory<IProduct>> list) {
        int count = 0;
        for (Inventory<IProduct> product : list) {
            count += product.getProduct().getQuantity();
        }
        System.out.printf("Total number of products : %d\n", count);
    }

    private static void calculateTotalPriceAndNumberOfParticularProduct(List<Inventory<IProduct>> list) {
        fillBatchMap(list);
        Set<Map.Entry<Integer, Inventory<IProduct>>> set = batchMap.entrySet();
        for (Map.Entry<Integer, Inventory<IProduct>> entrySet : set) {
            Integer key = entrySet.getKey();
            Inventory<IProduct> product =  batchMap.get(key);
            double prise = product.getProduct().getPrise() * product.getProduct().getQuantity();
            System.out.printf("\n==== Product : %s batch #%d ==== \n", product.getProduct().getTitle().toLowerCase(), key);
            System.out.printf("prise = %.2f\n" , prise);
            System.out.printf("quantity = %d\n", product.getProduct().getQuantity());
        }
    }

    private static void fillBatchMap(List<Inventory<IProduct>> list) {
        for (Inventory<IProduct> products : list) {
            batchMap.put(products.getBatch(), products);
        }
    }
}