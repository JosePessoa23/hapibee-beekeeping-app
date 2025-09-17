package com.isep.acme.model.Modelo;

import com.isep.acme.model.UserView;

import java.util.List;

public class DeclaracaoAnualDTO {


        public UserView user;

        public List<ApiarioDTO> listapiarios;

    protected DeclaracaoAnualDTO() {
    }

        public DeclaracaoAnualDTO(UserView user,List<ApiarioDTO> apiarioList) {
            this.listapiarios = apiarioList;
            this.user = user;
        }

    public UserView getUser() {
        return user;
    }

    public List<ApiarioDTO> getListapiarios() {
        return listapiarios;
    }

    public void setUser(UserView user) {
        this.user = user;
    }

    public void setListapiarios(List<ApiarioDTO> listapiarios) {
        this.listapiarios = listapiarios;
    }

    @Override
    public String toString() {
        return "Informação do Apicultor : \n" + user +
                "\n\nLista de Apiários :" + listapiarios;
    }
}

