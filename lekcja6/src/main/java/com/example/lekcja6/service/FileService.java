package com.example.lekcja6.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FileService {

    private static final int KLUCZ_SZYFRU = 13;

    public List<String> wczytajIPrzetworzPlik() throws IOException {
        List<String> slowa = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/dane.txt"))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] slowaZLinii = linia.split("\\s+");
                Collections.addAll(slowa, slowaZLinii);
            }
        }

        Collections.sort(slowa);
        return slowa;
    }

    public String szyfrCezara(String tekst) {
        StringBuilder wynik = new StringBuilder();
        for (char znak : tekst.toCharArray()) {
            if (Character.isLetter(znak)) {
                char baza = Character.isUpperCase(znak) ? 'A' : 'a';
                znak = (char) ((znak - baza + KLUCZ_SZYFRU) % 26 + baza);
            }
            wynik.append(znak);
        }
        return wynik.toString();
    }
}
