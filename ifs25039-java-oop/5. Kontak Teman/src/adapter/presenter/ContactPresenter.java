package adapter.presenter;

import domain.entity.Contact;
import java.util.List;

public class ContactPresenter {

    public void printContactList(List<Contact> people) {
        System.out.println("Daftar Kontak:");
        if (people.isEmpty()) {
            System.out.println("- Data kontak belum tersedia!");
        } else {
            for (Contact person : people) {
                System.out.printf("%d | %s | %s | %s\n",
                    person.getId(),
                    person.getName(),
                    person.getPhone(),
                    person.getEmail());
            }
        }
    }

    public void printSortedList(List<Contact> people) {
        System.out.println("Daftar Kontak (Terurut):");
        if (people.isEmpty()) {
            System.out.println("- Data kontak belum tersedia!");
        } else {
            for (Contact person : people) {
                System.out.printf("%d | %s | %s | %s\n",
                    person.getId(),
                    person.getName(),
                    person.getPhone(),
                    person.getEmail());
            }
        }
        System.out.println();
    }

    public void printSearchResult(String query, List<Contact> people) {
        System.out.printf("Hasil Pencarian: \"%s\"\n", query);
        if (people.isEmpty()) {
            System.out.println("- Kontak tidak ditemukan!");
        } else {
            for (Contact person : people) {
                System.out.printf("%d | %s | %s | %s\n",
                    person.getId(),
                    person.getName(),
                    person.getPhone(),
                    person.getEmail());
            }
        }
        System.out.println();
    }

    public void printAddSuccess(Contact person) {
        System.out.printf("Berhasil menambah kontak: %d | %s | %s | %s\n\n",
            person.getId(),
            person.getName(),
            person.getPhone(),
            person.getEmail());
    }
}