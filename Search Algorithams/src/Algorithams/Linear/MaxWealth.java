from . import idnadata
import bisect
import unicodedata
import re
from typing import Union, Optional
from .intranges import intranges_contain

_virama_combining_class = 9
_alabel_prefix = b'xn--'
_unicode_dots_re = re.compile('[\u002e\u3002\uff0e\uff61]')

class IDNAError(UnicodeError):
    """ Base exception for all IDNA-encoding related problems """
    pass


class IDNABidiError(IDNAError):
    """ Exception when bidirectional requirements are not satisfied """
    pass


class InvalidCodepoint(IDNAError):
    """ Exception when a disallowed or unallocated codepoint is used """
    pass


class InvalidCodepointContext(IDNAError):
    """ Exception when the codepoint is not valid in the context it is used """
    pass


def _combining_class(cp: int) -> int:
    v = unicodedata.combining(chr(cp))
    if v == 0:
        if not unicodedata.name(chr(cp)):
            raise ValueError('Unknown character in unicodedata')
    return v

def _is_script(cp: str, script: str) -> bool:
    return intranges_contain(ord(cp), idnadata.scripts[script])

def _punycode(s: str) -> bytes:
    return s.encode('punycode')

def _unot(s: int) -> str:
    return 'U+{:04X}'.format(s)


def valid_label_length(label: Union[bytes, str]) -> bool:
    if len(label) > 63:
        return False
    return True


def valid_string_length(label: Union[bytes, str], trailing_dot: bool) -> bool:
    if len(label) > (254 if trailing_dot else 253):
        return False
    return True


def check_bidi(label: str, check_ltr: bool = False) -> bool:
    # Bidi rules should only be applied if string contains RTL characters
    bidi_label = False
    for (idx, cp) in enumerate(label, 1):
        direction = unicodedata.bidirectional(cp)
        if direction == '':
            # String likely comes from a newer version of Unicode
            raise IDNABidiError('Unknown directionality in label {} at position 