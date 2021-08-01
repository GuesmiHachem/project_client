package com.devops.tools.engineer.appClientSales.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.tools.engineer.appClientSales.dao.Client;
import com.devops.tools.engineer.appClientSales.exceptions.ClientNotFoundException;
import com.devops.tools.engineer.appClientSales.repositories.ClientRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ClientController {

	@Autowired
	ClientRepository clientRepo;

	@PostMapping("/clients")
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		return ResponseEntity.ok().body(clientRepo.save(client));

	}

	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> getClient(@PathVariable(value = "id") Long id) throws ClientNotFoundException {

		Client client = clientRepo.findById(id)
				.orElseThrow(() -> new ClientNotFoundException("Employee not found for this id :: " + id));
		return ResponseEntity.ok().body(client);

	}

	@GetMapping("/clients")
	public List<Client> getClientsList() {
		return clientRepo.findAll();

	}

	@PutMapping("clients/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable(value = "id") Long id, @RequestBody Client clientBody)
			throws ClientNotFoundException {
		Client client = clientRepo.findById(id)
				.orElseThrow(() -> new ClientNotFoundException("Employee not found for this id :: " + id));
		client.setFirstName(clientBody.getFirstName());
		client.setLastName(clientBody.getLastName());
		client.setMail(clientBody.getMail());
		return ResponseEntity.ok().body(clientRepo.save(client));

	}

	@DeleteMapping("clients/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable(value = "id") Long id) throws ClientNotFoundException {
		Client client = clientRepo.findById(id)
				.orElseThrow(() -> new ClientNotFoundException("Employee not found for this id :: " + id));
		clientRepo.delete(client);
		return ResponseEntity.ok().body("Client with id :" + "" + id + " is deleted");

	}

}
