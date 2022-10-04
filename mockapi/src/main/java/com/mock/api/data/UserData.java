package com.mock.api.data;

import java.util.Map;

import com.mock.api.dto.User;

public class UserData {

	public static final Map<String,User> USERS = Map.of(//
			"1",new User(1,"John","Smith",25),
			"2",new User(2,"Samantha","Jonas",19),
			"3",new User(3,"Joana","Warn",32),
			"4",new User(4,"Noah","Jacobs",46));
}
