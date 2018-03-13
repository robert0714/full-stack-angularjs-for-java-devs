package com.apress.ravi.chapter2.ActuatorEndPoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

@Component
public class MyCustomEndpoint implements Endpoint<List<String>> {

	@Override
	public String getId() {
		return "myCustomEndpoint";
	}

	@Override
	public List<String> invoke() {
		List<String> customMessages = new ArrayList<String>();
		customMessages.add("This is custom message 1");
		customMessages.add("This is custom message 2");
		return customMessages;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isSensitive() {
		return true;
	}

}
