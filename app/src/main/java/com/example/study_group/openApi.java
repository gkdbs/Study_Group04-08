package com.example.study_group;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class openApi extends AppCompatActivity {
    ArrayList<String> items = new ArrayList<String>();
    ListView listView;
    ArrayAdapter adapter;

    String APIkey = "5541627573676b6438304e5878417a";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_api);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
    }


    public void study_find2(View view) {
        Thread t = new Thread() {
            @Override
            public void run() {
                Date date = new Date();
                date.setTime((date.getTime()-(1000*60*60*24)));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String dateStr="20181112";
                String address = "http://openapi.seoul.go.kr:8088/5541627573676b6438304e5878417a/xml/InstutBuildInfo/1/5/";

                try {

                    URL url = new URL(address);
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(isr);

                    int eventype = xpp.getEventType();

                    StringBuffer buffer = null;
                    while (eventype != XmlPullParser.END_DOCUMENT) {

                        switch (eventype) {
                            case XmlPullParser.START_DOCUMENT:
                                //????????? Thread??? UI????????? ??????!!
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(openApi.this, "?????? ??????", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;

                            case XmlPullParser.START_TAG:
                                String tagName = xpp.getName();
                                if (tagName.equals("row")) {
                                    buffer = new StringBuffer();
                                } else if (tagName.equals("ID")) {
                                    xpp.next();
                                    buffer.append("??????:" + xpp.getText() + "\n");

                                } else if (tagName.equals("NM")) {
                                    buffer.append("????????????:");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");

                                } else if (tagName.equals("ADDR_OLD")) {
                                    buffer.append("???????????????:");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");

                                } else if (tagName.equals("ADDR")) {
                                    buffer.append("???????????????:");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");

                                }else if (tagName.equals("STATE")) {
                                    buffer.append("????????????:");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");
                                }
                                else if (tagName.equals("TEL")) {
                                    buffer.append("????????????:");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");
                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                String tagName2 = xpp.getName();
                                if (tagName2.equals("row")) {
                                    //???????????? ??????1?????? ??????...
                                    //??? ????????? StringBuffer??? append ???
                                    //???????????? ??????????????? ???????????? ArrayList??? ??????
                                    items.add(buffer.toString());
                                    //?????? ????????? ?????? Thread??? ??? ??? ??????!!
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.notifyDataSetChanged();
                                        }
                                    });

                                }
                                break;

                        }//switch

                        eventype = xpp.next();
                    }//while


                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        };
        t.start();
    }
}