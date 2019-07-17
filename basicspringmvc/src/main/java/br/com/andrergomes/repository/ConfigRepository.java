package br.com.andrergomes.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.andrergomes.model.Configuration;

public interface ConfigRepository extends CrudRepository<Configuration, String> {

}
