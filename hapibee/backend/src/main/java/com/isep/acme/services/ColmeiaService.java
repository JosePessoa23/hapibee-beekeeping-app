package com.isep.acme.services;

import com.isep.acme.model.Modelo.ColmeiaDTO;

import java.text.ParseException;

public interface ColmeiaService {

    ColmeiaDTO create(Long id);

    ColmeiaDTO desdobramento(Long id,int alcas) throws ParseException;
}
