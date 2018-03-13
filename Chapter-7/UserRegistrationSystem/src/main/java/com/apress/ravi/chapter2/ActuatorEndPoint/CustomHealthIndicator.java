package com.apress.ravi.chapter2.ActuatorEndPoint;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		int errorCode = check();
		if (errorCode == 0) {
			return Health
					.up()
					.withDetail("Status", "UP")
					.withDetail("Error Code", errorCode)
					.withDetail("Description", 
							"Your Custom Health indicator point is UP")
					.build();
		}
		return Health.up().build();
	}

	public int check() {
		return 0;
	}
}
