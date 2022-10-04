package com.mock.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mock.api.data.UserData;
import com.mock.api.dto.User;

public class GetUsers implements RequestHandler<APIGatewayProxyRequestEvent,Map<String,Object>> {
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public Map<String, Object> handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		Map<String, String> queryParams = input.getQueryStringParameters();
		String firstname = Objects.nonNull(queryParams)? queryParams.get("firstname") : null;
		
		Collection<User> users = Objects.isNull(firstname)? UserData.USERS.values():
			UserData.USERS.values().stream()
			.filter(u-> u.getFirstname().contains(firstname))
			.collect(Collectors.toList())
			;

		Map<String, Object> response = new HashMap<>();
		response.put("statusCode", 200);
		response.put("body", gson.toJson(users,List.class));
		response.put("headers", Map.of("Content-Type", "application/json"));
		
		return response;
	}

}
