package framework.util;

public class EOFException extends RuntimeException {
    public EOFException() {
        super("Sistem menghentikan aliran masukan.");
    }
}