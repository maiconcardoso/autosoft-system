package br.com.autosoft.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.autosoft.dtos.LaborDTO;
import br.com.autosoft.entities.Labor;
import br.com.autosoft.exceptions.EntityNotFoundException;
import br.com.autosoft.exceptions.NoSuchElementException;
import br.com.autosoft.repositories.LaborRepository;

@Service
public class LaborService {

    @Autowired
    private LaborRepository repository;

    public Labor create(Labor laborToBeSaved) {
        return repository.save(laborToBeSaved);
    }

    // public LaborDTO readById(Integer id) {
    //     Optional<Labor> laborById = repository.findById(id);
    //     return laborById.stream().map((obj) -> new LaborDTO(obj)).findFirst()
    //             .orElseThrow(() -> new NoSuchElementException(NoSuchElementException.MESSAGE));
    // }

    public LaborDTO readById(Integer id) {
        Labor laborById = repository.findById(id).get();
        LaborDTO laborByIdDTO = new LaborDTO(laborById);
        try {
            return laborByIdDTO;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(NoSuchElementException.MESSAGE);
        }
    }

    public List<LaborDTO> readAll() {
        List<Labor> laborList = repository.findAll();
        return laborList.stream().map((obj) -> new LaborDTO(obj)).collect(Collectors.toList());
    }

    public List<LaborDTO> readByDescription(String description) {
        List<Labor> laborByDescription = repository.findByDescription(description);
        return laborByDescription.stream().map((obj) -> new LaborDTO(obj)).collect(Collectors.toList());
    }

    // public LaborDTO update(Integer id, Labor labor) {
    //     Optional<Labor> laborById = repository.findById(id);
    //     if (laborById.isPresent()) {
    //         Labor laborToBeUpdated = laborById.get();
    //         laborToBeUpdated.setDescription(labor.getDescription());
    //         laborToBeUpdated.setGroupFamily(labor.getGroupFamily());
    //         laborToBeUpdated.setSubGroup(labor.getSubGroup());
    //         laborToBeUpdated.setApplication(labor.getApplication());
    //         laborToBeUpdated.setPrice(labor.getPrice());
    //         repository.save(laborToBeUpdated);
    //     }
    //     return laborById.stream().map((obj) -> new LaborDTO(obj)).findFirst()
    //             .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.MESSAGE));
    // }

    public LaborDTO update(Integer id, Labor labor) {
        Labor laborById = repository.findById(id).get();
        if (laborById != null) {
            Labor laborToBeUpdated = laborById;
            laborToBeUpdated.setDescription(labor.getDescription());
            laborToBeUpdated.setGroupFamily(labor.getGroupFamily());
            laborToBeUpdated.setSubGroup(labor.getSubGroup());
            laborToBeUpdated.setApplication(labor.getApplication());
            laborToBeUpdated.setPrice(labor.getPrice());
            repository.save(laborToBeUpdated);
        }
        LaborDTO laborByIdDTO = new LaborDTO(laborById);
        try {
            return laborByIdDTO;
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(EntityNotFoundException.MESSAGE);
        }
    }

    public void delete(Integer id) {
        readById(id);
        repository.deleteById(id);
    }
}
