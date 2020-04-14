package thomas.park.customcalendar

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private var currentPageMonth = 0
    private var standardCurrentPage = 0

    private var beforePosition = 0


    private val dateFormat =
        SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    private val yearMonthFormat =
        SimpleDateFormat("yyyy-MM", Locale.KOREA)
    private val calendar = Calendar.getInstance()
    private var firstDayNumOfMonth = 0
    private var lastDayNumOfMonth = 0
    private var lastDayOfMonth = 0
    private var lastDayOfPrevMonth = 0
    private var today: String? = null
    private var year = 0
    private var month = 0
    private var currentYear = 0
    private var currentMonth = 0

    private var currentItemPosition: Int = 0
    private var calendarTotalCount: Int = 0

    private lateinit var calendarPagerAdapter: CalendarPagerAdapter
    private lateinit var currentFragment: CalendarFragment

    private lateinit var calendarFragments: ArrayList<CalendarFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setToday()

        val todays: Array<String> = today!!.split("-").toTypedArray()
        year = todays[0].toInt()
        month = todays[1].toInt()

        standardCurrentPage = calendar[Calendar.MONTH] - 2
        currentPageMonth = standardCurrentPage

        calendarFragments = initiateFragments(currentPageMonth)

        calendarPagerAdapter = CalendarPagerAdapter(this, calendarFragments)
        calendarViewPager.adapter = calendarPagerAdapter
        calendarViewPager.registerOnPageChangeCallback(pagerListener)
        calendarTotalCount = calendarViewPager.adapter!!.itemCount
        calendarViewPager.offscreenPageLimit = 5
        Log.e(TAG, "$calendarTotalCount")
        calendarViewPager.currentItem = 2
        beforePosition = calendarViewPager.currentItem
        currentItemPosition = calendarViewPager.currentItem

//        currentFragment = calendarPagerAdapter.calendarFragments[currentItemPosition]
//        currentFragment.setTodayText()


        prevMonthBtn.setOnClickListener {
            standardCurrentPage--
            calendarViewPager.setCurrentItem(calendarViewPager.currentItem - 1, true)
/*            if (currentItemPosition > 0) {
                if (currentItemPosition == 1) {

                }
                currentItemPosition--
            }*/
        }

        nextMonthBtn.setOnClickListener {
            standardCurrentPage++
            calendarViewPager.setCurrentItem(calendarViewPager.currentItem + 1, true)
/*            if (currentItemPosition < calendarTotalCount - 1) {
                currentItemPosition++
            }*/
        }
    }

    private val pagerListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Log.e(TAG, "onPageSelected position = $position")
            val fragment = calendarPagerAdapter.fragments[position]
            if(position == 0) {
                standardCurrentPage--
                Log.e(TAG, "onPageSelected .standardCurrentPage = ${fragment.currentMonth}")
                addFragment(fragment.currentMonth, -1)
                Log.e(TAG, "onPageSelected .currentMonth = ${fragment.currentMonth}")
            } else if(position == calendarPagerAdapter.itemCount - 1) {
                standardCurrentPage++
                addFragment(fragment.currentMonth, 1)
            }
        }
    }

    private fun initiateFragments(standardMonth: Int): ArrayList<CalendarFragment> {
        val calendarFragments = ArrayList<CalendarFragment>()
        for (i in 0 until CalendarPagerAdapter.FRAGMENT_COUNT) {
            calendarFragments.add(
                CalendarFragment.InitiateFragment(
                    this,
                    getFirstDayNumOfMonth(),
                    getLastDayOfMonth(),
                    getLastDayNumOfMonth(),
                    getLastDayOfPrevMonth(),
                    currentYear,
                    currentMonth
                )
            )
        }
        return calendarFragments
    }

    private fun addFragment(standardMonth: Int, way: Int) {
        val fragment = CalendarFragment.InitiateFragment(
                this,
                getFirstDayNumOfMonth(),
                getLastDayOfMonth(),
                getLastDayNumOfMonth(),
                getLastDayOfPrevMonth(),
                currentYear,
                currentMonth
            )
        if(way == -1) {
            calendarPagerAdapter.addFragment(fragment, way, calendarViewPager)
        } else {
            calendarPagerAdapter.addFragment(fragment, way, null)
        }
    }

    private fun removeFragment(standardMonth: Int): ArrayList<CalendarFragment> {
        val calendarFragments = ArrayList<CalendarFragment>()
        for (i in 0 until CalendarPagerAdapter.FRAGMENT_COUNT) {
            calendarFragments.add(
                CalendarFragment.InitiateFragment(
                    this,
                    getFirstDayNumOfMonth(),
                    getLastDayOfMonth(),
                    getLastDayNumOfMonth(),
                    getLastDayOfPrevMonth(),
                    currentYear,
                    currentMonth
                )
            )
        }
        return calendarFragments
    }

    fun setToday(today: String) {
        calendarTitle.text = today
    }

    override fun onDestroy() {
        super.onDestroy()
        calendarViewPager.registerOnPageChangeCallback(pagerListener)
    }


    private fun setToday() {
        today = yearMonthFormat.format(Date())
    }

    private fun getFirstDayNumOfMonth(): Int {
        calendar.set(year, currentPageMonth, 1)
        firstDayNumOfMonth = calendar.get(Calendar.DAY_OF_WEEK)
        return firstDayNumOfMonth
    }

    private fun getLastDayOfMonth(): Int {
        Log.e(TAG, "currentPageMonth = $currentPageMonth")

        calendar.set(year, currentPageMonth, 1)
        lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        currentYear = calendar.get(Calendar.YEAR)
        Log.e(TAG, "currentYear = $currentYear")
        currentMonth = calendar.get(Calendar.MONTH) + 1
        return lastDayOfMonth
    }

    private fun getLastDayNumOfMonth(): Int {
        calendar.set(year, currentPageMonth, lastDayOfMonth)
        lastDayNumOfMonth = calendar.get(Calendar.DAY_OF_WEEK)
        return lastDayNumOfMonth
    }

    private fun getLastDayOfPrevMonth(): Int {
        calendar.set(year, currentPageMonth - 1, 1)
        lastDayOfPrevMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        currentPageMonth++
        return lastDayOfPrevMonth
    }

}
