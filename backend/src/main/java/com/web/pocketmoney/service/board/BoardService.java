package com.web.pocketmoney.service.board;

import com.web.pocketmoney.dto.board.*;
import com.web.pocketmoney.entity.board.Board;
import com.web.pocketmoney.entity.board.BoardRepository;
import com.web.pocketmoney.entity.user.User;
import com.web.pocketmoney.exception.CBoardIdFailedException;
import com.web.pocketmoney.exception.CNoBoardAndUserException;
import com.web.pocketmoney.exception.handler.ErrorCode;
import com.web.pocketmoney.vo.CriteriaVo;
import com.web.pocketmoney.vo.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponseDto save(User user, BoardRequestDto dto)
    {
        log.info(1);
        int[] date = dto.getDate();
        log.info(2);
        LocalDateTime dateTime = LocalDateTime.of(date[0], date[1], date[2], date[3], date[4], 0,0);
        log.info(3);
        boardRepository.save(Board.builder()
                        .area(dto.getArea())
                        .content(dto.getContent())
                        .dayOfWeek(dto.getDayOfWeek())
                        .user(user)
                        .title(dto.getTitle())
                        .pay(dto.getPay())
                        .wantedTime(dateTime)
                .build()
        );

        return BoardResponseDto.builder()
                .nickName(user.getNickName())
                .area(dto.getArea())
                .pay(dto.getPay())
                .dayOfWeek(dto.getDayOfWeek())
                .title(dto.getTitle())
                .content(dto.getContent())
                .date(dateTime)
                 .build();
    }

    @Transactional
    public BoardResponseDto update(User user, BoardRequestDto dto, Long id)
    {
        Board board = boardRepository.findById(id).orElseThrow(CBoardIdFailedException::new);
        if(user.getId() != board.getUser().getId()) {
            throw new CNoBoardAndUserException();
        }
        int[] arr = dto.getDate();
        LocalDateTime dateTime = LocalDateTime.of(arr[0], arr[1], arr[2], arr[3], arr[4]);
        board.setArea(dto.getArea());
        board.setContent(dto.getContent());
        board.setDayOfWeek(dto.getDayOfWeek());
        board.setPay(dto.getPay());
        board.setTitle(dto.getTitle());
        board.setWantedTime(dateTime);
        String nickName = user.getNickName();
        boardRepository.save(board);

        return BoardResponseDto.builder()
                .nickName(nickName)
                .area(dto.getArea())
                .date(dateTime)
                .title(dto.getTitle())
                .content(dto.getContent())
                .dayOfWeek(dto.getDayOfWeek())
                .pay(dto.getPay())
                .build();
    }

    @Transactional
    public Long delete(User user, Long id)
    {
        Board board = boardRepository.findById(id).orElseThrow(CBoardIdFailedException::new);
        if(user.getId() != board.getUser().getId()) {
            throw new CNoBoardAndUserException();
        }
        boardRepository.delete(board);
        return id;
    }

    @Transactional
    public BoardDto postOne(User user, Long id)
    {
        Board board = boardRepository.findById(id).orElseThrow(CBoardIdFailedException::new);
        boardRepository.updateView(id);
        int isUser;
        if(user == null) {
            isUser=0;
        }
        else if(user.getId() != board.getUser().getId()) {
            isUser = 1;
        }
        else {
            isUser=2;
        }
        return BoardDto.builder()
                .dayOfWeek(board.getDayOfWeek())
                .content(board.getContent())
                .title(board.getTitle())
                .date(board.getWantedTime())
                .area(board.getArea())
                .nickName(board.getUser().getNickName())
                .pay(board.getPay())
                .view(board.getView())
                .area(board.getArea())
                .isUser(isUser)
        .build();
    }

    @Transactional
    public BoardResponseListDto boardList(int num)
    {
        List<Board> boards = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
        if(boards == null) {
            return new BoardResponseListDto(null, 1,1, false, false);
        }

       int total = boards.size();
       log.info("total : " + total);
       PageVo page = new PageVo(new CriteriaVo(num,10, total), total);

       List<BoardListDto> bd = new ArrayList<>();
       for(int i=page.getCri().getStart(); i<=page.getCri().getEnd(); i++) {
           bd.add(new BoardListDto(boards.get(i).getTitle(), boards.get(i).getView(), boards.get(i).getCreateTime(), boards.get(i).getUser().getNickName()));
       }
       return new BoardResponseListDto(bd,page.getStartPage(), page.getEndPage(), page.isPrev(), page.isNext());
    }

    @Transactional
    public BoardResponseListDto boardSearchList(String str, int num)
    {
        log.info("search list");
        List<Board> boards = (List<Board>) boardRepository.searchBoards(str);
        if(boards == null) {
            return new BoardResponseListDto(null, 1,1, false, false);
        }

        int total = boards.size();
        log.info("total : " + total);
        PageVo page = new PageVo(new CriteriaVo(num,10, total), total);

        int start = page.getStartPage();
        int end = page.getEndPage();

        List<BoardListDto> bd = new ArrayList<>();
        for(int i=page.getCri().getStart(); i<=page.getCri().getEnd(); i++) {
            bd.add(new BoardListDto(boards.get(i).getTitle(), boards.get(i).getView(), boards.get(i).getCreateTime(), boards.get(i).getUser().getNickName()));
        }
        BoardResponseListDto boardResponseListDto = new BoardResponseListDto(bd, start, end, page.isPrev(), page.isNext());
        return boardResponseListDto;
    }
}
