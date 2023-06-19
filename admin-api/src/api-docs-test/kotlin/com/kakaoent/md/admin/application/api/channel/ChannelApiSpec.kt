package com.kakaoent.md.admin.application.api.channel

import com.kakaoent.md.admin.application.api.ApiSpec
import com.kakaoent.md.admin.application.api.andDocument
import com.kakaoent.md.admin.application.api.channel.ChannelController.Companion.GET_CHANNEL_GROUPS
import com.kakaoent.md.admin.application.api.responseBody
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
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
                    "channelGroups[].groupId" type NUMBER means "그룹 ID"
                    "channelGroups[].groupName" type STRING means "그룹명"
                    "channelGroups[].groupCreatedAt" type NUMBER means "그룹 생성 날짜"
                }
            )
        }
    }
}