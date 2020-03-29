package com.lihao.thread.pattern.threadspecificstorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ThreadLocal 内存泄漏示例
 */
public class MemoryLeakingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final static ThreadLocal<Counter> TL_COUNTER = new ThreadLocal<Counter>(){
        @Override
        protected Counter initialValue(){
            return new Counter();
        }
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pwr = resp.getWriter();
        pwr.write(String.valueOf(TL_COUNTER.get().getAndIncrement()));
        pwr.close();
    }
}

class Counter{

    private int i = 1;

    public int getAndIncrement(){
        return (i++);
    }
}