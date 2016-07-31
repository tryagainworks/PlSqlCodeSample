#include <iostream>
#include <jni.h>
#include <string>
#include <vector>

#include "sqlite3.h"
#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>
#include <android/log.h>

using namespace std;

class Integer {
};

class String {
};

template<typename _Tp>
class List {
};

template<typename _Tp>
class ArrayList {
};

template<typename _Tp1, typename _Tp2>
class HashMap {
};

class myClass {

private:
	bool fnCreteDBFolder(const string& strDBFile,
			const string& strDBFileFullPath);

	bool fnCopySQLiteDBFile(const void* buffer, long lngLength,
			string strFilePath);

	sqlite3* fnOpenSQLiteDatabase(string strDBFilePath);

	bool fnCloseSQLiteDatabase(sqlite3 *db);

	bool fnTestFetchSQLiteDatabase(sqlite3 *db);

	string strDBResult;

public:

	std::vector<double> fnCalcAmt(JNIEnv *env, double dblCurrentWeight,
			double dblCurrentAmt, double dblChange);

	std::vector<double> fnCalcWightInGrams(JNIEnv *env, double dblCurrentWeight,
			double dblCurrentAmt, double dblChange);

	double fnConvertGramstoKiloGrams(JNIEnv *env, double dblWeightInGrams);

	string fnFormatValue(JNIEnv *env, jobject asset_mgr, const char *strDBPath);

};

