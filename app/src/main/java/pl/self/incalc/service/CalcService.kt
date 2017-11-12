package pl.self.incalc.service

import java.math.BigDecimal

/**
 * Created by pawel on 2017-11-01.
 */

class CalcService private constructor() {

    fun calc(amount: BigDecimal, percent: Float, time: Int, months: Boolean): BigDecimal {
        val realPercent = percent / DIVIDER
        return amount
                .multiply(BigDecimal.valueOf(realPercent.toDouble()))
                .multiply(BigDecimal.valueOf((if (months) time * DAYS_IN_MONTH else time) as Long))
                .multiply(BigDecimal.valueOf(VALUE_AFTER_TAX.toDouble()))
                .setScale(2, BigDecimal.ROUND_HALF_UP)
    }

    companion object {

        private val VALUE_AFTER_TAX = 0.81f
        private val DIVIDER = 36500f
        private val DAYS_IN_MONTH = 30f

        private val INSTANCE = CalcService()
        fun get(): CalcService {
            return INSTANCE
        }
    }

}
