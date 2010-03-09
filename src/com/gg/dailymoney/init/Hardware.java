package com.gg.dailymoney.init;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Hardware {

    public Hardware() {
    }

    public static boolean exists(String hardware) {
        String hwList[];
        int i;
        try {
            hwList = getAllHw();
            i = 0;
            while (i < hwList.length) {
                if (i >= hwList.length) {
                    break;
                }
                if (hwList[i].equalsIgnoreCase(hardware)) {
                    return true;
                }
                i++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static String getHwId() {
        try {
            String hwList[] = getAllHw();
            if (hwList != null && hwList.length > 0) {
                return hwList[0];
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getHdId() {
        String hdSerialNum = null;
        try {
            String hdQuery = getHdQuery();
            hdSerialNum = hdId(hdQuery);
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return hdSerialNum;
    }

    private static String getHdQuery()
            throws IOException {
        Process process = Runtime.getRuntime().exec("RTool");
        InputStream stdoutStream = new BufferedInputStream(process.getInputStream());
        StringBuffer buffer = new StringBuffer();
        do {
            int c = stdoutStream.read();
            if (c != -1) {
                buffer.append((char) c);
            } else {
                String outputText = buffer.toString();
                stdoutStream.close();
                return outputText;
            }
        } while (true);
    }

    private static final String hdId(String data)
            throws ParseException {
        String hdId = null;
        String tokens[] = data.split("\n");
        if (tokens.length >= 2) {
            String line = tokens[1].trim();
            int hdIdPos = line.indexOf(":");
            if (hdIdPos > 0) {
                hdId = line.substring(hdIdPos + 1).trim();
            }
        }
        return hdId;
    }

    private static final String[] getAllHw()
            throws IOException {
        String hwIds[] = null;
        String data = getHwIdData();
        try {
            hwIds = allHwId(data);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return hwIds;
    }

    private static final String getHwIdData()
            throws IOException {
        Process process = Runtime.getRuntime().exec("ipconfig /all");
        InputStream stdoutStream = new BufferedInputStream(process.getInputStream());
        StringBuffer buffer = new StringBuffer();
        do {
            int c = stdoutStream.read();
            if (c != -1) {
                buffer.append((char) c);
            } else {
                String outputText = buffer.toString();
                stdoutStream.close();
                return outputText;
            }
        } while (true);
    }

    private static final String[] allHwId(String data)
            throws ParseException {
        Vector vlist = new Vector();
        StringTokenizer tokenizer = new StringTokenizer(data, "\n");
        String lastHwId = null;
        do {
            if (!tokenizer.hasMoreTokens()) {
                break;
            }
            String line = tokenizer.nextToken().trim();
            int hwIdPos = line.indexOf(":");
            if (hwIdPos > 0) {
                String hwIdCandidate = line.substring(hwIdPos + 1).trim();
                if (isValidHwId(hwIdCandidate)) {
                    lastHwId = hwIdCandidate;
                    vlist.add(lastHwId);
                }
            }
        } while (true);
        String retlist[] = new String[vlist.size()];
        for (int i = 0; i < vlist.size(); i++) {
            retlist[i] = (String) vlist.elementAt(i);
        }

        return retlist;
    }

    private static final boolean isValidHwId(String hwIdCandidate) {
        return hwIdCandidate.length() == 17;
    }
}
