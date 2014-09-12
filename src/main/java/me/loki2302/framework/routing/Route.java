package me.loki2302.framework.routing;

import java.util.Map;

public interface Route {
    RouteMatchResult match(String url);

    String build(Map<String, Object> context);
}
