package com.coderszone.blog.model;

public class CommentParam extends Comment{
  private int blogId;

public int getBlogId() {
	return blogId;
}

public void setBlogId(int blogId) {
	this.blogId = blogId;
}
}
