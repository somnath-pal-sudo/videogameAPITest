package TCAll;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class TC001_Get_VideoGameAPI {
	
	
	  @Test(priority=1) public void TC_VideogameApi() { given()
	  .get("http://localhost:8080/app/videogames") .then() .statusCode(200);
	  
	  }
	  
	  @Test(priority=2) public void TC002_Post_VideoGameAPI() {
	  
	  HashMap map= new HashMap(); 
	  map.put("id", 100); 
	  map.put("name","Spider-Man2"); 
	  map.put("releaseDate","2021-02-03T11:35:29.672Z");
	  map.put("reviewScore", 2);
	  map.put("category", "adventure");
	  map.put("rating", "universal");
	  
	  Response response=
			  given() 
			  .contentType("application/json") 
			  .body(map) 
			  .when()
			  .post("http://localhost:8080/app/videogames")
			  .then() 
			  .statusCode(200) 
			  .log()
			  .all()
			  .extract()
			  .response();
			  
	  String JSONresponse=response.asString();
	  Assert.assertEquals(JSONresponse.contains( "Record Added Successfully"),
	  true);
	  
	  }
	  
	  @Test(priority=3) public void TC003_getVideogame() { given() .when()
	  .get("http://localhost:8080/app/videogames/10") .then() .statusCode(200)
	  .body("videogame.id",equalTo("10"))
	  .body("videoGame.name",equalTo("Grand Theft Auto III"));
	  
	  }
	 
	
	
	  @Test(priority=4) public void TC004_updateVideogame() {
	  
	  HashMap map= new HashMap(); map.put("id", 4); map.put("name",
	  "breakingbad-1"); map.put("releaseDate","2021-02-04T11:35:29.672R");
	  map.put("reviewScore", 4); map.put("category", "Romance"); map.put("rating",
	  "universal-1");
	  
	  given() .contentType("application/json") .body(map) .when()
	  .put("http://localhost:8080/app/videogames/4") .then() .statusCode(200)
	  .log().body() .body("videogame.id",equalTo("10"))
	  .body("videoGame.name",equalTo("breakingbad-1"));
	  
	  }
	 
	@Test(priority=5)
	public void TC005_deleteRequestvideogame() throws InterruptedException {
		
		
		Response response=
		given()
		.when()
		.delete("http://localhost:8080/app/videogames/1")
		.then()
		.log().all()
		.extract().response();
		Thread.sleep(3000);
		String jsonString=response.asString();
		Assert.assertEquals(jsonString.contains("Record Deleted Successfully"),true);
		
	}

}
