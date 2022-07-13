package com.slcp.devops.service.impl;

import com.slcp.devops.config.DoQueryCache;
import com.slcp.devops.mapper.CommentMapper;
import com.slcp.devops.dto.Comment;
import com.slcp.devops.mapper.IBlogMapper;
import com.slcp.devops.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Slcp
 * @date: 2020/9/24 10:57
 * @code: 一生的挚爱
 * @description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private IBlogMapper blogMapper;


    /**
     * 存放迭代找出的所有子代的集合
     */
    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    @DoQueryCache(expireTime = 10)
    public List<Comment> listCommentByBlogId(Long blogId) {
        //查询出父节点
        List<Comment> comments = commentMapper.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        for(Comment comment : comments){
            String id = comment.getCommentId();
            String parentNickname1 = comment.getNickname();
            List<Comment> childComments = commentMapper.findByBlogIdParentIdNotNull(blogId,id);
            //查询出子评论
            combineChildren(blogId, childComments, parentNickname1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }




    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
        //判断是否有一级子评论
        if(childComments.size() > 0){
            //循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                String childId = childComment.getCommentId();
                //查询出子二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }

    private void recursively(Long blogId, String childId, String parentNickname1) {
//        根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.findByBlogIdAndReplayId(blogId, childId);

        if (replayComments.size() > 0) {
            for (Comment replayComment : replayComments) {
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                String replayId = replayComment.getCommentId();
                tempReplys.add(replayComment);
                recursively(blogId, replayId, parentNickname);
            }
        }//    新增评论

    }

    @Override
    public void saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        commentMapper.saveComment(comment);
//        文章评论计数
        blogMapper.getCommentCountById(comment.getBlogId());
    }

    @Override
    public void deleteComment(Comment comment, Long id) {
        commentMapper.deleteComment(id);
        blogMapper.getCommentCountById(comment.getBlogId());
    }

}
