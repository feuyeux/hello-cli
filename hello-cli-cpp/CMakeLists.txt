# 设置CMake所需的最低版本
cmake_minimum_required(VERSION 3.11)

# 声明项目的名称(recipe-01)和支持的编程语言(CXX代表C++)
project(hello-cli VERSION 0.0.1 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_EXTENSIONS OFF)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# 打印当前编译器标志。CMake将对所有C++目标使用这些:
message("C++ compiler flags: ${CMAKE_CXX_FLAGS}")
message(STATUS "Is the C++ compiler loaded? ${CMAKE_CXX_COMPILER_LOADED}")
if(CMAKE_CXX_COMPILER_LOADED)
    message(STATUS "The C++ compiler ID is: ${CMAKE_CXX_COMPILER_ID}")
    message(STATUS "Is the C++ from GNU? ${CMAKE_COMPILER_IS_GNUCXX}")
    message(STATUS "The C++ compiler version is: ${CMAKE_CXX_COMPILER_VERSION}")
endif()

# 检测操作系统信息:
if(CMAKE_SYSTEM_NAME STREQUAL "Linux")
    message(STATUS "Configuring on/for Linux")
elseif(CMAKE_SYSTEM_NAME STREQUAL "Darwin")
    message(STATUS "Configuring on/for macOS")
elseif(CMAKE_SYSTEM_NAME STREQUAL "Windows")
    message(STATUS "Configuring on/for Windows")
elseif(CMAKE_SYSTEM_NAME STREQUAL "AIX")
    message(STATUS "Configuring on/for IBM AIX")
else()
    message(STATUS "Configuring on/for ${CMAKE_SYSTEM_NAME}")
endif()

# 创建目标——静态库。库的名称和源码文件名相同，第一个参数是目标名，生成库根据第二个参数(STATIC或SHARED)和操作系统确定
# add_library(message STATIC Message.hpp Message.cpp)

# 将目标库链接到可执行目标
# 编译成功后，构建目录包含libmessage.a一个静态库(在GNU/Linux上)和hello可执行文件。
# target_link_libraries(hello message)

add_library(CLI11::CLI11 IMPORTED INTERFACE)
target_include_directories(CLI11::CLI11 INTERFACE "${CMAKE_CURRENT_SOURCE_DIR}")
target_compile_features(CLI11::CLI11 INTERFACE cxx_std_11)

# 指示CMake创建一个新目标：可执行文件hello。这个可执行文件是通过编译和链接源文件hello.cpp生成的。
# CMake将为编译器使用默认设置，并自动选择生成工具
add_executable(hello src/hello.cpp)

target_compile_definitions(hello PUBLIC "COMPILER_NAME=\"${CMAKE_CXX_COMPILER_ID}\"")
if(CMAKE_SIZEOF_VOID_P EQUAL 8)
  target_compile_definitions(hello PUBLIC "IS_64_BIT_ARCH")
  message(STATUS "Target is 64 bits")
else()
  target_compile_definitions(hello PUBLIC "IS_32_BIT_ARCH")
  message(STATUS "Target is 32 bits")
endif()
if(CMAKE_HOST_SYSTEM_PROCESSOR MATCHES "i386")
    message(STATUS "i386 architecture detected")
elseif(CMAKE_HOST_SYSTEM_PROCESSOR MATCHES "i686")
    message(STATUS "i686 architecture detected")
elseif(CMAKE_HOST_SYSTEM_PROCESSOR MATCHES "x86_64")
    message(STATUS "x86_64 architecture detected")
else()
    message(STATUS "host processor architecture is unknown")
endif()
target_compile_definitions(hello  PUBLIC "ARCHITECTURE=${CMAKE_HOST_SYSTEM_PROCESSOR}"  )

configure_file(  version.h.in  generated/version.h  @ONLY  )
target_include_directories(hello  PRIVATE      ${CMAKE_CURRENT_BINARY_DIR}/generated  )
