package kr.or.ysedu.c402.myboard.answer;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import kr.or.ysedu.c402.myboard.question.Question;
import kr.or.ysedu.c402.myboard.question.QuestionService;
import kr.or.ysedu.c402.myboard.user.SiteUser;
import kr.or.ysedu.c402.myboard.user.UserService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
		Question question =this.questionService.getQuestion(id);
		SiteUser siteUser=this.userService.getUser(principal.getName());
		if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
		this.answerService.create(question, answerForm.getContent(), siteUser);	//답변을 저장
		return String.format("redirect:/question/detail/%s", id);
	}
}
