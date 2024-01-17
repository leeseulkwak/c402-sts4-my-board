package kr.or.ysedu.c402.myboard.answer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import kr.or.ysedu.c402.myboard.question.Question;
import kr.or.ysedu.c402.myboard.user.SiteUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	public Answer create(Question question, String content, SiteUser author) {
		Answer answer=new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		answer.setAuthor(author);
		this.answerRepository.save(answer);	
		
		return answer;
	}
}
