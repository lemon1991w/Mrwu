package com.comment.reply;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/5/27.
 */
public class CommentBean {
    private int id;					//评论记录ID
    private String commnetAccount;	//评论人账号
    private String commentNickname;	//评论人昵称
    private String commentTime;		//评论时间
    private String commentContent;	//评论内容
    private String paisenum;

    public String getPaisenum() {
        return paisenum;
    }

    public void setPaisenum(String paisenum) {
        this.paisenum = paisenum;
    }

    private List<ReplyBean> replyList = new ArrayList<ReplyBean>();
    //回复内容列表
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCommnetAccount() {
        return commnetAccount;
    }
    public void setCommnetAccount(String commnetAccount) {
        this.commnetAccount = commnetAccount;
    }
    public String getCommentNickname() {
        return commentNickname;
    }
    public void setCommentNickname(String commentNickname) {
        this.commentNickname = commentNickname;
    }
    public String getCommentTime() {
        return commentTime;
    }
    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
    public String getCommentContent() {
        return commentContent;
    }
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
    public List<ReplyBean> getReplyList() {
        return replyList;
    }
    public void setReplyList(List<ReplyBean> replyList) {
        this.replyList = replyList;
    }

}
