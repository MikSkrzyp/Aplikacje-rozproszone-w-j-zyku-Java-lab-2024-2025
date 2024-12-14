package zadanie1;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.TreeMap;

public class serwer extends UnicastRemoteObject implements adder {
    public serwer() throws RemoteException {
        super();
    }

    @Override
    public TreeMap<Integer, String> sortPairs(TreeMap<Integer, String> data) throws RemoteException {
        return new TreeMap<>(data);
    }

    public static void main(String[] args) throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(4444);
            reg.rebind("hi serwer", new serwer());
            System.out.println("serwer is ready");
        } catch (RemoteException e) {
            System.out.println("Exception: " + e);
        }
    }
}
