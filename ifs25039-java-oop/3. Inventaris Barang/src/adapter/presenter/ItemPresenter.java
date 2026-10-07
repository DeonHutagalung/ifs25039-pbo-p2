package adapter.presenter;

import domain.entity.Item;
import java.util.List;

public class ItemPresenter {

    public void printItemList(List<Item> products) {
        System.out.println("Daftar Barang:");
        if (products.isEmpty()) {
            System.out.println("- Data barang belum tersedia!");
        } else {
            for (Item product : products) {
                System.out.printf("%d | %s | %d | %s\n",
                    product.getId(),
                    product.getName(),
                    product.getQuantity(),
                    product.getCategory());
            }
        }
    }

    public void printSortedList(List<Item> products) {
        System.out.println("Daftar Barang (Terurut):");
        if (products.isEmpty()) {
            System.out.println("- Data barang belum tersedia!");
        } else {
            for (Item product : products) {
                System.out.printf("%d | %s | %d | %s\n",
                    product.getId(),
                    product.getName(),
                    product.getQuantity(),
                    product.getCategory());
            }
        }
        System.out.println();
    }

    public void printSearchResult(String query, List<Item> products) {
        System.out.printf("Hasil Pencarian: \"%s\"\n", query);
        if (products.isEmpty()) {
            System.out.println("- Barang tidak ditemukan!");
        } else {
            for (Item product : products) {
                System.out.printf("%d | %s | %d | %s\n",
                    product.getId(),
                    product.getName(),
                    product.getQuantity(),
                    product.getCategory());
            }
        }
        System.out.println();
    }

    public void printAddSuccess(Item product) {
        System.out.printf("Berhasil menambah barang: %d | %s | %d | %s\n\n",
            product.getId(),
            product.getName(),
            product.getQuantity(),
            product.getCategory());
    }
}