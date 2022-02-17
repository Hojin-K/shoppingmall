import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.shop.myapp.dto.Qna;
import com.shop.myapp.service.QnaService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Transactional

public class QnaTest {
	
	@Autowired
	private QnaService qnaService;
	
	@Test
	//전체불러오기, 1개만 불러오기, 삽입, 삭제
	public void insertQna() {
		Qna qna = new Qna();
		qna.setBoard_id(1);
		qna.setMember_id("asdf");
		qna.setBoard_title("test");
		qna.setBoard_content("text1111");
		qna.setRecommend_id(2);
		int result=qnaService.createQna(qna);
	}
	

}
