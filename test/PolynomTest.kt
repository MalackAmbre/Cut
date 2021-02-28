import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PolynomTest {

    private fun assertApproxEquals(expected: Polynom, actual: Polynom, eps: Double) {
        assertEquals(expected.degree(), actual.degree())
        for (i in 0..expected.degree()) {
            assertEquals(expected.coeff(i), actual.coeff(i), eps)
        }
    }

    @Test
    fun getValue() {
        val p1 = Polynom(1.0, 3.0, 2.0)
        assertEquals(42.0, p1.getValue(5.0), 1e-10)
        val p2 = Polynom(2.0, -1.0, 3.0, 1.0)
        assertEquals(241.0, p2.getValue(5.0), 1e-10)
        val p3 = Polynom(1.0)
        assertEquals(1.0, p3.getValue(3.0), 1e-10)
    }

    @Test
    fun degree() {
        val p = Polynom(1.0, 1.0, 1.0)
        assertEquals(2, p.degree())
        val q = Polynom(0.0)
        assertEquals(0, q.degree())
        val r = Polynom(0.0, 1.0, 2.0)
        assertEquals(1, r.degree())
    }

    @Test
    fun plus() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, -1.0, 2.0, 6.0)
        assertApproxEquals(r, p1 + p2, 1e-10)
        assertApproxEquals(r, p2 + p1, 1e-10)
        val p3 = Polynom(2.0, -1.0)
        val p4 = Polynom(7.0, 3.0, -6.0, 1.0 , -8.0)
        val r1 = Polynom(7.0, 3.0, -6.0, 3.0,-9.0)
        assertApproxEquals(r1, p3 + p4, 1e-10)
        assertApproxEquals(r1, p4 + p3, 1e-10)
    }

    @Test
    operator fun unaryMinus() {
        val p = Polynom(1.0, -1.0, 2.0)
        val r = Polynom(-1.0, 1.0, -2.0)
        assertApproxEquals(r, -p, 1e-11)
        val p1 = Polynom(1.0, -3.0, 2.0)
        val r1 = Polynom(-1.0, 3.0, -2.0)
        assertApproxEquals(r1, -p1, 1e-11)
    }

    @Test
    fun minus() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, -3.0, -4.0, 2.0)
        assertApproxEquals(r, p1 - p2, 1e-10)
        assertApproxEquals(-r, p2 - p1, 1e-10)
        val p3 = Polynom(2.0, -1.0)
        val p4 = Polynom(7.0, 3.0, -6.0, 1.0 , -8.0)
        val r1 = Polynom(-7.0, -3.0, 6.0, 1.0, 7.0)
        assertApproxEquals(r1, p3 - p4, 1e-10)
        assertApproxEquals(-r1, p4 - p3, 1e-10)
    }

    @Test
    fun times() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, 1.0, -5.0, -3.0, 10.0, 8.0)
        assertApproxEquals(r, p1 * p2, 1e-10)
        assertApproxEquals(r, p2 * p1, 1e-10)
        val p3 = Polynom(2.0, -1.0, 3.0, 1.0)
        val p4 = Polynom(3.0, 1.0, 2.0)
        val r1 = Polynom(6.0, -1.0, 12.0, 4.0, 7.0, 2.0)
        assertApproxEquals(r1, p3 * p4, 1e-10)
        assertApproxEquals(r1, p4 * p3, 1e-10)
        val p5 = Polynom(2.0, 0.0, 1.0)
        val p6 = Polynom(2.0)
        val r2 = Polynom(6.0, 2.0, 7.0 , 1.0 , 2.0)
        val r3 = Polynom(4.0, 0.0, 2.0)
        assertApproxEquals(r2, p5 * p4, 1e-10)
        assertApproxEquals(r3, p5 * p6, 1e-10)
    }

    @Test
    fun div() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, -5.0)
        assertApproxEquals(r, p1 / p2, 1e-10)
        assertApproxEquals(Polynom(1.0, 2.0), Polynom(2.0, 4.0) / Polynom(0.0, 2.0), 1e-10)
    }

    @Test
    fun rem() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, -5.0)
        val q = Polynom(12.0, 14.0)
        assertApproxEquals(q, p1 % p2, 1e-10)
        assertApproxEquals(p1, p2 * r + q, 1e-10)
    }

    @Test
    fun equals() {
        assertEquals(Polynom(1.0, 2.0, 3.0), Polynom(1.0, 2.0, 3.0))
        assertEquals(Polynom(0.0, 2.0, 3.0), Polynom(2.0, 3.0))
    }

    @Test
    fun hashCodeTest() {
        assertEquals(Polynom(1.0, 2.0, 3.0).hashCode(), Polynom(1.0, 2.0, 3.0).hashCode())
    }
}