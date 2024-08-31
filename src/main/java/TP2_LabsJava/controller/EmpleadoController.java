package TP2_LabsJava.controller;

import TP2_LabsJava.dto.EmpleadoDTO;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;


@RestController
@Validated
public class EmpleadoController {

    private IEmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/empleado")
    public ResponseEntity<?> altaEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) throws URISyntaxException {

            LocalDate fechaCreacion = LocalDate.now();

            empleadoService.agregarEmpleado(Empleado.builder()
                    .nombre(empleadoDTO.getNombre())
                    .apellido(empleadoDTO.getApellido())
                    .nroDocumento(empleadoDTO.getNroDocumento())
                    .email(empleadoDTO.getEmail())
                    .fechaNacimiento(empleadoDTO.getFechaNacimiento())
                    .fechaIngreso(empleadoDTO.getFechaIngreso())
                    .fechaCreacion(fechaCreacion)
                    .build());
            return ResponseEntity.created(new URI("/api/empleado/save")).build();
    }

}
