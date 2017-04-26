package github.olegts.java8.misused.stream.incorrect;

import github.olegts.java8.misused.Annotations.Bad;

import java.util.stream.IntStream;

public class ForgotTerminalOperation {
    @Bad
    public void willDoNothingInReality() {
        IntStream.range(1, 5)
                .peek(System.out::println)
                .peek(i -> {
                    if (i == 5)
                        throw new RuntimeException("bang");
                });
    }
}
