package com.taskmanagement.test.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTest 
{
	@Autowired
	private TestRestTemplate template;
	@Test
	public void getAllUsers() throws JSONException {
	String endpoint="http://localhost:9999/api/users/all";
	String users = """
			[
    {
        "userID": 1,
        "username": "john_doe",
        "password": "password123",
        "email": "john.doe@email.com",
        "fullName": "John Doe"
    },
    {
        "userID": 2,
        "username": "jane_smith",
        "password": "pass456",
        "email": "jane.smith@email.com",
        "fullName": "Jane Smith"
    },
    {
        "userID": 3,
        "username": "alex_jones",
        "password": "secret789",
        "email": "alex.jones@email.com",
        "fullName": "Alex Jones"
    },
    {
        "userID": 4,
        "username": "emily_jackson",
        "password": "my_pass",
        "email": "emily.jackson@email.com",
        "fullName": "Emily Jackson"
    },
    {
        "userID": 5,
        "username": "michael_wilson",
        "password": "secure_password",
        "email": "michael.wilson@email.com",
        "fullName": "Michael Wilson"
    },
    {
        "userID": 6,
        "username": "sarah_miller",
        "password": "sarahpass",
        "email": "sarah.miller@email.com",
        "fullName": "Sarah Miller"
    },
    {
        "userID": 7,
        "username": "robert_clark",
        "password": "robert123",
        "email": "robert.clark@email.com",
        "fullName": "Robert Clark"
    },
    {
        "userID": 8,
        "username": "linda_turner",
        "password": "lindapass",
        "email": "linda.turner@email.com",
        "fullName": "Linda Turner"
    },
    {
        "userID": 9,
        "username": "peter_anderson",
        "password": "peterpass",
        "email": "peter.anderson@email.com",
        "fullName": "Peter Anderson"
    },
    {
        "userID": 10,
        "username": "natalie_brown",
        "password": "natalie456",
        "email": "natalie.brown@email.com",
        "fullName": "Natalie Brown"
    },
    {
        "userID": 11,
        "username": "kevin_martin",
        "password": "kevinpass",
        "email": "kevin.martin@email.com",
        "fullName": "Kevin Martin"
    },
    {
        "userID": 12,
        "username": "rachel_carter",
        "password": "rachel789",
        "email": "rachel.carter@email.com",
        "fullName": "Rachel Carter"
    }
]
			""";
		ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(users,response.getBody(),true);
		assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));

	}

	@Test
	public void getUserById() throws JSONException {
	String endpoint="http://localhost:9999/api/users/3";
	String users = """
			{
    "userID": 3,
    "username": "alex_jones",
    "password": "secret789",
    "email": "alex.jones@email.com",
    "fullName": "Alex Jones"
}
			""";

		ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(users,response.getBody(),true);
		assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
	}
	@Test
	public void searchUsersByName() throws JSONException {
	String endpoint="http://localhost:9999/api/users/search/Linda Turner";
	String users = """
			[
			    {
			        "userID": 8,
			        "username": "linda_turner",			
			        "password": "lindapass",
			        "email": "linda.turner@email.com",
			        "fullName": "Linda Turner"
			    }
			]
			""";
		ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(users,response.getBody(),true);
		assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
	}
	@Test
	public void retrieveUsersByCompletedTasks() throws JSONException
	{
		String url = "/api/users/completed-tasks";
		String expectedJson = """
				[
				     {
				        "userID": 1
				     }
				]
				""";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(expectedJson,response.getBody(), false);
	}
	
	@Test
	public void retrieveUsersByMostTasks() throws JSONException
	{
		String url = "/api/users/most-tasks";
		String expectedJson = """
				[
				    {
				        "userID": 1
				    },
				    {
				        "userID": 2
				    },
				    {
				        "userID": 4
				    },
				    {
				        "userID": 5
				    },
				    {
				        "userID": 6
				    },
				    {
				        "userID": 7
				    },
				    {
				        "userID": 8
				    },
				    {
				        "userID": 9
				    },
				    {
				        "userID": 10
				    }
				]
				""";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(expectedJson,response.getBody(), false);
	}
	
	@Test
	public void retrieveUsersByEmailDomaine() throws JSONException{
		String endpoint="http://localhost:9999/api/users/email-domain/email";
		String user="""
				[
				    {
				        "userID": 1,
				        "username": "john_doe",
				        "password": "password123",
				        "email": "john.doe@email.com",
				        "fullName": "John Doe"
				    },
				    {
				        "userID": 2,
				        "username": "jane_smith",
				        "password": "pass456",
				        "email": "jane.smith@email.com",
				        "fullName": "Jane Smith"
				    },
				    {
				        "userID": 3,
				        "username": "alex_jones",
				        "password": "secret789",
				        "email": "alex.jones@email.com",
				        "fullName": "Alex Jones"
				    },
				    {
				        "userID": 4,
				        "username": "emily_jackson",
				        "password": "my_pass",
				        "email": "emily.jackson@email.com",
				        "fullName": "Emily Jackson"
				    },
				    {
				        "userID": 5,
				        "username": "michael_wilson",
				        "password": "secure_password",
				        "email": "michael.wilson@email.com",
				        "fullName": "Michael Wilson"
				    },
				    {
				        "userID": 6,
				        "username": "sarah_miller",
				        "password": "sarahpass",
				        "email": "sarah.miller@email.com",
				        "fullName": "Sarah Miller"
				    },
				    {
				        "userID": 7,
				        "username": "robert_clark",
				        "password": "robert123",
				        "email": "robert.clark@email.com",
				        "fullName": "Robert Clark"
				    },
				    {
				        "userID": 8,
				        "username": "linda_turner",
				        "password": "lindapass",
				        "email": "linda.turner@email.com",
				        "fullName": "Linda Turner"
				    },
				    {
				        "userID": 9,
				        "username": "peter_anderson",
				        "password": "peterpass",
				        "email": "peter.anderson@email.com",
				        "fullName": "Peter Anderson"
				    },
				    {
				        "userID": 10,
				        "username": "natalie_brown",
				        "password": "natalie456",
				        "email": "natalie.brown@email.com",
				        "fullName": "Natalie Brown"
				    },
				    {
				        "userID": 11,
				        "username": "kevin_martin",
				        "password": "kevinpass",
				        "email": "kevin.martin@email.com",
				        "fullName": "Kevin Martin"
				    },
				    {
				        "userID": 12,
				        "username": "rachel_carter",
				        "password": "rachel789",
				        "email": "rachel.carter@email.com",
				        "fullName": "Rachel Carter"
				    }
				]
				""";
		ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(user,response.getBody(), true);
		assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
 
}
	@Test
	public void retrieveUserAuthentication() throws JSONException{
		String endpoint="http://localhost:9999/api/users/authenticate/john_doe/password123";
		String user="""	
				{"code": "AUTHSUCCESS", "message": "User is valid"}
				""";
		ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is2xxSuccessful());
		JSONAssert.assertEquals(user,response.getBody(), true);
		assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
	}
}
 


