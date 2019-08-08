package club.sdll.ptc.spring.pojo;

public class Province {
	
	private String id;
	
	private String provinceName;
	
	private String type;//1:代表省；2:代表自治区

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
