package com.martins.valet.Utils.Helpers;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by policante on 7/6/16.
 */
public class EditTextWatcherHelper {

    public static TextWatcher timeWatcher(final EditText time){
        return new TextWatcher() {
            private String current = "";
            private String hhmmss = "HHMMSS";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)){
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 6){
                        clean = clean + hhmmss.substring(clean.length());
                    }else{

                        int hour  = Integer.parseInt(clean.substring(0,2));
                        int minute  = Integer.parseInt(clean.substring(2,4));
                        int second = Integer.parseInt(clean.substring(4,6));

                        if (second > 59 || second < 0){
                            second = 0;
                        }

                        if (minute > 59 || minute < 0){
                            minute = 0;
                        }

                        if (hour > 23 || hour < 0){
                            hour = 0;
                        }

                        clean = String.format("%02d%02d%02d",hour, minute, second);
                    }

                    clean = String.format("%s:%s:%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 6));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    time.setText(current);
                    time.setSelection(sel < current.length() ? sel : current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public static TextWatcher dateWatcher(final EditText date){
        return new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    date.setText(current);
                    date.setSelection(sel < current.length() ? sel : current.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public static TextWatcher nextFocus(final View viewPrevFocus, final EditText editText, final View viewNextFocus) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.clearFocus();
                if (count == 1) {
                    if (viewNextFocus != null) {
                        viewNextFocus.requestFocus();
                        if (viewNextFocus instanceof EditText){
                            ((EditText) viewNextFocus).setCursorVisible(true);
                        }
                    }
                }else{
                    if (viewPrevFocus != null){
                        viewPrevFocus.requestFocus();
                        if (viewPrevFocus instanceof EditText) {
                            ((EditText) viewPrevFocus).setCursorVisible(true);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

}
