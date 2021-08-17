import java.util.*
import kotlin.math.roundToInt

fun main() {
    val scanner = Scanner(System.`in`)
    while (true) {
        println("Выберите платежную систему: \n1. VK Pay\n2. MasterCard\n3. Maestro\n4. Visa\n5. Mir")
        val paymentType = when (scanner.nextInt()) {
            1 -> PaymentType.Vk
            2 -> PaymentType.MasterCard
            3 -> PaymentType.Maestro
            4 -> PaymentType.Visa
            5 -> PaymentType.Mir
            else -> continue
        }

        println("Введите сумму предыдущих переводов (в рублях) в этом месяце (по умолчанию - 0)")
        val monthAmount = scanner.nextDouble()

        println("Введите сумму совершаемого перевода в рублях")
        val transferSum = scanner.nextDouble()

        if (transferSum < 50) {
            println("Минимальная сумма перевода 50 р.")
            continue
        } else if (paymentType != PaymentType.Vk && transferSum > 150_000 || monthAmount > 600_000) {
            println("Максимальная сумма переводов по карте 150000 р. в сутки и 600000 р. в месяц")
            continue
        } else if (paymentType == PaymentType.Vk && transferSum > 15_000
            || paymentType == PaymentType.Vk && monthAmount > 40_000) {
            println("Максимальная сумма переводов через VK Pay - 15 т.р за один раз и 40000 р. в месяц")
            continue
        }

        calculateComission(paymentType, monthAmount, transferSum)
    }
}

fun calculateComission(
    paymentType: PaymentType = PaymentType.Vk,
    monthAmount: Double = 0.0,
    transferSum: Double
): Double {
    var commission = when (paymentType) {
        PaymentType.Vk -> 0.0
        PaymentType.MasterCard, PaymentType.Maestro -> {
            if (monthAmount < 75_000 && transferSum > 300) 0.0 else transferSum * 0.006 + 20.0
        }
        PaymentType.Visa, PaymentType.Mir -> if (transferSum * 0.0075 < 35.0) 35.0 else transferSum * 0.0075
    }
    commission *= 100 //перевод в копейки
    println("Комиссия составляет ${commission.roundToInt()} копеек")
    return commission
}

enum class PaymentType {
    Vk, MasterCard, Maestro, Visa, Mir
}