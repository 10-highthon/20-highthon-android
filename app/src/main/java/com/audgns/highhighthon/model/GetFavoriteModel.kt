package com.audgns.highhighthon.model

data class ChannelResponse(
    val channels: List<Channel>,
    val count: Int
)

data class Channel(
    val channelType: String,
    val channelDescription: String,
    val followerCount: Int,
    val openLive: Boolean,
    val channelId: String,
    val channelName: String,
    val channelImageUrl: String,
    val verifiedMark: Boolean,
    val userAdultStatus: String,
    val personalData: PersonalData
)

data class PersonalData(
    val privateUserBlock: Boolean
)
