package com.isep.acme.services;

import com.isep.acme.model.*;
import com.isep.acme.repositories.DeclaracaoRepository;
import com.isep.acme.repositories.TransumanciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeclaracaoServiceImpl implements DeclaracaoService{

    @Autowired
    private DeclaracaoRepository repository;

    @Override
    public void create(DeclaracaoDTO declaracaoDTO) {
        try {
            Declaracao temp = new Declaracao(declaracaoDTO);
            repository.save(temp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Iterable<DeclaracaoDTO> getAll() {
        Iterable<Declaracao> p = repository.findAll();
        List<DeclaracaoDTO> pDto = new ArrayList();
        for (Declaracao pd:p) {
            pDto.add(pd.toDTO());
        }
        return pDto;
    }
}
