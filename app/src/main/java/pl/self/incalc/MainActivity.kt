package pl.self.incalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

import java.math.BigDecimal

import pl.self.incalc.service.CalcService

class MainActivity : AppCompatActivity() {

    private var amountField: EditText? = null
    private var percentField: EditText? = null
    private var timeField: EditText? = null
    private var daysOrMonthsField: Switch? = null
    private var resultField: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFields()
    }

    fun handleCalculate(view: View) {
        if (!verifyField(amountField) || !verifyField(percentField) || !verifyField(timeField)) {
            Toast.makeText(this, "Amount, percent,time must be provided", Toast.LENGTH_LONG).show()
            return
        }
        val amount = parse(amountField)
        val percent = parse(percentField)
        val time = parse(timeField)


        val service = CalcService.get()
        val result = service.calc(amount, percent.toFloat(), time.toInt(), daysOrMonthsField!!.isChecked)
        resultField!!.text = result.toPlainString()
    }

    private fun initFields() {
        amountField = findViewById(R.id.amount)
        percentField = findViewById(R.id.percent)
        timeField = findViewById(R.id.time)
        daysOrMonthsField = findViewById(R.id.daysOrMonths)
        resultField = findViewById(R.id.result)
    }

    private fun parse(text: EditText?): BigDecimal {
        val str = text!!.text.toString()
        return BigDecimal.valueOf(java.lang.Float.parseFloat(str).toDouble())
    }

    private fun verifyField(text: EditText?): Boolean {
        return !nullOrEmpty(text?.text?.toString())
    }


    private fun nullOrEmpty(str: String?): Boolean {
        return str == null || str?.length == 0
    }
}
