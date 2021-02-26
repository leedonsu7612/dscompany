package com.ds.sap.service;

import com.ds.sap.domain.Criteria;
import com.ds.sap.domain.ReplyVO;
import com.ds.sap.persistence.BoardDAO;
import com.ds.sap.persistence.ReplyDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Inject
    private ReplyDAO replyDAO;

    @Inject
    private BoardDAO boardDAO;

    // 댓글 목록
    @Override
    public List<ReplyVO> list(Integer bno) throws Exception {
        return replyDAO.list(bno);
    }

    // 댓글 목록 + 페이징
    @Override
    public List<ReplyVO> list(Integer bno, Criteria criteria) throws Exception {
        return replyDAO.list(bno, criteria);
    }

    // 특정 게시글의 댓글 갯수
    @Override
    public int count(Integer bno) throws Exception {
        return replyDAO.count(bno);
    }

    // 댓글 입력
    @Transactional
    @Override
    public void addReply(ReplyVO replyVO) throws Exception {
        replyDAO.create(replyVO);
        boardDAO.updateReplyCnt(replyVO.getBno(), 1);
    }

    // 댓글 수정
    @Override
    public void modifyReply(ReplyVO replyVO) throws Exception {
        replyDAO.update(replyVO);
    }

    // 댓글 삭제
    @Transactional
    @Override
    public void removeReply(Integer rno) throws Exception {
        int bno = replyDAO.getBno(rno);
        replyDAO.delete(rno);
        boardDAO.updateReplyCnt(bno, -1);
    }

    // 회원이 작성한 댓글 목록
    @Override
    public List<ReplyVO> userReplies(String uid) throws Exception {
        return replyDAO.userReplies(uid);
    }
}
