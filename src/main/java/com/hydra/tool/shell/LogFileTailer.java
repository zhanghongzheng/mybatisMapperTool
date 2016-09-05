package com.hydra.tool.shell;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by ZhengGong on 15/5/14.
 * Description
 */
public class LogFileTailer extends AbstractReadLineExecutor {
    private long sampleInterval;

    private File logFile;
    private volatile boolean isStop = false;

    public LogFileTailer(File file, long sampleInterval) {
        this.logFile = file;
        this.sampleInterval = sampleInterval;
    }

    public void tail() {
        long filePointer;

        filePointer = this.logFile.length();

        try {
            RandomAccessFile file = new RandomAccessFile(logFile, "r");
            while (!isStop) {
                long fileLength = this.logFile.length();
                if (fileLength < filePointer) {
                    file = new RandomAccessFile(logFile, "r");
                    filePointer = this.logFile.length();
                }
                if (fileLength > filePointer) {
                    file.seek(filePointer - 1);
                    String line = file.readLine();
                    while (line != null) {
                        executeListener(line);
                        line = file.readLine();
                    }
                    filePointer = file.getFilePointer();
                }
                Thread.sleep(this.sampleInterval);
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        isStop = true;
    }
}
