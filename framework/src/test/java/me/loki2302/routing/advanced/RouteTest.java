package me.loki2302.routing.advanced;

import me.loki2302.routing.RouteMatchResult;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouteTest {
    @Test
    public void canMatch() {
        Route r = new Route(new AnyMethodMatcher(),
                Collections.<PartMatcher>singletonList(
                        new StaticPartMatcher("hello")));
        RouteMatchResult result = r.match(RequestMethod.GET, "/hello");
        assertTrue(result.match);
        assertTrue(result.context.isEmpty());
    }

    @Test
    public void canNotMatchWhenSegmentDoesNotMatch() {
        Route r = new Route(new AnyMethodMatcher(),
                Collections.<PartMatcher>singletonList(
                        new StaticPartMatcher("hello")));
        RouteMatchResult result = r.match(RequestMethod.GET, "/bye");
        assertFalse(result.match);
    }

    @Test
    public void canNotMatchWhenPathIsLongerThenExpected() {
        Route r = new Route(new AnyMethodMatcher(),
                Collections.<PartMatcher>singletonList(
                        new StaticPartMatcher("hello")));
        RouteMatchResult result = r.match(RequestMethod.GET, "/hello/there");
        assertFalse(result.match);
    }
}
