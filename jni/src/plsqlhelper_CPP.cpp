#include "../include/plsqlhelper_CPP.h"
#include <fstream>
#include <stddef.h>
#include <stdio.h>
#include <sys/stat.h>
#include <stdlib.h>

std::vector<double> myClass::fnCalcAmt(JNIEnv *env, double dblCurrentWeight,
		double dblCurrentAmt, double dblChange) {
}

std::vector<double> myClass::fnCalcWightInGrams(JNIEnv *env,
		double dblCurrentWeight, double dblCurrentAmt, double dblChange) {
}

double myClass::fnConvertGramstoKiloGrams(JNIEnv *env,
		double dblWeightInGrams) {
}

#define DB_FILE ""

bool myClass::fnCreteDBFolder(const string& strDBFile,
		const string& strDBFileFullPath) {
	string strDBFolder = strDBFileFullPath;
	strDBFolder = strDBFolder.substr(0,
			strDBFolder.length() - strDBFile.length() - 1);

	struct stat sb;

	if (stat(strDBFolder.c_str(), &sb) == 0 && S_ISDIR(sb.st_mode)) {
		//AAsset_close(asset);
		//return "folder exist";
	} else {
		char chrPath[250];
		sprintf(chrPath, "mkdir %s", strDBFolder.c_str());
		system(chrPath);
		//AAsset_close(asset);
		//return "folder created";
	}
	return true;
}

bool myClass::fnCopySQLiteDBFile(const void* buffer, long lngLength,
		string strFilePath) {
	ofstream myfile;
	myfile = ofstream(strFilePath, ios::out | ios::binary);
	if (myfile.fail()) {
		return false;
	}
	myfile.write((char*) buffer, lngLength);
	myfile.flush();
	myfile.close();
	return true;
}

sqlite3* myClass::fnOpenSQLiteDatabase(string strDBFilePath) {
	sqlite3 *db;
	int rc;
	rc = sqlite3_open(strDBFilePath.c_str(), &db);

	string str;
	if (rc) {
		return NULL;
	} else
		return db;
}

bool myClass::fnCloseSQLiteDatabase(sqlite3 *db) {
	sqlite3_close(db);
	return true;
}

bool myClass::fnTestFetchSQLiteDatabase(sqlite3 *db) {
	sqlite3_stmt *stmt;
	int rc = sqlite3_prepare_v2(db,
			"select * from tblscript where 'aa'= ? and 1=?", -1, &stmt, NULL);
	if (rc != SQLITE_OK) {
		//throw string(sqlite3_errmsg(db));
		return false;
	}

	//1st parameter
	//-1 = full length
	//0 is null value
	rc = sqlite3_bind_text(stmt, 1, "aa", -1, 0); // Using parameters ("?") is not
	//2nd parameter
	rc = sqlite3_bind_int(stmt, 2, 1); // Using parameters ("?") is not
	if (rc != SQLITE_OK) { // really necessary, but recommended
		string errmsg(sqlite3_errmsg(db)); // (especially for strings) to avoid
		sqlite3_finalize(stmt); // formatting problems and SQL
		//throw errmsg;                      // injection attacks.
		return false;
	}

	rc = sqlite3_step(stmt);
	if (rc != SQLITE_ROW && rc != SQLITE_DONE) {
		string errmsg(sqlite3_errmsg(db));
		sqlite3_finalize(stmt);
		//throw errmsg;
		return false;
	}

	strDBResult = "";
	do {
		strDBResult += (char*) sqlite3_column_text(stmt, 0); //+", ";
		strDBResult += ", ";
		strDBResult += (char*) sqlite3_column_text(stmt, 1); //+"\n";
		strDBResult += "\n";

		rc = sqlite3_step(stmt);
	} while (rc != SQLITE_DONE && rc != SQLITE_ERROR && rc == SQLITE_ROW);

	sqlite3_finalize(stmt);
	return true;
}

std::string myClass::fnFormatValue(JNIEnv *env, jobject asset_mgr,
		const char *strDBPath) {
	string strResult;
	string strFileName = "sqlhelper";
	if (!fnCreteDBFolder(strFileName, strDBPath)) {
		return "Create db folder Failed";
	}
	strResult = "DB Folder Created\n";

	AAssetManager* asset_manager = AAssetManager_fromJava(env, asset_mgr);
	AAsset* asset = AAssetManager_open(asset_manager, strFileName.c_str(),
			AASSET_MODE_BUFFER);
	long len = AAsset_getLength(asset);
	const void* buffer = AAsset_getBuffer(asset);

	bool blnValue = fnCopySQLiteDBFile(buffer, len, strDBPath);
	AAsset_close(asset);

	if (!blnValue) {
		return "Copy SQLite Database failed";
	}
	strResult += "Database File Copied\n";

	sqlite3 *db;
	if ((db = fnOpenSQLiteDatabase(strDBPath)) == NULL) {
		return "Failed to open Database";
	}
	strResult += "Database opened\n";

	if (!fnTestFetchSQLiteDatabase(db)) {
		return "Database reading failed";
	}
	strResult += "Database reading done\n";

	if (!fnCloseSQLiteDatabase(db)) {
		return "Failed to close Database";
	}
	strResult += "Database closed\n";
	strResult += strDBResult;
	return strResult;
}

