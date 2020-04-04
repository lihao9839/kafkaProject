package com.lihao.thread.pattern.guardedsuspensionpattern;

import jdk.vm.ci.code.site.Call;

import java.util.concurrent.Callable;

public interface Blocker {

    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;

    void signalAfter(Callable<Boolean> stateOperation) throws Exception;

    void singal() throws InterruptedException;

    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;
}
