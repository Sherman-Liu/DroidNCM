#include <cstdint>
#include <string>

const int BUFFER_SIZE = 8192;

int qcm_encrypt(int offset, char *buf, int len);
char qcm_mapL(int v);
bool qcm_convert(const std::string &in, const std::string &out);
