package com.udla.evaluaytor.businessdomain.evaluacion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.udla.evaluaytor.businessdomain.evaluacion.models.FormularioEvaluacionDetalle;
import com.udla.evaluaytor.businessdomain.evaluacion.models.MatrizEvaluacion;
import com.udla.evaluaytor.businessdomain.evaluacion.repositories.FormularioEvaluacionDetalleRepository;
import reactor.core.publisher.Mono;

@Service
public class FormularioEvaluacionDetalleImp implements FormularioEvaluacionDetalleService{

    @Autowired
    private WebClient.Builder webClientBuilder;

     @Autowired
    private FormularioEvaluacionDetalleRepository formularioEvaluacionDetalleRepository;

    public FormularioEvaluacionDetalle getFormularioEvaluacionDetalle(Long formularioId) {

        FormularioEvaluacionDetalle formularioEvaluacionDetalle = formularioEvaluacionDetalleRepository.findById(formularioId)
            .orElseThrow(() -> new RuntimeException("Formulario no encontrado"));

        Long matrizEvaluacionId = formularioEvaluacionDetalle.getMatrizEvaluacion_id();

        WebClient webClient = webClientBuilder.build();

         // Llama al microservicio de matriz evaluacion para obtener la información de la matriz evaluacion
        Mono<MatrizEvaluacion> matrizEvaluacionMono = webClient.get()
            .uri("http://localhost:8081/api/empresa/matrizevaluacion/findbyid/{id}", matrizEvaluacionId)
            .retrieve()
            .bodyToMono(MatrizEvaluacion.class);
        MatrizEvaluacion matrizEvaluacion = matrizEvaluacionMono.block();
        formularioEvaluacionDetalle.setMatrizEvaluacion(matrizEvaluacion);

        return formularioEvaluacionDetalle;

    }


}
