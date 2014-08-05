package com.sivalabs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sivalabs.entity.Contact;

@Component("contactFormValidator")
public class ContactFormValidator implements Validator {

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz)// 该校验支持的目标类
	{
		return Contact.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {// 对目标类对象进行校验，错误记录在errors中
		Contact contact = (Contact) target;
		// Name
		userCheck(contact.getName(), errors);
		// Password
		passwordCheck(contact.getPassword(), errors);
		

	}

	public void loginValidate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","required.name", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","required.password", "Password is required.");
	}

	private void userCheck(String name, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","required.name", "Name is required.");
		Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5a-zA-Z0-9_]{1,14}$");
		Matcher matcher = pattern.matcher(name);
		if (name.length() < 1) {
			errors.rejectValue("name", "filed.name","Name's length must be more than 1 character.");
		}else if(!matcher.matches()){
			errors.rejectValue("name", "filed.name","User name is allowed only for English, underline, figures and Chinese characters.");
		}
	}

	private void passwordCheck(String password, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","required.password", "Password is required.");
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{1,}$");
		Matcher matcher = pattern.matcher(password);
		if (password.length() < 8 || password.length() > 30) {
			errors.rejectValue("password", "filed.password","Password's length must be between 8 and 30.");
		}else if(!matcher.matches()){
			errors.rejectValue("password", "filed.password","Password is allowed only by English, underline and digital components.");
		}
	}

	
	// public static void main(String[] args) {
	// String s="13345678912";
	// Pattern pattern =Pattern.compile("^1[3|5|8][\\d]{9}$");
	// Matcher matcher= pattern.matcher(s);
	// System.out.println(matcher.matches());
	// }
}