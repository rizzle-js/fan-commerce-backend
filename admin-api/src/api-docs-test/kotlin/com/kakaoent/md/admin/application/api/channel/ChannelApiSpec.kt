package com.kakaoent.md.admin.application.api.channel

import com.kakaoent.md.admin.application.api.ApiSpec
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.GET_CHANNEL_GROUPS
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.REGISTER_CHANNEL_GROUP
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.UPDATE_CHANNEL_GROUP
import com.kakaoent.md.config.serde.objectMapper
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.requestBody
import com.kakaoent.md.docs.responseBody
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*
import org.springframework.restdocs.payload.JsonFieldType.*

@WebMvcTest(controllers = [ChannelController::class])
class ChannelApiSpec : ApiSpec() {
    init {
        test("채널 그룹 목록을 조회하다") {
            mockMvc.perform(
                get(GET_CHANNEL_GROUPS).contentType(APPLICATION_JSON)
            ).andDocument(
                "ChannelApiSpec 채널 그룹 목록 조회",
                responseBody {
                    "channelGroups[]" type ARRAY means "채널 그룹 목록"
                    "channelGroups[].groupId" type STRING means "그룹 ID"
                    "channelGroups[].groupName" type STRING means "그룹명"
                    "channelGroups[].groupCreatedAt" type NUMBER means "그룹 생성 날짜"
                }
            )
        }

        test("채널 그룹을 등록하다") {
            mockMvc.perform(
                post(REGISTER_CHANNEL_GROUP).contentType(APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            RegisterChannelGroupRequest(
                                groupName = "그룹1"
                            )
                        )
                    )
            ).andDocument(
                "ChannelApiSpec 채널 그룹 등록",
                requestBody {
                    "groupName" type STRING means "그룹명"
                },
                responseBody {
                    "groupId" type STRING means "그룹 ID"
                    "groupName" type STRING means "그룹명"
                    "groupCreatedAt" type NUMBER means "그룹 생성 날짜"
                }
            )
        }

        test("채널 그룹을 수정하다") {
            mockMvc.perform(
                put(UPDATE_CHANNEL_GROUP).contentType(APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            UpdateChannelGroupRequest(
                                groupId = CHNNEL_GROUP_UUID,
                                groupName = "수정된 그룹명"
                            )
                        )
                    )
            ).andDocument(
                "ChannelApiSpec 채널 그룹 수정",
                requestBody {
                    "groupId" type STRING means "그룹 ID"
                    "groupName" type STRING means "그룹명"
                },
                responseBody {
                    "groupId" type STRING means "그룹 ID"
                    "groupName" type STRING means "그룹명"
                    "updatedAt" type NUMBER means "수정 날짜"
                }
            )
        }
    }
}
