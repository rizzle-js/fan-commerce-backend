package com.kakaoent.md.admin.application.api.channel

import com.kakaoent.md.admin.application.api.ApiSpec
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.DELETE_CHANNEL
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.DELETE_CHANNEL_GROUP
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.GET_CHANNELS
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.GET_CHANNEL_DETAIL
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.GET_CHANNEL_GROUPS
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.GET_CHANNEL_GROUP_DETAIL
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.REGISTER_CHANNEL
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.REGISTER_CHANNEL_GROUP
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.UPDATE_CHANNEL
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.UPDATE_CHANNEL_GROUP
import com.kakaoent.md.config.serde.objectMapper
import com.kakaoent.md.docs.andDocument
import com.kakaoent.md.docs.pathVariables
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
                put(UPDATE_CHANNEL_GROUP, CHNNEL_GROUP_UUID).contentType(APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            UpdateChannelGroupRequest(
                                groupName = "수정된 그룹명"
                            )
                        )
                    )
            ).andDocument(
                "ChannelApiSpec 채널 그룹 수정",
                pathVariables {
                    "groupId" means "그룹 ID"
                },
                requestBody {
                    "groupName" type STRING means "그룹명"
                },
                responseBody {
                    "groupId" type STRING means "그룹 ID"
                    "groupName" type STRING means "그룹명"
                    "updatedAt" type NUMBER means "수정 날짜"
                }
            )
        }

        test("채널 그룹을 삭제하다") {
            mockMvc.perform(
                delete(DELETE_CHANNEL_GROUP, CHNNEL_GROUP_UUID).contentType(APPLICATION_JSON)
            ).andDocument(
                "ChannelApiSpec 채널 그룹 삭제",
                pathVariables {
                    "groupId" means "그룹 ID"
                },
                responseBody {
                    "groupId" type STRING means "그룹 ID"
                    "groupName" type STRING means "그룹명"
                    "deletedAt" type NUMBER means "삭제 날짜"
                }
            )
        }

        test("채널 그룹을 상세 조회하다") {
            mockMvc.perform(
                get(GET_CHANNEL_GROUP_DETAIL, CHNNEL_GROUP_UUID).contentType(APPLICATION_JSON)
            ).andDocument(
                "ChannelApiSpec 채널 그룹 상세 조회",
                pathVariables {
                    "groupId" means "그룹 ID"
                },
                responseBody {
                    "groupId" type STRING means "그룹 ID"
                    "groupName" type STRING means "그룹명"
                    "groupDescription" type STRING means "그룹 설명"
                    "createdDate" type NUMBER means "그룹 생성 날짜"
                    "updatedDate" type NUMBER means "그룹 수정 날짜"
                }
            )
        }

        test("채널 목록을 조회하다") {
            mockMvc.perform(
                get(GET_CHANNELS).contentType(APPLICATION_JSON)
            ).andDocument(
                "ChannelApiSpec 채널 목록 조회",
                responseBody {
                    "channels[]" type ARRAY means "채널 목록"
                    "channels[].channelId" type STRING means "채널 ID"
                    "channels[].name" type STRING means "채널명"
                    "channels[].type" type STRING means "채널 타입"
                    "channels[].status" type STRING means "채널 상태"
                    "channels[].createdAt" type NUMBER means "생성 날짜"
                }
            )
        }

        test("채널을 등록하다") {
            mockMvc.perform(
                post(REGISTER_CHANNEL).contentType(APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            RegisterChannelRequest(
                                name = "베토벤",
                                type = ChannelType.ARTIST,
                                status = ChannelStatus.ACTIVE
                            )
                        )
                    )
            ).andDocument(
                "ChannelApiSpec 채널 등록",
                requestBody {
                    "name" type STRING means "채널명"
                    "type" type STRING means "채널 타입"
                    "status" type STRING means "채널 상태"
                },
                responseBody {
                    "channelId" type STRING means "채널 ID"
                    "name" type STRING means "채널명"
                    "type" type STRING means "채널 타입"
                    "status" type STRING means "채널 상태"
                    "createdAt" type NUMBER means "생성 날짜"
                }
            )
        }

        test("채널을 수정하다") {
            mockMvc.perform(
                put(UPDATE_CHANNEL, CHNNEL_UUID).contentType(APPLICATION_JSON)
                    .content(
                        objectMapper.writeValueAsString(
                            UpdateChannelRequest(
                                name = "Test Channel",
                                type = ChannelType.CONTENT,
                                status = ChannelStatus.ACTIVE
                            )
                        )
                    )
            ).andDocument(
                "ChannelApiSpec 채널 수정",
                pathVariables {
                    "channelId" means "채널 ID"
                },
                requestBody {
                    "name" type STRING means "채널명"
                    "type" type STRING means "채널 타입"
                    "status" type STRING means "채널 상태"
                },
                responseBody {
                    "channelId" type STRING means "채널 ID"
                    "name" type STRING means "채널명"
                    "type" type STRING means "채널 타입"
                    "status" type STRING means "채널 상태"
                    "updatedAt" type NUMBER means "수정 날짜"
                }
            )
        }

        test("채널을 삭제하다") {
            mockMvc.perform(
                delete(DELETE_CHANNEL, CHNNEL_UUID).contentType(APPLICATION_JSON)
            ).andDocument(
                "ChannelApiSpec 채널 삭제",
                pathVariables {
                    "channelId" means "채널 ID"
                },
                responseBody {
                    "channelId" type STRING means "채널 ID"
                    "deletedAt" type NUMBER means "삭제 날짜"
                }
            )
        }

        test("채널을 상세 조회하다") {
            mockMvc.perform(
                get(GET_CHANNEL_DETAIL, CHNNEL_UUID).contentType(APPLICATION_JSON)
            ).andDocument(
                "ChannelApiSpec 채널 상세 조회",
                pathVariables {
                    "channelId" means "채널 ID"
                },
                responseBody {
                    "channelId" type STRING means "채널 ID"
                    "name" type STRING means "채널명"
                    "type" type STRING means "채널 타입"
                    "status" type STRING means "채널 상태"
                    "createdAt" type NUMBER means "생성 날짜"
                    "updatedAt" type NUMBER means "수정 날짜"
                }
            )
        }
    }
}
