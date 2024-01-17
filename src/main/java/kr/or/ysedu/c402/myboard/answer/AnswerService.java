package kr.or.ysedu.c402.myboard.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ysedu.c402.myboard.DataNotFoundException;
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
	
	//답변을 조회
	 public Answer getAnswer(Integer id) {
	        Optional<Answer> answer = this.answerRepository.findById(id);
	        if (answer.isPresent()) {
	            return answer.get();
	        } else {
	            throw new DataNotFoundException("answer not found");
	        }
	    }
	 //답변을 수정
	 public void modify(Answer answer, String content) {
	        answer.setContent(content);
	        answer.setModifyDate(LocalDateTime.now());
	        this.answerRepository.save(answer);
	    }
	 
	 public void delete(Answer answer) {
		 this.answerRepository.delete(answer);
	 }
}
