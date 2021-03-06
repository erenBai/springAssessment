package net.springetiqa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.springetiqa.entity.Student;
import net.springetiqa.repository.StudentRespository;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	private final StudentRespository studentRepository;
	@Autowired
	public StudentController(StudentRespository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@GetMapping("showForm")
	public String showStudentForm() {
		return "add-student";
	}
	
	@GetMapping("signup")
	public String showSignUpForm(Student student) {
		return "add-student";
	}
	  
	@GetMapping("list")
	public String students(Model model) {
		model.addAttribute("students",this.studentRepository.findAll());
		return "index";
	}
	
	@PostMapping("add")
	public String addStudent(@Valid Student student,BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add-student";
		}
		this.studentRepository.save(student);
		return "redirect:list";
	}
	
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = this.studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid student id :"+id));
        model.addAttribute("student", student);
        return "update-student";
    }
	
	 @PostMapping("update/{id}")
	    public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result,
	        Model model) {
	        if (result.hasErrors()) {
	            student.setId(id);
	            return "update-student";
	        }

	        studentRepository.save(student);
	        model.addAttribute("students", studentRepository.findAll());
	        return "index";
	    }
	 
	 @GetMapping("delete/{id}")
	 public String deleteStudent(@PathVariable ("id") long id,Model model) {
		  Student student = this.studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid student id :"+id));
		  this.studentRepository.delete(student);
		  model.addAttribute("students",this.studentRepository.findAll());
		  return "index";
	 }
 
}
