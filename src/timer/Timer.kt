package timer

import java.time.Duration
import java.time.LocalDateTime

class Timer {
    private var running = false
    private var durantion: Long = 0
    private var startTime: LocalDateTime? = null
    private var stopTime: LocalDateTime? = null

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
        durantion += getTimeDelta();
        return durantion
    }

    private fun getTimeDelta(): Long {
        if (startTime == null) {
            return 0
        }
        return Duration.between(startTime, getCurrentEndTime()).toMillis()
    }

    private fun getCurrentEndTime(): LocalDateTime? {
        var endTime = stopTime
        if (stopTime == null) {
            endTime = LocalDateTime.now()
        }
        return endTime
    }
}