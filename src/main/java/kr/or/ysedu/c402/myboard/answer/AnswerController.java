package kr.or.ysedu.c402.myboard.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ysedu.c402.myboard.question.Question;
import kr.or.ysedu.c402.myboard.question.QuestionService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	private final QuestionService questionService;
	private final AnswerService answerService;
	
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam(value="content") String content) {
		Question question =this.questionService.getQuestion(id);
		this.answerService.create(question, content);	//답변을 저장
		return String.format("redirect:/question/detail/%s", id);
	}
}
