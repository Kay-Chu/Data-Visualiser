public class TimeUtil {

    private static final int HOUR_SECOND = 60 * 60;
    private static final int MINUTE_SECOND = 60;

    public static String getTimeStrBySecond(int second) {

        if (second < 0) {
            return "INVALID";
        }

        if (second == 0) {
            return "00:00:00";
        }

        int hours = second / HOUR_SECOND;
        if (hours > 0) {
            second -= hours * HOUR_SECOND;
        }

        int minutes = second / MINUTE_SECOND;
        if (minutes > 0) {
            second -= minutes * MINUTE_SECOND;
        }

        return (hours >= 10 ? (hours + "")
                : ("0" + hours) + ":" + (minutes >= 10 ? (minutes + "") : ("0" + minutes)) + ":"
                + (second >= 10 ? (second + "") : ("0" + second)));

    }

}