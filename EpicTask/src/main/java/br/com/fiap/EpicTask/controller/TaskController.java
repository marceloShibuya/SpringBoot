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

import br.com.fiap.EpicTask.model.Task;
import br.com.fiap.EpicTask.repository.TaskRepository;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskRepository repository;
	
	@GetMapping()  
	public ModelAndView tasks() {
		List<Task> tasks = repository.findAll();
		ModelAndView modelAndView = new ModelAndView("tasks"); // o tasks refere-se ao View tasks.html
		// Abaixo o "tasks" é objeto que vai ser enviado para a View, e o segundo parâmetro users refere-se a lista
		modelAndView.addObject("tasks", tasks); 
		return modelAndView;
	}

	@RequestMapping("/new")
	public String formTasks(Task task) {
		return "task_new";
	}
	
	@PostMapping()  
	public String save(@Valid Task task, BindingResult result, RedirectAttributes attribute) {
		if (result.hasErrors()) return "task_new";
		repository.save(task);
		attribute.addFlashAttribute("message", "tarefa cadastrado com sucesso");
		return "redirect:task";
	}	
	
	@RequestMapping("delete/{id}")
	public String deleteTask(@PathVariable int id, RedirectAttributes attributes) {
		repository.deleteById(id);
		attributes.addFlashAttribute("message", "tarefa apagado com sucesso");
		return "redirect:/task";
	}
	
	@GetMapping("{id}")
	public ModelAndView editForm(@PathVariable int id) {
		Optional<Task> task = repository.findById(id);
		ModelAndView modelAndView = new ModelAndView("task_edit");
		modelAndView.addObject("task", task);
		return modelAndView;
	}
	
	@PostMapping("update")
	public String updateTask(@Valid Task task, BindingResult result, RedirectAttributes attributes){
		if (result.hasErrors()) return "task_edit";
		repository.save(task);
		attributes.addFlashAttribute("message", "tarefa atualizado!");
		return "redirect:/task";
	}
	
	
	
}