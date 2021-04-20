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
public class Student {
    @Autowired
    private Teacher teacher;

    public Student () {
        System.out.println("Student init:" + teacher);
    }

    public void learn () {
        System.out.println("Student learn");
    }
}
