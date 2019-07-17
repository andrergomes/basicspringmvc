package br.com.andrergomes.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.andrergomes.model.Configuration;

@Transactional
@Repository
public class ConfigurationDaoImpl implements ConfigurationDao {
	
	private SessionFactory sessionFactory;	
	
	@Autowired
	public ConfigurationDaoImpl(SessionFactory sessionFactory) {		
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Configuration> findAll() {
		@SuppressWarnings("unchecked")
		TypedQuery<Configuration> query = sessionFactory.getCurrentSession().createQuery("from Configuration");
		return query.getResultList();
	}

}
