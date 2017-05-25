package core.ajaxResult;

public enum ErrorCode {

	DuplicateNick("-100", "该用户名已经存在"), //
	WrongPassWord("-101", "密码错误或用户名不存在"),//
	BasicInfoWrong("-102", "用户基础信息读取失败"),//
	EduInfoWrong("-103", "教育信息读取失败"),//
	ProjectInfoWrong("-104", "项目信息读取失败"),//
	ExpectionWrong("-105", "工作期望读取失败"),//
	WorkExpWrong("-106", "工作经历读取失败"),//
	SaveUserInfoWrong("-110","保存用户信息失败");

	public String code;
	public String msg;

	private ErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return msg;
	}

}
