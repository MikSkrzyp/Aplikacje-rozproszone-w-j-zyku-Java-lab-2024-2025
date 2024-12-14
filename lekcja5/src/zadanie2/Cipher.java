package zadanie2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cipher extends Remote {
    public String shiftConsonants(String text, int shift) throws RemoteException;
    public String decryptConsonants(String text, int shift) throws RemoteException;
}