package kongs.model.dto;

public class Contest {
	private int num; // 공모전 번호
	private String title; // 공모전명
	private String sponser; // 주최사
	private String category; // 분야 ex) design, advertisement, it, media, idea
	private String startDate; // 시작일
	private String endDate; // 종료일
	private int clickCount; // 조회수
	private String regDate; // 글 등록일 
	private String photoName; // 사진명
	private String dday;
	
	public Contest(){}
	public Contest(int num, String startDate, String endDate ) {
		super();
		this.num = num;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Contest(int num, String startDate, String endDate, String dday) {
		super();
		this.num = num;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dday = dday;
	}
	public Contest(String title, String category, String sponser, String startDate, String endDate) {
		this.title = title;
		this.category = category;
		this.sponser = sponser;
		this.startDate = startDate;
		this.endDate = endDate;
	}	
	public Contest(int num, String title, String sponser, String category, String startDate, String endDate,
			int clickCount, String regDate, String photoName) {
		super();
		this.num = num;
		this.title = title;
		this.sponser = sponser;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.clickCount = clickCount;
		this.regDate = regDate;
		this.photoName = photoName;
	}
	public Contest(int num, String title, String sponser, String category, String startDate, String endDate,
			int clickCount, String regDate, String photoName, String dday) {
		super();
		this.num = num;
		this.title = title;
		this.sponser = sponser;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.clickCount = clickCount;
		this.regDate = regDate;
		this.photoName = photoName;
		this.dday = dday;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSponser() {
		return sponser;
	}
	public void setSponser(String sponser) {
		this.sponser = sponser;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getDday() {
		return dday;
	}
	public void setDday(String dday) {
		this.dday = dday;
	}
}
