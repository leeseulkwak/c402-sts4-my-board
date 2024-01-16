package kr.or.ysedu.c402.myboard.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ysedu.c402.myboard.DataNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }
    
    public Question getQuestion(Integer id) {  
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
    
   //제목과 내용을 입력 받아 이를 질문으로 저장하는 메서드
   public void create(String subject, String content) {
	   Question q=new Question();
	   q.setSubject(subject);
	   q.setContent(content);
	   q.setCreateDate(LocalDateTime.now());
	   this.questionRepository.save(q);
   }
}
