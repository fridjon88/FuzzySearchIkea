package com.ikea.fuzzy;

import com.ikea.fuzzy.util.DamerauLevenshtein;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DamerauLevenshteinTest {

    @ParameterizedTest
    @CsvSource({"ABC, ACB, 1"})
    @CsvSource({"ABBC, ABC, 1"})
    @CsvSource({"AKK, ABC, 2"})
    @CsvSource({"ABC ABC, ABC, 4"})
    @CsvSource({"ABDC, ABC, 1"})
    public void DamerauLevenshteinDistance(String a, String b, int expected) {

        int distance = DamerauLevenshtein.compute(a, b);
        Assertions.assertEquals(expected, distance);
    }
}
