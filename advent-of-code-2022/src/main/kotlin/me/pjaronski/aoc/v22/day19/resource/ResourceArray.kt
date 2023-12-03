package me.pjaronski.aoc.v22.day19.resource

import me.pjaronski.aoc.utils.EnumArray

class ResourceArray<T>(transform: (Resource) -> T) : EnumArray<Resource, T>(enumValues(), transform)
