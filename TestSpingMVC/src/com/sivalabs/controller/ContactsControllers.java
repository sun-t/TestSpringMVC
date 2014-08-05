package com.sivalabs.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.sivalabs.dao.ContactsDAO;
import com.sivalabs.entity.Contact;
import com.sivalabs.utils.ContactFormValidator;

@Controller
@SessionAttributes("login")
public class ContactsControllers
{
	private Logger logger = LoggerFactory.getLogger(ContactsControllers.class);
	
    @Autowired
    private ContactsDAO contactsDAO;
    
    @Autowired
    private ContactFormValidator validator;
        
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
        
//    @ModelAttribute("logUser")
//    public Contact getContact(){
//    	Contact logUser = new Contact();
//    	return logUser;
//    }
    
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public ModelAndView loginForm()
    {
        ModelAndView mav = new ModelAndView("login");
        Contact contact = new Contact();
        mav.addObject("login", contact);
        return mav;
    }
    
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(@RequestParam(required= true, defaultValue="") String name,@RequestParam(required= true, defaultValue="")String password,@ModelAttribute("login")Contact contact, BindingResult result, SessionStatus status){
    	validator.loginValidate(contact, result);
    	if(result.hasErrors()){
    		return "login";
    	}else{
    		if(contactsDAO.checkName(name)==0){
        		result.rejectValue("name", "filed.name", "Name Does not exist.");
        		return "login";
        	}else{
        		boolean flag = contactsDAO.loginContacts(name, password);
        		if(flag){
        			logger.info("login sucesss!");
        			return "redirect:viewAllContacts.do";
        		}else{
        			result.rejectValue("password", "filed.password","Password is Error!");	
        			return "login";
        		}
        	}
    	}
    }
    
    @RequestMapping("/loginContacts")
    public String loginContacts(@RequestParam(required= true, defaultValue="") String name,@RequestParam(required= true, defaultValue="")String password,@ModelAttribute("logUser")Contact logUser,BindingResult result){
    	boolean flag = contactsDAO.loginContacts(name, password);
    	//validator.loginValidate(logUser, result);
    	if(flag){
    		logUser.setName(name);
    		logger.info("login sucesss!");
    		return "redirect:viewAllContacts.do";
    	}else{
    		logUser.setName(null);
    		logger.info("login excess or exit");
    		return "loginContacts";
    	}
    }
    
    @RequestMapping("/searchContacts")
    public ModelAndView searchContacts(@RequestParam(required= false, defaultValue="") String name)
    {
        ModelAndView mav = new ModelAndView("showContacts");
        List<Contact> contacts = contactsDAO.searchContacts(name.trim());
        mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", contacts);
        return mav;
    }
    
    @RequestMapping("/viewAllContacts")
    public ModelAndView getAllContacts()
    {
    	logger.info("excute search...");
        ModelAndView mav = new ModelAndView("showContacts");
        List<Contact> contacts = contactsDAO.getAllContacts();
        mav.addObject("SEARCH_CONTACTS_RESULTS_KEY", contacts);
        return mav;
    }
    
    @RequestMapping(value="/saveContact", method=RequestMethod.GET)
    public ModelAndView newuserForm()
    {
        ModelAndView mav = new ModelAndView("newContact");
        Contact contact = new Contact();
        mav.getModelMap().put("newContact", contact);
        return mav;
    }
    
    @RequestMapping(value="/saveContact", method=RequestMethod.POST)
    public String create(@ModelAttribute("newContact")Contact contact, BindingResult result, SessionStatus status)
    {
    	if(contactsDAO.checkName(contact.getName())>0){
    		result.rejectValue("name", "filed.name", "Name is exists.");
    	}
        validator.validate(contact, result);
        if (result.hasErrors())
        {                
            return "newContact";
        }
        contactsDAO.save(contact);
        status.setComplete();
        return "login";
    }
    
    @RequestMapping(value="/saveContact2", method=RequestMethod.GET)
    public ModelAndView newuserForm2()
    {
        ModelAndView mav = new ModelAndView("newContact2");
        Contact contact = new Contact();
        mav.getModelMap().put("newContact2", contact);
        return mav;
    }
    
    @RequestMapping(value="/saveContact2", method=RequestMethod.POST)
    public String create2(@ModelAttribute("newContact2")Contact contact, BindingResult result,SessionStatus status)
    {
        validator.validate(contact, result);
        if (result.hasErrors())
        {                
            return "newContact2";
        }
        contactsDAO.save(contact);
        //status.setComplete();
        return "redirect:viewAllContacts.do";
    }
    
    @RequestMapping(value="/updateContact", method=RequestMethod.GET)
    public ModelAndView edit(@RequestParam("id")Integer id)
    {
        ModelAndView mav = new ModelAndView("editContact");
        Contact contact = contactsDAO.getById(id);
        mav.addObject("editContact", contact);
        return mav;
    }
    
    @RequestMapping(value="/updateContact", method=RequestMethod.POST)
    public String update(@ModelAttribute("editContact") Contact contact, BindingResult result, SessionStatus status)
    {
        validator.validate(contact, result);
        if (result.hasErrors()) {
            return "editContact";
        }
        contactsDAO.update(contact);
//        status.setComplete();
        return "redirect:viewAllContacts.do";
    }
        
    @RequestMapping("deleteContact")
    public ModelAndView delete(@RequestParam("id")Integer id)
    {
        ModelAndView mav = new ModelAndView("redirect:viewAllContacts.do");
        contactsDAO.delete(id);
        return mav;
    }    
    
    @RequestMapping("exit")
    public ModelAndView exit(SessionStatus status){
    	ModelAndView mav = new ModelAndView("login");
//    	status.setComplete();
    	logger.info("exit success!");
    	return mav;
    }
}