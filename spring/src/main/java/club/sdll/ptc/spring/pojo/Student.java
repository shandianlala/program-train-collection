package club.sdll.ptc.spring.pojo;

public class Student {
	
	private String id;
	
	private String name;
	
	private Character sex;
	
	private Integer age;
	
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String userId) {
		this.id = userId;
	}
	
	/**
	 * 通过无参构造方法、set方法注入userId的值
	 * @createUser lenovo
	 * @createDate 2017年10月18日
	 * @param userId
	 */
//	public void setUserId(String userId) {
//		System.out.println("setter方法注入userId属性=====================");
//		this.id = userId;
//	}
	
	/**
	 * 通过有参构造方法、constructor-arg name="userId" value="bbb"/注入userId的值
	 * @createUser lenovo
	 * @createDate 2017年10月18日
	 * @param userId
	 */
	public Student(String userId) {
		System.out.println("构造方法注入userId属性=====================");
		this.id = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
