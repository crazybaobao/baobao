package com.example.baobao.myapp;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by wangyun on 15/10/20.
 */
public class ParseXML {
    void parseXML(String xmlData) {
        try {
            XmlPullParserFactory factory = null;
            try {
                factory = XmlPullParserFactory.newInstance();
            } catch (XmlPullParserException e1) {
                e1.printStackTrace();
            }
            XmlPullParser xmlPullParser = null;
            try {
                xmlPullParser = factory.newPullParser();
            } catch (XmlPullParserException e1) {
                e1.printStackTrace();
            }
            try {
                xmlPullParser.setInput(new StringReader(xmlData));
            } catch (XmlPullParserException e1) {
                e1.printStackTrace();
            }
            int eventType = 0;
            try {
                eventType = xmlPullParser.getEventType();
            } catch (XmlPullParserException e1) {
                e1.printStackTrace();
            }
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
// 开始解析某个结点
                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodeName)) {
                            try {
                                id = xmlPullParser.nextText();
                            } catch (XmlPullParserException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if ("name".equals(nodeName)) {
                            try {
                                name = xmlPullParser.nextText();
                            } catch (XmlPullParserException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if ("version".equals(nodeName)) {
                            try {
                                version = xmlPullParser.nextText();
                            } catch (XmlPullParserException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        break;
                    }


// 完成解析某个结点
                    case XmlPullParser.END_TAG: {
                        if ("app".equals(nodeName)) {
                            Log.d("MainActivity", "id is " + id);
                            Log.d("MainActivity", "name is " + name);
                            Log.d("MainActivity", "version is " + version);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (
                Exception e
                )

        {
            e.printStackTrace();
        }
    }
}