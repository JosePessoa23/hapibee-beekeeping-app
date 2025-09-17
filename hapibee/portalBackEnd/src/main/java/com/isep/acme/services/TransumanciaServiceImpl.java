package com.isep.acme.services;

import com.isep.acme.model.*;
import com.isep.acme.repositories.TransumanciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransumanciaServiceImpl implements TransumanciaService{
    @Autowired
    private EmailService emailService;

    @Autowired
    private TransumanciaRepository repository;


    @Override
    public TransumanciaDTO create(TransumanciaDTO transumancia) {
        try {
            Transumancia temp = new Transumancia(transumancia);
            emailService.sendEmail("dgavTecnico@gmail.com","Pedido de instalação",createEmail(transumancia));
            return repository.save(temp).toDTO();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(String apiarioId,boolean approved) {
        if (approved==true) {
            try {
                Optional<Transumancia> p = repository.findById(Long.parseLong(apiarioId));
                emailService.sendEmail("zonasControladas@gmail.com","Pedido de instalação",createEmail(p.get().toDTO()));
                return true;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public TransumanciaDTO update2(String apiarioId,boolean approved) {
        if (approved==true) {
            try {
                Optional<Transumancia> p = repository.findById(Long.parseLong(apiarioId));
                p.get().setApproved();
                httpRequest httpRequest = new httpRequest();
                httpRequest.executeHttpRequest(p.get().getApiarioId().toString(),p.get().getCoordenadas().getLatitude(),p.get().getCoordenadas().getLongitude());
                return repository.save(p.get()).toDTO();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public Iterable<TransumanciaDTO> getAll() {
        Iterable<Transumancia> p = repository.findAll();
        List<TransumanciaDTO> pDto = new ArrayList();
        for (Transumancia pd:p) {
            pDto.add(pd.toDTO());
        }

        return pDto;
    }


    private String createEmail(TransumanciaDTO transumancia){

        StringBuilder conteudo = new StringBuilder();
        conteudo.append("Foi submetido um pedido de transumância de um apiario por parte do apicultor.\n " +
                "Embaixo encontram-se informações sobre o apiario com a nova latitude e longitude. Por favor verifique o pedido e tome a sua decisão\n\n Obrigado ");
        conteudo.append("<html><body style='font-family: Arial, sans-serif;'>");
        conteudo.append("<div style='background-color: #f0f0f0; padding: 20px;'>");
        conteudo.append("<h2 style='color: #333; font-size: 28px;'>Detalhes do Apiário</h2>");
        conteudo.append("<p style='font-size: 18px;'><b>Apiário Id:</b> ").append(transumancia.apiarioId).append("</p>");
        conteudo.append("<p style='font-size: 18px;'><b>Nome do Apiário:</b> ").append(transumancia.nome_apiario).append("</p>");
        conteudo.append("<p style='font-size: 18px;'><b>Flora:</b> ").append(transumancia.flora).append("</p>");

// Incluindo informações de coordenadas
        conteudo.append("<p style='font-size: 18px;'><b>Coordenadas:</b> Latitude ").append(transumancia.latitude)
                .append(", Longitude ").append(transumancia.longitude).append("</p>");

        conteudo.append("</div>");

        conteudo.append("<div style='margin-top: 20px;'>");
        conteudo.append("<h3 style='color: #333; font-size: 24px;'>Colmeias</h3>");

        for (ColmeiaDTO colmeia : transumancia.colmeiaList) {
            conteudo.append("<div style='background-color: #e0e0e0; padding: 10px; margin-bottom: 10px;'>");
            conteudo.append("<p style='font-size: 18px;'><b>Número da Colmeia:</b> ").append(colmeia.getNumeroColmeia()).append("</p>");
            conteudo.append("<p style='font-size: 18px;'><b>Alças:</b> ").append(colmeia.getAlças()).append("</p>");
            conteudo.append("<p style='font-size: 18px;'><b>Tipo:</b> ").append(colmeia.getTipo()).append("</p>");
            conteudo.append("<p style='font-size: 18px;'><b>Data da Rainha:</b> ").append(colmeia.getDataRainha()).append("</p>");
            conteudo.append("</div>");
        }

        conteudo.append("</div></body></html>");
        return conteudo.toString();
    }

}
