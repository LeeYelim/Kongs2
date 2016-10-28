package kongs.model.dto;

public class Board {
	private int boardNum; // 게시판 번호
	private String category; // 게시판 종류 ex) 게시판 : free/find, 팀 게시판 : notice, task, file
	private String title; // 글 제목
	private String contents; // 글 내용
	private String writer; // 글쓴이
	private String regDate; // 글 등록 날짜
	private int clickCount; // 조회수
	private String fileName; // 파일명
	
	public Board(){}
	public Board(int boardNum, String title, String writer, String regDate, int clickCount){
		this.boardNum = boardNum;
		this.title = title;
		this.writer = writer;
		this.regDate = regDate;
		this.clickCount = clickCount;
	}
	public Board(int boardNum, String category, String title, String contents, String writer, String regDate,
			int clickCount, String fileName) {
		this.boardNum = boardNum;
		this.category = category;
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.regDate = regDate;
		this.clickCount = clickCount;
		this.fileName = fileName;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
