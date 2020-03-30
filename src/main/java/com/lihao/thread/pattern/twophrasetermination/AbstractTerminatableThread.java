package com.lihao.thread.pattern.twophrasetermination;

import com.sun.tools.sjavac.server.Terminable;

public class AbstractTerminatableThread extends Thread implements Terminable {

    public final TerminationToken terminationToken;

}
