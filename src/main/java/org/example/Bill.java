package org.example;

/**
 * @author 86173
 */
public class Bill {
    private static final int Three = 3;
    private static int[] startTime = new int[Three];
    private static int[] endTime = new int[Three];
    private static int[] poorTime = new int[Three];
    private static int time;
    private static float money;
    private static int Year;
    private static int Month;
    private static int Day;
    private static int T = 0;
    private static int W = 1;
    private static int M = 1;

    public static float countBill(int year, int month, int day, int SHour, int SMinute, int SSecond,
                                  int EHour, int EMinute, int ESecond) {
        Year = year;
        Month = month;
        Day = day;
        startTime[0] = SHour;
        startTime[1] = SMinute;
        startTime[2] = SSecond;
        endTime[0] = EHour;
        endTime[1] = EMinute;
        endTime[2] = ESecond;
        Date();
        Time();
        Money();
        return money;
    }

    public static void Date() {
        //菜勒公式(给一个日期判断是星期几)
        if (1 == Month || 2 == Month) {
            Year--;
            money += 12;
        }
        W = (Day + 2 * Month + Three * (Month + 1) / 5 + Year + Year / 4 - Year / 100 + Year / 400 + 1) % 7;
        if (Month > 12) {
            Year++;
            Month -= 12;
        }
        if (0 == W) {
            if (Three == Month && Day >= 25 && Day <= 31) {
                T = 1;
            } else if (11 == Month && Day >= 1 && Day <= 7) {
                T = 2;
            } else {
                T = 0;
            }
        } else {
            T = 0;
        }
    }

    public static void Time() {
        if (startTime[0] > endTime[0]) {
            endTime[0] += 24;
        }
        for (int i = 0; i < Three; i++) {
            poorTime[i] = endTime[i] - startTime[i];
        }
        if (0 == T) {
            if (6 == W && endTime[0] >= 27) {
                if (Three == Month && Day >= 24 && Day <= 30) {
                    poorTime[0]--;
                } else if (11 == Month && Day >= 1 && Day <= 7) {
                    poorTime[0]++;
                }
            } else if (6 == W && endTime[0] >= 26 && endTime[0] <= 27 && 11 == Month && Day >= 1 && Day <= 7) {
                M = 0;
            }
        } else if (1 == T) {
            if (startTime[0] <= 2 && endTime[0] >= Three) {
                poorTime[0]--;
            }
        } else if (2 == T) {
            if (startTime[0] < 2 && endTime[0] >= Three) {
                poorTime[0]++;
            } else if (poorTime[1] < 0 && startTime[0] == 2 && endTime[0] == 2) {
                poorTime[0]++;
            }
        }
        time = poorTime[0] * 60 + poorTime[1];
        if (poorTime[2] > 0) {
            time++;
        }
    }

    public static void Money() {
        if (time <= 20) {
            money = (float) (time * 0.05);
        }
        if (time > 20) {
            money = (float) (1 + (time - 20) * 0.1);
        }
    }
}
