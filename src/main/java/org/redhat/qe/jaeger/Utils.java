package org.redhat.qe.jaeger;

import java.util.Random;

import org.apache.commons.io.FileUtils;

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

    public static String getWWW() {
        if (FileUtils.getFile("../www").exists()) {
            return FileUtils.getFile("../www").getAbsolutePath();
        } else if (FileUtils.getFile("www").exists()) {
            return FileUtils.getFile("www").getAbsolutePath();
        } else {
            return FileUtils.getFile("src/main/package/www").getAbsolutePath();
        }
    }
    
}
