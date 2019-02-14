package com.xworkz.interviewApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.interviewApp.dto.CandidateDTO;
import com.xworkz.interviewApp.dto.LoginDTO;
import com.xworkz.interviewApp.service.LoginService;


@Controller
@RequestMapping("/")
public class LoginSuccessController {
	private static final Logger slf4jLogger=LoggerFactory.getLogger(LoginSuccessController.class);

	@Autowired
	private LoginService service;

	public LoginSuccessController() {
		slf4jLogger.info(this.getClass().getSimpleName());
	}
	@RequestMapping(value = "/log.do", method = RequestMethod.POST)
	public ModelAndView loginController(LoginDTO dto) {

		ModelAndView modelAndView = new ModelAndView();
		CandidateDTO dtoFromService = service.loginSuccessService(dto);
		if (dtoFromService != null) {
			if (dtoFromService.getNewUser()) {
				boolean resultFromService = service.updateService(dtoFromService);
				modelAndView.setViewName("/passwordChange.jsp");
				modelAndView.addObject("cdto", resultFromService);
			} else {
				modelAndView.setViewName("/loginSuccess.jsp");
				modelAndView.addObject("ldto", "login successful");
			}
		} else {
			modelAndView.setViewName("/login.jsp");
			modelAndView.addObject("failure", "username and password doesnot match");
		}
		return modelAndView;
	}
	
}
