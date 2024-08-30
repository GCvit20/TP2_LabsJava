package TP2_LabsJava.controller;

import TP2_LabsJava.dto.EmpleadoDTO;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.service.IEmpleadoService;
import TP2_LabsJava.validator.EmpleadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;


@RestController
@RequestMapping("/api")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;


    @PostMapping("/empleado")
    public ResponseEntity<?> save(@RequestBody EmpleadoDTO empleadoDTO) throws URISyntaxException {

        LocalDate fechaCreacion = LocalDate.now();

        if (!EmpleadoValidator.esEdadValida(empleadoDTO.getFechaNacimiento())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La edad del empleado no puede ser menor a 18 años.");
        }

        if (empleadoService.esEmpleadoPorNumeroDocumento(empleadoDTO.getNroDocumento())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un empleado con el documento ingresado.");
        }

        if(empleadoService.esEmpleadoPorEmail(empleadoDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un empleado con el email ingresado.");
        }

        empleadoService.agregarEmpleado(Empleado.builder()
                .nombre(empleadoDTO.getNombre())
                .apellido(empleadoDTO.getApellido())
                .nroDocumento(empleadoDTO.getNroDocumento())
                .email(empleadoDTO.getEmail())
                .fechaNacimiento(empleadoDTO.getFechaNacimiento())
                .fechaIngreso(empleadoDTO.getFechaIngreso())
                .fechaCreacion(fechaCreacion)
                .build());
        return ResponseEntity.created(new URI("/api/employee/save")).build();
    }

}
