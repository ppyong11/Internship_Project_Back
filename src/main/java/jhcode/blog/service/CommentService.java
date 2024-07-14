package jhcode.blog.service;

import jakarta.transaction.Transactional;
import jhcode.blog.entity.Board;
import jhcode.blog.exception.ForbiddenException;
import jhcode.blog.exception.UnauthorizedException;
import jhcode.blog.repository.BoardRepository;
import jhcode.blog.entity.Comment;
import jhcode.blog.repository.CommentRepository;
import jhcode.blog.dto.request.comment.CommentDto;
import jhcode.blog.dto.response.comment.ResCommentDto;
import jhcode.blog.common.exception.ResourceNotFoundException;
import jhcode.blog.entity.Member;
import jhcode.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Page<ResCommentDto> getAllComments(Pageable pageable, Long boardId) {
        Page<Comment> comments = commentRepository.findAllWithMemberAndBoard(pageable, boardId);
        List<ResCommentDto> commentList = comments.getContent().stream()
                .map(ResCommentDto::fromEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(commentList, pageable, comments.getTotalElements());
    }

    public ResCommentDto write(Long boardId, Member member, CommentDto writeDto) {
        // board 정보 검색
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ResourceNotFoundException("Board", "Board id", String.valueOf(boardId))
        );
        // member(댓글 작성자) 정보 검색
        Member commentWriter = memberRepository.findById(member.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member id", String.valueOf(member.getId()))
        );
        // Entity 변환, 연관관계 매핑
        Comment comment = CommentDto.ofEntity(writeDto);
        comment.setBoard(board);
        comment.setMember(commentWriter);

        Comment saveComment = commentRepository.save(comment);
        return ResCommentDto.fromEntity(saveComment);
    }

    public ResCommentDto update(Long commentId, CommentDto commentDto, String currentUserId) {
        Comment comment = commentRepository.findByIdWithMemberAndBoard(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "Comment Id", String.valueOf(commentId))
        );
        log.info("comment member: {}",comment.getMember().getEmail());
        // 작성자와 현재 사용자가 같은지 확인
        if (!comment.getMember().getEmail().equals(currentUserId)) {
            log.info("userID: {}", currentUserId);
            //댓글 작성자와 요청자가 다를 경우 사용자의 권한이 없다고 표시
            throw new ForbiddenException("댓글 작성자와 삭제 요청자가 일치하지 않습니다.");
        }
        comment.update(commentDto.getContent());
        return ResCommentDto.fromEntity(comment);
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
