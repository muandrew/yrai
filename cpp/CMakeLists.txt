cmake_minimum_required(VERSION 3.1)
project(native)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Use our modified FindSDL2* modules
set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${native_SOURCE_DIR}/cmake")
# Set an output directory for our binaries
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/lib")
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/lib")
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_BINARY_DIR}/bin")

# Look up SDL2 and add the include directory to our include path
find_package(SDL2 REQUIRED)
include_directories(${SDL2_INCLUDE_DIR})

# https://cmake.org/cmake/help/latest/module/FindJNI.html
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})

# Look in the Lesson0 subdirectory to find its CMakeLists.txt so we can build the executable
add_subdirectory(native)
