package club.sdll.ptc.mybatis.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import java.util.Properties;

/**
 * description
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2020-05-10 23:35
 */
public class TestInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof ParameterHandler) {
            DefaultParameterHandler parameterHandler = (DefaultParameterHandler) target;
//            parameterHandler
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
