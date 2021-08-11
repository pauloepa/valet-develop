package com.martins.bematechprintmanager.constants;

/**
 * Created by policante on 7/14/16.
 */
public class BematechCode {

    public static final char ESC = 27;
    public static final char GS = 29;
    public static final char NEW_LINE = 10;

    public static final String DEFAULT = String.valueOf(ESC) + (char) 64;
    public static final String UTF8 = String.valueOf(ESC) + (char) 116 + (char) 56;

    public static final String ALIGN_LEFT = String.valueOf(ESC) + (char) 97 + (char) 48;
    public static final String ALIGN_CENTER = String.valueOf(ESC) + (char) 97 + (char) 49;
    public static final String ALIGN_RIGHT = String.valueOf(ESC) + (char) 97 + (char) 50;

    public static final String FULL_CUT = String.valueOf(ESC) + (char) 119;
    public static final String PARTIAL_CUT = String.valueOf(ESC) + (char) 109;

    public static final String ITALIC_ON = String.valueOf(ESC) + (char) 52;
    public static final String ITALIC_OFF = String.valueOf(ESC) + (char) 53;

    public static final String BOLD_ON = String.valueOf(ESC) + (char) 69;
    public static final String BOLD_OFF = String.valueOf(ESC) + (char) 70;

    public static final String UNDERLINE_ON = String.valueOf(ESC) + (char) 45 + (char) 48;
    public static final String UNDERLINE_OFF = String.valueOf(ESC) + (char) 45 + (char) 49;

    public static final String SIZE_SMALL = String.valueOf(ESC) + (char) 100 + (char) 48;
    public static final String SIZE_LARGE = String.valueOf(ESC) + (char) 100 + (char) 49;
}
