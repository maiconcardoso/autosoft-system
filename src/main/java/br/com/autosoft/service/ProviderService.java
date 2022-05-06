package br.com.autosoft.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.autosoft.dtos.ProviderDTO;
import br.com.autosoft.entities.Provider;
import br.com.autosoft.exceptions.EntityNotFoundException;
import br.com.autosoft.exceptions.NoSuchElementException;
import br.com.autosoft.repositories.ProviderRepository;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository repository;

    public Provider create(Provider provider) {
        return repository.save(provider);
    }

    public Page<Provider> readAllPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<ProviderDTO> readAll() {
        List<Provider> list = repository.findAll();
        return list.stream().map((obj) -> new ProviderDTO(obj)).collect(Collectors.toList());
    }

    public ProviderDTO readById(Integer id) {
        Optional<Provider> providerById = repository.findById(id);
        return providerById.stream().map((obj) -> new ProviderDTO(obj)).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.MESSAGE));
    }

    public List<ProviderDTO> readByName(String name) {
        List<Provider> providerByName = repository.findByName(name);
        return providerByName.stream().map((obj) -> new ProviderDTO(obj)).collect(Collectors.toList());
    }

    public ProviderDTO update(Integer id, Provider providerToBeUpdate) {
        Optional<Provider> providerById = repository.findById(id);
        if (providerById.isPresent()) {
            Provider provider = providerById.get();
            provider.setName(providerToBeUpdate.getName());
            provider.setAddress(providerToBeUpdate.getAddress());
            provider.setCep(providerToBeUpdate.getCep());
            provider.setCity(providerToBeUpdate.getCity());
            provider.setCnpj(providerToBeUpdate.getCnpj());
            provider.setEmail(providerToBeUpdate.getEmail());
            provider.setPhoneNumber(providerToBeUpdate.getPhoneNumber());
            repository.save(provider);
        }
        return providerById.stream().map((obj) -> new ProviderDTO(obj)).findFirst()
                .orElseThrow(() -> new NoSuchElementException(NoSuchElementException.MESSAGE));
    }

    public void deleteById(Integer id) {
        ProviderDTO providerById = readById(id);
        repository.deleteById(providerById.getId());
    }
}
