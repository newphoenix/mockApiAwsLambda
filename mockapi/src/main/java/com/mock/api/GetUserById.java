package com.mock.api;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mock.api.data.UserData;

public class GetUserById implements RequestHandler<APIGatewayProxyRequestEvent, Map<String, Object>> {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public Map<String, Object> handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		String userId = input.getPathParameters().get("userId");

		Map<String, Object> response = new HashMap<>();
		response.put("statusCode", 200);
		response.put("body", gson.toJson(UserData.USERS.getOrDefault(userId, null)));
		response.put("headers", Map.of("Content-Type", "application/json"));

		return response;
	}

}
