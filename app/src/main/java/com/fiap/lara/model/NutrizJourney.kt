package com.fiap.lara.model

data class NutrizJourney(
    val id: Int,
    val name: String,
    val city: String,
    val homeAddress: String,
    val babyProfile: String,
    val postpartumDay: Int,
    val currentStage: JourneyStage,
    val readinessScore: Int,
    val sentiment: Sentiment,
    val nearestMilkBank: MilkBank,
    val emotionalMemory: List<String>,
    val messages: List<LaraMessage>,
    val needsHumanSupport: Boolean
)

data class MilkBank(
    val name: String,
    val distanceKm: Double,
    val phone: String,
    val nextAvailableSlot: String
)

data class LaraMessage(
    val day: Int,
    val title: String,
    val text: String
)
