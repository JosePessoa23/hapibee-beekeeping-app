package com.isep.acme.model;

import java.util.List;

public class DeclaracaoDTO {


    public UserApicultor user;

    public List<ApiarioDTO> listapiarios;

    protected DeclaracaoDTO() {
    }

    public DeclaracaoDTO(UserApicultor user,List<ApiarioDTO> apiarioList) {
        this.listapiarios = apiarioList;
        this.user = user;
    }

    public UserApicultor getUser() {
        return user;
    }

    public List<ApiarioDTO> getListapiarios() {
        return listapiarios;
    }

    public void setUser(UserApicultor user) {
        this.user = user;
    }

    public void setListapiarios(List<ApiarioDTO> listapiarios) {
        this.listapiarios = listapiarios;
    }

    @Override
    public String toString() {
        return "DeclaracaoDTO{" +
                "user=" + user +
                ", listapiarios=" + listapiarios +
                '}';
    }
}
