package info.thecodinglive.member.repository;

import info.thecodinglive.member.model.FreeBoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FreeBoardRepository {
    private static final String MAPPER_NAME_SPACE="sample.sample.mapper.freeBoard.";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public void registBoard(FreeBoardVO freeBoardVO){
        sqlSessionTemplate.insert(MAPPER_NAME_SPACE+ "insertBoard", freeBoardVO);
    }
}
