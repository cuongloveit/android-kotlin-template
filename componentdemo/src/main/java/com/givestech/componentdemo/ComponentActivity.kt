package com.givestech.componentdemo

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.givestech.componentdemo.common.utils.removeShiftMode
import kotlinx.android.synthetic.main.activity_component.lineChart
import kotlinx.android.synthetic.main.activity_component.navigation

class ComponentActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_component)
    navigation.removeShiftMode()

    showLineChart()
  }

  private fun showLineChart() {
    val lineData = LineData(getDataSet(), getDataSet2())
    lineChart.data = lineData
    val description = Description()
    description.text = "test description"
    description.setPosition(200f, 300f)
    lineChart.description = description
    lineChart.setBorderColor(Color.BLUE)


    lineChart.invalidate()
  }

  fun getDataChart(): List<Entry> {

    return listOf(Entry(0f, 0f),
        Entry(1f, 1f, ContextCompat.getDrawable(this, R.drawable.ic_account_circle)),
        Entry(2f, 4f),
        Entry(3f, 5f),
        Entry(4f, 1f)

    )
  }

  fun getDataChart2(): List<Entry> {

    return listOf(Entry(0f, 2f),
        Entry(1f, 3f, ContextCompat.getDrawable(this, R.drawable.ic_account_circle)),
        Entry(2f, 4f),
        Entry(3f, 2f),
        Entry(4f, 6f)

    )
  }

  fun getDataSet(): LineDataSet {
    return LineDataSet(getDataChart(), "Line 1").apply {
      setCircleColor(Color.CYAN)
      color = Color.CYAN
    }

  }

  fun getDataSet2(): LineDataSet {
    return LineDataSet(getDataChart2(), "Line 2").apply {
      setCircleColor(Color.GREEN)
      color = Color.GREEN
    }

  }

}