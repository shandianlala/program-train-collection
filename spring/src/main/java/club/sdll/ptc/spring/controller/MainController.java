package club.sdll.ptc.spring.controller;


import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import club.sdll.ptc.spring.dao.StudentMapper;
import club.sdll.ptc.spring.pojo.Course;
import club.sdll.ptc.spring.pojo.Student;
import club.sdll.ptc.spring.service.ICourseService;

public class MainController {
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:conf/applicationContext-spring.xml");
//		StudentMapper studentMapper = (StudentMapper) ac.getBean("studentMapper");
//		Student student = studentMapper.selectByPrimaryKey("0000112aae6611e79a4fd9b5fed945ce");
//		if(student != null) {
//			System.out.println("name : " + student.getName() + "  ||  address : " + student.getAddress());
//		}
		
//		Student student = (Student) ac.getBean("student");
//		if(student != null) {
//			System.out.println("id : " + student.getId());
//		}
		
		ICourseService courseService = (ICourseService) ac.getBean("courseService");
		Course course = new Course();
		course.setId(UUID.randomUUID().toString().replace("-", ""));
		//course.setId("aaaa");
		course.setCourseName("心理学");
		course.setCourseCredit(3);
		course.setBeginTime(new Timestamp(System.currentTimeMillis()));
		System.out.println("begin_time : " + course.getBeginTime());
		try {
			courseService.save(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
