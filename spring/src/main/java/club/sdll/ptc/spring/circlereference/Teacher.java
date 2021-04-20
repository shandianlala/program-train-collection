package club.sdll.ptc.spring.circlereference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author shandianlala@gmail.com
 * @version 1.0
 * @date 2021-04-13 07:32
 */
@Service
public class Teacher {
    @Autowired
    private Student student;

    public Teacher () {
        System.out.println("Teacher init1:" + student);

    }

    public void teach () {
        System.out.println("teach:");
        student.learn();
    }

}
