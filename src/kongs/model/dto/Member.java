package kongs.model.dto;

public class Member {
	
	private String id;
	private String pwd;
	private String upwd; //변경할 비밀번호
	private String name;
	private String nick; // 닉네임
	private String teamId; // 팀이름
	
	public Member(){}
	public Member(String id) {
		this.id = id;
	}
	public Member(String id, String pwd, String name, String nick) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nick = nick;
	}
	public Member(String id, String pwd, String name, String nick, String teamId) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nick = nick;
		this.teamId = teamId;
	}
	public Member(String id, String pwd, String upwd, String name, String nick, String teamId) {
		this.id = id;
		this.pwd = pwd;
		this.upwd = upwd;
		this.name = name;
		this.nick = nick;
		this.teamId = teamId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
}
