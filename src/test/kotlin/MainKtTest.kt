import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateComission_Vk() {
        val paymentType = PaymentType.Vk
        val monthAmount = 0.0
        val transferSum = 500.0

        val result = calculateComission(
            paymentType = paymentType,
            monthAmount = monthAmount,
            transferSum = transferSum
        )

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calculateComission_MirVisaNoFixedComission() {
        val paymentType = PaymentType.Mir
        val monthAmount = 0.0
        val transferSum = 10000.0

        val result = calculateComission(
            paymentType = paymentType,
            monthAmount = monthAmount,
            transferSum = transferSum
        )

        assertEquals(7500.0, result, 0.0)
    }

    @Test
    fun calculateComission_MirVisaWithFixedComission() {
        val paymentType = PaymentType.Mir
        val monthAmount = 0.0
        val transferSum = 100.0

        val result = calculateComission(
            paymentType = paymentType,
            monthAmount = monthAmount,
            transferSum = transferSum
        )

        assertEquals(3500.0, result, 0.0)
    }

    @Test
    fun calculateComission_MastercardMaestroNoLimit() {
        val paymentType = PaymentType.MasterCard
        val monthAmount = 5_000.0
        val transferSum = 5000.0

        val result = calculateComission(
            paymentType = paymentType,
            monthAmount = monthAmount,
            transferSum = transferSum
        )

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calculateComission_MastercardMaestroWithLimit() {
        val paymentType = PaymentType.MasterCard
        val monthAmount = 100_000.0
        val transferSum = 500.0

        val result = calculateComission(
            paymentType = paymentType,
            monthAmount = monthAmount,
            transferSum = transferSum
        )

        assertEquals(2300.0, result, 0.0)
    }
}

