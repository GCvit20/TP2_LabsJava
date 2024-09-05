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
import java.util.List;

@RestController
@Validated
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody EmpleadoDTO empleadoDTO) throws URISyntaxException {

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
        List<EmpleadoDTO> empleadosDTO = empleadoService.obtenerEmpleados();
        return ResponseEntity.ok(empleadosDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmpleadoDTO> findAllById(@PathVariable Long id) {
        EmpleadoDTO empleadoDTO = empleadoService.obtenerEmpleadoPorId(id);
        return ResponseEntity.ok(empleadoDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDTO) {

        EmpleadoDTO updatedEmpleado = empleadoService.update(id, empleadoDTO);
        return ResponseEntity.ok(updatedEmpleado);
    }
}
