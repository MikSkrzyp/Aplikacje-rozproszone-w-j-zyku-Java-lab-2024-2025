import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OtoDomScraper {

    public static void main(String[] args) {
        ExecutorService executorservice = Executors.newFixedThreadPool(3);

        executorservice.submit(() -> {
            try {
                metoda();
            } catch (Exception ex) {
                Logger.getLogger(OtoDomScraper.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        executorservice.shutdown();
    }

    private static void metoda() throws Exception {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1; i++) {
            URL otodom = new URL("https://www.otodom.pl/wynajem/mieszkanie/sopot/");
            HttpURLConnection connection = (HttpURLConnection) otodom.openConnection();

            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }

            in.close();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " ms");
    }
}
