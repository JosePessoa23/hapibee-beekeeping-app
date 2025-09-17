package com.isep.acme.services;


import com.isep.acme.model.Modelo.DeclaracaoAnualDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


@Service
public class DeclaracaoAnualServiceImpl implements DeclaracaoAnualService{

    @Override
    public void create(DeclaracaoAnualDTO declaracaoAnualDTO) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DeclaracaoAnual/declaracao.txt"))) {
            // Write the content to the file
            writer.write(String.valueOf(declaracaoAnualDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
