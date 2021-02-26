package com.ds.sap.service;

import com.ds.sap.domain.BookmarkVO;

import java.util.List;

public interface BookmarkService {

    // 북마크 등록
    public void create(BookmarkVO bookmarkVO) throws Exception;

    // 북마크 목록
    public List<BookmarkVO> list(String uid) throws Exception;

    // 북마크 확인
    public boolean checkBookmark(Integer bno, String uid) throws Exception;

    // 북마크 삭제
    public void remove(Integer bno, String uid) throws Exception;

}
