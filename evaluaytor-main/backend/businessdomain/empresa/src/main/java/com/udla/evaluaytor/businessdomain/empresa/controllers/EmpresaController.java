package com.udla.evaluaytor.businessdomain.empresa.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udla.evaluaytor.businessdomain.empresa.dto.CategoriaDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.ProveedorDTO;
import com.udla.evaluaytor.businessdomain.empresa.dto.ProveedorResponseDTO;
import com.udla.evaluaytor.businessdomain.empresa.models.Categoria;
import com.udla.evaluaytor.businessdomain.empresa.models.MatrizEvaluacion;
import com.udla.evaluaytor.businessdomain.empresa.models.Perito;
import com.udla.evaluaytor.businessdomain.empresa.repositories.CategoriaRepository;
import com.udla.evaluaytor.businessdomain.empresa.repositories.MatrizEvaluacionRepository;
import com.udla.evaluaytor.businessdomain.empresa.repositories.PeritoRepository;
import com.udla.evaluaytor.businessdomain.empresa.services.ProveedorService;




@RestController
@RequestMapping("/api/empresa")
public class EmpresaController {
  

    @Autowired
    PeritoRepository peritoRepository;

    @Autowired 
    CategoriaRepository categoriaRepository;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    MatrizEvaluacionRepository matrizEvaluacionRepository;

