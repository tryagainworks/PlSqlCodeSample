#APP_STL := stlport_static

#Build the library by enabling C++11  
APP_STL:=c++_static

#APP_LDLIBS    += -llog

#APP_LDLIBS += -landroid

#Used the below flag for 
#(Error: '...' handler must be the last handler for its try block [-fpermissive]
#           } catch (const std::out_of_range &) {)
APP_CPPFLAGS += -fexceptions

#Used the below flag for (Error: 'dynamic_cast' not permitted with -fno-rtti)
APP_CPPFLAGS += -frtti

#Architecture to support armeabi-v7a & x86
APP_ABI := x86 #armeabi armeabi-v7a #mips arm64-v8a x86_64 mips64

#Android platform
APP_PLATFORM := android-15