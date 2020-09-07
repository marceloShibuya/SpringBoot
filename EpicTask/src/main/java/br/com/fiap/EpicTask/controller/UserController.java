package br.com.fiap.EpicTask.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.EpicTask.model.User;
import br.com.fiap.EpicTask.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository repository;

	@GetMapping()  
	public ModelAndView users() {
		List<User> users = repository.findAll();
		ModelAndView modelAndView = new ModelAndView("users"); // o users refere-se ao View users.html
		// Abaixo o "users" é objeto que vai ser enviado para a View, e o segundo parâmetro users refere-se a lista
		modelAndView.addObject("users", users); 
		return modelAndView;
	}
	
	@RequestMapping("/new")  
	public String formUser(User user) {
		return "user_new";
	}
	
	@PostMapping()  
	public String save(@Valid User user, BindingResult result, RedirectAttributes attribute) {
		if (result.hasErrors()) return "user_new";
		repository.save(user);
		attribute.addFlashAttribute("message", "usuário cadastrado com sucesso");
		return "redirect:user";
	}	
	
	@RequestMapping("delete/{id}")
	public String deleteUser(@PathVariable Long id, RedirectAttributes attributes) {
		repository.deleteById(id);
		attributes.addFlashAttribute("message", "usuário apagado com sucesso");
		return "redirect:/user";
	}
	
	@GetMapping("{id}")
	public ModelAndView editForm(@PathVariable Long id) {
		Optional<User> user = repository.findById(id);
		ModelAndView modelAndView = new ModelAndView("user_edit");
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	@PostMapping("update")
	public String updateUser(@Valid User user, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) return "user_edit";
		repository.save(user);
		attributes.addFlashAttribute("message", "usuário atualizado!");
		return "redirect:/user";
	}
	

}