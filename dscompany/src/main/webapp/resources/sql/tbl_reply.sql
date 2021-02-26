CREATE TABLE tbl_reply_likes (
  rno INT NOT NULL AUTO_INCREMENT,
  rlno INT ,
  regdate TIMESTAMP NOT NULL DEFAULT NOW(),
  updatedate TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (rno)
);

ALTER TABLE tbl_reply_likes ADD CONSTRAINT fk_board
FOREIGN KEY (bno) REFERENCES tbl_board (bno);
