package com.example.mangxahoi.repository;

import com.example.mangxahoi.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFriendRepo extends JpaRepository<Friend, Long> {
    // danh sách nguời quen của bạn bè chưa kb
    @Query(nativeQuery = true, value = "select * from friend where (friend.user1_id =:User1 or friend.user2_id =:User1)  and friend.status in (0,1)")
    public List<Friend> listNotAddFriend(@Param(value = "User1") Long id1);

    // tìm danh sách bạn bè đã kết bạn
    @Query(nativeQuery = true,value = "select * from friend where (friend.user1_id =:idUser or friend.user2_id =:idUser) and (friend.user1_id =:idFriend or friend.user2_id =:idFriend) and status = 1")
    Friend findAddedFriendById (@Param(value = "idUser")Long idUser,@Param(value = "idFriend")Long idFriend);

    // tìm friend bạn bè chờ kết bạn
    @Query(nativeQuery = true,value = "select * from friend where (friend.user1_id =:idUser or friend.user2_id =:idUser) and (friend.user1_id =:idFriend or friend.user2_id =:idFriend) and status = 0")
    Friend findWaitAddFriendById (@Param(value = "idUser")Long idUser,@Param(value = "idFriend")Long idFriend);
}
