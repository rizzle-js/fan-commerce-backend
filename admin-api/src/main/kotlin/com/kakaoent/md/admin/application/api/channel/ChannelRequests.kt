package com.kakaoent.md.admin.application.api.channel

data class RegisterChannelGroupRequest(
    val groupName: String
)

data class UpdateChannelGroupRequest(
    val groupName: String
)

data class RegisterChannelRequest(
    val name: String,
    val type: ChannelType,
    val status: ChannelStatus
)

data class UpdateChannelRequest(
    val name: String,
    val type: ChannelType,
    val status: ChannelStatus
)
