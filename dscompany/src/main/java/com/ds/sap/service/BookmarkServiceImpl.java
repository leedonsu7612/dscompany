package com.ds.sap.service;

import com.ds.sap.domain.BookmarkVO;
import com.ds.sap.persistence.BookmarkDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Inject
    private BookmarkDAO bookmarkDAO;

    @Override
    public void create(BookmarkVO bookmarkVO) throws Exception {
        bookmarkDAO.create(bookmarkVO);
    }

    @Override
    public List<BookmarkVO> list(String uid) throws Exception {
        return bookmarkDAO.list(uid);
    }

    @Override
    public boolean checkBookmark(Integer bno, String uid) throws Exception {
        return bookmarkDAO.checkBookmark(bno, uid);
    }

    @Override
    public void remove(Integer bno, String uid) throws Exception {
        bookmarkDAO.delete(bno, uid);
    }
}
