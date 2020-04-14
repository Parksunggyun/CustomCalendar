package thomas.park.customcalendar

data class CalendarItem(val day: String, val hasSchedule: Boolean, var isSelected: Boolean = false, var isThisMonth: Boolean, var isHolyDay: Boolean)