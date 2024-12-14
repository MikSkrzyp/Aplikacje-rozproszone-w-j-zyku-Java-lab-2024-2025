import java.net.*;
import java.io.*;
import java.sql.*;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
            System.out.println("Serwer nasłuchuje na porcie 6666...");
        } catch (IOException e) {
            System.out.println("Błąd przy tworzeniu gniazda serwerowego: " + e.getMessage());
            System.exit(-1);
        }

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Połączenie przyjęte od: " + socket);
                new Thread(new ClientHandler(socket)).start();
            } catch (IOException e) {
                System.out.println("Błąd przy obsłudze połączenia: " + e.getMessage());
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("Otrzymano: " + input);


                String[] parts = input.split(";");
                if (parts.length == 3) {
                    String imie = parts[0];
                    String nazwisko = parts[1];
                    int rokUr = Integer.parseInt(parts[2]);


                    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/proba", "root", "");
                         PreparedStatement statement = connection.prepareStatement(
                                 "INSERT INTO osoba (imie, nazwisko, rok_ur) VALUES (?, ?, ?)")) {
                        statement.setString(1, imie);
                        statement.setString(2, nazwisko);
                        statement.setInt(3, rokUr);
                        statement.executeUpdate();
                        out.println("Dodano do bazy: " + imie + " " + nazwisko);
                    } catch (SQLException e) {
                        out.println("Błąd zapisu do bazy danych: " + e.getMessage());
                    }
                } else {
                    out.println("Błędny format danych!");
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd przy obsłudze klienta: " + e.getMessage());
        }
    }
}