  /* 
    // Listar todo
    @GetMapping("/findall")
    public List<Empresa> listarEmpresa() {
        return empresaRepository.findAll();
    }


    // Listar por Id
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Empresa> obtenerEmpresaPorId(@PathVariable Long id) {
        Optional<Empresa> premioOptional = empresaRepository.findById(id);
        return premioOptional.map(premio -> new ResponseEntity<>(premio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva empresa
    @PostMapping("/save")
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa nuevoPremio) {
        Empresa premioGuardado = empresaRepository.save(nuevoPremio);
        return new ResponseEntity<>(premioGuardado, HttpStatus.CREATED);
    }

    // Actualizar empresa
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaActual) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        return empresaOptional.map(empresa -> {
            empresa.setId(id);
            empresa.setNombre(empresaActual.getNombre());
            empresa.setDireccion(empresaActual.getDireccion());
            Empresa empresaActualGuardado = empresaRepository.save(empresa);
            return new ResponseEntity<>(empresaActualGuardado, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un empresa por ID
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            empresaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

*/
    // Crear una nuevo proveedor
    @PostMapping("/proveedor/save")
    public ResponseEntity<ProveedorResponseDTO> createProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        ProveedorResponseDTO proveedorGuardado = proveedorService.createProveedor(proveedorDTO);
        return new ResponseEntity<>(proveedorGuardado, HttpStatus.CREATED);
    }
      
    @PutMapping("/proveedor/update/{id}")
    public ResponseEntity<ProveedorResponseDTO> updateProveedor(@PathVariable Long id, @RequestBody ProveedorDTO proveedorUpdateDTO) {
        ProveedorResponseDTO updatedProveedor = proveedorService.updateProveedor(id, proveedorUpdateDTO);
        return ResponseEntity.ok(updatedProveedor);
    }


    @GetMapping("proveedor/findall")
    public ResponseEntity<List<ProveedorResponseDTO>> getAllProveedores() {
        List<ProveedorResponseDTO> proveedores = proveedorService.getAllProveedores();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("proveedor/findbyid/{id}")
     public ResponseEntity<ProveedorResponseDTO> getProveedorById(@PathVariable Long id) {
        ProveedorResponseDTO proveedor = proveedorService.getProveedorById(id);
        return ResponseEntity.ok(proveedor);
    }

    // Actualizar un proveedor existente por ID
    @PutMapping("/proveedor/updatebyid/{id}")
    public ResponseEntity<ProveedorResponseDTO> updateProveedorById(@PathVariable Long id, @RequestBody ProveedorDTO proveedorUpdateDTO) {
        ProveedorResponseDTO updatedProveedor = proveedorService.updateProveedor(id, proveedorUpdateDTO);
        return ResponseEntity.ok(updatedProveedor);
    }

    // Eliminar un proveedor por ID
    @DeleteMapping("/proveedor/deletebyid/{id}")
    public ResponseEntity<Void> deleteProveedorById(@PathVariable Long id) {
        proveedorService.deleteProveedorById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/perito/findall")
    public ResponseEntity<List<Perito>> getAllPeritos() {
        List<Perito> peritos = peritoRepository.findAll();
        return ResponseEntity.ok(peritos);
    }

    @GetMapping("/perito/findbyid/{id}")
    public ResponseEntity<Perito> getPeritoById(@PathVariable Long id) {
        Optional<Perito> perito = peritoRepository.findById(id);
        return perito.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/perito/save")
    public ResponseEntity<Perito> createPerito(@RequestBody Perito perito) {
        Perito savedPerito = peritoRepository.save(perito);
        return new ResponseEntity<>(savedPerito, HttpStatus.CREATED);
    }

    @PutMapping("/perito/updatebyid/{id}")
    public ResponseEntity<Perito> updatePeritoById(@PathVariable Long id, @RequestBody Perito peritoDetails) {
        Optional<Perito> optionalPerito = peritoRepository.findById(id);
        if (!optionalPerito.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Perito perito = optionalPerito.get();
        perito.setId(id);
        perito.setNombre(peritoDetails.getNombre());
        perito.setDireccion(peritoDetails.getDireccion());
        Perito updatedPerito = peritoRepository.save(perito);
        return ResponseEntity.ok(updatedPerito);
    }

    @DeleteMapping("/perito/deletebyid/{id}")
    public ResponseEntity<Void> deletePeritoById(@PathVariable Long id) {
        if (!peritoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        peritoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Categoria endpoints
    @GetMapping("/categoria/findall")
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> categoriaDTOs = categorias.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(categoriaDTOs);
    }

    @GetMapping("/categoria/findbyid/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            CategoriaDTO categoriaDTO = convertToDTO(categoria.get());
            return ResponseEntity.ok(categoriaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/categoria/save")
    public ResponseEntity<CategoriaDTO> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return new ResponseEntity<>(convertToDTO(savedCategoria), HttpStatus.CREATED);
    }

    @PutMapping("/categoria/updatebyid/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoriaById(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setDescripcion(categoriaDTO.getDescripcion());
            Categoria updatedCategoria = categoriaRepository.save(categoria);
            return ResponseEntity.ok(convertToDTO(updatedCategoria));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/categoria/deletebyid/{id}")
    public ResponseEntity<Void> deleteCategoriaById(@PathVariable Long id) {
        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        categoriaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private CategoriaDTO convertToDTO(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setDescripcion(categoria.getDescripcion());
        return categoriaDTO;
    }

    // Matriz Evaluacion endpoints
    @GetMapping("/findall")
    public ResponseEntity<List<MatrizEvaluacion>> getAllMatrizEvaluaciones() {
        List<MatrizEvaluacion> matrizEvaluaciones = matrizEvaluacionRepository.findAll();
        return ResponseEntity.ok(matrizEvaluaciones);
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<MatrizEvaluacion> getMatrizEvaluacionById(@PathVariable Long id) {
        Optional<MatrizEvaluacion> matrizEvaluacion = matrizEvaluacionRepository.findById(id);
        return matrizEvaluacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/save")
    public ResponseEntity<MatrizEvaluacion> createMatrizEvaluacion(@RequestBody MatrizEvaluacion matrizEvaluacion) {
        MatrizEvaluacion savedMatrizEvaluacion = matrizEvaluacionRepository.save(matrizEvaluacion);
        return new ResponseEntity<>(savedMatrizEvaluacion, HttpStatus.CREATED);
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<MatrizEvaluacion> updateMatrizEvaluacionById(@PathVariable Long id, @RequestBody MatrizEvaluacion matrizEvaluacionDetails) {
        Optional<MatrizEvaluacion> optionalMatrizEvaluacion = matrizEvaluacionRepository.findById(id);
        if (!optionalMatrizEvaluacion.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        MatrizEvaluacion matrizEvaluacion = optionalMatrizEvaluacion.get();
        matrizEvaluacion.setDescripcion(matrizEvaluacionDetails.getDescripcion());
        matrizEvaluacion.setPuntos(matrizEvaluacionDetails.getPuntos());
        matrizEvaluacion.setRequiereDocumento(matrizEvaluacionDetails.getRequiereDocumento());
        MatrizEvaluacion updatedMatrizEvaluacion = matrizEvaluacionRepository.save(matrizEvaluacion);
        return ResponseEntity.ok(updatedMatrizEvaluacion);
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Void> deleteMatrizEvaluacionById(@PathVariable Long id) {
        if (!matrizEvaluacionRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        matrizEvaluacionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }    
}

