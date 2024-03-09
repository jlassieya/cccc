package CRUD2.Controllers;

import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import CRUD2.model.annonceDto;
import CRUD2.model.annonce;
import CRUD2.services.annonceRepository;
import jakarta.validation.Valid;
@Controller
@RequestMapping("/annonce")
public class annonceContoller {
	

@Autowired
private annonceRepository repo;
@GetMapping({"","/"})
public String showProductList(Model model) {
	List<annonce>annonce=repo.findAll(Sort.by(Sort.Direction.DESC,"id"));
	model.addAttribute("annonce",annonce);
	return"annonce/index";
}
@GetMapping("/create")
public String showCreatePage(Model model) {
annonceDto annonceDto =new annonceDto();
model.addAttribute("annonceDto",annonceDto);
return"annonce/Createannonce";
}
//@PostMapping("/create")
//public String create annonce(
	//	@Valid @ModelAttribute annonceDto annonceDto,BindingResult result) {
	//if (annonceDto.getDescription().isEmpty()){
		   //result.addError(new FieldError("annonceDto","description","The description is required "));}
	//if(result.hasErrors()) {
		//return"annonce/Createannonce";
	
}
@GetMapping ("/edit") 
 public String showEditPage(
		Model model,
		 @RequestParam int id
		) {
	try {
		annonce annonce=repo.findById(id).get( );
		model.addAttribute("annonce",annonce);
	
	
	annonceDto annonceDto=new annonceDto();
	annonceDto.setDescription(annonce.getDescription());
	annonceDto.setAdresse_depart(annonce.getAdresse_depart());
	annonceDto.setAdresse_destination(annonce.getAdresse_destination());
	annonceDto.setDatelimite(annonce.getDatelimite());
model.addAttribute("annonceDto",annonceDto);
}
		catch(Exception ex) {
			System.out.println("Exception:"+ex.getMessage());
			return "redirect:/annonce";
		
		
		}
	
	return"products/EditProduct";
	   }
@PostMapping("/edit")
public String updateannonce(
		Model model,
		@RequestParam int id,
		@Valid @ModelAttribute annonceDto annonceDto,BindingResult result) {
	try {
		annonce annonce=repo.findById(id).get();
		model.addAttribute("annonce",annonce);
	if (result.hasErrors()) {
		return"annonce/Editannonce";
	}
	catch (Exception ex) {
		System.out.println("Exception:"+ex.getMessage());
		
	}@GetMapping("/delete")
	public String deleteannonce(
			@RequestParam int id) {
		try {
			annonce annonce=repo.findById(id).get();
		}
		repo.delete(annonce);
	}
	catch (Exception ex) {
		System.out.println("Exception:"+ex.getMessage());
	
	}
	return "redirect:/annonce";

	