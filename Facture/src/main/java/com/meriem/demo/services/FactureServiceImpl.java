package com.meriem.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.meriem.demo.entities.Client;
import com.meriem.demo.entities.Facture;
import com.meriem.demo.repos.ClientRepository;
import com.meriem.demo.repos.FactureRepository;
@Service
public class FactureServiceImpl implements FactureService{
	@Autowired
	FactureRepository factureRepository;
	@Autowired
	ClientRepository clientRepository;

	@Override
	public Facture saveFacture(Facture p) {
		
		return factureRepository.save(p);
	}

	@Override
	public Facture updateFacture(Facture p) {
		
		return factureRepository.save(p);
	}

	@Override
	public void deleteFacture(Facture p) {
		factureRepository.delete(p);
		
	}

	@Override
	public void deleteFactureById(Long id) {
		factureRepository.deleteById(id);
		
	}

	@Override
	public Facture getFacture(Long id) {
		
		return factureRepository.findById(id).get();
	}

	@Override
	public List<Facture> getAllFactures() {
		
		return factureRepository.findAll();
	}

	@Override
	public Page<Facture> getAllFacturesParPage(int page, int size) {
		
		return factureRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public List<Facture> findByClient(Client client) {
		
		return factureRepository.findByClient(client);
	}

	@Override
	public List<Facture> findByClientIdClient(Long id) {
		
		return factureRepository.findByClientIdClient(id);
	}

	@Override
	public List<Client> getAllClients() {
		
		return clientRepository.findAll();
	}

}
