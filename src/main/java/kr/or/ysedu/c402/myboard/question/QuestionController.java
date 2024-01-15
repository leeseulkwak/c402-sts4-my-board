package kr.or.ysedu.c402.myboard.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {
	
//	private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	
	@GetMapping("/question/list")
	public String list(Model model) {
		List<Question> questionList=this.questionService.getList();
		model.addAttribute("questionList", questionList);
		return "question_list";
	}
	
	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
	}
	
}
