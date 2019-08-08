package club.sdll.ptc.mybatis.learn;

import club.sdll.ptc.mybatis.dao.BlogNoteMapper;
import club.sdll.ptc.mybatis.pojo.BlogNote;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Hello world!
 *
 * @author chengxiwang
 */
public class MybatisLearn {


    /**
     * 获取 SqlSessionFactory
     *
     * @return
     */
    public SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "mybatis-config.xml";
        try {
            //SqlSessionFactoryBuilder 读取配置文件
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources
                    .getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    /**
     * 获取 SqlSession
     *
     * @param sqlSessionFactory
     * @return
     */
    public SqlSession getSqlSession(SqlSessionFactory sqlSessionFactory) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    /**
     * 通过主键查询 namespace
     *
     * @param sqlSession
     */
    public void selectByPrimaryKeyWithNamespace(SqlSession sqlSession, String id) {
        BlogNote blogNote = sqlSession.selectOne(
                "BlogNoteMapper.selectByPrimaryKey",
                id);
        System.out.println("blogNote={}" + blogNote);

    }

    /**
     * 通过主键查询，mapper
     *
     * @param sqlSession
     */
    public void selectByPrimaryKeyWithMapper(SqlSession sqlSession, String id) {
        BlogNoteMapper blogNoteMapper = sqlSession.getMapper(BlogNoteMapper.class);
        BlogNote blogNote = blogNoteMapper.selectByPrimaryKey(id);
        System.out.println("blogNote={}" + blogNote);


    }


}
