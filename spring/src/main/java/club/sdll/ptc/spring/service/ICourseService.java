package club.sdll.ptc.spring.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import club.sdll.ptc.spring.pojo.Course;
import club.sdll.ptc.spring.pojo.Province;
@Transactional(propagation = Propagation.REQUIRED)
public interface ICourseService {
	
	int save(Course record) throws Exception;
	
	int saveProvince(Province record) throws Exception;

}
