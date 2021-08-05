package com.pearson.backend.service;

import com.pearson.backend.model.Country;
import com.pearson.backend.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

    @Override
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country readOne(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Country> readAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country update(Country country, Long id) {
        return countryRepository.findById(id).map(
                savedCountry -> {
                    savedCountry.setName(country.getName());
                    return countryRepository.save(savedCountry);
                }
        ).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public void delete(Long id) {
        try {
            countryRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteAll() {
        countryRepository.deleteAll();
    }
}
