package com.kakaoent.md.admin.application.api.channel

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class ChannelAdminController {
    @GetMapping(GET_CHANNEL_GROUPS)
    fun getChannelGroups(): ChannelGroupListResponse {
        // 이 부분은 실제로는 서비스로부터 데이터를 받아와야 하지만, 여기서는 임의로 데이터를 생성합니다.
        return ChannelGroupListResponse(
            listOf(
                ChannelGroup(
                    groupId = "2KMEzbB3vT3KWO9U3JLLFN",
                    groupName = "그룹1",
                    groupCreatedAt = Instant.ofEpochSecond(1686641320L),
                ),
                ChannelGroup(
                    groupId = "2g8MC8a6iuPaCj61TD6GdU",
                    groupName = "그룹2",
                    groupCreatedAt = Instant.ofEpochSecond(1686641340L),
                )
            )
        )
    }

    @PostMapping(REGISTER_CHANNEL_GROUP)
    fun registerChannelGroup(
        @RequestBody registerChannelGroupRequest: RegisterChannelGroupRequest
    ): RegisterChannelGroupResponse {
        // 이 부분은 실제로는 서비스로부터 데이터를 받아와야 하지만, 여기서는 임의로 데이터를 생성합니다.
        return RegisterChannelGroupResponse(
            groupId = "4AaVUXYXBX8ohD3S7eNtxr", // 실제로는 생성된 그룹 ID를 반환해야 합니다.
            groupName = registerChannelGroupRequest.groupName,
            groupCreatedAt = Instant.ofEpochSecond(1686641320L)
        )
    }

    @PutMapping(UPDATE_CHANNEL_GROUP)
    fun updateChannelGroup(
        @PathVariable groupId: String,
        @RequestBody updateChannelGroupRequest: UpdateChannelGroupRequest
    ): UpdateChannelGroupResponse {
        // 이 부분은 실제로는 서비스로부터 데이터를 받아와야 하지만, 여기서는 임의로 데이터를 생성합니다.
        return UpdateChannelGroupResponse(
            groupId = groupId,
            groupName = updateChannelGroupRequest.groupName,
            updatedAt = Instant.ofEpochSecond(1686641320L)
        )
    }


    @DeleteMapping(DELETE_CHANNEL_GROUP)
    fun deleteChannelGroup(
        @PathVariable groupId: String,
    ): DeleteChannelGroupResponse {
        // 이 부분은 실제로는 서비스로부터 데이터를 받아와야 하지만, 여기서는 임의로 데이터를 생성합니다.
        return DeleteChannelGroupResponse(
            groupId = groupId,
            groupName = "삭제된 그룹명",  // 실제로는 삭제 전 그룹명을 반환해야 합니다.
            deletedAt = Instant.ofEpochSecond(1686641320L)
        )
    }

    @GetMapping(GET_CHANNEL_GROUP_DETAIL)
    fun getChannelGroupDetail(
        @PathVariable groupId: String,
    ): ChannelGroupDetailResponse {
        // 이 부분은 실제로는 서비스로부터 데이터를 받아와야 하지만, 여기서는 임의로 데이터를 생성합니다.
        return ChannelGroupDetailResponse(
            groupId = groupId,
            groupName = "그룹 이름",
            groupDescription = "그룹 설명",
            createdDate = Instant.ofEpochSecond(1686641320L),
            updatedDate = Instant.ofEpochSecond(1686641320L)
        )
    }

    @GetMapping(GET_CHANNELS)
    fun getChannels(): ChannelsResponse {
        return ChannelsResponse(
            listOf(
                Channel(
                    channelId = "3O4cbCd34nAYNYCx3uRns3",
                    name = "박효신",
                    type = ChannelType.ARTIST,
                    status = ChannelStatus.ACTIVE,
                    createdAt = Instant.ofEpochSecond(1686641320L)
                ),
                Channel(
                    channelId = "1hWgrL5AvH8xMbIXAC7xIU",
                    name = "베토벤",
                    type = ChannelType.CONTENT,
                    status = ChannelStatus.INACTIVE,
                    createdAt = Instant.ofEpochSecond(1686641320L)
                )
            )
        )
    }

    @PostMapping(REGISTER_CHANNEL)
    fun registerChannel(
        @RequestBody request: RegisterChannelRequest
    ): RegisterChannelResponse {
        return RegisterChannelResponse(
            channelId = "1hWgrL5AvH8xMbIXAC7xIU",
            name = request.name,
            type = request.type,
            status = request.status,
            createdAt = Instant.ofEpochSecond(1686641320L)
        )
    }

    @PutMapping(UPDATE_CHANNEL)
    fun updateChannel(
        @PathVariable channelId: String,
        @RequestBody request: UpdateChannelRequest
    ): UpdateChannelResponse {
        return UpdateChannelResponse(
            channelId = channelId,
            name = request.name,
            type = request.type,
            status = request.status,
            updatedAt = Instant.ofEpochSecond(1686641320L)
        )
    }


    @DeleteMapping(DELETE_CHANNEL)
    fun deleteChannel(
        @PathVariable channelId: String,
    ): DeleteChannelResponse {
        return DeleteChannelResponse(
            channelId = channelId,
            deletedAt = Instant.ofEpochSecond(1686641320L)
        )
    }

    @GetMapping(GET_CHANNEL_DETAIL)
    fun getChannelDetail(
        @PathVariable channelId: String,
    ): GetChannelDetailResponse {
        return GetChannelDetailResponse(
            channelId = channelId,
            name = "베토벤",
            type = ChannelType.CONTENT,
            status = ChannelStatus.ACTIVE,
            createdAt = Instant.ofEpochSecond(1686641320L),
            updatedAt = Instant.ofEpochSecond(1686641320L)
        )
    }

    companion object {
        const val GET_CHANNEL_GROUPS = "/channel-groups"
        const val REGISTER_CHANNEL_GROUP = "/channel-groups"
        const val UPDATE_CHANNEL_GROUP = "/channel-groups/{groupId}"
        const val DELETE_CHANNEL_GROUP = "/channel-groups/{groupId}"
        const val GET_CHANNEL_GROUP_DETAIL = "/channel-groups/{groupId}"
        const val GET_CHANNELS = "/channels"
        const val REGISTER_CHANNEL = "/channels"
        const val UPDATE_CHANNEL = "/channels/{channelId}"
        const val DELETE_CHANNEL = "/channels/{channelId}"
        const val GET_CHANNEL_DETAIL = "/channels/{channelId}"
    }
}