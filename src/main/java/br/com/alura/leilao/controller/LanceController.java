package br.com.alura.leilao.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.mudi.dto.NovoLanceDto;
import br.com.alura.leilao.service.LanceService;

@Controller
@RequestMapping(value = "/lances")
public class LanceController {

	
	@Autowired
	private LanceService service;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView novoLance(@Valid @ModelAttribute("lance") NovoLanceDto lanceDto, Errors erros, Principal principal, RedirectAttributes redirectAttributes) {

		Leilao leilao = service.getLeilao(lanceDto.getLeilaoId());

		if(erros.hasErrors()) {
			ModelAndView mv = new ModelAndView("/leilao/show");
			mv.addObject("lance", lanceDto);
			mv.addObject("leilao", leilao);
			return mv;
		}
		
		if(service.propoeLance(lanceDto, principal.getName())) {
			redirectAttributes.addFlashAttribute("message", "Lance adicionado com sucesso!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Lance invalido!");
		}
		
		String redirectURL = "redirect:/leiloes/" + lanceDto.getLeilaoId();
		return new ModelAndView(redirectURL);
	}

	
}
