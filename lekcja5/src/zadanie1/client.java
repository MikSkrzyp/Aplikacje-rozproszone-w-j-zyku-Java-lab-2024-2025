package zadanie1;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.TreeMap;

public class client {
    public static void main(String[] args) throws RemoteException {
        client c = new client();
        c.connectRemote();
    }

    private void connectRemote() {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 4444);
            adder ad = (adder) reg.lookup("hi serwer");

            TreeMap<Integer, String> data = new TreeMap<>();
            data.put(120, "Warszawa");
            data.put(80, "Gdańsk");
            data.put(200, "Kraków");
            data.put(60, "Poznań");
            data.put(100, "Wrocław");

            TreeMap<Integer, String> sortedData = ad.sortPairs(data);

            System.out.println("Posortowane dane:");
            sortedData.forEach((key, value) -> System.out.println(key + " km -> " + value));
        } catch (NotBoundException | RemoteException e) {
            System.out.println(e);
        }
    }
}
