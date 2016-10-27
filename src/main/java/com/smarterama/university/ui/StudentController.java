package com.smarterama.university.ui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smarterama.university.domain.University;
import com.smarterama.university.domain.personal.Student;

@RequestMapping("/students")
@Controller
public class StudentController {

	private static final Logger LOG = Logger.getLogger(StudentController.class);

	private University universityService;

	private MessageSource messageSource;

	// view students list
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		LOG.info("Listing students...");
		List<Student> students = universityService.getAllStudent();
		model.addAttribute("students", students);
		LOG.info("No. of students: " + students.size());
		return "student/list";
	}

	// student view
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") long id, Model model) {
		Student student = universityService.getStudentById(id);
		model.addAttribute("student", student);
		return "student/show";
	}

	// student show edit form
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") long id, Model model) {
		model.addAttribute("student", universityService.getStudentById(id));
		return "student/edit";
	}

	// student update
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(@Valid Student student, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value = "photo", required = false) MultipartFile photo) {
		LOG.info("Updating contact...");
		if (bindingResult.hasErrors()) {
			setErrorMessages(bindingResult, model, locale);
			model.addAttribute("student", student);
			return "student/edit";
		}
		model.asMap().clear();
		redirectAttributes.addFlashAttribute("message",
				new Message("success", messageSource.getMessage("save_success", new Object[] {}, locale)));
		setPersonPhoto(student, photo);
		universityService.updateStudent(student);
		return "redirect:/students/" + student.getId();
	}

	// student show create form
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute(new Student());
		return "student/edit";
	}

	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(@Valid Student student, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value = "photo", required = false) MultipartFile photo) {
		LOG.info("Creating contact...");
		if (bindingResult.hasErrors()) {
			setErrorMessages(bindingResult, model, locale);
			model.addAttribute("student", student);
			return "student/edit";
		}
		model.asMap().clear();
		redirectAttributes.addFlashAttribute("message",
				new Message("success", messageSource.getMessage("save_success", new Object[] {}, locale)));
		LOG.info("Create student, id = " + student.getId());
		if (photo != null) {
			setPersonPhoto(student, photo);
		}
		universityService.addStudent(student);
		return "redirect:/students/" + student.getId();
	}

	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("id") long id) {
		Student student = universityService.getStudentById(id);
		if (student != null && student.getPhoto() != null) {
			LOG.info("Download photo for id: " + student.getId() + " whith size: " + student.getPhoto().length);
			return student.getPhoto();
		}
		LOG.info("Download photo for NULL");
		return null;
	}

	@Autowired
	public void setUnversityServise(University universityService) {
		this.universityService = universityService;
	}

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	private void setPersonPhoto(Student student, MultipartFile photo) {
		if (photo == null) {
			student.setPhoto(universityService.getStudentById(student.getId()).getPhoto());
			return;
		}
			LOG.info("Photo size: " + photo.getSize() + " content type: " + photo.getContentType());
			byte[] fileContent = null;
			try {
				fileContent = photo.getBytes();
			} catch (IOException e) {
				LOG.error("Error saving photo");
			}
			student.setPhoto(fileContent);
	}

	private void setErrorMessages(BindingResult bindingResult, Model model, Locale locale) {
		Map<String, String> errors = new HashMap<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errors.put(fieldError.getField(), "has-error");
			LOG.info("Error field: " + fieldError.getField());
		}
		model.addAttribute("errors", errors);
		model.addAttribute("message",
				new Message("error", messageSource.getMessage("save_fail", new Object[] {}, locale)));
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}