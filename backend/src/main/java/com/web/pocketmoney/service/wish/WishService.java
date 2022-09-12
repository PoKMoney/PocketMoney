package com.web.pocketmoney.service.wish;

import com.web.pocketmoney.dto.wish.InsertWishDTO;
import com.web.pocketmoney.dto.wish.WishDTO;
import com.web.pocketmoney.dto.wish.WishPageRequestDTO;
import com.web.pocketmoney.dto.wish.WishPageResultDTO;
import com.web.pocketmoney.entity.board.Board;
import com.web.pocketmoney.entity.user.User;
import com.web.pocketmoney.entity.wish.Wish;

import java.util.List;

public interface WishService {

    Long register(InsertWishDTO insertwishDTO, User user);

    void remove(Long boardId, Long userId);

    WishPageResultDTO<WishDTO, Object[]> findAll(WishPageRequestDTO wishPageRequestDTO, Long id);

    default Wish dtoToEntity(InsertWishDTO dto, User user1){

        User user = User.builder().id(user1.getId()).build();
        Board board = Board.builder().id(dto.getBoardId()).build();

        Wish wish = Wish.builder()
                .userId(user)
                .boardId(board)
                .build();

        return wish;
    }

    default WishDTO entityToDTO(Wish wish, Board board, User user){

        // 게시글을 작성한 유저의 엔티티 불러오기
        User user2 = User.builder()
                .id(board.getId())
                .build();

        WishDTO wishDTO = WishDTO.builder()
//                .id(wish.getId())
//                .userId(user.getId())
                .boardId(board.getId())
//                .nickName(user2.getNickName())
                .title(board.getTitle())
//                .content(board.getContent())
                .build();

        return wishDTO;
    }

}
