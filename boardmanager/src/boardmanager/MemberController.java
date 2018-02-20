package project1;

public class MemberController {

	// 로그인
	public MemberVO login(String id, String pw) {
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.login(id, pw);
		
		return vo;
	}
}
