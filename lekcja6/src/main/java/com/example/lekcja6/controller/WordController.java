package com.example.lekcja6.controller;

import com.example.lekcja6.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class WordController {

    @Autowired
    private FileService fileService;

    @GetMapping("/przetwarzaj-slowa")
    public String przetwarzajSlowa() throws IOException {
        List<String> slowa = fileService.wczytajIPrzetworzPlik();
        return "Posortowane słowa: " + slowa + ", Szyfr Cezara dla pierwszego słowa: " + fileService.szyfrCezara(slowa.get(0));
    }
}
