package com.kakaoent.md.admin.application.api.channel

import java.time.Instant

data class ChannelGroupListResponse(
    val channelGroups: List<ChannelGroup>
)

data class ChannelGroup(
    val groupId: String,
    val groupName: String,
    val groupCreatedAt: Instant
)

data class RegisterChannelGroupResponse(
    val groupId: String,
    val groupName: String,
    val groupCreatedAt: Instant
)

data class UpdateChannelGroupResponse(
    val groupId: String,
    val groupName: String,
    val updatedAt: Instant
)

data class DeleteChannelGroupResponse(
    val groupId: String,
    val groupName: String,
    val deletedAt: Instant
)

data class ChannelGroupDetailResponse(
    val groupId: String,
    val groupName: String,
    val groupDescription: String,
    val createdDate: Instant,
    val updatedDate: Instant
)
