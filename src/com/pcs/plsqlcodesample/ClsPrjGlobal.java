package com.pcs.plsqlcodesample;

public class ClsPrjGlobal {
	static ClsJNIMethods cls = null;

	public static ClsJNIMethods getJNIMethod() {
		if (cls == null)
			cls = new ClsJNIMethods();
		return cls;
	}
}
