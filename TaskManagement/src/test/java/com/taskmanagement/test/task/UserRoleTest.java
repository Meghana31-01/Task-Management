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
import org.springframework.http.ResponseEntity;
 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRoleTest 
{
	@Autowired
	private TestRestTemplate template;
	@Test
	public void getAllUserRoles() throws JSONException {
	String endpoint="http://localhost:9999/api/userrole/all";
	String userrole = """
			[
    {
        "userRoleID": 1,
        "roleName": "Admin"
    },
    {
        "userRoleID": 2,
        "roleName": "User"
    },
    {
        "userRoleID": 3,
        "roleName": "Manager"
    },
    {
        "userRoleID": 4,
        "roleName": "Product Manager"
    },
    {
        "userRoleID": 5,
        "roleName": "Designer"
    },
    {
        "userRoleID": 6,
        "roleName": "Marketing Specialist"
    },
    {
        "userRoleID": 7,
        "roleName": "Developer"
    },
    {
        "userRoleID": 8,
        "roleName": "Customer Support Representative"
    },
    {
        "userRoleID": 9,
        "roleName": "Sales Trainer"
    },
    {
        "userRoleID": 10,
        "roleName": "Documentation Specialist"
    }
]
			""";
	ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is2xxSuccessful());
	JSONAssert.assertEquals(userrole,response.getBody(),true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
 
 
	}
	@Test
	public void getUserRoleById() throws JSONException {
	String endpoint="http://localhost:9999/api/userrole/6";
	String userrole = """
			{
    "userRoleID": 6,
    "roleName": "Marketing Specialist"
}

			""";
	int userroleId=-1;
	try {
		userroleId = Validation.extractId(endpoint);
	} catch (Exception e) {
		e.printStackTrace();
	}
	assertTrue(userroleId >= 0, "Expected categoryId to be a positive integer");
	ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is2xxSuccessful());
	JSONAssert.assertEquals(userrole,response.getBody(),true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
 
 
	}
	@Test
	public void deleteUserRoleById() throws JSONException
	{
		String endpoint = "http://localhost:9999/api/userrole/delete/68";
		String userrole = """
				{
    "code": "DLTFAILS",
    "message": "UserRoleid doesn't exist"
}
				""";
		ResponseEntity<String> response = template.exchange(endpoint,HttpMethod.DELETE,null,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(userrole,response.getBody(), true);
	}
}