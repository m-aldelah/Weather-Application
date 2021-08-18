package com.app.truetemp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.os.AsyncTask
import org.json.JSONObject
import java.net.URL
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    val CITY: String = "Dearborn"
    val API: String = "23408758b6f96d1e9dacebfd604a7240"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherTask().execute()
    }
    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {

            super.onPreExecute()

        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/onecall?lat=42.32226&lon=-83.17631&exclude=hourly,alerts,minutely&appid=$API&units=imperial").readText(
                        Charsets.UTF_8
                )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)

                val current = jsonObj.getJSONObject("current")

                val weather = current.getJSONArray("weather").getJSONObject(0)
                val temp = current.getString("temp")
                val weatherDescription = weather.getString("main")
                val icon = weather.getString("icon")

                //Updating UI
                findViewById<TextView>(R.id.weather).text = weatherDescription
                //Calc current temp
                findViewById<TextView>(R.id.temperature).text = temp.substringBefore(".") + "°F"
                changeImage(icon)

                val dateList = makeDate()
                //Calc 7 day temp
                val jsonObjArray = JSONObject(result).getJSONArray("daily")
                //First day
                val firstDay =  jsonObjArray.getJSONObject(0)
                val weather1 = firstDay.getJSONArray("weather").getJSONObject(0)
                val firstIcon = weather1.getString("icon")
                val firstTemp = firstDay.getJSONObject("temp").getString("max")
                findViewById<TextView>(R.id.dayOneTemp).text = firstTemp.substringBefore(".") + "°F"
                changeImageMini(firstIcon,0)
                findViewById<TextView>(R.id.dayOneDate).text = dateList[0]
                //Second day
                val day2 =  jsonObjArray.getJSONObject(1)
                val weather2 = day2.getJSONArray("weather").getJSONObject(0)
                val icon2 = weather2.getString("icon")
                val temp2 = day2.getJSONObject("temp").getString("max")
                findViewById<TextView>(R.id.dayTwoTemp).text = temp2.substringBefore(".") + "°F"
                changeImageMini(icon2,1)
                findViewById<TextView>(R.id.dayTwoDate).text = dateList[1]
                //Third day
                val day3 =  jsonObjArray.getJSONObject(2)
                val weather3 = day3.getJSONArray("weather").getJSONObject(0)
                val icon3 = weather3.getString("icon")
                val temp3 = day3.getJSONObject("temp").getString("max")
                findViewById<TextView>(R.id.dayThreeTemp).text = temp3.substringBefore(".") + "°F"
                changeImageMini(icon3,2)
                findViewById<TextView>(R.id.dayThreeDate).text = dateList[2]
                //Fourth day
                val day4 =  jsonObjArray.getJSONObject(3)
                val weather4 = day4.getJSONArray("weather").getJSONObject(0)
                val icon4 = weather4.getString("icon")
                val temp4 = day4.getJSONObject("temp").getString("max")
                findViewById<TextView>(R.id.dayFourTemp).text = temp4.substringBefore(".") + "°F"
                changeImageMini(icon4,3)
                findViewById<TextView>(R.id.dayFourDate).text = dateList[3]
                //Fifth day
                val day5 =  jsonObjArray.getJSONObject(4)
                val weather5 = day5.getJSONArray("weather").getJSONObject(0)
                val icon5 = weather5.getString("icon")
                val temp5 = day5.getJSONObject("temp").getString("max")
                findViewById<TextView>(R.id.dayFiveTemp).text = temp5.substringBefore(".") + "°F"
                changeImageMini(icon5,4)
                findViewById<TextView>(R.id.dayFiveDate).text = dateList[4]
                //Sixth day
                val day6 =  jsonObjArray.getJSONObject(5)
                val weather6 = day6.getJSONArray("weather").getJSONObject(0)
                val icon6 = weather6.getString("icon")
                val temp6 = day6.getJSONObject("temp").getString("max")
                findViewById<TextView>(R.id.daySixTemp).text = temp6.substringBefore(".") + "°F"
                changeImageMini(icon6,5)
                findViewById<TextView>(R.id.daySixDate).text = dateList[5]
                //Seventh day
                val day7 =  jsonObjArray.getJSONObject(6)
                val weather7 = day7.getJSONArray("weather").getJSONObject(0)
                val icon7 = weather7.getString("icon")
                val temp7 = day7.getJSONObject("temp").getString("max")
                findViewById<TextView>(R.id.daySevenTemp).text = temp7.substringBefore(".") + "°F"
                changeImageMini(icon7,6)
                findViewById<TextView>(R.id.daySevenDate).text = dateList[6]
            } catch (e: Exception) { }
        }

        private fun changeImageMini(icon: String,index:Int) {
            if(icon == "01d" || icon == "02d" || icon == "01n" || icon == "02n"){
                when (index) {
                    1 -> findViewById<ImageView>(R.id.dayOneIcon).setImageResource(R.drawable.sun)
                    2 -> findViewById<ImageView>(R.id.dayTwoIcon).setImageResource(R.drawable.sun)
                    3 -> findViewById<ImageView>(R.id.dayThreeIcon).setImageResource(R.drawable.sun)
                    4 -> findViewById<ImageView>(R.id.dayFourIcon).setImageResource(R.drawable.sun)
                    5 -> findViewById<ImageView>(R.id.dayFiveIcon).setImageResource(R.drawable.sun)
                    6 -> findViewById<ImageView>(R.id.daySixIcon).setImageResource(R.drawable.sun)
                    7 -> findViewById<ImageView>(R.id.daySevenIcon).setImageResource(R.drawable.sun)
                }
            }
            else if(icon == "09d" || icon == "10d" || icon == "09n" || icon == "10n") {
                when (index) {
                    1 -> findViewById<ImageView>(R.id.dayOneIcon).setImageResource(R.drawable.rain)
                    2 -> findViewById<ImageView>(R.id.dayTwoIcon).setImageResource(R.drawable.rain)
                    3 -> findViewById<ImageView>(R.id.dayThreeIcon).setImageResource(R.drawable.rain)
                    4 -> findViewById<ImageView>(R.id.dayFourIcon).setImageResource(R.drawable.rain)
                    5 -> findViewById<ImageView>(R.id.dayFiveIcon).setImageResource(R.drawable.rain)
                    6 -> findViewById<ImageView>(R.id.daySixIcon).setImageResource(R.drawable.rain)
                    7 -> findViewById<ImageView>(R.id.daySevenIcon).setImageResource(R.drawable.rain)
                }
            }
            else if(icon == "11d" || icon == "11n") {
                when (index) {
                    1 -> findViewById<ImageView>(R.id.dayOneIcon).setImageResource(R.drawable.storm)
                    2 -> findViewById<ImageView>(R.id.dayTwoIcon).setImageResource(R.drawable.storm)
                    3 -> findViewById<ImageView>(R.id.dayThreeIcon).setImageResource(R.drawable.storm)
                    4 -> findViewById<ImageView>(R.id.dayFourIcon).setImageResource(R.drawable.storm)
                    5 -> findViewById<ImageView>(R.id.dayFiveIcon).setImageResource(R.drawable.storm)
                    6 -> findViewById<ImageView>(R.id.daySixIcon).setImageResource(R.drawable.storm)
                    7 -> findViewById<ImageView>(R.id.daySevenIcon).setImageResource(R.drawable.storm)
                }
            }
            else{
                when (index) {
                    1 -> findViewById<ImageView>(R.id.dayOneIcon).setImageResource(R.drawable.cloud)
                    2 -> findViewById<ImageView>(R.id.dayTwoIcon).setImageResource(R.drawable.cloud)
                    3 -> findViewById<ImageView>(R.id.dayThreeIcon).setImageResource(R.drawable.cloud)
                    4 -> findViewById<ImageView>(R.id.dayFourIcon).setImageResource(R.drawable.cloud)
                    5 -> findViewById<ImageView>(R.id.dayFiveIcon).setImageResource(R.drawable.cloud)
                    6 -> findViewById<ImageView>(R.id.daySixIcon).setImageResource(R.drawable.cloud)
                    7 -> findViewById<ImageView>(R.id.daySevenIcon).setImageResource(R.drawable.cloud)
                }
            }

        }

        private fun changeImage(icon: String) {
            val background = findViewById<ImageView>(R.id.background)
            val iconUI = findViewById<ImageView>(R.id.icon)
            //Set BG
            if(icon == "01d" || icon == "02d" || icon == "01n" || icon == "02n"){
                if(icon.get(2) == 'n') {
                    background.setImageResource(R.drawable.nightbg)
                    iconUI.setImageResource(R.drawable.moon)
                }
                else {
                    background.setImageResource(R.drawable.sunnybg)
                    iconUI.setImageResource(R.drawable.sun)
                }
            }
            else if(icon == "09d" || icon == "10d" || icon == "09n" || icon == "10n") {
                background.setImageResource(R.drawable.rainybg)
                iconUI.setImageResource(R.drawable.rainy)
            }
            else if(icon == "11d" || icon == "11n") {
                background.setImageResource(R.drawable.stormbg)
                iconUI.setImageResource(R.drawable.storm)
            }
            else{
                background.setImageResource(R.drawable.nightbg)
                if(icon.get(2) == 'n') {
                    background.setImageResource(R.drawable.nightbg)
                    iconUI.setImageResource(R.drawable.moon)
                }
                else {
                    iconUI.setImageResource(R.drawable.cloud)
                }
            }
        }

        private fun makeDate():List<String>{
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_WEEK)

            if(day == 1){
                return listOf("MONDAY","TUESDAY","WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY")
            }
            else if(day == 2){
                return listOf("TUESDAY","WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY","MONDAY")
            }
            else if(day == 3){
                return listOf("WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY","MONDAY","TUESDAY")
            }
            else if(day == 4){
                return listOf("THURSDAY","FRIDAY","SATURDAY","SUNDAY","MONDAY","TUESDAY","WEDNESDAY")
            }
            else if(day == 5){
                return listOf("FRIDAY","SATURDAY","SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY")
            }
            else if(day == 6){
                return listOf("SATURDAY","SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY")
            }
            else if(day == 7){
                return listOf("SUNDAY","SATURDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY")
            }
            return listOf("MONDAY","TUESDAY","WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY")
        }
    }
}

