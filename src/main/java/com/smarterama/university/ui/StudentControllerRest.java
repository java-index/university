package com.smarterama.university.ui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.smarterama.university.domain.University;
import com.smarterama.university.domain.personal.Student;

@RestController
public class StudentControllerRest {

	private static final Logger LOG = Logger.getLogger(StudentControllerRest.class);

	private University universityService;

	//private MessageSource messageSource;

	// view all students
	@GetMapping("/students")
	public List<Student> list(Model model) {
		LOG.info("Listing students...");
		List<Student> students = universityService.getAllStudent();
		LOG.info("No. of students: " + students.size());
		return students;
	}

	// view
	@GetMapping(value = "/students/{id}")
	public ResponseEntity show(@PathVariable("id") long id, Model model) {
		Student student = universityService.getStudentById(id);
		if (student == null) {
			return new ResponseEntity ("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity (student, HttpStatus.OK);
	}
	
	// create
	@PostMapping(value = "/students")
	public ResponseEntity createStudent(@RequestBody Student student) {
		universityService.addStudent(student);
		return new ResponseEntity(student, HttpStatus.OK);
	}
	
	// delete
	@DeleteMapping("/students/{id}")
	public ResponseEntity deleteStudent(@PathVariable long id) {
		if (0.0 == universityService.deleteStudent(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(id, HttpStatus.OK);
	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity updateStudents(@PathVariable long id, @RequestBody Student student) {

		universityService.updateStudent(student);

		if (null == student) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(student, HttpStatus.OK);
	}

//	// student show edit form
//	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
//	public String updateForm(@PathVariable("id") long id, Model model) {
//		model.addAttribute("student", universityService.getStudentById(id));
//		return "student/edit";
//	}
//
//	// student update
//	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
//	public String update(@Valid Student student, BindingResult bindingResult, Model model,
//			RedirectAttributes redirectAttributes, Locale locale,
//			@RequestParam(value = "photo", required = false) MultipartFile photo) {
//		LOG.info("Updating contact...");
//		if (bindingResult.hasErrors()) {
//			setErrorMessages(bindingResult, model, locale);
//			model.addAttribute("student", student);
//			return "student/edit";
//		}
//		model.asMap().clear();
//		redirectAttributes.addFlashAttribute("message",
//				new Message("success", messageSource.getMessage("save_success", new Object[] {}, locale)));
//		setPersonPhoto(student, photo);
//		universityService.updateStudent(student);
//		return "redirect:/students/" + student.getId();
//	}
//
//	// student show create form
//	@RequestMapping(params = "form", method = RequestMethod.GET)
//	public String createForm(Model model) {
//		model.addAttribute(new Student());
//		return "student/edit";
//	}
//
//	@RequestMapping(params = "form", method = RequestMethod.POST)
//	public String create(@Valid Student student, BindingResult bindingResult, Model model,
//			RedirectAttributes redirectAttributes, Locale locale,
//			@RequestParam(value = "photo", required = false) MultipartFile photo) {
//		LOG.info("Creating contact...");
//		if (bindingResult.hasErrors()) {
//			setErrorMessages(bindingResult, model, locale);
//			model.addAttribute("student", student);
//			return "student/edit";
//		}
//		model.asMap().clear();
//		redirectAttributes.addFlashAttribute("message",
//				new Message("success", messageSource.getMessage("save_success", new Object[] {}, locale)));
//		LOG.info("Create student, id = " + student.getId());
//		if (photo != null) {
//			setPersonPhoto(student, photo);
//		}
//		universityService.addStudent(student);
//		return "redirect:/students/" + student.getId();
//	}
//
//	@RequestMapping(value = "/photo/{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public byte[] downloadPhoto(@PathVariable("id") long id) {
//		Student student = universityService.getStudentById(id);
//		if (student != null && student.getPhoto() != null) {
//			LOG.info("Download photo for id: " + student.getId() + " whith size: " + student.getPhoto().length);
//			return student.getPhoto();
//		}
//		LOG.info("Download photo for NULL");
//		return null;
//	}
//
	@Autowired
	public void setUnversityServise(University universityService) {
		this.universityService = universityService;
	}

//	@Autowired
//	public void setMessageSource(MessageSource messageSource) {
//		this.messageSource = messageSource;
//	}
//
//	private void setPersonPhoto(Student student, MultipartFile photo) {
//		if (photo == null) {
//			student.setPhoto(universityService.getStudentById(student.getId()).getPhoto());
//			return;
//		}
//			LOG.info("Photo size: " + photo.getSize() + " content type: " + photo.getContentType());
//			byte[] fileContent = null;
//			try {
//				fileContent = photo.getBytes();
//			} catch (IOException e) {
//				LOG.error("Error saving photo");
//			}
//			student.setPhoto(fileContent);
//	}
//
//	private void setErrorMessages(BindingResult bindingResult, Model model, Locale locale) {
//		Map<String, String> errors = new HashMap<>();
//		for (FieldError fieldError : bindingResult.getFieldErrors()) {
//			errors.put(fieldError.getField(), "has-error");
//			LOG.info("Error field: " + fieldError.getField());
//		}
//		model.addAttribute("errors", errors);
//		model.addAttribute("message",
//				new Message("error", messageSource.getMessage("save_fail", new Object[] {}, locale)));
//	}
//
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
//	}
}