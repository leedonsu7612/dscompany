package com.ds.sap.persistence;

import com.ds.sap.domain.BoardLikeVO;
import com.ds.sap.domain.BoardVO;
import com.ds.sap.domain.Criteria;
import com.ds.sap.domain.SearchCriteria;

import java.util.List;

public interface BoardDAO {

    // 게시글 입력
    public void create(BoardVO boardVO) throws Exception;

    // 게시글 조회
    public BoardVO read(Integer bno) throws Exception;

    // 게시글 조회수 증가
    public void updateViewCnt(Integer bno) throws Exception;

    // 게시글 수정
    public void update(BoardVO boardVO) throws Exception;

    // 게시글 삭제
    public void delete(Integer bno) throws Exception;

    // 게시글 목록
    public List<BoardVO> list() throws Exception;

    // 게시글 목록 + 페이징
    public List<BoardVO> list(Criteria criteria) throws Exception;

    // 게시글 전체 갯수
    public int countList(Criteria criteria) throws Exception;

    // 게시글 목록 + 페이징 + 검색
    public List<BoardVO> list(SearchCriteria criteria) throws Exception;

    // 게시글 전체 갯수 or 검색된 게시글 갯수
    public int countSearchedList(SearchCriteria criteria) throws Exception;

    // 게시글 댓글 갯수 갱신
    public void updateReplyCnt(Integer bno, int amount) throws Exception;

    // 회원이 작성한 게시글 목록
    public List<BoardVO> userBoardList(String uid) throws Exception;

}
