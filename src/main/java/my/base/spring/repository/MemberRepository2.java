package my.base.spring.repository;

import my.base.spring.model.Member;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository2
{
	private static final String MAPPER_NAME_SPACE = "mapper.memberMapper.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Member> getMemberBoard() {
			return sqlSessionTemplate.selectList(MAPPER_NAME_SPACE + "selectMemberBoard");
	}
}
