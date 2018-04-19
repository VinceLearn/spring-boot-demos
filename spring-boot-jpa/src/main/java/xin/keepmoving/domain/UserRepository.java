package xin.keepmoving.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018-04-18 10:52
 */
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameOrEmail(String username, String email);

    List<User> findByAgeBetween(int age1, int age2);

    List<User> findByIsAdminFalse();

    /**
     * 分页查询
     * @param username
     * @param pageable
     * @return
     */
    List<User> findByUsernameLike(String username, Pageable pageable);

    /**
     * 查询一个元素
     *
     * @return
     */
    User findTopByOrderByCreateDateTimeDesc();

    /**
     * 查询前10个元素
     * @param email
     * @param sort
     * @return
     */
    List<User> findFirst10ByEmailLike(String email, Sort sort);

    /**
     * 自定义SQL
     * @param email
     * @param id
     * @return
     */
    @Modifying
    @Transactional
    @Query("update t_user u set u.email= ?1 where u.id = ?2")
    int modifyById(String email, Long id);

    @Query("select u from t_user u where u.email = ?1")
    User findByEmail(String email);
}
