package core.ajaxResult;

/**
 * 
 * create by Liujishuai on 2015年9月21日
 * 
 */
public class AjaxResult {
	/**
	 * 请求结果是否成功(1:成功;负数表示失败)
	 */
	private String Code;

	/**
	 * 请求返回信息
	 */
	private String Message = ActionConstants.DEFAULT_SUCCESS_RETURNMSG;

	/**
	 * 请求结果
	 */
	private Object Data = null;

	/**
	 * Instantiates a new Ajax result.
	 */
	private AjaxResult() {
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Object getData() {
		return Data;
	}

	public void setData(Object data) {
		Data = data;
	}

	/**
	 * 获取正确结果模板
	 *
	 * @param message
	 *            请求返回信息
	 * @param obj
	 *            请求结果
	 * @return AjaxResult
	 */
	public static AjaxResult getOK(String message, Object obj) {
		AjaxResult result = new AjaxResult();
		result.setMessage(message);
		result.setData(obj);
		result.setCode("1");
		return result;
	}

	/**
	 * 获取正确结果模板
	 *
	 * @param obj
	 *            请求结果
	 * @return AjaxResult
	 */
	public static AjaxResult getOK(Object obj) {
		AjaxResult result = new AjaxResult();
		result.setMessage(ActionConstants.DEFAULT_SUCCESS_RETURNMSG);
		result.setData(obj);
		result.setCode("1");
		return result;
	}

	/**
	 * 获取正确结果模板
	 *
	 * @return AjaxResult
	 */
	public static AjaxResult getOK() {
		return getOK(ActionConstants.DEFAULT_SUCCESS_RETURNMSG, null);
	}

	/**
	 * 获取错误结果模板
	 *
	 * @param message
	 *            请求返回信息
	 * @param obj
	 *            请求结果
	 * @return AjaxResult
	 */
	public static AjaxResult getError(ErrorCode errorCode) {
		AjaxResult result = new AjaxResult();
		result.setCode(errorCode.code);
		result.setMessage(errorCode.msg);
		return result;
	}

	@Override
	public String toString() {
		return "AjaxResult{" + "Code='" + Code + '\'' + ", Message='" + Message + '\'' + ", Data=" + Data + '}';
	}
}
