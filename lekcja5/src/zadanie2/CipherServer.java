package zadanie2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CipherServer extends UnicastRemoteObject implements Cipher {

    public CipherServer() throws RemoteException {
        super();
    }

    @Override
    public String shiftConsonants(String text, int shift) throws RemoteException {
        return processText(text, shift);
    }

    @Override
    public String decryptConsonants(String text, int shift) throws RemoteException {
        return processText(text, -shift);
    }

    private String processText(String text, int shift) {
        char[] chars = text.toCharArray();
        ArrayList<Integer> consonantPositions = new ArrayList<>();
        ArrayList<Character> consonants = new ArrayList<>();
        String vowels = "aeiouy";

        for (int i = 0; i < chars.length; i++) {
            if (!vowels.contains(String.valueOf(chars[i]))) {
                consonantPositions.add(i);
                consonants.add(chars[i]);
            }
        }

        int size = consonants.size();
        for (int i = 0; i < size; i++) {
            int newPosition = (i + shift + size) % size;
            chars[consonantPositions.get(newPosition)] = consonants.get(i);
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(4444);
            registry.rebind("CipherService", new CipherServer());
            System.out.println("CipherServer is ready.");
        } catch (RemoteException e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
}
