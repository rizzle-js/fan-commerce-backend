package com.kakaoent.md.admin.application.api.channel

import java.time.Instant

data class ChannelGroupListResponse(
    val channelGroups: List<ChannelGroup>
)

data class ChannelGroup(
    val groupId: String,
    val groupName: String,
)

data class RegisterChannelGroupResponse(
    val groupId: String,
    val groupName: String,
)

data class UpdateChannelGroupResponse(
    val groupId: String,
    val groupName: String,
)

data class DeleteChannelGroupResponse(
    val groupId: String,
    val groupName: String,
)

data class ChannelGroupDetailResponse(
    val groupId: String,
    val groupName: String,
    val groupDescription: String,
    val createdDate: Instant,
    val updatedDate: Instant
)

data class ChannelsResponse(
    val channels: List<Channel>
)

data class Channel(
    val channelId: String,
    val name: String,
    val type: ChannelType,
    val status: ChannelStatus,
)

enum class ChannelType {
    ARTIST,
    CONTENT,
    EVENT,
    ETC
}

enum class ChannelStatus {
    ACTIVE,
    INACTIVE
}

data class RegisterChannelResponse(
    val channelId: String,
    val name: String,
    val type: ChannelType,
    val status: ChannelStatus,
)

data class UpdateChannelResponse(
    val channelId: String,
    val name: String,
    val type: ChannelType,
    val status: ChannelStatus,
)

data class DeleteChannelResponse(
    val channelId: String,
)

data class GetChannelDetailResponse(
    val channelId: String,
    val name: String,
    val type: ChannelType,
    val status: ChannelStatus,
)
