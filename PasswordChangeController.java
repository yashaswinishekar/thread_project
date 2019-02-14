package com.xworkz.interviewApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.interviewApp.dto.PasswordChangeDTO;
import com.xworkz.interviewApp.service.PasswordChangeService;

@Controller
@RequestMapping("/")
public class PasswordChangeController {
	private static final Logger slf4jLogger=LoggerFactory.getLogger(PasswordChangeController.class);

	@Autowired
	private PasswordChangeService service;

	public PasswordChangeController() {
		slf4jLogger.info(this.getClass().getSimpleName());
	}

	@RequestMapping(value = "/passwordChange.do", method = RequestMethod.POST)
	public ModelAndView passwordChangeController(PasswordChangeDTO dto) {
		ModelAndView modelAndView = new ModelAndView();
		String dtoFromService = service.passwordChangeService(dto);
		if (dtoFromService != null) {
			modelAndView.setViewName("/login.jsp");
			modelAndView.addObject("pdto", "Login Successful");
		} else {
			modelAndView.setViewName("/passwordChange.jsp");
			modelAndView.addObject("pdto", "Your password doesnot match ");
		}
		return modelAndView;
	}
}
