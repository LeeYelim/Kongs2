package kongs.model.dto;

public class TeamBoard extends Board {
	private String teamId;

	public TeamBoard(){}
	public TeamBoard(String teamId, int boardNum, String category, String title, String contents, String writer, String regDate,
			int clickCount, String fileName) {
		super(boardNum, category, title, contents, writer, regDate, clickCount, fileName);
		this.teamId = teamId;
	}
	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
	
	
	
}
