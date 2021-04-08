import org.junit.jupiter.api.Test

internal class CutTest {
    @Test
    fun testConsole() {
        val c = Cut("file/input", "file/output", true, "2-5")
        c.cutInfo()
        val m = Cut("file/input", "file/nOutput", false, "2-5")
        m.cutInfo()
        val r = Cut("file/input", "file/nOutput", true, "5-2")
        r.cutInfo()
        AssertionError(
            "Invalid Range.The first number must be less than the second.Rewrite the command correctly please."
        )
        val f =Cut("input.txt", "file/output", true, "2-5")
        f.cutInfo()
        AssertionError(
            "Error in the receveid file" +
                    " Rewrite the command correctly please."
        )
    }
}