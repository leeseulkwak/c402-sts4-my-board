package kr.or.ysedu.c402.myboard.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.or.ysedu.c402.myboard.DataNotFoundException;
import kr.or.ysedu.c402.myboard.user.SiteUser;
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
   public void create(String subject, String content, SiteUser user) {
	   Question q=new Question();
	   q.setSubject(subject);
	   q.setContent(content);
	   q.setCreateDate(LocalDateTime.now());
	   q.setAuthor(user);
	   this.questionRepository.save(q);
   }
   
   public Page<Question> getList(int page){
	   List<Sort.Order> sorts=new ArrayList<>();
	   sorts.add(Sort.Order.desc("createDate"));
	   Pageable pageable=PageRequest.of(page,  10, Sort.by(sorts));
	   return this.questionRepository.findAll(pageable);
   }
   
   public void modify(Question question, String subject, String content) {
	   question.setSubject(subject);
	   question.setContent(content);
	   question.setModifyDate(LocalDateTime.now());
	   this.questionRepository.save(question);
   }
   
   public void delete(Question question) {
	   this.questionRepository.delete(question);
   }
}
