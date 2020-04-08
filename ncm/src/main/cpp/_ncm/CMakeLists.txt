# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
include_directories(
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/toolkit
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/asf
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/mpeg
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/ogg
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/ogg/flac
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/flac
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/mpc
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/mp4
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/ogg/vorbis
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/ogg/speex
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/ogg/opus
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/mpeg/id3v2
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/mpeg/id3v2/frames
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/mpeg/id3v1
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/ape
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/wavpack
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/trueaudio
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/riff
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/riff/aiff
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/riff/wav
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/mod
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/s3m
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/it
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/xm
        ${CMAKE_CURRENT_SOURCE_DIR}/../libtag/
)

add_library( # Sets the name of the library.
             ncm

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
        cJSON.cpp
        io_bunnyblue_droidncm_dump_NcmDumper.cpp
        ncmcrypt.cpp
        aes.cpp
         qcm.cpp )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       ncm

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib}
                       libtag)