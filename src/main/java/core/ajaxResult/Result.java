package core.ajaxResult;

public class Result<T> {
	
	public T data;
	public boolean success;
	public ErrorCode code;
	
	public Result(){
		
	}

	public Result(T data) {
		this.data=data;
		this.success=true;
	}
	
	public Result(ErrorCode code){
		this.code=code;
		this.success=false;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
		this.success=true;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
		this.success=false;
	}
	
	
	
	
	

}
