package me.loki2302.results;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HandlerResultProcessor {
    boolean canProcess(Object result);
    void process(Object result, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
