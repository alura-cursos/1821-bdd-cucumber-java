package br.com.alura.leilao.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.repositories.UsuarioRepository;


//@Controller
//@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	
	@GetMapping()
	public List<Usuario> index() {
		return repository.findAll();
	}
	
	@PostMapping()
	public String create(Usuario usuario, BindingResult result) {
		
		if (usuario.getNome().isEmpty()) {
		    result.addError(new FieldError("usuario", "nome", "Nome obrigatorio!"));
		}
		if (usuario.getEmail().isEmpty()) {
		    result.addError(new FieldError("usuario", "email", "Email obrigatorio!"));
		}
		
		if(result.hasErrors()) {
			return "/usuarios/new";
		}
		
		repository.save(usuario);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/new")
	public ModelAndView newUsuario() {
		ModelAndView mv = new ModelAndView("/newUsuarios");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@PutMapping()
	public String update(Usuario usuario, BindingResult result) {
		
		if (usuario.getNome().isEmpty()) {
		    result.addError(new FieldError("usuario", "nome", "Nome obrigatorio!"));
		}
		if (usuario.getEmail().isEmpty()) {
		    result.addError(new FieldError("usuario", "email", "Email obrigatorio!"));
		}
		
		if(result.hasErrors()) {
			return "/usuarios/edit";
		}
		
		repository.save(usuario);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathParam("id") Long usuarioId) {
		ModelAndView mv = new ModelAndView("edit");
		mv.addObject("usuario", repository.getOne(usuarioId));
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView show(@PathParam("id") Long usuarioId) {
		ModelAndView mv = new ModelAndView("show");
		mv.addObject("usuario", repository.getOne(usuarioId));
		return mv;
	}

	@DeleteMapping("/{id}")
	public String destroy(@PathParam("id") Long usuarioId) {
		repository.delete(repository.getOne(usuarioId));
		return "redirect:/leileos";
	}
}