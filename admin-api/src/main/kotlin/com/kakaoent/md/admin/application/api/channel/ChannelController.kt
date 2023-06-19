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
                    groupId = 1,
                    groupName = "그룹1",
                    groupCreatedAt = Instant.ofEpochSecond(1686641320L),
                ),
                ChannelGroup(
                    groupId = 2,
                    groupName = "그룹2",
                    groupCreatedAt = Instant.ofEpochSecond(1686641340L),
                )
            )
        )
    }

    companion object {
        const val GET_CHANNEL_GROUPS = "/channel-groups"
    }
}