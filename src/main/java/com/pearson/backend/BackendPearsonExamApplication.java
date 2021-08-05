package com.pearson.backend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pearson.backend.model.Country;
import com.pearson.backend.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class BackendPearsonExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPearsonExamApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CountryRepository countryRepository) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/countries.json");
			try {
				List<Country> users = mapper.readValue(inputStream,typeReference);
				countryRepository.saveAll(users);
				System.out.println("Data saved");
			} catch (IOException e){
				System.out.println("Unable to save: " + e.getMessage());
			}
		};
	}
}
