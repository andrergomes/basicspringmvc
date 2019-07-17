package br.com.andrergomes.service.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andrergomes.model.Configuration;
import br.com.andrergomes.repository.ConfigRepository;
import br.com.andrergomes.util.Constants;

@Service
public class ConfigurationService {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(ConfigurationService.class);

	ConfigRepository configRepository;

	private Map<String, Configuration> configurationList;

	private List<String> mandatoryConfigs;

	@Autowired
	public ConfigurationService(ConfigRepository configRepository) {
		this.configRepository = configRepository;
		this.configurationList = new ConcurrentHashMap<>();
		this.mandatoryConfigs = new ArrayList<>();
		this.mandatoryConfigs.add(Constants.CONFIG_KEY_REFRESH_RATE_CONFIG);
		this.mandatoryConfigs.add(Constants.CONFIG_KEY_REFRESH_RATE_METRIC);
		this.mandatoryConfigs.add(Constants.CONFIG_KEY_REFRESH_RATE_TOKEN);
		this.mandatoryConfigs.add(Constants.CONFIG_KEY_REFRESH_RATE_USER);
	}

	/**
	 * Loads configuration parameters from Database
	 */
	@PostConstruct
	public void loadConfigurations() {
		//LOGGER.debug("Scheduled Event: Configuration table loaded/updated from database");
		StringBuilder sb = new StringBuilder();
		sb.append("Configuration Parameters:");
		List<Configuration> configs = new ArrayList<>();
		configRepository.findAll().forEach(c -> configs.add(c));;
		for (Configuration configuration : configs) {
			sb.append("\n" + configuration.getConfigKey() + ":" + configuration.getConfigValue());
			this.configurationList.put(configuration.getConfigKey(), configuration);
		}
		//LOGGER.debug(sb.toString());

		checkMandatoryConfigurations();
	}

	public Configuration getConfiguration(String key) {
		return configurationList.get(key);
	}

	/**
	 * Checks if the mandatory parameters are exists in Database
	 */
	public void checkMandatoryConfigurations() {
		for (String mandatoryConfig : mandatoryConfigs) {
			boolean exists = false;
			for (Map.Entry<String, Configuration> pair : configurationList.entrySet()) {
				if (pair.getKey().equalsIgnoreCase(mandatoryConfig) && !pair.getValue().getConfigValue().isEmpty()) {
					exists = true;
				}
			}
			if (!exists) {
				/*
				 * String errorLog =
				 * String.format("A mandatory Configuration parameter is not found in DB: %s",
				 * mandatoryConfig);
				 */
				//LOGGER.error(errorLog);
			}
		}

	}
}
