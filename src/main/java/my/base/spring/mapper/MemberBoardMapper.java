package my.base.spring.mapper;

import my.base.spring.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper  // Annotation 사용 방식 : 별도의 mapper xml을 만들 필요는 없지만, dynamic query를 사용하지 못하는 등의 제약사항
@Component
public interface MemberBoardMapper
{
	@Select("<script>select username, real_name, email, address, mobile, role_name, update_date, enabled from tbl_member</script>")
	List<Member> getMemberBoard() throws Exception;

	@Select("select * from tbl_member where username = #{username}")
	Member getOneMember(@Param("username") String username);

	@Select("select * from tbl_member")
	List<Member> getAllMembers();

//	@Insert("insert into DEPT(DEPT_NO, DEPT_NAME, LOC) values (#{deptNo}, #{deptName}, #{loc})")
//	public void insertDepartment(DeptVO vo);
//
//	@Update("update DEPT set DEPT_NAME = #{deptName}, LOC = #{loc} WHERE DEPT_NO = #{deptNo}")
//	public int updateDepartment(DeptVO vo);
//
//	@Delete("delete from DEPT WHERE DEPT_NO = #{deptNo}")
//	public int deleteDepartment(BigDecimal deptNo);

	
}
