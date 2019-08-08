package club.sdll.ptc.spring.pojo;

import java.util.Date;

public class Course {
	
	private String id;
	
	private String courseName;
	
	private Integer courseCredit;
	
	private Date beginTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getCourseCredit() {
		return courseCredit;
	}

	public void setCourseCredit(Integer courseCredit) {
		this.courseCredit = courseCredit;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
}
