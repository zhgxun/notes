cmake_minimum_required(VERSION 3.14)
project(socket C)

set(CMAKE_C_STANDARD 99)

add_library(socket library.c library.h)