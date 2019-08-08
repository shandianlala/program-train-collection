package club.sdll.ptc.spring.service.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import club.sdll.ptc.spring.pojo.Course;
import club.sdll.ptc.spring.pojo.Province;
import club.sdll.ptc.spring.service.ICourseService;

public class CourseServiceImpl implements ICourseService{

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int save(Course record) throws Exception {
		String sql = "insert into course(id, course_name, course_credit, begin_time) values(?, ?, ?, ?) ";
		Object[] params = {record.getId(), record.getCourseName(), record.getCourseCredit(), record.getBeginTime()};
		//Object[] params = {'a', "心理学", 3.5};
		System.out.println(params.toString());
		int[] argTypes = {java.sql.Types.CHAR, java.sql.Types.VARCHAR, java.sql.Types.DOUBLE, java.sql.Types.TIMESTAMP};
		int row = jdbcTemplate.update(sql, params, argTypes);
		if(row == 1) {
			//throw new RuntimeException("测试事物回滚啦！！！============");
		}
		return row;
	}
	
	public int saveProvince(Province record) throws Exception {
		String sql = "insert into province(id, province_name, type) values(?, ?, ?) ";
		Object[] params = {record.getId(), record.getProvinceName(), record.getType()};
		//Object[] params = {'a', "心理学", 3.5};
		System.out.println(params.toString());
		int[] argTypes = {java.sql.Types.CHAR, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR};
		int row = jdbcTemplate.update(sql, params, argTypes);
		if(row == 1) {
			//throw new RuntimeException("测试事物回滚啦！！！============");
		}
		return row;
	}

}
