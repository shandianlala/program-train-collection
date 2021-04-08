package club.sdll.ptc.javaeight.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shandianlala@gmail.com
 */
public class StreamTest {

    @Test
    public void streamTest() {
        final Collection<Streams.Task> tasks = Arrays.asList(
                new Streams.Task(Streams.Status.OPEN, 5),
                new Streams.Task(Streams.Status.OPEN, 13),
                new Streams.Task(Streams.Status.CLOSED, 8)
        );

        final long totalPointsOfOpenTasks = tasks
                .stream()
                .filter(task -> task.getStatus() == Streams.Status.OPEN)
                .mapToInt(Streams.Task::getPoints)
                .sum();
        System.out.println("Total points: " + totalPointsOfOpenTasks);


        // Calculate total points of all tasks
        final double totalPoints = tasks
                .stream()
                .parallel()
                .map(task -> task.getPoints()) // or map( Task::getPoints )
                .reduce(0, Integer::sum);

        System.out.println("Total points (all tasks): " + totalPoints);

    }

    @Test
    public void testStream() {

        Student student1 = new Student("张三", 22, 'a');
        Student student2 = new Student("张三", 33, 'b');
        Student student3 = new Student("张三", 44, 'b');
        Student student4 = new Student("李四", 33, 'a');
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        studentList = studentList.stream().filter(temp -> {
            if (!"张三".equals(temp.getName())) {
                return false;
            }
            if (!('b' == temp.getSex())) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        System.out.println(studentList);





    }




}
