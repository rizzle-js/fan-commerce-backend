package com.kakaoent.md.admin.application.api.channel

import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
class ChannelController {
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
            groupCreatedAt = Instant.now()
        )
    }

    companion object {
        const val GET_CHANNEL_GROUPS = "/channel-groups"
        const val REGISTER_CHANNEL_GROUP = "/channel-group"
    }
}