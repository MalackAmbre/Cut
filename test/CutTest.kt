import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CutTest {
    @Test
    fun testCorrectConsole() {
        val c = Cut("file/input", "file/output", true, "2-5")
        c.cutInfo()
        val m = Cut("file/input", "file/nOutput", false, "2-5")
        m.cutInfo()

    }
    @Test
    fun  testInvalidRange(){
        assertThrows<IllegalArgumentException> {
            Cut("file/input", "file/nOutput", true, "5-2").cutInfo()
        }
    }

    @Test
    fun inexistingFile(){
        assertThrows<IllegalArgumentException> {
            Cut("inputFile", "file/output", true, "2-5").cutInfo()
        }
    }
}