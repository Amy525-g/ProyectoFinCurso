package com.udla.evaluaytor.businessdomain.evaluacion.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class FormularioEvaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;
    private LocalDate fecha;
    private Integer evaluacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_formulario_id", nullable = false)
    private EstadoFormulario estadoFormulario;

    private Long proveedor_id;
    private Long categoria_id;
    private Long perito_id;

    @Transient
    private Proveedor proveedor;

    @Transient
    private Perito Perito;

    @Transient
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "formulario_evaluacion_id")
    private List<FormularioEvaluacionDetalle> detalles;
}
