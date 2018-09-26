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

	// SQL로 테이블 일부 필드만 쿼리해서 결과를 resultType=hashmap을 만들고 => 이것을 Member 리스트로 변환이 가능하군
	public List<Member> getMemberBoard() {
			return sqlSessionTemplate.selectList(MAPPER_NAME_SPACE + "selectMemberBoard");
	}


}
