package com.udla.evaluaytor.businessdomain.evaluacion.models;

import lombok.Data;

@Data
public class MatrizEvaluacion {

    private Long id;
    private String descripcion;
    private int puntos;
    private Boolean requiereDocumento;
}
