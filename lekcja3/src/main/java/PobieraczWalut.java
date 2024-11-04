import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;

public class PobieraczWalut {
    private static final Map<String, Double> kursy = new HashMap<>();

    public static void main(String[] args) {
        new Thread(() -> pobierzKurs("EUR")).start();
        new Thread(() -> pobierzKurs("USD")).start();
        new Thread(() -> pobierzKurs("CHF")).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        kursy.forEach((waluta, kurs) -> System.out.printf("%s: %.4f\n", waluta, kurs));
    }

    private static synchronized void zaktualizujKurs(String waluta, double kurs) {
        kursy.put(waluta, kurs);
    }

    private static void pobierzKurs(String waluta) {
        try {
            URL url = new URL("https://api.nbp.pl/api/exchangerates/rates/A/" + waluta);
            HttpURLConnection polaczenie = (HttpURLConnection) url.openConnection();
            polaczenie.setRequestMethod("GET");

            BufferedReader czytnik = new BufferedReader(new InputStreamReader(polaczenie.getInputStream()));
            String odpowiedz = czytnik.readLine();
            czytnik.close();

            double kurs = Double.parseDouble(odpowiedz.split("\"mid\":")[1].split("}")[0]);
            zaktualizujKurs(waluta, kurs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
