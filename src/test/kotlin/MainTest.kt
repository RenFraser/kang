import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

class MainTest {

    @Test
    fun `main should succeed`() {
        assertDoesNotThrow {
            main()
        }
    }
}
