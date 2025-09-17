package com.isep.acme.services;

import com.isep.acme.model.Modelo.*;
import com.isep.acme.model.Product;
import com.isep.acme.repositories.IRepos.IApiarioRepository;
import com.isep.acme.repositories.IRepos.IColmeiaRepository;
import com.isep.acme.repositories.IRepos.IHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ColmeiaServiceImpl implements ColmeiaService {

    @Autowired
    private IColmeiaRepository repository;

    @Autowired
    private IApiarioRepository apiarioRepository;

    @Autowired
    private IHistoricoRepository historicoRepository;


    @Override
    public ColmeiaDTO create(Long colmeia) {
        Optional<Colmeia> p = repository.findById(colmeia);
        if (p.isEmpty()){
            return null;
        }
        return null;
    }

    @Override
    public ColmeiaDTO desdobramento(Long id, int alcas) throws ParseException {
        Optional<Colmeia> p = repository.findById(id);
        if (p.isEmpty()){
            return null;
        }
        Colmeia colmeia_mae = p.get();
        int alcas_mae= colmeia_mae.getAlças();
        if (alcas >= alcas_mae){
            return null;
        }

        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAtual.format(formatter);


        Colmeia colmeia_filho = new Colmeia(colmeia_mae.getNumeroColmeia()+1,alcas,colmeia_mae.getTipo(),dataFormatada,colmeia_mae.getColmeiaId());
        colmeia_mae.setAlças(alcas_mae-alcas);

        try {
            repository.save(colmeia_filho);
            repository.save(colmeia_mae);
            Apiario apiario= apiarioRepository.findByColmeia(colmeia_mae.getColmeiaId()).get();
            apiario.addColmeia(colmeia_filho);
            apiarioRepository.save(apiario);
            ColmeiaDTO result = colmeia_filho.toDTO();
            historicoRepository.save(new Historico(apiario.getApiarioId(),colmeia_filho.getColmeiaId(),"Desdobramento"));
            return  result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
