package com.web.pocketmoney.controller.board;

import com.web.pocketmoney.dto.board.BoardDto;
import com.web.pocketmoney.dto.board.BoardRequestDto;
import com.web.pocketmoney.dto.board.BoardResponseDto;
import com.web.pocketmoney.dto.board.BoardResponseListDto;
import com.web.pocketmoney.entity.user.User;
import com.web.pocketmoney.model.CommonResult;
import com.web.pocketmoney.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("boards")
@RequiredArgsConstructor
@Log4j2
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<BoardResponseDto> saveBoard(@RequestBody BoardRequestDto boardRequestDto,
                                                      @AuthenticationPrincipal User user)
    {
        log.info("Board save Controller : " + user.toString());
        log.info(boardRequestDto.toString());

        return ResponseEntity.ok(boardService.save(user, boardRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable("id") Long id,
                                                        @RequestBody BoardRequestDto boardRequestDto,
                                                        @AuthenticationPrincipal User user)
    {
        return ResponseEntity.ok(boardService.update(user, boardRequestDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteBoard(@PathVariable("id") Long id,
                                                    @AuthenticationPrincipal User user)
    {
       // boardService.delete();
        return ResponseEntity.ok(boardService.delete(user, id));
    }

    @GetMapping("/{id}") //하나의 게시글 조회
    public ResponseEntity<BoardDto> viewOne(@AuthenticationPrincipal User user, @PathVariable("id") Long id)
    {
        return ResponseEntity.ok(boardService.postOne(user, id));
    }

    @GetMapping("/list/{num}")
    public ResponseEntity<BoardResponseListDto> boardList(@PathVariable("num") int num)
    {
        return ResponseEntity.ok(boardService.boardList(num));
    }
}
