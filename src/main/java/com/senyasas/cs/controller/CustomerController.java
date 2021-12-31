package com.senyasas.cs.controller;

import com.senyasas.cs.model.api.request.my.CustomerRequest;
import com.senyasas.cs.model.entity.CustomerModel;
import com.senyasas.cs.model.mapping.CustomerMapper;
import com.senyasas.cs.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerMapper mapper;
    private final CustomerRepository repository;

    @PostMapping
    public CustomerModel create(@Valid @RequestBody final CustomerRequest request) {
        log.info("Creating customer");
        CustomerModel customerModel = mapper.fromRequest(request);
        return repository.save(customerModel);
    }

    @GetMapping("/{id}")
    public CustomerModel get(@PathVariable Long id) {
        log.info(String.format("Retrieving customer of id=%s", id));
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<CustomerModel>> findAll() {
        log.info("fetch all customers");
        List<CustomerModel> customers = repository.findAll();
        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customers);
        }

        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @PutMapping("/{id}")
    public CustomerModel update(@RequestBody CustomerRequest request, @PathVariable Long id) {
        log.info("updating customer");
        return repository.findById(id).map(customerModel -> {
                    customerModel.setName(request.name());
                    customerModel.setAge(Integer.parseInt(request.age()));
                    customerModel.setEmail(request.email());
                    return repository.save(customerModel);
                })
                .orElseGet(
                        () -> {
                            return repository.save(new CustomerModel().withId(id));
                        }
                );

    }
}
