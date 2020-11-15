import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import timer.Timer
import kotlin.test.*

internal class TimerTest {
    lateinit var timer: Timer

    @BeforeEach
    fun setup() {
        timer = Timer()
    }

    @Test
    fun isNotRunning_WhenNotStarted() {
        assertFalse { timer.isRunning() }
    }

    @Test
    fun isRunning_WhenStarted() {
        timer.start()
        assertTrue { timer.isRunning() }
    }

    @Test
    fun isNotRunning_WhenStopped() {
        timer.stop()
        assertFalse { timer.isRunning() }
    }

    @Test
    fun isTime0_whenNotStarted() {
        val duration = timer.getDuration()
        assertEquals(0, duration)
    }

    @Test
    fun isTimeNot0_whenStarted() {
        timer.start()
        Thread.sleep(1)
        val duration = timer.getDuration()
        assertNotEquals(0, duration)
    }

    @Test
    fun isTimeEqual_whenStartedThenStoppedAndWait() {
        timer.start()
        timer.stop()
        val duration1 = timer.getDuration()
        Thread.sleep(500)
        val duration2 = timer.getDuration()
        assertEquals(duration1, duration2)
    }

    @Test
    fun isTimeNotEqual_whenStartedThenWaitThenStopped() {
        timer.start()
        val duration1 = timer.getDuration()
        Thread.sleep(5)
        timer.stop()
        val duration2 = timer.getDuration()
        assertNotEquals(duration1, duration2)
    }

    @Test
    fun isTimeNotEqual_whenStartWaitStopWaitStartWaitStop() {
        timer.start()
        Thread.sleep(5)
        timer.stop()
        val duration1 = timer.getDuration()
        timer.start()
        Thread.sleep(5)
        timer.stop()
        val duration2 = timer.getDuration()
        assertNotEquals(duration1, duration2)
    }

    @Test
    fun isDurationIncrease_whenStartWaitStopWaitStartWaitStop() {
        timer.start()
        Thread.sleep(5)
        timer.stop()
        val duration1 = timer.getDuration()
        timer.start()
        Thread.sleep(5)
        timer.stop()
        val duration2 = timer.getDuration()
        assertTrue { duration2 > duration1 }
    }
}