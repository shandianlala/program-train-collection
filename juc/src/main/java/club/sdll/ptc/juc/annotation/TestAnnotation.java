package club.sdll.ptc.juc.annotation;

/**
 * description
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2019年04月11日 15:42
 */
public class TestAnnotation {

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("李四");


//        Field[] fields = student.getClass().getDeclaredFields();

//        for (Field field : fields) {
////            field.getName();
////            field.getInt(student);
//            BeanProperty beanProperty = field.getAnnotation(BeanProperty.class);
//            System.out.println("name="  +", value=" + beanProperty.description());
//
//        }


    }

}
