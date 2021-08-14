package com.ferreira.clientcrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.ferreira.clientcrud.dto.ClientDTO;
import com.ferreira.clientcrud.entities.Client;
import com.ferreira.clientcrud.repositories.ClientRepository;
import com.ferreira.clientcrud.services.exceptions.ResourceNotFoundException;

@Repository
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(PageRequest pageRequest) {
		
		Page<Client> entityList = repository.findAll(pageRequest);

		return entityList.map(entity -> new ClientDTO(entity));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(@RequestParam Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(entity);
	}
}
