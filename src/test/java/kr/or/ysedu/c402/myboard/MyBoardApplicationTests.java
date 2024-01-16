package kr.or.ysedu.c402.myboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ysedu.c402.myboard.answer.Answer;
import kr.or.ysedu.c402.myboard.answer.AnswerRepository;
import kr.or.ysedu.c402.myboard.question.QuestionRepository;
import kr.or.ysedu.c402.myboard.question.QuestionService;

@SpringBootTest
class MyBoardApplicationTests {

	@Autowired
	//의존성 주입(DI)
	 private QuestionService questionService;
	
    @Autowired
    private AnswerRepository answerRepository;

	
//1. 게시글 생성
//	void testJpa() {        
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        q1.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q1);  // 첫번째 질문 저장
//
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문입니다.");
//        q2.setContent("id는 자동으로 생성되나요?");
//        q2.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q2);  // 두번째 질문 저장
	
//2. assertEquals(기댓값, 실젯값)와 같이 작성하고 기댓값과 실젯값이 동일한지를 조
//	 void testJpa() {
//        List<Question> all = this.questionRepository.findAll();
//        assertEquals(2, all.size());
//
//        Question q = all.get(0);
//        assertEquals("sbb가 무엇인가요?", q.getSubject());
//    }
	
//3. id 값으로 데이터 조회
//	 void testJpa() {
//        Optional<Question> oq = this.questionRepository.findById(1);
//        if(oq.isPresent()) {
//            Question q = oq.get();
//            assertEquals("sbb가 무엇인가요?", q.getSubject());
//        }
//    }
	

//4.  subject값으로 데이터를 조회
//	void testJpa() {
//        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
//        assertEquals(1, q.getId());
//    }
//	
//5. subject and content 로 조회
//	
//	void testJpa() {
//        Question q = this.questionRepository.findBySubjectAndContent(
//                "sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
//        assertEquals(1, q.getId());
//    }
	
//6. 특정 문자열을 포함하는 데이터를 조회
//	void testJpa() {
//        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
//        Question q = qList.get(0);
//        assertEquals("sbb가 무엇인가요?", q.getSubject());
//    }

//7. 질문 데이터를 수정하는 테스트 코드
//	 void testJpa() {
//        Optional<Question> oq = this.questionRepository.findById(1);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//        q.setSubject("수정된 제목");
//        this.questionRepository.save(q);
//    }
	
//8. 데이터 삭제하기
//	void testJpa() {
//        assertEquals(2, this.questionRepository.count());
//        Optional<Question> oq = this.questionRepository.findById(1);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//        this.questionRepository.delete(q);
//        assertEquals(1, this.questionRepository.count());
//    }
//	9.답변 데이터 생성하기
//	void testJpa() {
//        Optional<Question> oq = this.questionRepository.findById(2);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//
//        Answer a = new Answer();
//        a.setContent("네 자동으로 생성됩니다.");
//        a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
//        a.setCreateDate(LocalDateTime.now());
//        this.answerRepository.save(a);
//	}
//// 10.답변 데이터 조회하기
//	void testJpa() {
//		Optional<Answer> oa=this.answerRepository.findById(1);
//		assertTrue(oa.isPresent());
//		Answer a=oa.get();
//		assertEquals(2, a.getQuestion().getId());
//	}
	
//	데이터 만들기
    @Test
    void testJpa() {
        for (int i = 1; i <= 100; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            this.questionService.create(subject, content);
        }
    }
}
