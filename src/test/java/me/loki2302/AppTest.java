package me.loki2302;

import me.loki2302.framework.routing.Route;
import me.loki2302.framework.routing.RouteResolutionResult;
import me.loki2302.framework.routing.Router;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static me.loki2302.framework.routing.RouterDSL.*;
import static org.junit.Assert.*;

public class AppTest {
    private final static Router<String> router = new Router<String>()
        .addRoute(route(c("api"), c("posts"), v("id"), c("comments")), "comments")
        .addRoute(route(c("api"), c("posts"), v("id")), "post");

    @Test
    public void canResolveRouteByUrl() {
        RouteResolutionResult r = router.resolve("/api/posts/123");
        assertTrue(r.resolved);
        assertEquals("post", r.handler);
        assertEquals(1, r.context.size());
        assertEquals("123", r.context.get("id"));

        r = router.resolve("/api/posts/123/comments");
        assertEquals("comments", r.handler);
        assertTrue(r.resolved);
        assertEquals(1, r.context.size());
        assertEquals("123", r.context.get("id"));

        r = router.resolve("/api/posts");
        assertFalse(r.resolved);
    }

    @Test
    public void canBuildUrlFromRoute() {
        Route route = route(c("api"), c("posts"), v("id"), c("comments"));
        Map<String, Object> context = Collections.<String, Object>singletonMap("id", 123);
        String url = route.build(context);
        assertEquals("api/posts/123/comments/", url);
    }
}
