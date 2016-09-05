package com.hydra.tool.shell;


import com.hydra.tool.shell.listener.IReadLineFileListener;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * Created by ZhengGong on 15/5/16.
 * Description
 */
public abstract class AbstractReadLineExecutor {
    private CopyOnWriteArraySet<IReadLineFileListener> set = new CopyOnWriteArraySet<IReadLineFileListener>();

    public void addListener(IReadLineFileListener readLineListener) {
        set.add(readLineListener);
    }

    public void removeListener(IReadLineFileListener readLineListener) {
        set.remove(readLineListener);
    }

    public Set<IReadLineFileListener> getListenerSet() {
        return new HashSet<IReadLineFileListener>(set);
    }

    protected void executeListener(String line) throws UnsupportedEncodingException {
        for (IReadLineFileListener lineListener : set) {
            lineListener.readLine( new String(line.getBytes("8859_1"), "utf-8")
            );
        }
    }
}
