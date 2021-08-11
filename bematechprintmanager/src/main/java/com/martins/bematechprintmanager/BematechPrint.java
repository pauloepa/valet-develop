package com.martins.bematechprintmanager;

import com.martins.bematechprintmanager.constants.BematechCode;

/**
 * Created by policante on 7/14/16.
 */
public class BematechPrint {

    StringBuilder stringBuilder;

    public BematechPrint() {
        stringBuilder = new StringBuilder();
        setupDefault();
    }

    public void setupDefault() {
        stringBuilder.append(BematechCode.DEFAULT);
        stringBuilder.append(BematechCode.UTF8);
    }

    public BematechPrint withText(String message) {
        stringBuilder.append(message);
        return this;
    }

    public BematechPrint withTextBold(String message) {
        stringBuilder.append(BematechCode.BOLD_ON);
        stringBuilder.append(message);
        stringBuilder.append(BematechCode.BOLD_OFF);
        return this;
    }

    public BematechPrint withTextItalic(String message) {
        stringBuilder.append(BematechCode.ITALIC_ON);
        stringBuilder.append(message);
        stringBuilder.append(BematechCode.ITALIC_OFF);
        return this;
    }

    public BematechPrint withTextUnderline(String message) {
        stringBuilder.append(BematechCode.UNDERLINE_ON);
        stringBuilder.append(message);
        stringBuilder.append(BematechCode.UNDERLINE_OFF);
        return this;
    }

    public BematechPrint newLine() {
        stringBuilder.append(BematechCode.NEW_LINE);
        return this;
    }

    public BematechPrint withPartilCut() {
        newLine();
        stringBuilder.append(BematechCode.PARTIAL_CUT);
        return this;
    }

    public BematechPrint withFullCut() {
        newLine().newLine();
        stringBuilder.append(BematechCode.FULL_CUT);
        return this;
    }

    public BematechPrint alignLeft() {
        stringBuilder.append(BematechCode.ALIGN_LEFT);
        return this;
    }

    public BematechPrint alignCenter() {
        stringBuilder.append(BematechCode.ALIGN_CENTER);
        return this;
    }

    public BematechPrint alignRight() {
        stringBuilder.append(BematechCode.ALIGN_RIGHT);
        return this;
    }

    public BematechPrint sizeLarge() {
        stringBuilder.append(BematechCode.SIZE_LARGE);
        return this;
    }

    public BematechPrint sizeSmall() {
        stringBuilder.append(BematechCode.SIZE_SMALL);
        return this;
    }

    public BematechPrint withQRCode(String data) {

        int dataLength = data.length();
        char[] model = new char[]{20, 38, 61, 90, 122, 154, 178, 221, 262, 311, 366, 419, 483, 528, 600,
                656, 734, 816, 909, 970, 1035, 1134, 1248, 1326, 1451, 1542, 1637, 1732, 1839,
                1994, 2113, 2238, 2369, 2506, 2632, 2780, 2894, 3054, 3220, 3391};

        boolean flag = false;
        int i;
        for (i = 0; i < model.length; i++) {
            if (model[i] >= dataLength){
                flag = true;
                break;
            }
        }

        if (!flag){
            throw new IllegalArgumentException("O numero de caracteres da mensagem é maior que a capacidade do QRCode");
        }

        stringBuilder.append(BematechCode.GS);
        stringBuilder.append("kQ");

        stringBuilder.append((char) 1);                   //n1
        stringBuilder.append((char) (11 << 1));           //n2
        stringBuilder.append((char) (i + 1));             //n3
        stringBuilder.append((char) 1);                   //n4

        stringBuilder.append((char) (dataLength % 256));  //n5
        stringBuilder.append((char) (dataLength / 256));  //n6

        stringBuilder.append(data);

        return this;
    }

    public String toString() {
        return stringBuilder.toString();
    }
}
