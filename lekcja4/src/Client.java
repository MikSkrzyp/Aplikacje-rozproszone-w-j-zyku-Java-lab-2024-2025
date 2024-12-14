import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6666);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Połączono z serwerem.");
            System.out.println("Wprowadź dane w formacie: imie;nazwisko;rok_ur lub 'quit' aby zakończyć.");

            String input;
            while (true) {
                System.out.print("Dane: ");
                input = scanner.nextLine();

                if ("quit".equalsIgnoreCase(input)) {
                    System.out.println("Zakończenie pracy klienta.");
                    break;
                }

                out.println(input);
                String response = in.readLine();
                System.out.println("Serwer: " + response);
            }
        } catch (IOException e) {
            System.out.println("Błąd połączenia: " + e.getMessage());
        }
    }
}
