package com.isep.acme.services;

import com.isep.acme.model.Modelo.*;
import com.isep.acme.model.Product;
import com.isep.acme.model.User;
import com.isep.acme.repositories.IRepos.IApiarioRepository;
import com.isep.acme.repositories.IRepos.IHistoricoRepository;
import com.isep.acme.repositories.IRepos.IProductRepository;
import com.isep.acme.repositories.IRepos.IUserRepository;
import com.isep.acme.repositories.dataModels.JPA.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApiarioServiceImpl implements ApiarioService{

    @Autowired
    private EmailService emailService;

    @Autowired
    private IApiarioRepository repository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IHistoricoRepository historicoRepository;

    @Override
    public Iterable<ApiarioDTO> getAll() {
        Iterable<Apiario> p = repository.findAll();
        if (p == null){
            return null;
        }
        List<ApiarioDTO> list = new ArrayList<>();
        for (Apiario apiario: p) {
            list.add(apiario.toDTO());
        }

        return list;
    }

    @Override
    public Optional<ApiarioDTO> getDetails(Long sku) {
        Optional<Apiario> p = repository.findById(sku);
        if (p.isEmpty()){
            return null;
        }

        return Optional.ofNullable(p.get().toDTO());
    }

    @Override
    public Iterable<Historico> getHistorico(Long sku) {
        List<Historico> historico = historicoRepository.getByApiarioId(sku);

        if (historico ==null){
            return null;
        }

        return historico;
    }

    @Override
    public Optional<ApiarioDTO> getByUserId(String sku) {
        Optional<Apiario> p = repository.findByUserId(sku);
        if (p.isEmpty()){
            return null;
        }

        return Optional.ofNullable(p.get().toDTO());
    }

    @Override
    public ApiarioCreateDTO create(ApiarioCreateDTO apiario, String userId) {
        try {
            Optional<UserJPA> user = userRepository.findByUsername2(userId);
            System.out.println(user.get());
            if (user.isEmpty()){
                return null;
            }
            Apiario temp = new Apiario(apiario);
            temp.setUser(user.get());
            emailService.sendEmail("zonasControladas@gmail.com","Pedido de instalação",createEmail(apiario));
            ApiarioCreateDTO apiarioCreateDTO = repository.save(temp).toDTOCreate();
            System.out.println(apiarioCreateDTO);
            historicoRepository.save(new Historico(apiarioCreateDTO.apiarioId,"Criação apiario"));
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
    public ApiarioDTO updateBySku(String sku, ApiarioDTO product) {
        return null;
    }

    @Override
    public ApiarioDTO addColemia(Long sku, ColmeiaDTOList colmeiaDTO) {
        try {
            Optional<Apiario> p = repository.findById(sku);
            if (p.isEmpty()){
                return null;
            }

            Apiario apiario = p.get();

            for (ColmeiaDTO colmeia : colmeiaDTO.colmeiaList) {
                Colmeia temp = new Colmeia(colmeia);
                apiario.addColmeia(temp);
            }

            //emailService.sendEmail("zonasControladas@gmail.com","Pedido de instalação",createEmail());
            ApiarioDTO apiarioDTO = repository.save(p.get()).toDTO();

            for (ColmeiaDTO colmeia : apiarioDTO.colmeiaList) {
                historicoRepository.save(new Historico(apiario.getApiarioId(),colmeia.id,"Criação colmeia"));
            }

            return apiarioDTO;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(String sku) {
        repository.delete(sku);
    }
}
