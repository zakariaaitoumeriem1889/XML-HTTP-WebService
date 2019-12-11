package com.example.demo_xml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            parcoursDonnee();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }

    private void parcoursDonnee() throws IOException, XmlPullParserException {
     StringBuilder strb = new StringBuilder();
        Resources res = getResources();
        XmlResourceParser xp=res.getXml(R.xml.donnees);
        xp.next();
        int evt =xp.getEventType();
        while (evt != XmlPullParser.END_DOCUMENT) {
            if(evt == XmlPullParser.START_DOCUMENT) {
                strb.append("Start document");
            } else if(evt == XmlPullParser.END_DOCUMENT) {
                strb.append("End document");
            } else if(evt == XmlPullParser.START_TAG) {
                strb.append("Start tag "+xp.getName());
            } else if(evt == XmlPullParser.END_TAG) {
                strb.append("End tag "+xp.getName());
            } else if(evt == XmlPullParser.TEXT) {
                strb.append("Text "+xp.getText());
            }
            evt = xp.next();
        }
        EditText ed = (EditText)findViewById(R.id.text1);
        ed.setText(strb.toString());


    }
}
