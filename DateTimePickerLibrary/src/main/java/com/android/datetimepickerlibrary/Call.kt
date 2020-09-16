package com.android.datetimepickerlibrary

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.date_picker_dialog.view.*
import kotlinx.android.synthetic.main.date_time_picker_dialog.view.*
import kotlinx.android.synthetic.main.time_picker_dialog.view.*
import java.util.*

fun getDateWithDialog(
    context: Context,
    onListen: (Date) -> Unit,
    minDate: Date? = null,
    maxDate: Date? = null
) {
    /**
     * I define the view which will be used in the alertDialog
     */

    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    val dateView =
        layoutInflater.inflate(R.layout.date_picker_dialog, null, false)
    /**
     * I declare the AlertDialog
     */
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        dateView.date_picker.setOnDateChangedListener { datePicker, i, i2, i3 ->

        }
    }
    if (maxDate != null) {
        dateView.date_picker.maxDate = maxDate.time
    }

    if (minDate != null) {
        dateView.date_picker.minDate = minDate.time
    }

    AlertDialog.Builder(context)
        .setView(dateView)
        .setNegativeButton(context.getString(R.string.dialog_cancel), null)
        .setPositiveButton(context.getString(R.string.dialog_ok)) { dialogInterface, i ->
            /**
             * we fetch the Date
             */
            val calendar = Calendar.getInstance()
            calendar.set(
                dateView.date_picker.year,
                dateView.date_picker.month,
                dateView.date_picker.dayOfMonth
            )
            onListen(calendar.time)
        }
        .create()
        .show()
}

fun getTimeWithDialog(
    context: Context,
    onListen: (Int, Int) -> Unit
) {
    /**
     * I define the view which will be used in the alertDialog
     */

    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    val timeView =
        layoutInflater.inflate(R.layout.time_picker_dialog, null, false)

    AlertDialog.Builder(context)
        .setView(timeView)
        .setNegativeButton(context.getString(R.string.dialog_cancel), null)
        .setPositiveButton(context.getString(R.string.dialog_ok)) { dialogInterface, i ->
            /**
             * we fetch the Date
             */
            val hour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timeView.time_picker.hour
            } else {
                timeView.time_picker.currentHour
            }
            val minutes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timeView.time_picker.minute
            } else {
                timeView.time_picker.currentMinute
            }
            onListen(hour, minutes)
        }
        .create()
        .show()
}

fun getDateTimeWithDialog(
    context: Context,
    onListen: (Date) -> Unit,
    minDate: Date? = null,
    maxDate: Date? = null
) {
    /**
     * I define the view which will be used in the alertDialog
     */

    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    val dateTimeView =
        layoutInflater.inflate(R.layout.date_time_picker_dialog, null, false)
    /**
     * I declare the AlertDialog
     */

//    dateTimeView.date_time_date_picker.setOnDateChangeListener { calendarView, i, i2, i3 ->
//
//    }
//
    if (maxDate != null) {
        dateTimeView.date_time_date_picker.maxDate = maxDate.time
    }

    if (minDate != null) {
        dateTimeView.date_time_date_picker.minDate = minDate.time
    }

    dateTimeView.btn_previous.setOnClickListener {
        dateTimeView.date_time_date_picker.visibility = View.VISIBLE
        dateTimeView.btn_next.visibility = View.VISIBLE
        dateTimeView.btn_previous.visibility = View.GONE
        dateTimeView.btn_ok.visibility = View.GONE
        dateTimeView.date_time_time_picker.visibility = View.GONE
    }

    dateTimeView.btn_next.setOnClickListener {
        dateTimeView.date_time_date_picker.visibility = View.GONE
        dateTimeView.btn_next.visibility = View.GONE
        dateTimeView.btn_previous.visibility = View.VISIBLE
        dateTimeView.btn_ok.visibility = View.VISIBLE
        dateTimeView.date_time_time_picker.visibility = View.VISIBLE
    }

    val alert = AlertDialog.Builder(context)
        .setView(dateTimeView)
        .create()

    dateTimeView.btn_cancel.setOnClickListener {
        alert.dismiss()
    }

    dateTimeView.btn_ok.setOnClickListener {
        val calendar = Calendar.getInstance()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            calendar.set(
                dateTimeView.date_time_date_picker.year,
                dateTimeView.date_time_date_picker.month,
                dateTimeView.date_time_date_picker.dayOfMonth,
                dateTimeView.date_time_time_picker.hour,
                dateTimeView.date_time_time_picker.minute
            )
        }else {
            calendar.set(
                dateTimeView.date_time_date_picker.year,
                dateTimeView.date_time_date_picker.month,
                dateTimeView.date_time_date_picker.dayOfMonth,
                dateTimeView.date_time_time_picker.currentHour,
                dateTimeView.date_time_time_picker.currentMinute
            )
        }
        onListen(calendar.time)
        alert.dismiss()
    }

    alert.show()
}