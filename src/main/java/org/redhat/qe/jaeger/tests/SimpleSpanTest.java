package org.redhat.qe.jaeger.tests;

import java.util.List;

import org.hawkular.client.core.ClientResponse;
import org.redhat.qe.jaeger.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.uber.jaeger.rest.model.Result;
import com.uber.jaeger.rest.model.Trace;

import io.opentracing.Span;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
public class SimpleSpanTest extends BaseTest {

    @Test
    public void singleSpanTest() throws InterruptedException {
        Span span = tracer().buildSpan("simple-span").start();
        span.setTag("testType", "singleSpanTest");
        long randomLong = Utils.nextLong(100000L);
        span.setTag("randomLong", randomLong);
        span.finish();
        waitForFlush();

        //Validation
        ClientResponse<Result<Trace>> response = server().trace().list(
                getCriteria(testData.getServiceName(), testStartTime));
        validateResponse(response);
        List<Trace> traces = response.getEntity().getData();
        Assert.assertEquals(traces.size(), 1);
        List<com.uber.jaeger.rest.model.Span> spans = traces.get(0).getSpans();
        Assert.assertEquals(spans.size(), 1);
        Assert.assertEquals(spans.get(0).getOperationName(), "simple-span");
        validateTag(spans.get(0).getTags(), "testType", "singleSpanTest");
        validateTag(spans.get(0).getTags(), "randomLong", randomLong);
    }

    @Test
    public void spanWithChildTest() throws InterruptedException {
        long randomSleep = Utils.nextLong(1000 * 10L);
        Span parentSpan = tracer().buildSpan("parent-span").start();
        parentSpan.setTag("sentFrom", "automation code");
        Span childSpan = tracer().buildSpan("child-span").asChildOf(parentSpan).start();
        Thread.sleep(randomSleep);
        childSpan.finish();
        Thread.sleep(50L);
        parentSpan.finish();
        waitForFlush();

        //Validation
        ClientResponse<Result<Trace>> response = server().trace().list(
                getCriteria(testData.getServiceName(), testStartTime));
        validateResponse(response);
        List<Trace> traces = response.getEntity().getData();
        Assert.assertEquals(traces.size(), 1);
        List<com.uber.jaeger.rest.model.Span> spans = traces.get(0).getSpans();
        Assert.assertEquals(spans.size(), 2);
        com.uber.jaeger.rest.model.Span span1 = getSpan(spans, "parent-span");
        Assert.assertNotNull(span1);
        validateTag(span1.getTags(), "sentFrom", "automation code");
        com.uber.jaeger.rest.model.Span span2 = getSpan(spans, "child-span");
        Assert.assertNotNull(span2);
    }
}
