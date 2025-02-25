package com.gestionR.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@GetMapping("/error")
	public ModelAndView handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Map<String, Object> M = new HashMap<String, Object>();
		M.put("status", status);
    	System.out.println("Error with status code " + status + " happened. Support! Do something about it!");
	    return new ModelAndView("error",M);
	}
}