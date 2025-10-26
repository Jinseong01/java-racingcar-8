package racingcar.domain;

public class Car {

    private static final int MOVE_THRESHOLD = 4;

    private final String name;
    private int distance;

    public Car(String name) {
        this.name = name;
        this.distance = 0;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public void moveForward(int num) {
        if (num >= MOVE_THRESHOLD) {
            distance++;
        }
    }
}