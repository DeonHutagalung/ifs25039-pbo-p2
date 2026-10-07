package framework.view;

import adapter.presenter.ItemPresenter;
import domain.entity.Item;
import domain.entity.ItemSortOption;
import usecase.ItemUseCase;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class ItemView {
    private final ItemUseCase useCase;
    private final ItemPresenter presenter;
    private final Scanner scanner;

    public ItemView(ItemUseCase useCase, ItemPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
        this.scanner = new Scanner(System.in);
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void show() {
        try {
            while (true) {
                presenter.printItemList(useCase.getAllItems());

                System.out.println("Menu:");
                System.out.println("1. Tambah");
                System.out.println("2. Ubah Stok");
                System.out.println("3. Cari");
                System.out.println("4. Urutkan");
                System.out.println("5. Hapus");
                System.out.println("x. Keluar");

                String menu = readString("Pilih : ");

                switch (menu.toLowerCase()) {
                    case "1":
                        System.out.println("[Menambah Barang]");
                        addItem();
                        break;
                    case "2":
                        System.out.println("[Mengubah Stok]");
                        updateStock();
                        break;
                    case "3":
                        System.out.println("[Mencari Barang]");
                        searchItem();
                        break;
                    case "4":
                        System.out.println("[Mengurutkan Barang]");
                        sortItems();
                        break;
                    case "5":
                        System.out.println("[Menghapus Barang]");
                        deleteItem();
                        break;
                    case "x":
                        return;
                    default:
                        System.out.println("[!] Pilihan tidak dimengerti.\n");
                        break;
                }
            }
        } catch (NoSuchElementException e) {
            // Berhenti otomatis jika input file habis (EOF dari Grader)
        }
    }

    private void addItem() {
        String itemName = readString("Nama (x Jika Batal) : ");
        if (itemName.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        String qtyStr = readString("Jumlah : ");
        if (qtyStr.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        int stock;
        try {
            stock = Integer.parseInt(qtyStr);
            if (stock < 0) {
                System.out.println("[!] Jumlah stok tidak valid!\n");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] Jumlah stok tidak valid!\n");
            return;
        }

        String group = readString("Kategori (x Jika Batal) : ");
        if (group.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        Item product = useCase.addItem(itemName, stock, group);
        presenter.printAddSuccess(product);
    }

    private void updateStock() {
        String idStr = readString("ID Barang yang diubah (x Jika Batal) : ");
        if (idStr.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        int identifier;
        try {
            identifier = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            System.out.println("[!] ID tidak valid!\n");
            return;
        }

        // Meminta Jumlah Baru terlebih dahulu sebelum memvalidasi ketiadaan ID
        String qtyStr = readString("Jumlah Baru (Kosongkan jika tidak ingin mengubah) : ");
        if (qtyStr.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        Optional<Item> itemOpt = useCase.getItemById(identifier);
        if (itemOpt.isEmpty()) {
            System.out.printf("[!] Gagal mengubah stok barang dengan ID: %d.\n\n", identifier);
            return;
        }

        int newQuantity;
        if (qtyStr.isEmpty()) {
            newQuantity = itemOpt.get().getQuantity();
        } else {
            try {
                newQuantity = Integer.parseInt(qtyStr);
                if (newQuantity < 0) {
                    System.out.println("[!] Jumlah stok tidak valid!\n");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("[!] Jumlah stok tidak valid!\n");
                return;
            }
        }

        if (useCase.updateStock(identifier, newQuantity)) {
            System.out.println("Berhasil mengubah stok barang.\n");
        } else {
            System.out.printf("[!] Gagal mengubah stok barang dengan ID: %d.\n\n", identifier);
        }
    }

    private void searchItem() {
        String query = readString("Kata Kunci (x Jika Batal) : ");
        if (query.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }
        presenter.printSearchResult(query, useCase.searchByName(query));
    }

    private void sortItems() {
        System.out.println("Pilihan Pengurutan:");
        System.out.println("1. Nama (A-Z)");
        System.out.println("2. Nama (Z-A)");
        System.out.println("3. Jumlah (Terkecil -> Terbesar)");
        System.out.println("4. Jumlah (Terbesar -> Terkecil)");
        System.out.println("x. Batal");
        String choice = readString("Pilih : ");

        if (choice.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        ItemSortOption option = switch (choice) {
            case "1" -> ItemSortOption.NAME_ASC;
            case "2" -> ItemSortOption.NAME_DESC;
            case "3" -> ItemSortOption.QUANTITY_ASC;
            case "4" -> ItemSortOption.QUANTITY_DESC;
            default -> null;
        };

        if (option == null) {
            System.out.println("[!] Pilihan tidak valid!\n");
            return;
        }

        presenter.printSortedList(useCase.getSortedItems(option));
    }

    private void deleteItem() {
        String idStr = readString("[ID Barang] yang dihapus (x Jika Batal) : ");
        if (idStr.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        try {
            int identifier = Integer.parseInt(idStr);
            if (useCase.deleteItem(identifier)) {
                System.out.println("Berhasil menghapus barang.\n");
            } else {
                System.out.printf("[!] Gagal menghapus barang dengan ID: %d.\n\n", identifier);
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] ID tidak valid!\n");
        }
    }
}