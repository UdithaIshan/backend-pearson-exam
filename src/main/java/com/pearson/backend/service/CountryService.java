package com.pearson.backend.service;

import com.pearson.backend.model.Country;

import java.util.List;

public interface CountryService {
    Country create(Country country);

    Country readOne(Long id);

    List<Country> readAll();

    Country update(Country country, Long id);

    void delete(Long id);

    void deleteAll();
}
