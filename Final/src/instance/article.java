package instance;

public class article {
	private int id;
	private String username;
	private String title;
	private String key;
	private String description;
	private int point;
	public article(){
		this.id=0;
		this.username="";
		this.title="";
		this.key="";
		this.description="";
		this.point=0;
	}
	
	public article(int id,String username,String title,String key,String description,int point){
		this.id = id;
		this.username = username;
		this.title = title;
		this.key = key;
		this.description = description;
		this.point = point;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
}
