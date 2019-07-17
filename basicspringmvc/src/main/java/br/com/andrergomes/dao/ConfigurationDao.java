package br.com.andrergomes.dao;


import java.util.List;

import br.com.andrergomes.model.Configuration;

public interface ConfigurationDao {

	public List<Configuration> findAll();
}
