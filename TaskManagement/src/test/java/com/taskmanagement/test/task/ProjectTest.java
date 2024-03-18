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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProjectTest {
	@Autowired
	private TestRestTemplate template;
	@Test
	public void getOngoingProjects() throws JSONException {
	String endpoint="/api/projects/ongoing";
	String projects = """
			{
    "code": "GETALLFAILS",
    "message": "Project List is Empty"
}
			""";
	ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is4xxClientError());
	JSONAssert.assertEquals(projects,response.getBody(),true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
} 
	@Test
	public void getProjectsByDateRange() throws JSONException {
	String endpoint="http://localhost:9999/api/projects/date-range/2022-02-01/2022-03-01";
	String projects = """
	[
    {
        "projectID": 2,
        "projectName": "Project Two",
        "description": "Description for Project Two",
        "startDate": "2022-02-01",
        "endDate": "2022-03-01",
        "user": {
            "userID": 2,
            "username": "jane_smith",
            "password": "pass456",
            "email": "jane.smith@email.com",
            "fullName": "Jane Smith"
        }
    },
    {
        "projectID": 3,
        "projectName": "Project Three",
        "description": "Description for Project Three",
        "startDate": "2022-03-01",
        "endDate": "2022-04-01",
        "user": {
            "userID": 3,
            "username": "alex_jones",
            "password": "secret789",
            "email": "alex.jones@email.com",
            "fullName": "Alex Jones"
        }
    },
    {
        "projectID": 4,
        "projectName": "New Product Launch",
        "description": "Launching our latest product",
        "startDate": "2022-02-15",
        "endDate": "2022-04-30",
        "user": {
            "userID": 4,
            "username": "emily_jackson",
            "password": "my_pass",
            "email": "emily.jackson@email.com",
            "fullName": "Emily Jackson"
        }
    },
    {
        "projectID": 7,
        "projectName": "Mobile App Development",
        "description": "Building a mobile app for iOS and Android",
        "startDate": "2022-03-01",
        "endDate": "2022-06-30",
        "user": {
            "userID": 7,
            "username": "robert_clark",
            "password": "robert123",
            "email": "robert.clark@email.com",
            "fullName": "Robert Clark"
        }
    }
]
			""";	
	ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is2xxSuccessful());
	JSONAssert.assertEquals(projects,response.getBody(),true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
} 
	@Test
	public void getProjectsByUserRole() throws JSONException {
	String endpoint="http://localhost:9999/api/projects/user-role/manager";
	String projects = """
		[
    {
        "projectID": 4,
        "projectName": "New Product Launch",
        "description": "Launching our latest product",
        "startDate": "2022-02-15",
        "endDate": "2022-04-30",
        "user": {
            "userID": 4,
            "username": "emily_jackson",
            "password": "my_pass",
            "email": "emily.jackson@email.com",
            "fullName": "Emily Jackson"
        }
    }
]
			""";
	ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is2xxSuccessful());
	JSONAssert.assertEquals(projects,response.getBody(),true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
}
	@Test
	public void getProjectsByStatus() throws JSONException {
	String endpoint="http://localhost:9999/api/projects/status/Completed";
	String projects = """
			[
    {
        "projectID": 2,
        "projectName": "Project Two",
        "description": "Description for Project Two",
        "startDate": "2022-02-01",
        "endDate": "2022-03-01",
        "user": {
            "userID": 2,
            "username": "jane_smith",
            "password": "pass456",
            "email": "jane.smith@email.com",
            "fullName": "Jane Smith"
        }
    }
]
			""";
	ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is2xxSuccessful());
	JSONAssert.assertEquals(projects,response.getBody(),true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
} 
	@Test
	public void getProjectsWithHighPriorityTasks() throws JSONException {
	String endpoint="http://localhost:9999/api/projects/high-priority-tasks";
	String projects = """
			[
    {
        "projectID": 1,
        "projectName": "Project One",
        "description": "Description for Project One",
        "startDate": "2022-01-01",
        "endDate": "2022-02-01",
        "user": {
            "userID": 1,
            "username": "john_doe",
            "password": "password123",
            "email": "john.doe@email.com",
            "fullName": "John Doe"
        }
    },
    {
        "projectID": 4,
        "projectName": "New Product Launch",
        "description": "Launching our latest product",
        "startDate": "2022-02-15",
        "endDate": "2022-04-30",
        "user": {
            "userID": 4,
            "username": "emily_jackson",
            "password": "my_pass",
            "email": "emily.jackson@email.com",
            "fullName": "Emily Jackson"
        }
    },
    {
        "projectID": 6,
        "projectName": "Marketing Campaign",
        "description": "Launching a new marketing campaign",
        "startDate": "2022-04-01",
        "endDate": "2022-05-15",
        "user": {
            "userID": 6,
            "username": "sarah_miller",
            "password": "sarahpass",
            "email": "sarah.miller@email.com",
            "fullName": "Sarah Miller"
        }
    },
    {
        "projectID": 7,
        "projectName": "Mobile App Development",
        "description": "Building a mobile app for iOS and Android",
        "startDate": "2022-03-01",
        "endDate": "2022-06-30",
        "user": {
            "userID": 7,
            "username": "robert_clark",
            "password": "robert123",
            "email": "robert.clark@email.com",
            "fullName": "Robert Clark"
        }
    }
]
			""";
	ResponseEntity<String>response = template.getForEntity(endpoint,String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is2xxSuccessful());
	JSONAssert.assertEquals(projects,response.getBody(),true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
}
	@Test
	public void deleteProject() throws JSONException
	{
		String endpoint = "http://localhost:9999/api/projects/delete/15";
		String userrole = """
				{
				    "code": "DLTFAILS",
				    "message": "Project doesn't exist exist"
				}
				""";
		ResponseEntity<String> response = template.exchange(endpoint,HttpMethod.DELETE,null,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(userrole,response.getBody(), true);
	}	


}
