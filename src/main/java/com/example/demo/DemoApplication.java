
package com.example.demo;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
@RestController
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	// HTTP GET /hello?name=Welt&anderen=Nichts
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	// Neues GET-Mapping zur Auflistung aller Entities in dieser Collection
	@GetMapping("/characters")
	public String getAllCharacters() {

		// Platzhalter 1
		MyCustomCharacter myFirstCharacter = new MyCustomCharacter();
		myFirstCharacter.setId(1);
		myFirstCharacter.setFirstName("Peter");
		myFirstCharacter.setLastName("Schnee");

		// Platzhalter 2
		MyCustomCharacter mySecondCharacter = new MyCustomCharacter();
		mySecondCharacter.setId(2);
		mySecondCharacter.setFirstName("Peter");
		mySecondCharacter.setLastName("Schnee");

		// Liste mit den beiden Objekten oben
		ArrayList<MyCustomCharacter> list = new ArrayList<>();
		list.add(myFirstCharacter);
		list.add(mySecondCharacter);

		// Serialisierung der ganzen Liste in JSON
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	// Detail-API
	// #1 Neues GET-Mapping zum Aufruf mit /characters/45 bzw. /characters/:characterId
	@GetMapping("/characters/{id}")
	public String getCharacter(@PathVariable(value="id") int characterId) {
		// #2 Mock character mit characterId
		// Klasse erstellen und Objekt instanziieren
		MyCustomCharacter myCharacter = new MyCustomCharacter();
		myCharacter.setId(characterId);
		myCharacter.setFirstName("Peter");
		myCharacter.setLastName("Schnee");
		// ^-- das ist im nächsten Schritt durch eine echte Datenbank-Anbindung zu ersetzen

		// #3 Serialisierung als JSON
		Gson gson = new Gson();
		return gson.toJson(myCharacter);
	}
}
            