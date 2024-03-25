package com.ceos19.everytime.repository;

import com.ceos19.everytime.domain.Member;
import com.ceos19.everytime.domain.Post;
import com.ceos19.everytime.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostIdAndUser(Post postId, Member user);
}
