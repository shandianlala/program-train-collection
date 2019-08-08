package club.sdll.ptc.javaeight.lambda;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author shandianlala@gmail.com
 * https://www.cnblogs.com/onetwo/p/8526374.html
 */
public class LambdaTest {

    @Test
    public void array() {
        // 参数e的类型是由编译器推理得出
        Arrays.asList(1, 2, 3, 4, 5).forEach(e -> print(e));
        System.out.println("---------------------------------");
        Arrays.asList(1, 2, 3, 4, 5).forEach((Integer e) -> print(e));
        System.out.println("---------------------------------");

        // 如果Lambda表达式需要更复杂的语句块，则可以使用花括号将该语句块括起来，类似于Java中的函数体
        Arrays.asList(1, 2, 3, 4, 5).forEach(e -> {
            System.out.print(e + ",");
        });

        // Lambda表达式可以引用类成员和局部变量 （会将这些变量隐式得转换成final的），例如下列两个代码块的效果完全相同：
        String separator = ",";
        //隐式得转换成final的  final String separator = ",";
        Arrays.asList( "a", "b", "d" ).forEach(
                ( String e ) -> System.out.print( e + separator ) );


        // Lambda表达式有返回值，返回值的类型也由编译器推理得出。
        // 如果Lambda表达式中的语句块只有一行，则可以不用使用return语句，下列两个代码片段效果相同：
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
            int result = e1.compareTo( e2 );
            return result;
        } );





    }

    public void print(Object o) {
        System.out.print(o + ",");
    }


}
