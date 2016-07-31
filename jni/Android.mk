LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
#Used the below flag for (error: undefined reference to '__atomic_fetch_add_4')
LOCAL_LDLIBS += -latomic
#Here we give our module name and source file(s)
LOCAL_MODULE    := cppmethods
LOCAL_C_INCLUDES := $(LOCAL_PATH)/include
#MY_SCR_PATH := $(LOCAL_PATH)/src
LOCAL_LDLIBS += -llog
#LOCAL_STATIC_LIBRARIES += android_native_app_glue
#When you include any one of the below headears then use -landroid
#<native_activity.h>, <looper.h>,#<input.h>, <keycodes.h>, <sensor.h>
#<rect.h>, <window.h>, <native_window.h>, <native_window_jni.h>
#<configuration.h>, <asset_manager.h>, <storage_manager.h>, <obb.h>
LOCAL_LDLIBS += -landroid
#LOCAL_SHARED_LIBRARIES += libandroid
#FILE_LIST := $(wildcard $(LOCAL_PATH)/*.cpp)
#FILE_LIST += $(wildcard $(LOCAL_PATH)/**/*.cpp)
#FILE_LIST += $(wildcard $(LOCAL_PATH)/**/**/*.cpp)
#LOCAL_SRC_FILES := $(FILE_LIST:$(LOCAL_PATH)/%=%)
LOCAL_SRC_FILES := src/plsqlhelper_CPP.cpp src/plsqlhelper_JNI.cpp src/clsCppToJni.cpp src/sqlite3.c
LOCAL_CPP_EXTENSION := .cpp #.cxx .cpp .cc .hpp

include $(BUILD_SHARED_LIBRARY)

#LOCAL_PATH := $(call my-dir) #won't get cleared by clear_vars
#include $(CLEAR_VARS) #clearing the variables defined for chaiscript build
#LOCAL_MODULE := test_chaiscript
#LOCAL_SRC_FILES := libchaiscript.so #can be 'chaiscript' alone
#LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)/ChaiScript/include
#LOCAL_C_INCLUDES := $(LOCAL_PATH)/Caller/header
#MY_SCR_PATH := Caller/src
#LOCAL_SRC_FILES := $(MY_SCR_PATH)/NativeFunction.cpp $(MY_SCR_PATH)/clsArithmeticParser.cpp
#include $(BUILD_SHARED_LIBRARY)