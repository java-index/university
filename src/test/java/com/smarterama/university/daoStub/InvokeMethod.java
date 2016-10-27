package com.smarterama.university.daoStub;

import java.lang.reflect.InvocationHandler;

public interface InvokeMethod extends InvocationHandler{
	String getDaoMethod();
	void setDaoFailure(boolean value);
}
