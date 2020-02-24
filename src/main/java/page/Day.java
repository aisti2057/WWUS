package page;

public enum Day {

    SUNDAY(0, "Sun"),
    MONDAY(1, "Mon"),
    TUESDAY(2, "Tue"),
    WEDNESDAY(3, "Wed"),
    THURSDAY(4, "Thu"),
    FRIDAY(5, "Fri"),
    SATURDAY(6, "Sat");

    private int num;
    private String value;

    Day(int num, String value) {
        this.num = num;
        this.value = value;
    }

    public int getNum() {
        return num;
    }

    public String getDay() {
        return value;
    }

}
