package com.isep.acme.services;

import com.isep.acme.model.*;
import com.isep.acme.repositories.ApiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApiarioServiceImpl implements ApiarioService{

    @Autowired
    private EmailService emailService;

    @Autowired
    private ApiarioRepository repository;


    @Override
    public Iterable<ApiarioDTO> getAll() {
        return null;
    }

    @Override
    public ApiarioCreateDTO create(ApiarioCreateDTO apiario, String userId) {
        try {

            Apiario temp = new Apiario(apiario,userId);
            System.out.println(temp);
            emailService.sendEmail("dgavTecnico@gmail.com","Pedido de instalação",createEmail(apiario));
            ApiarioCreateDTO apiarioCreateDTO = repository.save(temp).toDTOCreate();
            System.out.println(apiarioCreateDTO);
            return apiarioCreateDTO;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    private String createEmail(ApiarioCreateDTO apiario){

        StringBuilder conteudo = new StringBuilder();
        conteudo.append("Foi submetido um pedido de instalação de um apiario por parte do apicultor.\n " +
                "Embaixo encontram-se informações sobre o apiario a ser instalado. Por favor verifique o pedido e toma a sua decisão\n\n Obrigado ");
        conteudo.append("<html><body style='font-family: Arial, sans-serif;'>");
        conteudo.append("<div style='background-color: #f0f0f0; padding: 20px;'>");
        conteudo.append("<h2 style='color: #333; font-size: 28px;'>Detalhes do Apiário</h2>");
        conteudo.append("<p style='font-size: 18px;'><b>Nome do Apiário:</b> ").append(apiario.nome_apiario).append("</p>");
        conteudo.append("<p style='font-size: 18px;'><b>Flora:</b> ").append(apiario.flora).append("</p>");
        conteudo.append("<p style='font-size: 18px;'><b>Proximidade da Água:</b> ").append(apiario.proximidade_agua).append("</p>");

// Incluindo informações de coordenadas
        conteudo.append("<p style='font-size: 18px;'><b>Coordenadas:</b> Latitude ").append(apiario.latitude)
                .append(", Longitude ").append(apiario.longitude).append("</p>");

        conteudo.append("</div>");
/*
        conteudo.append("<div style='margin-top: 20px;'>");
        conteudo.append("<h3 style='color: #333; font-size: 24px;'>Colmeias</h3>");

        for (ColmeiaDTO colmeia : apiario.colmeiaList) {
            conteudo.append("<div style='background-color: #e0e0e0; padding: 10px; margin-bottom: 10px;'>");
            conteudo.append("<p style='font-size: 18px;'><b>Número da Colmeia:</b> ").append(colmeia.getNumeroColmeia()).append("</p>");
            conteudo.append("<p style='font-size: 18px;'><b>Alças:</b> ").append(colmeia.getAlças()).append("</p>");
            conteudo.append("<p style='font-size: 18px;'><b>Tipo:</b> ").append(colmeia.getTipo()).append("</p>");
            conteudo.append("<p style='font-size: 18px;'><b>Data da Rainha:</b> ").append(colmeia.getDataRainha()).append("</p>");
            conteudo.append("</div>");
        }*/

        conteudo.append("</div></body></html>");
        return conteudo.toString();
    }

    @Override
    public boolean update(Long apiarioId,boolean approved) {
        if (approved==true) {
            try {
                Optional<Apiario> p = repository.findById(apiarioId);
                emailService.sendEmail("zonasControladas@gmail.com","Pedido de instalação",createEmail(p.get().toDTOCreate()));
                return true;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public ApiarioDTO update2(Long apiarioId,boolean approved) {
        if (approved==true) {
            try {
                Optional<Apiario> p = repository.findById(apiarioId);
                p.get().setApproved(true);
                httpRequest httpRequest = new httpRequest();
                httpRequest.executeHttpRequestApiario(p.get(),p.get().getUser().getUsername());
                return repository.save(p.get()).toDTO();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }


    @Override
    public void delete(Long sku) {
        Optional<Apiario> apiario = repository.findById(sku);
        if (apiario.isEmpty()){
            return;
        }
        repository.delete(apiario.get());
    }
}
