package TP2_LabsJava.controller;

import TP2_LabsJava.dto.EmpleadoDTO;
import TP2_LabsJava.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<EmpleadoDTO> save(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoGuardadoDTO = empleadoService.agregarEmpleado(empleadoDTO);
        return ResponseEntity.ok(empleadoGuardadoDTO);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.noContent().build();
    }
}
