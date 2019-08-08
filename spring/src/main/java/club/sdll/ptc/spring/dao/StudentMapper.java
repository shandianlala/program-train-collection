package club.sdll.ptc.spring.dao;

import org.apache.ibatis.annotations.Param;

import club.sdll.ptc.spring.pojo.Student;

public interface StudentMapper {
	
	Student queryUserByUserAccount(@Param("id") String id);
	
	int deleteByPrimaryKey(String id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}