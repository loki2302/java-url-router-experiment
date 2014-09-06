package me.loki2302.routing;

import java.util.HashMap;
import java.util.Map;

public class Route {
    private final RouteSegment[] routeSegments;

    public Route(RouteSegment... routeSegments) {
        this.routeSegments = routeSegments;
    }

    public RouteMatchResult match(String url) {
        if(url.startsWith("/")) {
            url = url.substring(1);
        }

        String[] urlSegments = url.split("/");
        if(urlSegments.length != routeSegments.length) {
            return RouteMatchResult.noMatch(this);
        }

        Map<String, Object> context = new HashMap<String, Object>();
        for(int i = 0; i < routeSegments.length; ++i) {
            RouteSegment routeSegment = routeSegments[i];
            String urlSegment = urlSegments[i];
            SegmentMatchResult matchResult = routeSegment.match(urlSegment);
            if(!matchResult.match) {
                return RouteMatchResult.noMatch(this);
            }

            context.putAll(matchResult.context);
        }

        return RouteMatchResult.match(this, context);
    }

    public String build(Map<String, Object> context) {
        StringBuilder sb = new StringBuilder();
        for(RouteSegment routeSegment : routeSegments) {
            String value = routeSegment.getValue(context);
            sb.append(value);
            sb.append("/");
        }

        return sb.toString();
    }
}