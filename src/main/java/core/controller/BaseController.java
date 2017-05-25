package core.controller;

import javax.servlet.http.HttpServletRequest;

import org.lionsoul.jcseg.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import sspku.exception.MyException;

/**
 * 
 * @Description:
 */
public class BaseController {
	
	private static Logger logger=LoggerFactory.getLogger(BaseController.class);
	 /**
     * 获取用户ID，用户ID可能为NULL,需自行判断
     * @param request
     * @return
     */
    protected Integer getUserId(HttpServletRequest request){

        String sId = request.getHeader("userId");

        if(Strings.isNullOrEmpty(sId)){
            try {
                Integer userId = Integer.valueOf(sId);
                return userId;
            } catch (NumberFormatException e) {
                logger.warn("请求头userId参数格式错误:{}",sId);
            }

        }

        return null;
    }

	 /**
     * 获取用户ID,当userId为空的时候抛出异常
     * @param request
     * @return
     * @throws BusinessException 用户ID不能为空
     */
    protected Integer getNotNullUserId(HttpServletRequest request) throws MyException{
        Integer userId = getUserId(request);
        if(userId == null){
            throw new MyException("用户ID不能为空");
        }
        return userId;
    }
}

