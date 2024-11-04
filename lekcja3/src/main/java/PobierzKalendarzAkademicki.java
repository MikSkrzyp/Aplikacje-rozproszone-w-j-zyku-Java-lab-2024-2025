import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.lang.String;
public class PobierzKalendarzAkademicki {
    private String url = "https://ug.edu.pl/strona/121819/kalendarz-akademicki-organizacja-roku-akademickiego-20232024";

    public String pobierzTekstKalendarza() {
        try {
            Document doc = Jsoup.connect(url).get();
            Element tabela = doc.select("table").first();
            return tabela.text();
        } catch (Exception e) {
            e.printStackTrace();
            return "Nie udało się pobrać danych.";
        }
    }

    public static void main(String[] args) {
        PobierzKalendarzAkademicki kalendarz = new PobierzKalendarzAkademicki();
        System.out.println(kalendarz.pobierzTekstKalendarza());
    }
}
