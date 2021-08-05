package com.pearson.backend.service;

import com.pearson.backend.model.Country;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenGivenCorrectId_thenCountryShouldFound() {
        String countryName = "Sri Lanka";
        Country found = countryService.readOne(214L);

        assertEquals(countryName, found.getName());
    }

    @Test
    void whenGivenWrongId_thenCountryShouldNotFound() {
        String countryName = "Sri Lanka";
        Country found = countryService.readOne(1L);

        assertNotEquals(countryName, found.getName());
    }
}