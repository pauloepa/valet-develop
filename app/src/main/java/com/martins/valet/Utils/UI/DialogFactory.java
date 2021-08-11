package com.martins.valet.Utils.UI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

/**
 * Created by policante on 7/14/16.
 */
public class DialogFactory {

    private DialogFactory() {
    }

    public static ProgressDialog createProgressDialogModal(Activity activity) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }

    public static ProgressDialog createProgressDialogIndeterminateModal(Activity activity) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }


    public static AlertDialog createAlertSuccessDialog(Activity activity, String message){
        return createAlertDialog(activity, "Sucesso", message, "Ok", null);
    }

    public static AlertDialog createAlertWarningDialog(Activity activity, String message, DialogInterface.OnClickListener okButtonListener){
        return createAlertDialog(activity, "Atenção", message, "Ok", okButtonListener);
    }

    public static AlertDialog createAlertWarningDialog(Activity activity, String message){
        return createAlertDialog(activity, "Atenção", message, "Ok", null);
    }

    public static AlertDialog createAlertDialog(Activity activity, String title, String message){
        return createAlertDialog(activity, title, message, "Ok", null);
    }

    public static AlertDialog createAlertDialog(Activity activity, String title, String message,
                                                String okButton,
                                                DialogInterface.OnClickListener okButtonListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(okButton, okButtonListener);
        return builder.create();
    }
//
//    public static AlertDialog createAlertDialogConfirm(Activity activity, int resIdTitle, int resIdMessage,
//                                                       DialogInterface.OnClickListener yesButtonListener,
//                                                       DialogInterface.OnClickListener noButtonListener) {
//        return createAlertDialogConfirm(activity, resIdTitle, resIdMessage, R.string.yes, yesButtonListener, R.string.no, noButtonListener);
//    }
//
    public static AlertDialog createAlertDialogConfirm(Activity activity,
                                                       String title,
                                                       String message,
                                                       String yesButton,
                                                       DialogInterface.OnClickListener yesButtonListener,
                                                       String noButton,
                                                       DialogInterface.OnClickListener noButtonListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(yesButton, yesButtonListener);
        builder.setNegativeButton(noButton, noButtonListener);

        return builder.create();

    }
}
