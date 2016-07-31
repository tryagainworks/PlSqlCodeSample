package com.pcs.plsqlcodesample;

import android.content.res.AssetManager;

public class ClsJNIMethods {
	static {
		System.loadLibrary("cppmethods");
	}
	
    public native double[] fnCalcAmt(double dblCurrentWeight, double dblCurrentAmt, double dblChange) throws ArithmeticException;

    public native double[] fnCalcWightInGrams(double dblCurrentWeight, double dblCurrentAmt, double dblChange) throws ArithmeticException;

    public native double fnConvertGramstoKiloGrams(double dblWeightInGrams) throws ArithmeticException;

    public native String fnFormatValue(AssetManager assetManager, String strDBPath);
}

