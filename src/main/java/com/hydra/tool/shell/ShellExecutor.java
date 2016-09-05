package com.hydra.tool.shell;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ZhengGong on 15/5/16.
 * Description 执行shell脚本
 */
public class ShellExecutor extends AbstractReadLineExecutor {

    public void exec(String shellPath) {
        try {
            Process process = Runtime.getRuntime().exec(shellPath);
            process.waitFor();

            String str;

            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedInputStream = new BufferedReader(inputStreamReader);

            while ((str = bufferedInputStream.readLine()) != null) {
                executeListener(str);
            }

            inputStream.close();
            inputStreamReader.close();
            bufferedInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
