package com.pearson.backend.controller;

import com.pearson.backend.model.Country;
import com.pearson.backend.repository.CountryRepository;
import com.pearson.backend.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
@RequestMapping("/rest/v2")
public class CountryController {

    private CountryService countryService;

    @GetMapping("/countries")
    public ResponseEntity<List<EntityModel<Country>>> read() {
        List<EntityModel<Country>> countries = countryService.readAll().stream().map(country -> EntityModel.of(country,
                        linkTo(methodOn(CountryController.class).read(country.getId())).withSelfRel(),
                        linkTo(methodOn(CountryController.class).read()).withRel("countries")))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(countries);
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<EntityModel<Country>> read(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(EntityModel.of(countryService.readOne(id), linkTo(methodOn(CountryController.class).read(id)).withSelfRel(),
                linkTo(methodOn(CountryController.class).read()).withRel("countries")));
    }

    @PostMapping("/countries")
    public ResponseEntity<Country> create(@Valid @RequestBody Country country) {
        return ResponseEntity.ok().body(countryService.create(country));
    }

    @PutMapping("/countries/{id}")
    public ResponseEntity<Country> update(@Valid @RequestBody Country country, @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(countryService.update(country, id));
    }

    @DeleteMapping("/countries")
    public ResponseEntity<?> delete() {
        countryService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/countries/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        countryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
