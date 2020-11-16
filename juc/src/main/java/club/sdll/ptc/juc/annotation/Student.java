package club.sdll.ptc.juc.annotation;

/**
 * description
 *
 * @author chengxiwang
 * @version v0.1
 * @date 2019年04月11日 15:43
 */
public class Student {

    @BeanProperty(id = "name", description = "姓名")
    private String name;


    private int age;


    private String idCardNo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @BeanProperty(id = "age", description = "年龄")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @BeanProperty(id = "idCardNo", description = "身份证号")
    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }
}
