package com.android.datetimepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.datetimepickerlibrary.getDateTimeWithDialog
import com.android.datetimepickerlibrary.getDateWithDialog
import com.android.datetimepickerlibrary.getTimeWithDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDateTimeWithDialog(this, { date ->
            Toast.makeText(this, date.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}