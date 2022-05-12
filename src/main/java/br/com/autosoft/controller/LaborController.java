package br.com.autosoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.autosoft.dtos.LaborDTO;
import br.com.autosoft.entities.Labor;
import br.com.autosoft.service.LaborService;

@CrossOrigin(origins = "https://autosoft-system.firebaseapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("v1/labors")
public class LaborController {
    
    @Autowired
    private LaborService service;

    @PostMapping
    public ResponseEntity<Labor> save(@RequestBody Labor laborToBeSaved) {
        Labor laborSaved = service.create(laborToBeSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(laborSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaborDTO> findById(@PathVariable Integer id) {
        LaborDTO laborById = service.readById(id);
        return ResponseEntity.status(HttpStatus.OK).body(laborById);
    }

    @GetMapping("/find")
    public ResponseEntity<List<LaborDTO>> findByDescription(@RequestParam String description) {
        List<LaborDTO> laborByDescription = service.readByDescription(description);
        return ResponseEntity.status(HttpStatus.OK).body(laborByDescription);
    }

    @GetMapping
    public ResponseEntity<List<LaborDTO>> findAll() {
        List<LaborDTO> laborList = service.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(laborList);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<LaborDTO> update(@PathVariable Integer id, @RequestBody Labor laborToBeUpdated) {
        LaborDTO laborUpdated = service.update(id, laborToBeUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(laborUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
