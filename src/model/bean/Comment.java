package model.bean;

public class Comment {
	private int id;
	private String name;
	private String comment;
	private String datePost;
	private int song_id;

	public Comment() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDatePost() {
		return datePost;
	}

	public void setDatePost(String datePost) {
		this.datePost = datePost;
	}

	public Comment(int id, String name, String comment, String datePost, int song_id) {
		this.id = id;
		this.name = name;
		this.comment = comment;
		this.datePost = datePost;
		this.song_id = song_id;
	}

	public int getSong_id() {
		return song_id;
	}

	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", comment=" + comment + ", datePost=" + datePost + "]";
	}

}
