package com.meriem.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.meriem.demo.entities.Client;
import com.meriem.demo.entities.Facture;
import com.meriem.demo.services.ClientService;
import com.meriem.demo.services.FactureService;

@Controller
public class FactureController {
	@Autowired
	FactureService factureService;
	@Autowired
	ClientService clientService;
	
	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {
		List<Client> clients=clientService.getAllClients();
	    
		modelMap.addAttribute("facture", new Facture());
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("clients", clients);
		return "formFacture";
	}
	
	@RequestMapping("/saveFacture")
	public String saveFacture(@Valid Facture facture,
			 BindingResult bindingResult) 
	{
		if (bindingResult.hasErrors()) return "formFacture";
	 factureService.saveFacture(facture);
	return "formFacture";
	}
@RequestMapping("/ListeFactures")
public String listeFactures(ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
		@RequestParam (name="size", defaultValue = "3") int size)
{
	Page<Facture> fact = factureService.getAllFacturesParPage(page, size);
	modelMap.addAttribute("factures", fact);
	 modelMap.addAttribute("pages", new int[fact.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "listeFactures";

}
@RequestMapping("/supprimerFacture")
public String supprimerFacture(@RequestParam("id") Long id,
 ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
 @RequestParam (name="size", defaultValue = "2") int size)
{ 
factureService.deleteFactureById(id);
Page<Facture> fact = factureService.getAllFacturesParPage(page, size);
		modelMap.addAttribute("factures", fact);
		modelMap.addAttribute("pages", new int[fact.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);

return "listeFactures";
}
@RequestMapping("/modifierFacture")
public String editerFacture(@RequestParam("id") Long id,ModelMap modelMap)
{
Facture f= factureService.getFacture(id);
List<Client> clients=clientService.getAllClients();
modelMap.addAttribute("facture", f);
modelMap.addAttribute("mode", "edit");
modelMap.addAttribute("clients", clients);
return "formFacture";
}
@RequestMapping("/updateFacture")
public String updateFacture(@ModelAttribute("facture") Facture facture,
@RequestParam("date") String date,ModelMap modelMap) throws ParseException 
{
	 
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 Date dateEmission = dateformat.parse(String.valueOf(date));
	 facture.setDateEmission(dateEmission);
	 
	factureService.updateFacture(facture);
	 List<Facture> fact = factureService.getAllFactures();
	 modelMap.addAttribute("factures", fact);
	return "listeFactures";
	}
}
