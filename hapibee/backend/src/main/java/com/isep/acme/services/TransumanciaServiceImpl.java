package com.isep.acme.services;

import com.isep.acme.model.Modelo.*;
import com.isep.acme.repositories.IRepos.IApiarioRepository;
import com.isep.acme.repositories.IRepos.IHistoricoRepository;
import com.isep.acme.repositories.IRepos.ITransumanciaRepository;
import com.isep.acme.repositories.dataModels.JPA.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransumanciaServiceImpl implements TransumanciaService{

    @Autowired
    private EmailService emailService;

    @Autowired
    private ITransumanciaRepository repository;

    @Autowired
    private IApiarioRepository apiarioRepository;

    @Autowired
    private IHistoricoRepository historicoRepository;


    @Override
    public TransumanciaDTO create(TransumanciaDTO transumancia) {
        try {
            Transumancia temp = new Transumancia(transumancia);
            emailService.sendEmail("zonasControladas@gmail.com","Pedido de instalação",createEmail(transumancia));
            return repository.save(temp).toDTO();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ApiarioDTO update(String apiarioId,double latitude,double longitude) {
        try {
            ApiarioDTO apiarioDTO =  apiarioRepository.transumancia(apiarioId, latitude, longitude);
            historicoRepository.save(new Historico(apiarioDTO.apiarioId,"Transumancia"));
            return apiarioDTO;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private String createEmail(TransumanciaDTO transumancia){

        StringBuilder conteudo = new StringBuilder();
        conteudo.append("Foi submetido um pedido de transumância de um apiario por parte do apicultor.\n " +
                "Embaixo encontram-se informações sobre o apiario a serem alteradas. Por favor verifique o pedido e tome a sua decisão\n\n Obrigado ");
        conteudo.append("<html><body style='font-family: Arial, sans-serif;'>");
        conteudo.append("<div style='background-color: #f0f0f0; padding: 20px;'>");
        conteudo.append("<h2 style='color: #333; font-size: 28px;'>Detalhes do Apiário</h2>");
        conteudo.append("<p style='font-size: 18px;'><b>Proximidade da Água:</b> ").append(transumancia.proximidade_agua).append("</p>");
        conteudo.append("<p style='font-size: 18px;'><b>Coordenadas:</b> Latitude ").append(transumancia.latitude)
                .append(", Longitude ").append(transumancia.longitude).append("</p>");

        conteudo.append("</div>");

        conteudo.append("<div style='margin-top: 20px;'>");

        conteudo.append("</div></body></html>");
        return conteudo.toString();
    }
}
