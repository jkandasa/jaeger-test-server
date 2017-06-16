package org.redhat.qe.jaeger.api.model.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Count {
    private Integer passed;
    private Integer failed;
    private Integer skipped;
    private Integer configurationFailures;
    private Integer configurationSkips;
}
