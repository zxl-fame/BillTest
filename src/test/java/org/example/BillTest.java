package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv")
    void testWithCsvSource(int year, int month, int day, int SHour, int SMinute, int SSecond, int EHour,
                           int EMinute, int ESecond, float expected) {
        Bill bill = new Bill();
        assertEquals(expected, bill.countBill(year, month, day, SHour, SMinute, SSecond, EHour, EMinute, ESecond));
    }
}
