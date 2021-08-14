package com.ferreira.clientcrud.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.ferreira.clientcrud.dto.ClientDTO;
import com.ferreira.clientcrud.entities.Client;
import com.ferreira.clientcrud.repositories.ClientRepository;

@Repository
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(PageRequest pageRequest) {
		
		Page<Client> entityList = repository.findAll(pageRequest);

		return entityList.map(entity -> new ClientDTO(entity));
	}
}
