#include <iostream>
#include <jni.h>
#include "../include/plsqlhelper_JNI.h"
#include "../include/plsqlhelper_CPP.h"
#include "../include/clsCppToJni.h"

using namespace std;

myClass objBL;

clsCppToJni objConvert;


/*
 * Class:     com_ph_plsqlhelper_ClsJNIMethods
 * Method:    fnCalcAmt
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_com_pcs_plsqlcodesample_ClsJNIMethods_fnCalcAmt
  (JNIEnv *, jobject, jdouble, jdouble, jdouble){};

/*
 * Class:     com_ph_plsqlhelper_ClsJNIMethods
 * Method:    fnCalcWightInGrams
 * Signature: (DDD)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_com_pcs_plsqlcodesample_ClsJNIMethods_fnCalcWightInGrams
  (JNIEnv *, jobject, jdouble, jdouble, jdouble){};

/*
 * Class:     com_ph_plsqlhelper_ClsJNIMethods
 * Method:    fnConvertGramstoKiloGrams
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_com_pcs_plsqlcodesample_ClsJNIMethods_fnConvertGramstoKiloGrams
  (JNIEnv *, jobject, jdouble){
return 1.2;
};

JNIEXPORT jstring JNICALL Java_com_pcs_plsqlcodesample_ClsJNIMethods_fnFormatValue
(JNIEnv *env, jobject obj1, jobject asset_mgr, jstring strDBPath){
const char *strDBPathChar = env->GetStringUTFChars(strDBPath, 0);
std::string str = objBL.fnFormatValue(env, asset_mgr, strDBPathChar);
env->ReleaseStringUTFChars(strDBPath, strDBPathChar);
return env->NewStringUTF(str.c_str());
}

