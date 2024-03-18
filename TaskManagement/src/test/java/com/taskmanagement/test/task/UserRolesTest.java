package com.taskmanagement.test.task;
 
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity;
 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRolesTest {
	@Autowired
	private TestRestTemplate template;
	@Test
	public void getUserRolesByUserId() throws JSONException {
	String endpoint="/api/userroles/user/hey";
	String projects = """
			[
    {
        "user": {
            "userID": 4,
            "username": "emily_jackson",
            "password": "my_pass",
            "email": "emily.jackson@email.com",
            "fullName": "Emily Jackson"
        },
        "userRole": {
            "userRoleID": 3,
            "roleName": "Manager"
        }
    }
]
			""";
	int categoryId=-1;
	try {
		categoryId = Validation.extractId(endpoint);
	} catch (Exception e) {
		e.printStackTrace();
	}
	assertFalse(categoryId >= 0, "Expected categoryId to be a positive integer");
//	ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
//	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
//	assertTrue(response.getStatusCode().is2xxSuccessful());
//	JSONAssert.assertEquals(projects,response.getBody(),true);
//	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
}
	
	@Test
	public void deleteUserRoles() throws JSONException
	{
		String endpoint = "http://localhost:9999/api/userroles/revoke/15/16";
		String userroles = """
				{
				    "code": "DLTFAILS",
				    "message": "UserRole doesn't exist"
				}
				""";
		ResponseEntity<String> response = template.exchange(endpoint,HttpMethod.DELETE,null,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(userroles,response.getBody(), true);
	}
 
}

