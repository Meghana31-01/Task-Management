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

public class CategoryTest 
{
	@Autowired
	private TestRestTemplate template;
	
	@Test
	public void retrieveAllCategories() throws JSONException
	{
	String endpoint="http://localhost:9999/api/categories/all";
	String categories="""
 [
    {
        "categoryID": 1,
        "categoryName": "Development"
    },
    {
        "categoryID": 2,
        "categoryName": "Design"
    },
    {
        "categoryID": 3,
        "categoryName": "Marketing"
    },
    {
        "categoryID": 4,
        "categoryName": "Testing"
    },
    {
        "categoryID": 5,
        "categoryName": "Documentation"
    },
    {
        "categoryID": 6,
        "categoryName": "Marketing"
    },
    {
        "categoryID": 7,
        "categoryName": "Development"
    },
    {
        "categoryID": 8,
        "categoryName": "Customer Support"
    },
    {
        "categoryID": 9,
        "categoryName": "Training"
    },
    {
        "categoryID": 10,
        "categoryName": "Documentation"
    }
]
			""";
	
	ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is2xxSuccessful());
	JSONAssert.assertEquals(categories,response.getBody(), true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
	
  }
	
	
	@Test
	public void retrieveCategoriesById() throws JSONException
	{
	String endpoint="http://localhost:9999/api/categories/thira";
	String categories="""
			
					{
						"categoryID": 7
					}
				""";
	int categoryId=-1;
	try {
		categoryId = Validation.extractId(endpoint);
	} catch (Exception e) {
		e.printStackTrace();
	}
	assertFalse(categoryId >= 0, "Expected categoryId to be a positive integer");	
//	ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
//	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
//	assertTrue(response.getStatusCode().is2xxSuccessful());
//	JSONAssert.assertEquals(categories,response.getBody(), false);
//	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
	
  }
	
	@Test
	public void retrieveTaskCountWithCategories() throws JSONException
	{
	String endpoint="http://localhost:9999/api/categories/task-count";
	String categoriestasks="""
			[
    {
        "category": {
            "categoryID": 1,
            "categoryName": "Development"
        },
        "taskCount": 0
    },
    {
        "category": {
            "categoryID": 2,
            "categoryName": "Design"
        },
        "taskCount": 4
    },
    {
        "category": {
            "categoryID": 3,
            "categoryName": "Marketing"
        },
        "taskCount": 1
    },
    {
        "category": {
            "categoryID": 4,
            "categoryName": "Testing"
        },
        "taskCount": 1
    },
    {
        "category": {
            "categoryID": 5,
            "categoryName": "Documentation"
        },
        "taskCount": 1
    },
    {
        "category": {
            "categoryID": 6,
            "categoryName": "Marketing"
        },
        "taskCount": 1
    },
    {
        "category": {
            "categoryID": 7,
            "categoryName": "Development"
        },
        "taskCount": 0
    },
    {
        "category": {
            "categoryID": 8,
            "categoryName": "Customer Support"
        },
        "taskCount": 1
    },
    {
        "category": {
            "categoryID": 9,
            "categoryName": "Training"
        },
        "taskCount": 0
    },
    {
        "category": {
            "categoryID": 10,
            "categoryName": "Documentation"
        },
        "taskCount": 1
    }
]
			""";
	
	ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
	assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
	assertTrue(response.getStatusCode().is2xxSuccessful());
	JSONAssert.assertEquals(categoriestasks,response.getBody(), true);
	assertEquals("keep-alive",response.getHeaders().get("Connection").get(0));
	
  }
	
	@Test
	public void deleteCategoryById() throws JSONException
	{
		String endpoint = "http://localhost:9999/api/categories/78";
		String categories = """
				{
    "code": "DLTFAILS",
    "message": "Category doesn't exist exist"
}
				""";
		
		
		ResponseEntity<String> response = template.exchange(endpoint,HttpMethod.DELETE,null,String.class);
		assertEquals("application/json",response.getHeaders().get("Content-Type").get(0));
		assertTrue(response.getStatusCode().is4xxClientError());
		JSONAssert.assertEquals(categories,response.getBody(), true);
	}
	
}
