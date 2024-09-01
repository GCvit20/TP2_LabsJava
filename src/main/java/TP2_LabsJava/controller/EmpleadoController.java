package TP2_LabsJava.controller;

import TP2_LabsJava.dto.EmpleadoDTO;
import TP2_LabsJava.entity.Empleado;
import TP2_LabsJava.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@Validated
@RequestMapping("/empleado")
public class EmpleadoController {

    private IEmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
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

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Empleado> empleados = empleadoService.obtenerEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{empleadoId}")
    public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable Long empleadoId) {

        Empleado empleado = empleadoService.obtenerEmpleadoPorId(empleadoId);
        return ResponseEntity.ok(empleado);
    }

}
