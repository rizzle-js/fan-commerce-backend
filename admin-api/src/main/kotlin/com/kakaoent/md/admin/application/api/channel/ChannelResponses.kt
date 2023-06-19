package com.kakaoent.md.admin.application.api.channel

import java.time.Instant

data class ChannelGroupListResponse(
    val channelGroups: List<ChannelGroup>
)

data class ChannelGroup(
    val groupId: Long,
    val groupName: String,
    val groupCreatedAt: Instant
)
