project(native)

add_executable(native_cli
    src/main.cpp
    src/window.cpp
)
target_link_libraries(native_cli ${SDL2_LIBRARY})
install(TARGETS native_cli
    RUNTIME DESTINATION bin
    LIBRARY DESTINATION lib
    ARCHIVE DESTINATION lib
)

add_library(native SHARED
    src/native.cpp
    src/window.cpp
)
include_directories($JNI_INCLUDE_DIRS)
target_link_libraries(native ${SDL2_LIBRARY})
install(TARGETS native
    RUNTIME DESTINATION bin
    LIBRARY DESTINATION lib
    ARCHIVE DESTINATION lib
)
