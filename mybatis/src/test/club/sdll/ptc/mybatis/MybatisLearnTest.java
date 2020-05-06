package club.sdll.ptc.mybatis;

import club.sdll.ptc.mybatis.learn.MybatisLearn;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * MybatisLearn Tester.
 * git@github.com:shandianlala/mybatis-3.git
 * git@github.com:apache/rocketmq.git
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>五月 17, 2019</pre>
 */
public class MybatisLearnTest {

    @Before
    public void before() throws Exception {
        System.out.println("===========开始测试===========");
    }

    @After
    public void after() throws Exception {
        System.out.println("===========结束测试===========");
    }


    /**
     * Method: selectByPrimaryKeyWithNamespace(SqlSession sqlSession, String id)
     */
    @Test
    public void testSelectByPrimaryKeyWithNamespace() throws Exception {
        MybatisLearn mybatisLearn = new MybatisLearn();
        SqlSessionFactory sqlSessionFactory = mybatisLearn.getSqlSessionFactory();
        Assert.assertEquals("sqlSessionFactory不为空", true, sqlSessionFactory != null);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Assert.assertEquals("sqlSession不为空", true, sqlSession != null);
        mybatisLearn.selectByPrimaryKeyWithNamespace(sqlSession, "399545ac39474466a82e51131fd5b85d");

    }

    /**
     * Method: selectByPrimaryKeyWithMapper(SqlSession sqlSession, String id)
     */
    @Test
    public void testSelectByPrimaryKeyWithMapper() throws Exception {
        //TODO: Test goes here...
    }


} 
