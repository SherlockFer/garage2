package com.apress.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlyWayConfig {

	@Bean
	public FlywayMigrationStrategy cleanMigrationStrategy() {
		return flyway -> {
			flyway.clean();
			flyway.migrate();
		};
	}

}
