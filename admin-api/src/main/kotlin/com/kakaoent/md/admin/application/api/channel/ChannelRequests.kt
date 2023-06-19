package com.kakaoent.md.admin.application.api.channel

data class RegisterChannelGroupRequest(
    val groupName: String
)

data class UpdateChannelGroupRequest(
    val groupId: String,
    val groupName: String
)
