package com.udla.evaluaytor.businessdomain.evaluacion.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class FormularioEvaluacionDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean cumplimiento;
    private String observacion;

    @OneToOne
    @JoinColumn(name = "documento_id")
    private Documento documento;

    @ManyToOne
    @JoinColumn(name = "estado_detalle_id")
    private EstadoDetalle estadoDetalle;

    private Long matrizEvaluacion_id;

    @Transient
    private MatrizEvaluacion matrizEvaluacion;
}
