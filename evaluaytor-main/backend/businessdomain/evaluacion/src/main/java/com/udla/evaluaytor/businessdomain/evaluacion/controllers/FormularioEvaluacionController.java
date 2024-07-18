package com.udla.evaluaytor.businessdomain.evaluacion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udla.evaluaytor.businessdomain.evaluacion.models.FormularioEvaluacion;
import com.udla.evaluaytor.businessdomain.evaluacion.services.FormularioEvaluacionService;

@RestController
@RequestMapping("/api/evaluiacion")
public class FormularioEvaluacionController {

    @Autowired
    private FormularioEvaluacionService formularioEvaluacionService;
    
    @GetMapping("/formulario/{formularioId}")
    public FormularioEvaluacion getFormularioEvaluacion(@PathVariable Long formularioId) {
        return formularioEvaluacionService.getFormularioEvaluacion(formularioId);
    }

}
