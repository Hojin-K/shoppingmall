package com.shop.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shop.myapp.dto.Qna;
import com.shop.myapp.service.QnaService;

@RestController
@RequestMapping("/qna")
public class QnaController3 {
	
	@Autowired
	private QnaService qnaService;
	
	 /** 게시글 목록 조회 */
    @GetMapping
    public List<Qna> getQnaList() throws Exception {
        return qnaService.getQnaList();
    }
    
    /** 게시글 등록  */
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public Qna insertBoard(@RequestBody Qna qna) throws Exception {
 
        qnaService.insertBoard(qna); 
        int QnaSeq = qna.getQna_seq(); 
        Qna boardDetail = qnaService.getQnaDetail(QnaSeq);
 
        return boardDetail;
    }
    
    /** 게시글 수정 */
    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping("/{board_id}")
    public Qna updateBoard(@PathVariable("board_id") int board_id, @RequestBody Qna qna) throws Exception {
 
    	qnaService.updateBoard(qna); 
        Qna boardDetail = qnaService.getQnaDetail(board_id);
 
        return boardDetail;
    }
    
    /** 게시글 삭제  */
    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{board_id}")
    public Qna deleteBoard(@PathVariable("board_id") int board_id) throws Exception {
 
    	qnaService.deleteBoard(board_id);
 
        Qna deleteBoard = new Qna();
        deleteBoard.setBoard_seq(board_id);
 
        return deleteBoard;
    }
    

}
