package kr.or.ysedu.c402.myboard.question;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import kr.or.ysedu.c402.myboard.answer.AnswerForm;
import kr.or.ysedu.c402.myboard.user.SiteUser;
import kr.or.ysedu.c402.myboard.user.UserService;
import lombok.RequiredArgsConstructor;
@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
//	private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	private final UserService userService;
	

	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		 Page<Question> paging = this.questionService.getList(page);
	     model.addAttribute("paging", paging);
		return "question_list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create") 
	public String questionCreate(QuestionForm questionForm) {//오버로딩
		return "question_form";
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        SiteUser siteUser=this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list";
    }

	
	@GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question=this.questionService.getQuestion(id);
		model.addAttribute("question", question);
        return "question_detail";
    }
}
