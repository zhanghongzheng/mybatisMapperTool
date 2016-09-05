package com.hydra.tool.mysqldata;

import com.hydra.tool.system.Sys;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * tool Created by ZhengGong on 15/10/29.
 * PackageName com.hydra.tool.mysqldata
 * Description
 */
public class MysqlDataDict {
    public static void build() throws IOException {
        InputStreamReader i = new InputStreamReader(new FileInputStream("/Users/MLS/mls/sql/pay_naccount.sql"));
        BufferedReader b = new BufferedReader(i);
        String str;
        boolean isTable = false;
        while ((str = b.readLine()) != null) {
            String line = str.trim();
            String[] strs = line.split(" ");

            if (line.startsWith("CREATE TABLE")) {
                isTable = true;

                Sys.pl(strs[2].replace("`", ""));
                continue;
            }

            if (line.startsWith("(")) {
                isTable = false;
            }

            if (isTable && strs[0].startsWith("`") && strs[0].endsWith("`")) {
                String notNullString = "CAN NULL; ";
                if (line.contains("NOT NULL")) {
                    notNullString = "NOT NULL";
                }


                String comment = "";
                for (int j=0; j<strs.length; j++) {
                    if (strs[j].equals("COMMENT")) {
                        comment = strs[j + 1].replace("`", "");
                        break;
                    }
                }

                Sys.pl(strs[0].replace("`", "") + "; " + strs[1] + "; " + notNullString + "; " + comment);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        build();
    }
}
