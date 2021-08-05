package ie.xie.controllers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ie.xie.entities.Director;
import ie.xie.entities.Film;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class Controllers {
	
@SuppressWarnings("serial")
 HttpHeaders createHeaders(String username, String password)
 { 
   /*
   * A HttpHeader is a data structure representing a HTTP request or response header. 
   * It maps String header names to a list of String values (Map).
   */
  return new HttpHeaders() {
   {
    // create a string from the given username and password
    String auth = username + ":" + password;
    // convert the string to a byte array
    byte[] encodeStringIntoBytes = auth.getBytes(StandardCharsets.UTF_8);
    // encode the binary data to ASCII text.
    byte[] encodedAuth = Base64.encodeBase64(encodeStringIntoBytes);
    // convert the encoded byte array to a string with "Basic" at the start
    String authHeader = "Basic " + new String( encodedAuth );
    // set --> sets the value of "Authorization" to authHeader
    set(HttpHeaders.AUTHORIZATION, authHeader);
    // Thus the authorisation data can be sent to the REST API for authorisation/authentication 
   }};
 }

 private String USERNAME = "api@cit.ie";
 private String PASSWORD = "password";
 private HttpHeaders headers = createHeaders(USERNAME, PASSWORD);

 
 @GetMapping("/showdirectors")
 public String authenticateDirectors(Model model)
 { 
  try {
   RestTemplate restTemplate = new RestTemplate();
   String URL = "http://localhost:8086/myapi/directors";
 
   ResponseEntity<List<Director>> responseEntity = restTemplate.exchange
     (
       URL, 
       HttpMethod.GET, 
       new HttpEntity<>(this.headers),   // the headers are sent along with the request
       new ParameterizedTypeReference<List<Director>>(){}
       );
   List<Director> directors = responseEntity.getBody();
   model.addAttribute("directors", directors);
   return "directors";
  } catch(Exception e) {
   return "error";
  }
 }

 @GetMapping("/showfilms")
 public String authenticateFilms(Model model)
 {
	  try {
		   RestTemplate restTemplate = new RestTemplate();
		   String URL = "http://localhost:8086/myapi/films";
		 
		   ResponseEntity<List<Film>> responseEntity = restTemplate.exchange
		     (
		       URL, 
		       HttpMethod.GET, 
		       new HttpEntity<>(this.headers),   // the headers are sent along with the request
		       new ParameterizedTypeReference<List<Film>>(){}
		       );
		   List<Film> films = responseEntity.getBody();
		   model.addAttribute("films", films);
		   return "films";
		  } catch(Exception e) {
		   return "error";
		  } 
 }
 
 @GetMapping("/showfilms/{releaseDate}")
 public String authenticateFilmsByReleaseDate(@PathVariable(name="releaseDate") int releaseDate, Model model)
 {
	 try {
		 RestTemplate restTemplate = new RestTemplate();
		 String URL = "http://localhost:8086/myapi/film/releaseYear/{releaseDate}";
		 @SuppressWarnings("unused")
		 ResponseEntity<List<Film>> responseEntity = restTemplate.exchange
		 (
				 URL, 
				 HttpMethod.GET, 
				 new HttpEntity<>(this.headers),
				 new ParameterizedTypeReference<List<Film>>() {},
				 new Object[] {releaseDate});
		 //return "redirect:/showfilms/"+releaseDate;
		 List<Film> films = responseEntity.getBody();
		 model.addAttribute("films", films);
		 return "films";
	 }
	 catch (Exception ex)
	 {
		 return "error";
	 }
 }

 
	@GetMapping("/deletedirector/{id}")
	public String deleteDirector(@PathVariable(name="id") int id, Model model) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String URL = "http://localhost:8086/myapi/director/{id}";
			@SuppressWarnings("unused")
			ResponseEntity<Director> responseEntity = restTemplate.exchange
					(
							URL, 
							HttpMethod.DELETE, 
							new HttpEntity<>(this.headers), 
							new ParameterizedTypeReference<Director>() {}, 
							new Object[] {id}
							);
			return "redirect:/showdirectors";
		}
		catch (Exception ex)
		{
			return "error";
		}
	}
}
