cmake_minimum_required(VERSION 3.9)
project(c2 C)

set(CMAKE_C_STANDARD 99)

add_executable(c2 main.c list.h list.c s_gets.c test.c tar.c)