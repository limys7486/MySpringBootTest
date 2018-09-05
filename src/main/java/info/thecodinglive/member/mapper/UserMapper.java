package info.thecodinglive.member.mapper;

import info.thecodinglive.member.model.MyUserDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper
{
	public MyUserDetails readUser(String username);
	
	public List<String> readAuthority(String username);
	
}
