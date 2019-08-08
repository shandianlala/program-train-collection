package club.sdll.ptc.javaeight.stream;

/**
 * @author shandianlala@gmail.com
 */
public class Streams {


    enum Status {
        OPEN, CLOSED
    }

    static final class Task {
        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }
}
