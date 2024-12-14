package zadanie1;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.TreeMap;

public interface adder extends Remote {
    TreeMap<Integer, String> sortPairs(TreeMap<Integer, String> data) throws RemoteException;
}
