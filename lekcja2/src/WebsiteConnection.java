import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class WebsiteConnection {
    public static void main(String[] args) {
        String[] websites = {"www.wp.pl", "www.ug.edu.pl", "www.onet.pl", "www.interia.pl", "www.helion.pl"};
        Random random = new Random();

        for (int i = websites.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            String temp = websites[index];
            websites[index] = websites[i];
            websites[i] = temp;
        }

        for (String website : websites) {
            try {
                Socket socket = new Socket(website, 80);
                InetAddress remoteAddress = socket.getInetAddress();

                System.out.println("Connected to: " + website);
                System.out.println("IP Address: " + remoteAddress.getHostAddress());
                System.out.println("Remote Port: " + socket.getPort());
                System.out.println("Local Port: " + socket.getLocalPort());
                System.out.println();

                socket.close();
            } catch (Exception e) {
                System.out.println("Error connecting to " + website + ": " + e.getMessage());
            }
        }
    }
}
