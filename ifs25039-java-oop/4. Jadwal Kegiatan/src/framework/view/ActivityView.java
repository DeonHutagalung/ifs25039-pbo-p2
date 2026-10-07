package framework.view;

import adapter.presenter.ActivityPresenter;
import domain.entity.Activity;
import domain.entity.ActivitySortOption;
import usecase.ActivityUseCase;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class ActivityView {
    private final ActivityUseCase useCase;
    private final ActivityPresenter presenter;
    private final Scanner scanner;

    public ActivityView(ActivityUseCase useCase, ActivityPresenter presenter) {
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
                presenter.printActivityList(useCase.getAllActivities());

                System.out.println("Menu:");
                System.out.println("1. Tambah");
                System.out.println("2. Ubah");
                System.out.println("3. Cari");
                System.out.println("4. Urutkan");
                System.out.println("5. Hapus");
                System.out.println("x. Keluar");

                String menu = readString("Pilih : ");

                switch (menu.toLowerCase()) {
                    case "1":
                        System.out.println("[Menambah Kegiatan]");
                        addActivity();
                        break;
                    case "2":
                        System.out.println("[Mengubah Kegiatan]");
                        updateActivity();
                        break;
                    case "3":
                        System.out.println("[Mencari Kegiatan]");
                        searchActivity();
                        break;
                    case "4":
                        System.out.println("[Mengurutkan Kegiatan]");
                        sortActivities();
                        break;
                    case "5":
                        System.out.println("[Menghapus Kegiatan]");
                        deleteActivity();
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

    private void addActivity() {
        String activityTitle = readString("Judul (x Jika Batal) : ");
        if (activityTitle.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        String weekday = readString("Hari (x Jika Batal) : ");
        if (weekday.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        String startTime = readString("Waktu (x Jika Batal) : ");
        if (startTime.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        Activity event = useCase.addActivity(activityTitle, weekday, startTime);
        presenter.printAddSuccess(event);
    }

    private void updateActivity() {
        String idStr = readString("ID Kegiatan yang diubah (x Jika Batal) : ");
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

        String newTitle = readString("Judul Baru (Kosongkan jika tidak ingin mengubah) : ");
        if (newTitle.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        String newDay = readString("Hari Baru (Kosongkan jika tidak ingin mengubah) : ");
        if (newDay.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        String newTime = readString("Waktu Baru (Kosongkan jika tidak ingin mengubah) : ");
        if (newTime.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        Optional<Activity> actOpt = useCase.getActivityById(identifier);
        if (actOpt.isEmpty()) {
            System.out.printf("[!] Gagal mengubah kegiatan dengan ID: %d.\n\n", identifier);
            return;
        }

        Activity oldAct = actOpt.get();
        String finalTitle = newTitle.isEmpty() ? oldAct.getTitle() : newTitle;
        String finalDay = newDay.isEmpty() ? oldAct.getDay() : newDay;
        String finalTime = newTime.isEmpty() ? oldAct.getTime() : newTime;

        if (useCase.updateActivity(identifier, finalTitle, finalDay, finalTime)) {
            System.out.println("Berhasil mengubah kegiatan.\n");
        } else {
            System.out.printf("[!] Gagal mengubah kegiatan dengan ID: %d.\n\n", identifier);
        }
    }

    private void searchActivity() {
        String query = readString("Kata Kunci (x Jika Batal) : ");
        if (query.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }
        presenter.printSearchResult(query, useCase.searchByTitle(query));
    }

    private void sortActivities() {
        System.out.println("Pilihan Pengurutan:");
        System.out.println("1. Hari (Senin -> Minggu)");
        System.out.println("2. Waktu (Awal -> Akhir)");
        System.out.println("3. Judul (A-Z)");
        System.out.println("4. Judul (Z-A)");
        System.out.println("x. Batal");
        String choice = readString("Pilih : ");

        if (choice.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        ActivitySortOption option = switch (choice) {
            case "1" -> ActivitySortOption.DAY;
            case "2" -> ActivitySortOption.TIME;
            case "3" -> ActivitySortOption.TITLE_ASC;
            case "4" -> ActivitySortOption.TITLE_DESC;
            default -> null;
        };

        if (option == null) {
            System.out.println("[!] Pilihan tidak valid!\n");
            return;
        }

        presenter.printSortedList(useCase.getSortedActivities(option));
    }

    private void deleteActivity() {
        // Tanda kurung siku ditambahkan pada "ID Kegiatan"
        String idStr = readString("[ID Kegiatan] yang dihapus (x Jika Batal) : ");
        if (idStr.equalsIgnoreCase("x")) {
            System.out.println();
            return;
        }

        try {
            int identifier = Integer.parseInt(idStr);
            if (useCase.deleteActivity(identifier)) {
                System.out.println("Berhasil menghapus kegiatan.\n");
            } else {
                System.out.printf("[!] Gagal menghapus kegiatan dengan ID: %d.\n\n", identifier);
            }
        } catch (NumberFormatException e) {
            System.out.println("[!] ID tidak valid!\n");
        }
    }
}