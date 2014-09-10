package me.loki2302;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import me.loki2302.framework.results.HandlerResultProcessorRegistry;
import me.loki2302.framework.results.is.InputStreamHandlerResultProcessor;
import me.loki2302.framework.results.mav.ModelAndViewHandlerResultProcessor;
import me.loki2302.framework.handling.RouteHandler;
import me.loki2302.framework.routing.Router;

import static me.loki2302.framework.routing.RouterDSL.*;

public class DummyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DummyServlet.class).asEagerSingleton();

        Router<Class<? extends RouteHandler>> router = new Router<Class<? extends RouteHandler>>()
                .addRoute(route(c("")), IndexRouteHandler.class)
                .addRoute(route(c("page"), v("id")), PageRouteHandler.class)
                .addRoute(route(c("css")), ResourceRouteHandler.class);

        bind(new TypeLiteral<Router<Class<? extends RouteHandler>>>() {}).toInstance(router);

        bind(ModelAndViewHandlerResultProcessor.class).asEagerSingleton();
        bind(InputStreamHandlerResultProcessor.class).asEagerSingleton();
        bind(HandlerResultProcessorRegistry.class).asEagerSingleton();
    }
}
