package model.bean;

public class Song {
	private int id;
	private String name;
	private String previewText;
	private String detailText;
	private String dateCreate;
	private String picture;
	private int counter;
	private int catId;

	public Song() {
	}

	public Song(int id, String name, String previewText, String detailText, String dateCreate, String picture,
			int counter, int catId) {
		this.id = id;
		this.name = name;
		this.previewText = previewText;
		this.detailText = detailText;
		this.dateCreate = dateCreate;
		this.picture = picture;
		this.counter = counter;
		this.catId = catId;
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

	public String getPreviewText() {
		return previewText;
	}

	public void setPreviewText(String previewText) {
		this.previewText = previewText;
	}

	public String getDetailText() {
		return detailText;
	}

	public void setDetailText(String detailText) {
		this.detailText = detailText;
	}

	public String getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", previewText=" + previewText + ", detailText=" + detailText
				+ ", dateCreate=" + dateCreate + ", picture=" + picture + ", counter=" + counter + ", catId=" + catId
				+ "]";
	}

}
