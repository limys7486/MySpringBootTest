package info.thecodinglive.member.repository;

import info.thecodinglive.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>
{
	//List<Member> findByRealname(String realname);
	
	Member findByEmail(String email);
	Member findByEmpNumber(String empNumber);
	Member findByUsername(String username);
	
	List<Member> findAll();
	
	@Query(value="select username, real_name as realName, email, address, mobile" +
			", role_name as roleName, update_date as updateDate,  enabled from tbl_member", nativeQuery=true)
	List<Object[]> findMemberBoard();
	
//	@Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname")
//	User findByLastnameOrFirstname(@Param("lastname") String lastname, @Param("firstname") String firstname);
}
