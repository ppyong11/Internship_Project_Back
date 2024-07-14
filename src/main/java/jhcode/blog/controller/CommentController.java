package jhcode.blog.controller;

import jhcode.blog.repository.BoardRepository;
import jhcode.blog.repository.CommentRepository;
import jhcode.blog.service.CommentService;
import jhcode.blog.dto.request.comment.CommentDto;
import jhcode.blog.dto.response.comment.ResCommentDto;
import jhcode.blog.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/{boardId}/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public ResponseEntity<Page<ResCommentDto>> commentList(
            @PathVariable Long boardId,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<ResCommentDto> commentList = commentService.getAllComments(pageable, boardId);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    @PostMapping("/write")
    public ResponseEntity<ResCommentDto> write(
            @AuthenticationPrincipal Member member,
            @PathVariable Long boardId,
            @RequestBody CommentDto commentDto) {
        ResCommentDto saveCommentDTO = commentService.write(boardId, member, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCommentDTO);
    }

    @PatchMapping("/update/{commentId}")
    public ResponseEntity<ResCommentDto> update(
            @PathVariable Long commentId,
            @RequestBody CommentDto commentDto,
            @AuthenticationPrincipal UserDetails currentUser) {
        String currentUserId = currentUser.getUsername();
        ResCommentDto updateCommentDTO = commentService.update(commentId, commentDto, currentUserId);
        return ResponseEntity.status(HttpStatus.OK).body(updateCommentDTO);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Long> delete(@PathVariable Long commentId) {

        commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
