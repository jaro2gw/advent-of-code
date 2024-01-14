package me.pjaronski.aoc.v23.day19.parts

import me.pjaronski.aoc.utils.EnumArray
import me.pjaronski.aoc.v23.day19.parts.MechanicalPart.Attribute.A
import me.pjaronski.aoc.v23.day19.parts.MechanicalPart.Attribute.M
import me.pjaronski.aoc.v23.day19.parts.MechanicalPart.Attribute.S
import me.pjaronski.aoc.v23.day19.parts.MechanicalPart.Attribute.X
import me.pjaronski.aoc.utils.NATURAL_NUMBER_PATTERN as NUM

class MechanicalPart(
    private val attributes: EnumArray<Attribute, Int>
) {
    enum class Attribute {
        X, M, A, S;

        companion object {
            fun parse(attribute: String) = when (attribute) {
                "x"  -> X
                "m"  -> M
                "a"  -> A
                "s"  -> S
                else -> throw IllegalArgumentException("Unrecognized mechanical part attribute: \"$attribute\"")
            }
        }
    }

    companion object {
        private val regex = Regex("x=$NUM,m=$NUM,a=$NUM,s=$NUM")

        fun parse(part: String): MechanicalPart {
            val attrs = part.removePrefix("{").removeSuffix("}")
                .let { regex.find(it)!! }
                .groupValues
                .drop(1)
                .map { it.toInt() }
            val attributes = EnumArray.of<Attribute, Int> { attrs[it.ordinal] }
            return MechanicalPart(attributes)
        }
    }

    operator fun get(attr: Attribute): Int = attributes[attr]

    operator fun component1() = attributes[X]
    operator fun component2() = attributes[M]
    operator fun component3() = attributes[A]
    operator fun component4() = attributes[S]
}
