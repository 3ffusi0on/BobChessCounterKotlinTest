package timer

import java.time.Duration
import java.time.LocalDateTime

class Timer {
    private var running = false
    private var duration: Long = 0
    private var startTime: LocalDateTime? = null
    private var stopTime: LocalDateTime? = null
    private var nowPeriodTime: LocalDateTime? = null
    private var currentPeriodEndTime: LocalDateTime? = null
    private var currentPeriodStartTime: LocalDateTime? = null

    fun start() {
        running = true
        startTime = LocalDateTime.now()
    }

    fun isRunning(): Boolean {
        return running
    }

    fun stop() {
        running = false
        stopTime = LocalDateTime.now()
    }

    fun getDuration(): Long {
        updateDuration()
        return duration
    }

    private fun updateDuration() {
        stopCurrentPeriod()
        duration += Duration.between(currentPeriodStartTime, currentPeriodEndTime).toMillis()
        startNewPeriod()
    }

    private fun stopCurrentPeriod() {
        nowPeriodTime = LocalDateTime.now()
        currentPeriodStartTime = getCurrentStartTime()
        currentPeriodEndTime = getCurrentEndTime()
    }

    private fun startNewPeriod() {
        startTime = currentPeriodEndTime
        if (isRunning()) {
            stopTime = null
        }
    }

    private fun getCurrentStartTime(): LocalDateTime? {
        if (startTime == null) {
            return nowPeriodTime
        }
        return startTime
    }

    private fun getCurrentEndTime(): LocalDateTime? {
        if (stopTime == null) {
            return nowPeriodTime
        }
        return stopTime
    }
}