package org.redhat.qe.jaeger;

import java.util.Random;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {
    static final Random RANDOM = new Random();

    public static Long nextLong(long start, long end) {
        return start + ((long) (RANDOM.nextDouble() * (end - start)));
    }

    public static Long nextLong(long end) {
        return nextLong(0L, end);
    }
}
