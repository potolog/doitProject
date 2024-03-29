package com.kakaolove.doit.controller;

import java.security.Principal;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.kakaolove.doit.service.userProfileService;
import com.kakaolove.doit.vo.userProfileVO;

@Controller
public class ProfileController {
	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	
	@Inject
	private userProfileService profileservice; 
	
    @RequestMapping(value = "/myprofile", method = RequestMethod.GET)
	public String myprofile(Principal principal,Locale locale,Model model) {    		
    		Authentication authentication=SecurityContextHolder.getContext().getAuthentication(); // context 인증정보 받기
    		userProfileVO userprofilevo=new userProfileVO(); 
    		if(authentication.getName()=="anonymousUser") { // 로그인되어있지않은(허가 되지않은) 사용자
    			return "redirect:/login";
    		}
    		
    		try{
    			userprofilevo=(userProfileVO)profileservice.getuserProfile(authentication.getName());
    		}catch(Exception a){
    			return "redirect:/";
    		}
    		model.addAttribute("profile",userprofilevo);
    		
    	return "myprofile"; // login.jsp(Custom Login Page)
	}  
    
    @RequestMapping(value = "/myprofile", method = RequestMethod.POST)
	public String modifyProfile(userProfileVO userprofilevo,Principal principal,Locale locale,Model model) {    		
    		Authentication authentication=SecurityContextHolder.getContext().getAuthentication(); // context 인증정보 받기
    		userprofilevo.setId(authentication.getName());
    		userprofilevo.setFiled("Spring_Java");
    		
    		try{
    			profileservice.modifyuserProfile(userprofilevo); 
    			return "redirect:/";
    			
    		}catch(Exception a){
    			a.printStackTrace();
    			return "redirect:/";
    		}
    		
	}  
	
}
