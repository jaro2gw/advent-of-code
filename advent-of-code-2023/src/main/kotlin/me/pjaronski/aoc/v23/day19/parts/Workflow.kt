package me.pjaronski.aoc.v23.day19.parts

import me.pjaronski.aoc.utils.tail
import me.pjaronski.aoc.v23.day19.parts.Workflow.Decision.ACCEPT
import me.pjaronski.aoc.v23.day19.parts.Workflow.Decision.REJECT
import me.pjaronski.aoc.utils.NATURAL_NUMBER_PATTERN as NUM

data class Workflow(
    val rules: List<Rule>,
    val default: Destination
): (MechanicalPart) -> Workflow.Destination {
    companion object {
        fun parse(workflow: String): Workflow {
            val (rlz, def) = workflow.removePrefix("{")
                .removeSuffix("}")
                .split(',')
                .tail()

            val rules = rlz.map { Rule.parse(it) }
            val default = Destination.parse(def!!)

            return Workflow(rules, default)
        }
    }

    sealed interface Destination {
        companion object {
            fun parse(destination: String): Destination = when (destination) {
                "A"  -> ACCEPT
                "R"  -> REJECT
                else -> Redirect(destination)
            }
        }
    }

    data class Redirect(val workflow: String) : Destination
    enum class Decision : Destination {
        ACCEPT,
        REJECT,
    }

    data class Rule(
        val attribute: MechanicalPart.Attribute,
        val operator: Operator,
        val number: Int,
        val destination: Destination
    ): (MechanicalPart) -> Boolean {
        enum class Operator(cmp: (Int, Int) -> Boolean) : (Int, Int) -> Boolean by cmp {
            LT({ x, y -> x < y }),
            GT({ x, y -> x > y });

            companion object {
                fun parse(op: String) = when (op) {
                    "<"  -> LT
                    ">"  -> GT
                    else -> throw IllegalStateException("Unknown operator: \"$op\"")
                }
            }
        }

        companion object {
            private const val RULE_PATTERN = "([xmas])([<>])$NUM:([a-z]+|[AR])"
            private val regex = Regex(RULE_PATTERN)
            fun parse(rule: String): Rule {
                val (_, attr, op, num, dst) = regex.find(rule)!!.groupValues

                val attribute = MechanicalPart.Attribute.parse(attr)
                val operator = Operator.parse(op)
                val number = num.toInt()
                val destination = Destination.parse(dst)

                return Rule(attribute, operator, number, destination)
            }
        }

        override fun invoke(part: MechanicalPart): Boolean = operator(part[attribute], number)
    }

    override operator fun invoke(part: MechanicalPart): Destination {
        return rules.firstOrNull { it(part) }?.destination ?: default
    }
}
