package com.example.mangxahoi.repository;

import com.example.mangxahoi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);// tim kiem user co ton tai ko
    Boolean existsByUsername(String username);// kiem tra user co ton tai hay ko
    Boolean existsByEmail(String email);// kiem tra email co ton tai hay ko
    List<User> findAll();


    @Query(value = "select * from user where role_id = :id", nativeQuery = true)
    List<User> findUserByRole(@Param("id") Long id);
    // tim kiếm những tk gần giống tên nhập vào
    @Query(value = "select * from user where name = :name and like concat('%',:name,'%')", nativeQuery = true)
    List<User> findUserByUsername(@Param("name") String name);
    // danh sách bạn bè chung
    @Query(nativeQuery = true,value = "select users.* from users join (select bb1.id from (select friend.user2_id as id from friend where friend.user1_id =:idUser1 and friend.status = 1 union select friend.user1_id as id from friend where friend.user2_id =:idUser1 and friend.status = 1) as bb1 join (select friend.user2_id as id from friend where friend.user1_id =:idUser2 and friend.status = 1 union select friend.user1_id as id from friend where friend.user2_id =:idUser2 and friend.status = 1) as bb2 on bb1.id = bb2.id) as bbc on users.id = bbc.id")
    List<User> listMutualFriend(@Param(value = "idUser1") Long idUser1, @Param(value = "idUser2") Long idUser2);
    // danh sách bạn bè kb
    @Query(nativeQuery = true, value = "select * from users join (select friend.user2_id as id from friend where friend.user1_id =:User1 and friend.status = 1 union select friend.user1_id as id from friend where friend.user2_id =:User1 and friend.status = 1)as bb on users.id = bb.id")
    List<User> listAddedFriend(@Param(value = "User1") Long id1);

    // danh sách bạn bè chờ kb
    @Query(nativeQuery = true, value = "select * from users join (select friend.user2_id as id from friend where (friend.user1_id =:User1)  and friend.status = 0) as bb on users.id = bb.id")
    List<User> listWaitMakeFriend(@Param(value = "User1") Long id1);

    // danh sách like post
    @Query(nativeQuery = true,value = "select * from users join (select user_id as id_user from likes where post_id =:idPost) as l on users.id = l.id_user")
    List<User> listUserLikePost(@Param(value = "idPost") Long idPost);

    // danh sách like comment
    @Query(nativeQuery = true,value = "select * from users join (select user_id as id_user from likes where comment_id =:idComment) as l on users.id = l.id_user")
    List<User> listUserLikeComment(@Param(value = "idComment") Long idComment);
}
