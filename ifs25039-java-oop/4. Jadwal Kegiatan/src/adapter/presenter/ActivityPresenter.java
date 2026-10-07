package adapter.presenter;

import domain.entity.Activity;
import java.util.List;

public class ActivityPresenter {

    public void printActivityList(List<Activity> events) {
        System.out.println("Daftar Kegiatan:");
        if (events.isEmpty()) {
            System.out.println("- Data kegiatan belum tersedia!");
        } else {
            for (Activity event : events) {
                System.out.printf("%d | %s | %s | %s\n",
                    event.getId(),
                    event.getTitle(),
                    event.getDay(),
                    event.getTime());
            }
        }
    }

    public void printSortedList(List<Activity> events) {
        System.out.println("Daftar Kegiatan (Terurut):");
        if (events.isEmpty()) {
            System.out.println("- Data kegiatan belum tersedia!");
        } else {
            for (Activity event : events) {
                System.out.printf("%d | %s | %s | %s\n",
                    event.getId(),
                    event.getTitle(),
                    event.getDay(),
                    event.getTime());
            }
        }
        System.out.println();
    }

    public void printSearchResult(String query, List<Activity> events) {
        System.out.printf("Hasil Pencarian: \"%s\"\n", query);
        if (events.isEmpty()) {
            System.out.println("- Kegiatan tidak ditemukan!");
        } else {
            for (Activity event : events) {
                System.out.printf("%d | %s | %s | %s\n",
                    event.getId(),
                    event.getTitle(),
                    event.getDay(),
                    event.getTime());
            }
        }
        System.out.println();
    }

    public void printAddSuccess(Activity event) {
        System.out.printf("Berhasil menambah kegiatan: %d | %s | %s | %s\n\n",
            event.getId(),
            event.getTitle(),
            event.getDay(),
            event.getTime());
    }
}