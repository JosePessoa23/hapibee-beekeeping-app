package com.isep.acme.services;

import com.isep.acme.model.Modelo.*;
import com.isep.acme.repositories.IRepos.*;
import com.isep.acme.repositories.dataModels.JPA.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistoServiceImpl implements RegistoService {

    @Autowired
    private final IRegistoRepository registoRepository;

    @Autowired
    private  IHistoricoRepository historicoRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IColmeiaRepository colmeiaRepository;

    @Autowired
    private IApiarioRepository apiarioRepository;

    @Autowired
    public RegistoServiceImpl(IRegistoRepository registoRepository) {
        this.registoRepository = registoRepository;
    }

    public Inspeção adicionarInspecao(Long colmeiaId,String userId ,InspecaoDTO inspecaoDTO) {

        Optional<UserJPA> user = userRepository.findByUsername2(userId);
        if (user.isEmpty()){
            return null;
        }

        Optional<Colmeia> colmeia = colmeiaRepository.findById(colmeiaId);
        if (colmeia.isEmpty()){
            return null;
        }

        Inspeção inspecao = new Inspeção(userId,inspecaoDTO.higiene,inspecaoDTO.tendencia_enxamear,inspecaoDTO.agressividade,inspecaoDTO.produtividade,inspecaoDTO.capacidade_polinizadora,inspecaoDTO.observacoes);

        inspecao.setColmeia(colmeia.get());

        Inspeção inspecao1 = registoRepository.save(inspecao);

        Long apiarioId = apiarioRepository.findByColmeia(colmeiaId).get().getApiarioId();

        historicoRepository.save(new Historico(apiarioId,colmeiaId,"Inspeção"));

        return inspecao1;
    }

    public List<Inspeção> findAll() {

       // return registoRepository.;
        return null;
    }

}
