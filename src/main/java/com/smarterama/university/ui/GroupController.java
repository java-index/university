package com.smarterama.university.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smarterama.university.domain.University;
import com.smarterama.university.domain.personal.Group;

//@RequestMapping("/groups")
//@Controller
public class GroupController {

	private static final Logger LOG = Logger.getLogger(GroupController.class);

	private University universityService;

	private MessageSource messageSource;

	// view groups list
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		LOG.info("Listing groups...");
		List<Group> groups = universityService.getAllGroup();
		model.addAttribute("groups", groups);
		LOG.info("No. of groups: " + groups.size());
		return "group/list";
	}

	// group view
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") long id, Model model) {
		Group group = universityService.getGroupById(id);
		model.addAttribute("group", group);
		return "group/show";
	}

	// group show edit form
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") long id, Model model) {
		model.addAttribute("group", universityService.getGroupById(id));
		return "group/edit";
	}

	// student update
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(@Valid Group group, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes, Locale locale) {
		LOG.info("Updating contact...");
		if (bindingResult.hasErrors()) {
			setErrorMessages(bindingResult, model, locale);
			model.addAttribute("group", group);
			return "group/edit";
		}
		model.asMap().clear();
		redirectAttributes.addFlashAttribute("message",
				new Message("success", messageSource.getMessage("save_success", new Object[] {}, locale)));
		LOG.info("Update group, id = " + group.getId());
		universityService.updateGroup(group);
		return "redirect:/groups/" + group.getId();
	}

	// group show create form
	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute(new Group());
		return "group/edit";
	}

	@RequestMapping(params = "form", method = RequestMethod.POST)
	public String create(@Valid Group group, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes, Locale locale) {
		LOG.info("Creating contact...");
		if (bindingResult.hasErrors()) {
			setErrorMessages(bindingResult, model, locale);
			model.addAttribute("group", group);
			return "group/edit";
		}
		model.asMap().clear();
		redirectAttributes.addFlashAttribute("message",
				new Message("success", messageSource.getMessage("save_success", new Object[] {}, locale)));
		LOG.info("Create group, id = " + group.getId());
		universityService.addGroup(group);
		return "redirect:/groups/" + group.getId();
	}

	@Autowired
	public void setUnversityServise(University universityService) {
		this.universityService = universityService;
	}

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
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
}