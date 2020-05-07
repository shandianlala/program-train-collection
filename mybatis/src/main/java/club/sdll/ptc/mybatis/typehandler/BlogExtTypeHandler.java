package club.sdll.ptc.mybatis.typehandler;

import club.sdll.ptc.mybatis.pojo.BlogExt;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * description
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2020-05-07 01:44
 */
@MappedJdbcTypes(value = JdbcType.VARCHAR)
@MappedTypes(value = BlogExt.class)
public class BlogExtTypeHandler extends BaseTypeHandler<BlogExt> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BlogExt parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public BlogExt getNullableResult(ResultSet rs, String columnName) throws SQLException {
        System.out.println("getNullableResult1========");
        String value = rs.getString(columnName);
        if (value!= null) {
            return JSON.parseObject(value, BlogExt.class);
        }
        return null;
    }

    @Override
    public BlogExt getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        System.out.println("getNullableResult2========");
        return null;
    }

    @Override
    public BlogExt getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        System.out.println("getNullableResult3==========");
        return null;
    }
}
