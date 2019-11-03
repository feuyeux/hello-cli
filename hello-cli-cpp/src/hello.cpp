
#include <cstdlib>
#include <iostream>
#include <string>
// https://github.com/CLIUtils/CLI11/releases
#include "CLI11.hpp"
#include "version.h"

#define STRINGIFY(x) #x
#define TOSTRING(x) STRINGIFY(x)

std::string say_os() {
#ifdef IS_WINDOWS
  return std::string("Hello from Windows!");
#elif IS_LINUX
  return std::string("Hello from Linux!");
#elif IS_MACOS
  return std::string("Hello from macOS!");
#else
  return std::string("Hello from an unknown system!");
#endif
}

std::string say_compile() {
#ifdef IS_INTEL_CXX_COMPILER
  // only compiled when Intel compiler is selected
  // such compiler will not compile the other branches
  return std::string("Hello Intel compiler!");
#elif IS_GNU_CXX_COMPILER
  // only compiled when GNU compiler is selected
  // such compiler will not compile the other branches
  return std::string("Hello GNU compiler!");
#elif IS_PGI_CXX_COMPILER
  // etc.
  return std::string("Hello PGI compiler!");
#elif IS_XL_CXX_COMPILER
  return std::string("Hello XL compiler!");
#else
  return std::string("Hello unknown compiler - have we met before?");
#endif
}

std::string say_cpu() {
  std::string arch_info(TOSTRING(ARCHITECTURE));
  arch_info += std::string(" architecture. ");
#ifdef IS_32_BIT_ARCH
  return arch_info + std::string("Compiled on a 32 bit host processor.");
#elif IS_64_BIT_ARCH
  return arch_info + std::string("Compiled on a 64 bit host processor.");
#else
  return arch_info + std::string("Neither 32 nor 64 bit, puzzling ...");
#endif
}

int main(int argc, char const *argv[])
{
  printf("dddd");
}

void x()
{
  std::cout << say_os() << std::endl;
  std::cout << say_compile() << std::endl;
  std::cout << say_cpu() << std::endl;
  std::cout << "compiler name is " COMPILER_NAME << std::endl;

  printf("This is output from code %s\n", PROJECT_VERSION);
  printf("Major version number: %i\n", PROJECT_VERSION_MAJOR);
  printf("Minor version number: %i\n", PROJECT_VERSION_MINOR);

  using std::cout;
  using std::endl;
  CLI::App app{"Flag example program"};

  /// [define]
  bool flag_bool;
  app.add_flag("--bool,-b", flag_bool, "This is a bool flag");

  int flag_int;
  app.add_flag("-i,--int", flag_int, "This is an int flag");

  CLI::Option* flag_plain = app.add_flag("--plain,-p", "This is a plain flag");
  /// [define]

  /// [usage]
  cout << "The flags program" << endl;
  if (flag_bool) cout << "Bool flag passed" << endl;
  if (flag_int > 0) cout << "Flag int: " << flag_int << endl;
  if (*flag_plain) cout << "Flag plain: " << flag_plain->count() << endl;
  /// [usage]
}
