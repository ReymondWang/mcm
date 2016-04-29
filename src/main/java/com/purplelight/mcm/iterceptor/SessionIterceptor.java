package com.purplelight.mcm.iterceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.purplelight.mcm.action.LoginAction;
import com.purplelight.mcm.util.McmConstant;

public class SessionIterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -5585293553860206842L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		Action action = (Action)invocation.getAction();
		if (action instanceof LoginAction){
			return invocation.invoke();
		}
		if (session.get(McmConstant.USER_SESSION) == null){
			return Action.LOGIN;
		} else {
			return invocation.invoke();
		}
	}

}
