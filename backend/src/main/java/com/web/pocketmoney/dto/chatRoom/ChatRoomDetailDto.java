package com.web.pocketmoney.dto.chatRoom;

import com.web.pocketmoney.dto.message.MessageDetailDto;
import com.web.pocketmoney.entity.room.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoomDetailDto {

    private Long id;
    private String name;
    private Long employerId;
    private Long employeeId;

    private List<MessageDetailDto> messageDetailDtoList;

//    public static ChatRoomDetailDto toChatRoomDetailDto(ChatRoom chatRoom){
//        ChatRoomDetailDto chatRoomDetailDto = new ChatRoomDetailDto();
//
//        chatRoomDetailDto.setChatRoomId(chatRoom.getId());
//        chatRoomDetailDto.setChatMentor(chatRoom.getChatMentor());
//        chatRoomDetailDto.setRoomId(chatRoom.getRoomId());
//        chatRoomDetailDto.setName(chatRoom.getRoomName());
//
//        return chatRoomDetailDto;
//    }
}

