
public class ProducentKonsument {
    private String dane;
    private boolean gotowe = false;

    public static void main(String[] args) {
        ProducentKonsument zasob = new ProducentKonsument();

        Thread producent = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                zasob.producent("Dane " + i);
            }
        });

        Thread konsument = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Odczytano: " + zasob.konsument());
            }
        });

        producent.start();
        konsument.start();
    }

    public synchronized String konsument() {
        while (!gotowe) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        gotowe = false;
        notify();
        return dane;
    }

    public synchronized void producent(String noweDane) {
        while (gotowe) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dane = noweDane;
        gotowe = true;
        notify();
    }
}
