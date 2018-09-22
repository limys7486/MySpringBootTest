package my.base.spring.mapper;

import my.base.spring.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MemberBoardMapper
{
	@Select("<script>select username, real_name, email, address, mobile, role_name, update_date,  enabled  from tbl_member</script>")
	List<Member> getMemberBoard() throws Exception;
	
}
